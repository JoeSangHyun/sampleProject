package com.springboot.sampleproject.controller;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JasyptConfigTest {

    String ALGORITHTM = "PBEWithMD5AndDES";
    @Value("${ENC_KEY}")
//    String KEY = "shinsungengrestapienckey20230117";
    String KEY;
    @Test
    public void jasypt_암호화_복호화() {
        // given

        String url = "jdbc:mariadb://localhost:3306/suapapa";
        String username = "suapapa";
        String password = "Passw0rd";

        StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();

        jasypt.setPassword(KEY);
        jasypt.setAlgorithm(ALGORITHTM);

        // when
        String encryptedUrl = jasypt.encrypt(url);
        System.out.println("기존 url : " + url + ", 변경 url : " + encryptedUrl);
        String encryptedUsername = jasypt.encrypt(username);
        System.out.println("기존 username : " + username + ", 변경 username : " + encryptedUsername);
        String encryptedPassword = jasypt.encrypt(password);
        System.out.println("기존 password : " + password + ", 변경 password : " + encryptedPassword);

        String decryptedUrl = jasypt.decrypt(encryptedUrl);
        String decryptedUser = jasypt.decrypt(encryptedUsername);
        String decryptedPass = jasypt.decrypt(encryptedPassword);

        // then
        assertThat(url).isEqualTo(decryptedUrl);
        assertThat(username).isEqualTo(decryptedUser);
        assertThat(password).isEqualTo(decryptedPass);
    }
}
