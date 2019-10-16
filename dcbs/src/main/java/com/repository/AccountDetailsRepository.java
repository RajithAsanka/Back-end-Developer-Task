package com.repository;

import com.model.AccountDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDetailsRepository extends JpaRepository<AccountDetailEntity, Long> {

    AccountDetailEntity findBalanceByAccountNo(String accountNo);

    List<AccountDetailEntity> findTotalBalanceByUserId(String userId);


}
