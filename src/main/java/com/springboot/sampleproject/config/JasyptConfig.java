package com.springboot.sampleproject.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 프로퍼티 암호화
 * 암호화 키 값은 application.properties
 */
@Configuration
@EnableEncryptableProperties
//@Profile({"local","prod"}) // application.properties에 dev 항목으로 넣었을 경우
public class JasyptConfig {
    private static final String ALGORITHM =  "PBEWithMD5AndDES";
    @Value("${jasypt.encryptor.password}")
    private String KEY;

    @Bean(name="jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(KEY); // 암호화 키
        config.setAlgorithm(ALGORITHM); // 알고리즘
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);

        return encryptor;
    }
}
