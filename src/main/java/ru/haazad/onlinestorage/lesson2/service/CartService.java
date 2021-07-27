package ru.haazad.onlinestorage.lesson2.service;

import org.springframework.stereotype.Component;

@Component
public interface CartService {
    void addProduct(Long id);

    boolean removeProduct(Long id);

    boolean isEmpty();
}
