package com.example.Consumo.una.api.publica.Control;


import com.example.Consumo.una.api.publica.Entidad.Countries;
import com.example.Consumo.una.api.publica.Entidad.DataResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController

public class Controller {
    List<Countries>countries=new ArrayList<>();


@GetMapping("/cities")
    public  List<Countries>getPaisd(){
    String uri = "https://countriesnow.space/api/v0.1/countries";
    RestTemplate restTemplate=new RestTemplate();
   Object[] objects= new Object[] {restTemplate.getForObject(uri,Object.class)};

    ObjectMapper objectMapper=new ObjectMapper();
  List<Countries>countries=  Arrays.stream(objects).map(o -> objectMapper.convertValue(o, DataResponse.class))
            .map(DataResponse::getData)
            .collect(Collectors.toList()).get(0);
    return countries;
}


     @GetMapping("/city")
    public List<Countries> getCity() {
      return countries.stream().filter(e->e.getCountry().equals("Afghanistan")).collect(Collectors.toList());
    }
}


