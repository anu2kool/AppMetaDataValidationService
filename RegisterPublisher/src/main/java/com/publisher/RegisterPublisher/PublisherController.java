package com.publisher.RegisterPublisher;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("publish")
public class PublisherController {
    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }
    @PostMapping
    public void addApp(@RequestBody Map<String, Object> data){
//        System.out.println(data);
//        System.out.println(data.getClass());
//        System.out.println(data.get("name"));
//        System.out.println(data.get("name").getClass());
        String publisher_name = (String)data.get("name");
        String publisher_email = (String)data.get("email");
        String app_bundle_id = (String)data.get("app_bundle_id");
        String app_name = (String)data.get("app_name");
        publisherService.addNewApp(data);

    }
}
