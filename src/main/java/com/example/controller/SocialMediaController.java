package com.example.controller;

import java.util.List;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

/*
 * Remaining Requirements:
 * 3: Our API should be able to process the creation of new messages.
 * 6: Our API should be able to delete a message identified by a message ID.
 * 7: Our API should be able to update a message text identified by a message ID.
 * 8: Our API should be able to retrieve all messages written by a particular user.
 * 9: The Project utilizes the Spring Framework.
*/

/*
 * Completed Requirements:
 * 1: Our API should be able to process new User registrations. 
 * 2: Our API should be able to process User logins.
 * 4: Our API should be able to retrieve all messages.
 * 5: Our API should be able to retrieve a message by its ID.
 */
@Controller
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;

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
            return ResponseEntity.status(409).body(registeredAccount);
        else
            return ResponseEntity.status(400).body(registeredAccount);
    }

    // 2: Our API should be able to process User logins.
    @PostMapping("/login")
    public ResponseEntity<Account> login (@RequestBody Account account) {
        Account loginAccount = accountService.getAccount(account);

        if (loginAccount != null)
            return ResponseEntity.ok(loginAccount);
        else
            return ResponseEntity.status(401).body(loginAccount);
    }

    // 4: Our API should be able to retrieve all messages.
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messageList = messageService.getAllMessages();

        return ResponseEntity.ok(messageList);
    }

    // 5: Our API should be able to retrieve a message by its ID.
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable  Integer messageId) {
        return ResponseEntity.ok(messageService.getMessageById(messageId));
    }
}
