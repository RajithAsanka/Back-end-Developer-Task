package com.task.dcbs;

import com.dummy_core_bank.ws.AccountInfo;
import com.dummy_core_bank.ws.GetAccountBalanceByAccNoRequest;
import com.dummy_core_bank.ws.GetAccountBalanceByAccNoResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CoreBankEndPoint {

    @PayloadRoot(namespace = "http://www.dummy-core-bank.com/ws", localPart = "getAccountBalanceByAccNoRequest")
    @ResponsePayload
    public GetAccountBalanceByAccNoResponse processAccountBalanceRequest(@RequestPayload GetAccountBalanceByAccNoRequest request) {
        GetAccountBalanceByAccNoResponse response = new GetAccountBalanceByAccNoResponse();
        AccountInfo accountInfo = new AccountInfo();

        accountInfo.setAccountNo("123123123");
        accountInfo.setUserId("111");
        accountInfo.setBalance(1000);
        response.setAccountInfo(accountInfo);
        return response;
    }


}
