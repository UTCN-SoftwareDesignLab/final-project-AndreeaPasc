package com.finalprojectandreeapasc.websockets;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import static com.finalprojectandreeapasc.UrlMapping.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConf implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(final MessageBrokerRegistry registry){
        registry.enableSimpleBroker(RECIPES);
        registry.setApplicationDestinationPrefixes(CUSTOMERS);
    }

    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry){
        registry.addEndpoint(WEBSOCKET).withSockJS();
    }
}
