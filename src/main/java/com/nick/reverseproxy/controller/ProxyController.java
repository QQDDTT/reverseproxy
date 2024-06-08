package com.nick.reverseproxy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class ProxyController {
	private final Logger LOGGER = LoggerFactory.getLogger(ProxyController.class);
	private String realUrl = "";

    @GetMapping("/nickproxy/{url}")
    public ResponseEntity<?> proxy(@PathVariable String url) {
        LOGGER.info("[GET PROXY]" + url);
        realUrl = "http://" + url;
        return getResponse(realUrl);
    }
    
    @RequestMapping("/{url}*")
    public ResponseEntity<?> proxyPost(@PathVariable String url) {
        LOGGER.info("[POST]" + url);
        return getResponse(realUrl + "/" + url);
    }

    private ResponseEntity<String> getResponse(String url){
        RestTemplate restTemplate = new RestTemplate();
        try{
            return ResponseEntity.ok(restTemplate.getForObject(url, String.class));
        }catch(Exception e){
            LOGGER.error(e.getMessage());
            return ResponseEntity.ok("[ ERROR ] : " + e.getMessage());
        }
        
    }
}

