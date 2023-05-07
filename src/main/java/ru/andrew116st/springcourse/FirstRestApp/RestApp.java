package ru.andrew116st.springcourse.FirstRestApp;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RestApp {

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/sensors/registration";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String,String> jsonData = new HashMap();

        jsonData.put("name", "Sensor2");

        HttpEntity<Map<String,String>> request = new HttpEntity<>(jsonData, headers);

        try {
            restTemplate.postForObject(url, request, String.class);

            System.out.println("Изменение успешно отправлено на сервер!");
        } catch (HttpClientErrorException e) {
            System.out.println("ОШИБКА!");
            System.out.println(e.getMessage());
        }

        String url2 = "http://localhost:8080/measurements/add";

        HttpHeaders headers2 = new HttpHeaders();
        headers2.setContentType(MediaType.APPLICATION_JSON);

        Map<String,Object> jsonData2 = new HashMap();

        for (int i=0; i < 1000; i++){

            int max= 100;
            int min= -100;

            Random temp_1 = new Random();

            double temp = temp_1.nextDouble((max - min) + 1) + min;

            boolean raining = Math.random() > 0.5;

            jsonData2.put("value", temp);
            jsonData2.put("raining", raining);
            jsonData2.put("sensor", Map.of("name", "Sensor2"));

            HttpEntity<Map<String,Object>> request2 = new HttpEntity<>(jsonData2, headers);

            try {
            String response = restTemplate.postForObject(url2, request2, String.class);

                System.out.println("The change was successfully sent to the server!");

            } catch (HttpClientErrorException e) {
                System.out.println("Error!");
                System.out.println(e.getMessage());
            }
        }

    }
}
