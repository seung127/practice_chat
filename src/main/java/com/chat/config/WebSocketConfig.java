package com.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * StompEndpointRegistry interface의 addEndpoint 메서드를 통해 클라이언트가 웹소켓 서버에 연결하는데 사용할 엔드포인트를 등록
     * 이렇게 등록된 URL을 통해 Stomp가 연결되고, 메시지에 처리에 따른 url로 요청을 보냄
     * controller의 @MessageMapping 어노테이션으로 해당하는 url을 할당해줘 SimpMessagingTemplate를
     * 통해 약속된 경로 또는 유저에게 메시지를 전달
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

    /**
     *메시지 브로커를 구성하는 메서드로 유저가 메세지를 전송하거나 받을 수 있도록
     * 중간에서 URL prefix(접두어)를 인식하여 올바르게 전송(publish), 전달(subscribe)을 중개해주는 중개자(Broker) 역할
     *
     * - enableSimpleBroker("/topic")
     *    메모리 내 메세지 브로커가 /topic 접두사가 붙은 대상에서 클라이언트로 메시지를 다시 전달할 수 있도록 합니다.
     *    "/topic"으로 시작하는 메시지가 메세지 브로커로 라우팅 되어야 한다고 정의
     *
     * - setApplicationDestinationPrefixes("/app")
     *   "/app"으로 시작하는 메시지가 메세지를 처리하는 메서드(message-handling methods)로 라우팅 되어야 한다고 정의
     *
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }
}