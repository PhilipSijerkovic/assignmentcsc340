package com.csc340.quotable;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Assignment2 {

    public static void main(String[] args) {
        SpringApplication.run(Assignment2.class, args);
        fetchRandomQuote();
        System.exit(1);
    }

    public static void fetchRandomQuote() {
        try {
            String apiUrl = "https://api.quotable.io/random";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jsonQuote = restTemplate.getForObject(apiUrl, String.class);
            JsonNode root = mapper.readTree(jsonQuote);

            // Get the quote content
            String content = root.findValue("content").asText();
            // Get the quote author
            String author = root.findValue("author").asText();

            // Print the quote
            System.out.println("Quote: " + content);
            System.out.println("Author: " + author);

        } catch (Exception ex) {
            System.err.println("Error fetching and processing the quote: " + ex.getMessage());
        }
    }
}
