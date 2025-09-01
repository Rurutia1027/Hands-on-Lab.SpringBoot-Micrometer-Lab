package com.hol.springbootmicrometer.service;

import com.hol.springbootmicrometer.repository.FooRepository;
import org.springframework.stereotype.Service;

@Service
public class FooService {
    private final FooRepository repository;

    public FooService(FooRepository repository) {
        this.repository = repository;
    }

    public String getFoo(Long id) {
        return repository.findById(id);
    }

    public void processFoo(Long id, String value) {
        repository.save(id, value.toUpperCase());
    }

    public void deleteFoo(Long id) {
        repository.delete(id);
    }
}