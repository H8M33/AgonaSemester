package com.technokratos.repository;

import com.technokratos.model.MessageEntity;
import dto.response.MessageResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<MessageEntity, UUID> {

    Optional<MessageEntity> findById(UUID id);

    List<MessageEntity> findDistinctMessageByUser_Id(UUID id);

    List<MessageEntity> findDistinctMessageByChat_Id(UUID id);

    List<MessageEntity> findDistinctMessageByChat_IdAndUser_Id(UUID chatId, UUID userId);
}
