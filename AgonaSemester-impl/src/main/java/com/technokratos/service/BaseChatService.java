package com.technokratos.service;

import com.technokratos.exception.ChatNotFoundException;
import com.technokratos.model.ChatEntity;
import com.technokratos.model.UserEntity;
import com.technokratos.repository.ChatRepository;
import com.technokratos.util.mapper.ChatMapper;
import dto.request.ChatRequest;
import dto.response.ChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BaseChatService implements ChatService{

    private final ChatRepository repository;
    private final ChatMapper mapper;
    private final UserService userService;

    @Override
    public void createChat(UUID userId, ChatRequest request) {
        ChatEntity entity = mapper.toEntity(request);
        ArrayList<UserEntity> listUsers = new ArrayList<>();
        listUsers.add(userService.getUserEntity(userId));
        entity.setMessages(new ArrayList<>());
        entity.setUsers(listUsers);
        repository.save(entity);
    }

    @Override
    public ChatResponse updateChat(UUID chatId, ChatRequest request) {
        Optional<ChatEntity> chatEntityOptional = repository.findById(chatId);
        if (chatEntityOptional.isPresent()){
            ChatEntity chatEntity = mapper.toEntity(request);
            chatEntity.setId(chatId);
            chatEntity.setUsers(chatEntityOptional.get().getUsers());
            chatEntity.setMessages(chatEntityOptional.get().getMessages());
            return mapper.toResponse(repository.save(chatEntity));
        }
        throw new ChatNotFoundException(chatId);
    }

    @Override
    public void deleteChatById(UUID chatId) {
        repository.deleteById(chatId);
    }

    @Override
    public ChatResponse getChatById(UUID chatId) {
        return repository.findById(chatId)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ChatNotFoundException(chatId));
    }

    @Override
    public List<ChatResponse> getChats() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<ChatResponse> getChatsByUserId(UUID userID) {
        return repository.findDistinctChatByUsers_Id(userID).stream().map(mapper::toResponse).toList();
    }

    @Override
    public ChatEntity getChatEntity(UUID chatId) {
        Optional<ChatEntity> chatEntityOptional = repository.findById(chatId);
        if (chatEntityOptional.isPresent()){
            return chatEntityOptional.get();
        }
        throw new ChatNotFoundException(chatId);
    }

    @Override
    public void addUserToChat(UUID userId, UUID chatId) {
        ChatEntity entity = getChatEntity(chatId);
        entity.getUsers().add(userService.getUserEntity(userId));
        repository.save(entity);
    }

}
