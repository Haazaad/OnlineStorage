package ru.haazad.onlinestorage.core.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.haazad.onlinestorage.api.dtos.CartDto;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;

    public CartDto getUserCartDto(String username) {
        return cartServiceWebClient.get()
                .uri("/api/v1/cart")
                .header("username", username)
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
    }

    public void clearUserCart(String username){
        try {
            cartServiceWebClient.get()
                    .uri("/api/v1/cart/clear")
                    .header("username", username)
                    .wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
