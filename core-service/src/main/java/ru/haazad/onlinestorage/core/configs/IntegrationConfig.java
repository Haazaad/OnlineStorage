package ru.haazad.onlinestorage.core.configs;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

@Configuration
public class IntegrationConfig {

    @Value("${integration.user-service.url}")
    private String userServiceUrl;

    @Value("${integration.cart-service.url}")
    private String cartServiceUrl;

    @Bean
    public WebClient userServiceWebClient() {
        return prepareWebClient(userServiceUrl);
    }

    @Bean
    public WebClient cartServiceWebClient() {
        return prepareWebClient(cartServiceUrl);
    }

    private WebClient prepareWebClient(String connectionUrl) {
        TcpClient tcpClient = TcpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(10000, TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
                });

        return WebClient.builder()
                .baseUrl(connectionUrl)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();
    }
}
