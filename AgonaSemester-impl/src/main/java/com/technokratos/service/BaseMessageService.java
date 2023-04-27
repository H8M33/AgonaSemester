package com.technokratos.service;

import com.technokratos.exception.MessageNotFoundException;
import com.technokratos.model.ChatEntity;
import com.technokratos.model.MessageEntity;
import com.technokratos.repository.MessageRepository;
import com.technokratos.util.mapper.MessageMapper;
import dto.request.MessageRequest;
import dto.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BaseMessageService implements MessageService{

    private final MessageRepository repository;
    private final MessageMapper mapper;
    private final UserService userService;
    private final ChatService chatService;

    @Override
    public void createMessage(UUID userId, UUID chatId, MessageRequest request) {
        MessageEntity entity = mapper.toEntity(request);
        entity.setUser(userService.getUserEntity(userId));
        ChatEntity chat = chatService.getChatEntity(chatId);
        entity.setChat(chat);
        chat.getMessages().add(entity);
        repository.save(entity);
    }

    @Override
    public MessageResponse updateMessage(UUID messageId, MessageRequest request) {
        Optional<MessageEntity> messageEntityOptional = repository.findById(messageId);
        if (messageEntityOptional.isPresent()){
            MessageEntity messageEntity = mapper.toEntity(request);
            messageEntity.setId(messageId);
            messageEntity.setChat(messageEntityOptional.get().getChat());
            messageEntity.setUser(messageEntityOptional.get().getUser());
            return mapper.toResponse(repository.save(messageEntity));
        }
        throw new MessageNotFoundException(messageId);
    }

    @Override
    public void deleteMessageById(UUID messageId) {
        repository.deleteById(messageId);
    }

    @Override
    public MessageResponse getMessageById(UUID messageId) {
       return repository.findById(messageId)
               .map(mapper::toResponse)
               .orElseThrow(()->new MessageNotFoundException(messageId));
    }

    @Override
    public List<MessageResponse> getMessages() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public List <MessageResponse> getMessagesByUserId(UUID messageId) {
        return repository.findDistinctMessageByUser_Id(messageId).stream().map(mapper::toResponse).toList();
    }

    @Override
    public List <MessageResponse> getMessagesByChatId(UUID messageId) {
        return repository.findDistinctMessageByChat_Id(messageId).stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<MessageResponse> getMessagesByChatIdAndUserId(UUID chatId, UUID userId) {
        return repository.findDistinctMessageByChat_IdAndUser_Id(chatId, userId).stream()
                .map(mapper::toResponse).toList();
    }

}
