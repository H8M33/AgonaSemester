package com.technokratos.service;

import dto.request.MessageRequest;
import dto.response.ChatResponse;
import dto.response.MessageResponse;
import dto.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface MessageService {

    void createMessage(UUID userId, UUID chatId, MessageRequest request);

    MessageResponse updateMessage(UUID messageIdm, MessageRequest messageRequest);

    void deleteMessageById(UUID messageId);

    MessageResponse getMessageById(UUID messageId);

    List<MessageResponse> getMessages();

    List<MessageResponse> getMessagesByUserId(UUID messageId);

    List<MessageResponse> getMessagesByChatId(UUID messageId);

    List<MessageResponse> getMessagesByChatIdAndUserId(UUID chatId, UUID userId);
}
