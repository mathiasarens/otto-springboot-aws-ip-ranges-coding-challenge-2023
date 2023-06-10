package de.otto.challenge.service;

import de.otto.challenge.domain.AwsIpRangeDto;
import de.otto.challenge.domain.AwsIpRangesDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
class AwsIpRangeServiceTest {

    private AwsIpRangeService awsIpRangeService;

    @Mock
    private RestTemplate restTemplateMock;

    private String remoteUrlMock;

    @BeforeEach
    public void setUp() {
        remoteUrlMock = "remoteUrl";
        awsIpRangeService = new AwsIpRangeService(restTemplateMock, remoteUrlMock);
    }

    @Test
    public void shouldLoadAwsIpRangeForUsRegion() {
        // given
        final List<AwsIpRangeDto> awsIpRangeDtoList = new LinkedList<>();
        final AwsIpRangeDto awsIpRangeDtoUs = new AwsIpRangeDto("ipPrefix1Mock", "us-east-1", "service1Mock", "networkBorderGroup1Mock");
        awsIpRangeDtoList.add(awsIpRangeDtoUs);
        final AwsIpRangeDto awsIpRangeDtoCa = new AwsIpRangeDto("ipPrefix2Mock", "ca-east-1", "service2Mock", "networkBorderGroup2Mock");
        awsIpRangeDtoList.add(awsIpRangeDtoCa);
        final AwsIpRangesDto awsIpRangesDto = new AwsIpRangesDto("syncTokenMock", "creationDateMock", awsIpRangeDtoList);
        Mockito.when(restTemplateMock.getForEntity(remoteUrlMock, AwsIpRangesDto.class)).thenReturn(new ResponseEntity<>(awsIpRangesDto, HttpStatusCode.valueOf(200)));

        // when
        final List<AwsIpRangeDto> resultList = awsIpRangeService.loadAwsIpRangeForRegion("US");

        //then
        assertEquals(1, resultList.size());
        assertEquals("ipPrefix1Mock", resultList.get(0).ipPrefix());
        assertEquals("us-east-1", resultList.get(0).region());
        assertEquals("service1Mock", resultList.get(0).service());
        assertEquals("networkBorderGroup1Mock", resultList.get(0).networkBorderGroup());
    }

    @Test
    public void shouldLoadAwsIpRangeForAllRegion() {
        // given
        final List<AwsIpRangeDto> awsIpRangeDtoList = new LinkedList<>();
        final AwsIpRangeDto awsIpRangeDtoUs = new AwsIpRangeDto("ipPrefix1Mock", "us-east-1", "service1Mock", "networkBorderGroup1Mock");
        awsIpRangeDtoList.add(awsIpRangeDtoUs);
        final AwsIpRangeDto awsIpRangeDtoCa = new AwsIpRangeDto("ipPrefix2Mock", "ca-east-1", "service2Mock", "networkBorderGroup2Mock");
        awsIpRangeDtoList.add(awsIpRangeDtoCa);
        final AwsIpRangesDto awsIpRangesDto = new AwsIpRangesDto("syncTokenMock", "creationDateMock", awsIpRangeDtoList);
        Mockito.when(restTemplateMock.getForEntity(remoteUrlMock, AwsIpRangesDto.class)).thenReturn(new ResponseEntity<>(awsIpRangesDto, HttpStatusCode.valueOf(200)));

        // when
        final List<AwsIpRangeDto> resultList = awsIpRangeService.loadAwsIpRangeForRegion("ALL");

        //then
        assertEquals(2, resultList.size());

        assertEquals("ipPrefix1Mock", resultList.get(0).ipPrefix());
        assertEquals("us-east-1", resultList.get(0).region());
        assertEquals("service1Mock", resultList.get(0).service());
        assertEquals("networkBorderGroup1Mock", resultList.get(0).networkBorderGroup());

        assertEquals("ipPrefix2Mock", resultList.get(1).ipPrefix());
        assertEquals("ca-east-1", resultList.get(1).region());
        assertEquals("service2Mock", resultList.get(1).service());
        assertEquals("networkBorderGroup2Mock", resultList.get(1).networkBorderGroup());
    }
}