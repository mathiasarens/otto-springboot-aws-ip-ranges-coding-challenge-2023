package de.otto.challenge.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AwsIpRangeDto(@JsonProperty("ip_prefix") String ipPrefix, String region, String service,
                            @JsonProperty("network_border_group") String networkBorderGroup) {

}
