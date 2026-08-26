	package com.example.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;   // <-- needed for Map

@RestController
public class HelloWorldController {

    @GetMapping("/")
    public String hello() {
        return "Hello, Sathish rohit Hari and Archana  — Spring Boot Web Service Phase 1!";
    }

    @GetMapping("/status")
    public Map<String, String> status() {
        return Map.of("status", "running", "version", "phase 1 Sai Hari");
    }

    @GetMapping("/info")
    public String info() {
        return "This is a demo Spring Boot service August 24.";
    }
	@GetMapping("/india")
	public String india(){
	return" India is my country Vandhe matharam";
}

}
