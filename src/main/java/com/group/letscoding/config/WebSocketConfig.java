package com.group.letscoding.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

//웹소켓을 사용하려면, 클라이언트가 보내온 통신을 처리하기 위한 핸들러가 필요합니다.
// 따라서 필요에 따라 개발자가 직접 구현한 핸들러를 웹소켓이 연결될때
// handshake 될 주소와 함께 인자로 넘겨주면 설정이 됩니다.

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    // connection을 맺을때 CORS 허용
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        //CORS (Cross-Origin Resource Sharing) 설정입니다.
        // "*"은 모든 도메인에서의 접근을 허용한다는 의미입니다.
        // 보안상의 이유로 특정 도메인만 허용하려면 해당 도메인의 문자열을 여기에 넣을 수 있습니다.
        registry.addEndpoint("/").setAllowedOrigins("*").withSockJS();
    }
}
