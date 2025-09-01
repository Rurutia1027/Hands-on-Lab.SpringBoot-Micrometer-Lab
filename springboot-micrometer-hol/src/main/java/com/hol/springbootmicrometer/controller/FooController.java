package com.hol.springbootmicrometer.controller;

import com.hol.springbootmicrometer.service.FooService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foo")
public class FooController {
    private final FooService service;

    public FooController(FooService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public String get(@PathVariable Long id) {
        return service.getFoo(id);
    }

    @PostMapping("/{id}")
    public void process(@PathVariable Long id, @RequestBody String value) {
        service.processFoo(id, value);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteFoo(id);
    }
}