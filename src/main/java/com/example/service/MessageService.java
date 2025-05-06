package com.example.service;

import java.util.List;
import com.example.entity.Message;
import com.example.repository.MessageRepository;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageService {
    MessageRepository messageRepository;
    AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    // 3: Our API should be able to process the creation of new messages.
    public Message postMessage(Message message) {
        if(message.getMessageText().length() > 0 && message.getMessageText().length() <= 255 && accountRepository.getAccountByAccountId(message.getPostedBy()) != null) {
            messageRepository.save(message);
            return message;
        } else {
            return null;
        }
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

    // 6: Our API should be able to delete a message identified by a message ID.
    public Integer deleteMessage(Integer id) {
        Message message = messageRepository.getMessageById(id);
        
        if (message != null) {
            messageRepository.delete(message);
            return 1;
        } else {
            return null;
        }
    }

    // 7: Our API should be able to update a message text identified by a message ID.\
    public Integer updateMessage(Integer messageId, String messageText) {    
        Message messageToUpdate = messageRepository.getMessageById(messageId);

        if (messageToUpdate != null && messageText.length() > 0 && messageText.length() <= 255) {
            messageToUpdate.setMessageText(messageText);
            return 1;
        } else {
            return null;
        }
    }

    // 8: Our API should be able to retrieve all messages written by a particular user.
    public List<Message> getMessagesByUserId(Integer accountId) {
        return messageRepository.getMessagesByUserId(accountId);
    }
}
