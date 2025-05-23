package com.example.repository;

import java.util.List;
import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<Message, Integer>{
    // 5: Our API should be able to retrieve a message by its ID.
    @Query("FROM Message WHERE id = :id")
    Message getMessageById(@Param("id") Integer id);

    // 8: Our API should be able to retrieve all messages written by a particular user.
    @Query("FROM Message WHERE postedBy = :postedBy")
    List<Message> getMessagesByUserId(@Param("postedBy") Integer postedBy);
}
