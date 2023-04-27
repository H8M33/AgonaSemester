package com.technokratos.service;


import com.technokratos.model.ChatEntity;
import dto.request.ChatRequest;
import dto.response.ChatResponse;

import java.util.List;
import java.util.UUID;

public interface ChatService {

    void createChat(UUID userId, ChatRequest request);

    ChatResponse updateChat(UUID chatId, ChatRequest request);

    void deleteChatById(UUID chatId);

    ChatResponse getChatById(UUID chatId);

    List<ChatResponse> getChats();

    List<ChatResponse> getChatsByUserId(UUID userID);

    ChatEntity getChatEntity(UUID chatId);

    void addUserToChat(UUID userId, UUID chatId);
}
