package de.otto.challenge.domain;

import java.util.List;

public record AwsIpRangesDto(String syncToken, String createDate, List<AwsIpRangeDto> prefixes){ }
