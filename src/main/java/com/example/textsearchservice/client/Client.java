package com.example.textsearchservice.client;

import com.example.textsearchservice.models.Term;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class Client {

    public List<Term> getAllWords(){
        String url = "https://raw.githubusercontent.com/dwyl/english-words/master/words_dictionary.json";
        RestTemplate restTemplate = new RestTemplate();



        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root = mapper.readTree(response.getBody());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        List<String> out = new ArrayList<>();
        root.fieldNames().forEachRemaining(s -> {
            out.add(s);
        });

        //return out;
        List<Term> terms = new ArrayList<>();
        terms = out.stream()
//            .limit(5000)
            .map(s -> new Term(s, new Random().nextInt(1000)))
            .peek(p -> System.out.println(p.getTerm()))
            .collect(Collectors.toList());

        return terms;
    }
}
