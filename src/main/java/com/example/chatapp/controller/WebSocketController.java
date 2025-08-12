package com.example.chatapp.controller;

import com.example.chatapp.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WebSocketController {

    private final List<ChatMessage> publicChatHistory = new ArrayList<>();


    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/api/public-chat/history")
    public List<ChatMessage> getPublicChatHistory() {
        return publicChatHistory;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        publicChatHistory.add(chatMessage);
        return chatMessage;
    }
}
