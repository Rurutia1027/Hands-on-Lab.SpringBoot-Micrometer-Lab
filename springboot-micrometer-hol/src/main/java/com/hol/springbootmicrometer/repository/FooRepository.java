package com.hol.springbootmicrometer.repository;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class FooRepository {
    private final Map<Long, String> store = new ConcurrentHashMap<>();

    public String findById(Long id) {
        return store.getOrDefault(id, "not found");
    }

    public void save(Long id, String value) {
        store.put(id, value);
    }

    public void delete(Long id) {
        store.remove(id);
    }
}