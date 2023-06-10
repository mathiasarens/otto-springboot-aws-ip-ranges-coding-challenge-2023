package de.otto.challenge.api;

import de.otto.challenge.domain.AwsIpRangeDto;
import de.otto.challenge.domain.AwsIpRangesDto;
import de.otto.challenge.service.AwsIpRangeService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class AwsIpRangeRestResource {

    private final AwsIpRangeService awsIpRangeService;

    public AwsIpRangeRestResource(@Autowired AwsIpRangeService awsIpRangeService) {
        this.awsIpRangeService = awsIpRangeService;
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String awsIpRange(@RequestParam(value = "region", required = false) @Pattern(regexp = "(EU|US|AP|CN|SA|AF|CA|ALL)?", message = "Valid regions are EU|US|AP|CN|SA|AF|CA|ALL") String region) {
        final List<AwsIpRangeDto> awsIpRangeDtos = awsIpRangeService.loadAwsIpRangeForRegion(region);
        return convertAwsIpRangeDtosToString(awsIpRangeDtos);
    }

    private String convertAwsIpRangeDtosToString(final List<AwsIpRangeDto> awsIpRangeDtos){
        final StringBuilder resultBuilder = new StringBuilder();
        for (final AwsIpRangeDto awsIpRangeDto : awsIpRangeDtos) {
            resultBuilder.append("Region: " + awsIpRangeDto.region() + "; ");
            resultBuilder.append("IpPrefix: " + awsIpRangeDto.ipPrefix() + ";");
            resultBuilder.append(System.getProperty("line.separator"));
        }
        return resultBuilder.toString();
    }
}
