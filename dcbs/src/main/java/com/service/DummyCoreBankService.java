package com.service;

import com.exception.AccountNotFoundException;
import com.model.AccountDetailEntity;
import com.repository.AccountDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DummyCoreBankService {

  /*  @Autowired
    AccountDetailsRepository accountDetailsRepository;*/

    private final AccountDetailsRepository accountDetailsRepository;

    public AccountDetailEntity getAccountBalanceByAccountNo(String accountNo) throws AccountNotFoundException {

        AccountDetailEntity balanceByAccountNo = accountDetailsRepository.findBalanceByAccountNo(accountNo);

        if (null != balanceByAccountNo) {
            return balanceByAccountNo;
        } else {
            throw new AccountNotFoundException("dcbs.err.no.acc.found");

        }


    }
}
