package com.example.demo.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Items;
import com.example.demo.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductService {

    private final RestTemplate restTemplate;

    public ProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Items getPostsPlainJSON() throws JsonMappingException, JsonProcessingException {
        String url = "https://dummyjson.com/products/";
        String strJson =  this.restTemplate.getForObject(url, String.class);
        //Product product = new Gson().fromJson(strJson, Product.class);
        
        ObjectMapper mapper = new ObjectMapper();
        Items participantJsonList = mapper.readValue(strJson, Items.class);
        
        List<Product> list=  participantJsonList.getProducts().stream().filter(p->p.getId()==4).collect(Collectors.toList());

        Items item=new Items();
        item.setProducts(list);
        return item;
    }
}