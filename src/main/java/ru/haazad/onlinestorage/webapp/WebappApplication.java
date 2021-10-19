package ru.haazad.onlinestorage.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebappApplication {
	// Домашнее задание:
	// 1. Добавить страницу с товаром, и для пользователей, которые купили этот товар, возможность
	// оставлять отзывы. Для всех остальных просто смотреть на отзывы
	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}

}
