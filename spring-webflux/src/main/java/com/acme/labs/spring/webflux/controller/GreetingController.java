package com.acme.labs.spring.webflux.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.acme.labs.common.Greeting;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/naive-greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/naive-greeting-with-delay")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name,
            @RequestParam(value = "delay") int delayInMillis) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name), delayInMillis);
    }

    @GetMapping("/greeting")
    public Mono<ResponseEntity<Greeting>> greetingMono(
            @RequestParam(value = "name", defaultValue = "World") String name) {
        return Mono.just(name).map(value -> {
            Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, value));
            return ResponseEntity.status(HttpStatus.CREATED).body(greeting);
        });
    }

    @GetMapping("/greeting-with-delay")
    public Mono<ResponseEntity<Greeting>> greetingMono(
            @RequestParam(value = "name", defaultValue = "World") String name,
            @RequestParam(value = "delay") int delayInMillis) {
        Mono<ResponseEntity<Greeting>> res = Mono.just(name).map(value -> {
            Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, value), delayInMillis);
            return ResponseEntity.status(HttpStatus.CREATED).body(greeting);
        });
        return res;
    }

    @GetMapping("/greeting-with-delay-ok")
    public Mono<ResponseEntity<Greeting>> greetingMonoOk(
            @RequestParam(value = "name", defaultValue = "World") String name,
            @RequestParam(value = "delay") int delayInMillis) {
        Mono<ResponseEntity<Greeting>> res = Mono.just(name).map(value -> {
            Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, value), delayInMillis);
            return ResponseEntity.status(HttpStatus.CREATED).body(greeting);
        });
        // return res.subscribeOn(Schedulers.boundedElastic());
        return res.subscribeOn(Schedulers.elastic());
    }
}
