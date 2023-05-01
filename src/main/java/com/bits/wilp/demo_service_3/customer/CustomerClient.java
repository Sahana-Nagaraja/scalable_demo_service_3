package com.bits.wilp.demo_service_3.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
public class CustomerClient {
    @Autowired
    RestTemplate restTemplate;

    public Customer getCustomer(Integer customerId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);
        Customer responseEntity = restTemplate
                .exchange("http://customer.service.1:8080/customer/" + customerId, HttpMethod.GET, entity, Customer.class).getBody();
        return responseEntity;
    }
}
