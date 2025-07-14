package com.BackendApplication.Service;


import com.BackendApplication.Model.CaptchaResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
public class CaptchaService {

    @Value("${captcha.key}")
    private String KEY;

    @Value ("${captcha.url.verify}")
    private String URL_VERIFY;


    public boolean validateRecaptcha (String token){
        MultiValueMap<String,String> requestMap = new LinkedMultiValueMap<>();
        requestMap.add("secret","");
        requestMap.add("response","");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(requestMap,httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CaptchaResponseDTO> response = restTemplate.postForEntity(URL_VERIFY,request, CaptchaResponseDTO.class);

        CaptchaResponseDTO captchaResponse = response.getBody();

        return (captchaResponse != null) ? captchaResponse.isSuccess(): false;
    }

}
