package com.example.service;

import java.util.List;
import com.example.entity.Message;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageService {
    MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // 4: Our API should be able to retrieve all messages.
    public List<Message> getAllMessages() {
        // findAll method inherited from JpaRepository
        return messageRepository.findAll();
    }

    // 5: Our API should be able to retrieve a message by its ID.
    public Message getMessageById(Integer id) {
        return messageRepository.getMessageById(id);
    }
}
