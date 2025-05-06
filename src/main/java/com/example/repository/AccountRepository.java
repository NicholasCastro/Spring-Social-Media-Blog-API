package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Integer>{
    // 2: Our API should be able to process User logins.
    // Also used by checkIfUsernameExists for Requirement 1
    @Query("FROM Account WHERE username = :username")
    Account getAccountByUsername(@Param("username") String username);

    // Used by postMessage in MessageService (Requirement 3)
    @Query("FROM Account WHERE accountId = :accountId")
    Account getAccountByAccountId(@Param("accountId") Integer accountId);
}
