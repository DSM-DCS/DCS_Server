package com.dsm.dcs.infra.ses;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "aws.ses")
public class SesProperties {

    private final String accessKey;
    private final String secretKey;
    private final String region;
    private final String email;

    public SesProperties(String accessKey, String secretKey, String region, String email) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.region = region;
        this.email = email;
    }

}
