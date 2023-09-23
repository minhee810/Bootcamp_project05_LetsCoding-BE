package com.group.letscoding.controller;

import com.group.letscoding.config.auth.PrincipalDetails;
import org.springframework.stereotype.Controller;

import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Controller
@ServerEndpoint("/websocket")
public class MessageController extends Socket {

    private static final List<Session> session = new ArrayList<Session>();

    @OnOpen //WebSocket 연결이 열렸을 때 호출되는 메소드
    public void open(Session newUser, EndpointConfig config) {
        System.out.println("connected");
        session.add(newUser);
        System.out.println(newUser.getId());

        // Spring Security 컨텍스트에서 Principal 객체 가져오기
        PrincipalDetails principalDetails = (PrincipalDetails) config.getUserProperties().get("principal");
        if (principalDetails != null) {
            newUser.getUserProperties().put("username", principalDetails.getUsername());
        }
    }

    @OnMessage //클라이언트로부터 메시지를 받았을 때 호출되는 메소드
    public void getMsg(Session recieveSession, String msg) {

        String username = (String) recieveSession.getUserProperties().get("username");

        for (int i = 0; i < session.size(); i++) {
            if (!recieveSession.getId().equals(session.get(i).getId())) {
                try {
                    session.get(i).getBasicRemote().sendText(" 상대 : "+msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    session.get(i).getBasicRemote().sendText(" 나 : "+msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
