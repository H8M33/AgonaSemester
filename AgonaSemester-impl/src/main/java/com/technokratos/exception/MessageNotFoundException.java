package com.technokratos.exception;

import java.util.UUID;

public class MessageNotFoundException extends NotFoundException{
    public MessageNotFoundException(UUID messageId) {
        super(String.format("Message with this id = %s, not found", messageId));
    }
}
