package com.task.dcbs;

import com.dummy_core_bank.ws.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigDecimal;

@Endpoint
public class CoreBankEndPoint {

    private static final String CORE_BANK_NAMESPACE_URI = "http://www.dummy-core-bank.com/ws";


    @PayloadRoot(namespace = CORE_BANK_NAMESPACE_URI, localPart = "getTotalAccountBalanceRequest")
    @ResponsePayload
    public GetTotalAccountBalanceResponse processTotalAccountBalanceRequest(@RequestPayload GetTotalAccountBalanceRequest request) {
        GetTotalAccountBalanceResponse response = new GetTotalAccountBalanceResponse();

        TotalUserAccountBalanceInfo userAccountBalanceInfo = new TotalUserAccountBalanceInfo();

        userAccountBalanceInfo.setUserId("111");
        userAccountBalanceInfo.setAccountDetails("123123,4546456");
        userAccountBalanceInfo.setTotalBalanceForUser(BigDecimal.valueOf(1000.00));
        response.setTotalUserAccountBalanceInfo(userAccountBalanceInfo);

        return response;
    }

    @PayloadRoot(namespace = CORE_BANK_NAMESPACE_URI, localPart = "fundTransferRequest")
    @ResponsePayload
    public FundTransferResponse processFundTransferRequest(@RequestPayload FundTransferRequest request) {
        FundTransferResponse response = new FundTransferResponse();

        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatus("Success");
        serviceStatus.setMessage("Fund Transfer Success fully completed");

        FundTransferInfo fundTransferInfo = new FundTransferInfo();
        fundTransferInfo.setDebitUserId("1");
        fundTransferInfo.setDebitAccountNo("11111");
        fundTransferInfo.setDebitAccountBalance(BigDecimal.valueOf(1000.00));
        fundTransferInfo.setCreditUserId("2");
        fundTransferInfo.setCreditAccountNo("2222");
        fundTransferInfo.setCreditAccountBalance(BigDecimal.valueOf(1000.00));
        response.setFundTransferInfo(fundTransferInfo);
        response.setServiceStatus(serviceStatus);

        return response;
    }


}
