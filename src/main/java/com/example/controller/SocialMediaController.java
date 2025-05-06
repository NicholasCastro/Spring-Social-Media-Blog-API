package com.example.controller;

import java.util.List;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/*
 * Completed Requirements:
 * 1: Our API should be able to process new User registrations. 
 * 2: Our API should be able to process User logins.
 * 3: Our API should be able to process the creation of new messages.
 * 4: Our API should be able to retrieve all messages.
 * 5: Our API should be able to retrieve a message by its ID.
 * 6: Our API should be able to delete a message identified by a message ID.
 * 7: Our API should be able to update a message text identified by a message ID.
 * 8: Our API should be able to retrieve all messages written by a particular user.
 * 9: The Project utilizes the Spring Framework.
 */
@Controller
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    // 1: Our API should be able to process new User registrations. 
    @PostMapping("/register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account) {
        Account registeredAccount = accountService.registerAccount(account);

        if (registeredAccount != null)
            return ResponseEntity.ok(registeredAccount);
        else if (accountService.checkIfUsernameExists(account))
            return ResponseEntity.status(409).body(null);
        else
            return ResponseEntity.status(400).body(null);
    }

    // 2: Our API should be able to process User logins.
    @PostMapping("/login")
    public ResponseEntity<Account> login (@RequestBody Account account) {
        Account loginAccount = accountService.getAccount(account);

        if (loginAccount != null)
            return ResponseEntity.ok(loginAccount);
        else
            return ResponseEntity.status(401).body(null);
    }

    // 3: Our API should be able to process the creation of new messages.
    @PostMapping("/messages")
    public ResponseEntity<Message> postMessage(@RequestBody Message message) {
        Message postedMessage = messageService.postMessage(message);

        if (postedMessage != null)
            return ResponseEntity.ok(postedMessage);
        else
            return ResponseEntity.status(400).body(null);
    }

    // 4: Our API should be able to retrieve all messages.
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messageList = messageService.getAllMessages();

        return ResponseEntity.ok(messageList);
    }

    // 5: Our API should be able to retrieve a message by its ID.
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer messageId) {
        return ResponseEntity.ok(messageService.getMessageById(messageId));
    }

    // 6: Our API should be able to delete a message identified by a message ID.
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable Integer messageId) {
        return ResponseEntity.ok(messageService.deleteMessage(messageId));
    }

    // 7: Our API should be able to update a message text identified by a message ID.
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessage(@PathVariable Integer messageId, @RequestBody Message message) {
        // The RequestBody will be inputted as a Message called "message" that only contains a messageText
        Integer output = messageService.updateMessage(messageId, message.getMessageText());

        if (output != null)
            return ResponseEntity.ok(output);
        else
            return ResponseEntity.status(400).body(null);
    }

    // 8: Our API should be able to retrieve all messages written by a particular user.
    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getMessageByUserId(@PathVariable Integer accountId) {
        return ResponseEntity.ok(messageService.getMessagesByUserId(accountId));
    }
}
