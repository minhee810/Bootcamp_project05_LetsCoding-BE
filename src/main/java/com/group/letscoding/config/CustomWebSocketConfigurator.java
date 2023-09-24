package com.group.letscoding.config;

import com.group.letscoding.config.auth.PrincipalDetails;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class CustomWebSocketConfigurator extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        if (httpSession != null) {
            PrincipalDetails principalDetails = (PrincipalDetails) httpSession.getAttribute("SPRING_SECURITY_CONTEXT_KEY"); // 세션에서 필요한 정보 가져오기.
            if (principalDetails != null) {
                sec.getUserProperties().put("principal", principalDetails);
            }
        }
    }
}
