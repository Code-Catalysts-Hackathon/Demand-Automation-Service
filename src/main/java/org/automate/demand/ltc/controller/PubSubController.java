package org.automate.demand.ltc.controller;

import org.automate.demand.ltc.service.PubSubPublisherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
public class PubSubController {

    private final PubSubPublisherService pubSubPublisherService;

    public PubSubController(PubSubPublisherService pubSubPublisherService) {
        this.pubSubPublisherService = pubSubPublisherService;
    }

    @GetMapping("/publish")
    public String publishMessage(@RequestParam("message") String message) {
        try {
            pubSubPublisherService.publishMessage(message);
            return "Message published: " + message;
        } catch (IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return "Error publishing message: " + e.getMessage();
        }
    }
}
