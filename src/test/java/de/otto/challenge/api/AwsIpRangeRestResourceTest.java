package de.otto.challenge.api;

import de.otto.challenge.domain.AwsIpRangeDto;
import de.otto.challenge.service.AwsIpRangeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class AwsIpRangeRestResourceTest {

    private AwsIpRangeRestResource awsIpRangeRestResource;

    @Mock
    private AwsIpRangeService awsIpRangeServiceMock;

    @BeforeEach
    void setUp() {
        awsIpRangeRestResource = new AwsIpRangeRestResource(awsIpRangeServiceMock);
    }

    @Test
    void shouldReturnAwsIpRange() {
        // given
        when(awsIpRangeServiceMock.loadAwsIpRangeForRegion("ALL")).thenReturn(new LinkedList<AwsIpRangeDto>() {{
            add(new AwsIpRangeDto("ipPrefix1", "region1", "service1", "networkBorderGroup1"));
        }});

        // when
        final String result = awsIpRangeRestResource.awsIpRange("ALL");

        // then
        assertEquals("Region: region1; IpPrefix: ipPrefix1;"+System.lineSeparator(), result);
    }
}