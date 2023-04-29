package com.bits.wilp.demo_service_3.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class CustomerClient {

    @Autowired
    RestTemplate restTemplate;

    public Customer getCustomerById(@PathVariable final Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Customer> responseEntity = restTemplate
                .exchange("http://localhost:8080/customer/2" , HttpMethod.GET, entity, Customer.class);
        return responseEntity.getBody();
    }
}
