package com.task.dcbs.repository;

import com.task.dcbs.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDetailsRepository extends JpaRepository<Account, Long> {

    Account findByAccountNo(String accountNo);

    List<Account> findByUserId(String userId);

}
