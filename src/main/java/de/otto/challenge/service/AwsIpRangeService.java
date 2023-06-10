package de.otto.challenge.service;

import de.otto.challenge.domain.AwsIpRangeDto;
import de.otto.challenge.domain.AwsIpRangesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AwsIpRangeService {

    // attributes
    private final RestTemplate restTemplate;
    private final String awsIpRangeJsonUrl;

    // constructor
    public AwsIpRangeService(@Autowired final RestTemplate restTemplate, @Value("${awsiprange.remote.url}") final String awsIpRangeJsonUrl) {
        this.restTemplate = restTemplate;
        this.awsIpRangeJsonUrl = awsIpRangeJsonUrl;
    }

    public List<AwsIpRangeDto> loadAwsIpRangeForRegion(final String region) {
        final ResponseEntity<AwsIpRangesDto> awsIpRangeListResponseEntity = restTemplate.getForEntity(awsIpRangeJsonUrl, AwsIpRangesDto.class);
        if (awsIpRangeListResponseEntity.getStatusCode().is2xxSuccessful()) {
            return filterRegion(awsIpRangeListResponseEntity.getBody(), region);
        } else {
            throw new RuntimeException(String.format("Could not load AwsIpRange from %s: return code %s", awsIpRangeJsonUrl, awsIpRangeListResponseEntity.getStatusCode()));
        }
    }

    private List<AwsIpRangeDto> filterRegion(final AwsIpRangesDto awsIpRangesDto, final String region) {
        if (region != null && !region.equals("ALL")) {
            return awsIpRangesDto.prefixes().stream().filter(awsIpRangeDto -> awsIpRangeDto.region().startsWith(region.toLowerCase())).toList();
        }
        return awsIpRangesDto.prefixes();
    }

}
