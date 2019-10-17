package com.task.dcbs.service;

import com.dummy_core_bank.ws.*;
import com.task.dcbs.common.Status;
import com.task.dcbs.model.Account;
import com.task.dcbs.repository.AccountDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class DummyCoreBankService {
    private final AccountDetailsRepository accountDetailsRepository;
    private final MessageSource messageSource;

    /**
     * get account balance of particular account
     *
     * @param accountNo
     * @return
     */
    public GetAccountBalanceByAccNoResponse getAccountBalanceByAccountNo(String accountNo) {

        Account account = accountDetailsRepository.findByAccountNo(accountNo);
        GetAccountBalanceByAccNoResponse response = new GetAccountBalanceByAccNoResponse();

        if (checkValue(accountNo)) {
            if (null != account) {
                response.setAccountInfo(setAccountDetails(account));
                response.setServiceStatus(setServiceDetails(Status.SUCCESS, ""));
                return response;
            } else {
                response.setServiceStatus(setServiceDetails(Status.FAILED, messageSource.getMessage("dcbs.err.no.acc.found", null, Locale.ENGLISH)));
                return response;
            }
        } else {
            response.setServiceStatus(setServiceDetails(Status.FAILED, messageSource.getMessage("dcbs.err.no.acc.num.found", null, Locale.ENGLISH)));
            return response;
        }

    }

    /**
     * get total account balance for particular user
     *
     * @param userId
     * @return
     */
    public GetTotalAccountBalanceResponse getTotalAccountBalance(String userId) {
        List<Account> accountList = accountDetailsRepository.findByUserId(userId);
        GetTotalAccountBalanceResponse totalAccountBalanceResponse = new GetTotalAccountBalanceResponse();
        if (null != accountList && !(accountList.isEmpty())) {
            totalAccountBalanceResponse.setTotalUserAccountBalanceInfo(setUserAccountBalanceInfo(accountList));
            totalAccountBalanceResponse.setServiceStatus(setServiceDetails(Status.SUCCESS, ""));
        } else {
            totalAccountBalanceResponse.setServiceStatus(setServiceDetails(Status.FAILED, messageSource.getMessage("dcbs.err.no.acc.for.user", null, Locale.ENGLISH)));
        }
        return totalAccountBalanceResponse;
    }

    /**
     * set core bank service details for particular request
     *
     * @param success
     * @param message
     * @return
     */
    private ServiceStatus setServiceDetails(Status success, String message) {
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatus(success.name());
        serviceStatus.setMessage(message);
        return serviceStatus;
    }

    /**
     * set account details fot particular user
     *
     * @param balanceByAccountNo
     * @return
     */
    private AccountInfo setAccountDetails(Account balanceByAccountNo) {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setUserId(balanceByAccountNo.getUserId());
        accountInfo.setAccountNo(balanceByAccountNo.getAccountNo());
        accountInfo.setBalance(balanceByAccountNo.getBalance());
        return accountInfo;
    }

    /**
     * set user account details for total account balance option
     *
     * @param accountList
     * @return
     */
    private TotalUserAccountBalanceInfo setUserAccountBalanceInfo(List<Account> accountList) {

        TotalUserAccountBalanceInfo userAccountBalanceInfo = new TotalUserAccountBalanceInfo();
        final StringBuilder accountDetailsBuilder = new StringBuilder();

        BigDecimal totalBalance = BigDecimal.ZERO;

        for (Account account : accountList) {
            accountDetailsBuilder.append(account.getAccountNo() + "-" + account.getBalance() + ",");
            totalBalance = totalBalance.add(account.getBalance());
        }
        userAccountBalanceInfo.setUserId(accountList.get(0).getUserId());
        userAccountBalanceInfo.setTotalBalanceForUser(totalBalance);
        userAccountBalanceInfo.setAccountDetails(accountDetailsBuilder.toString());
        return userAccountBalanceInfo;

    }

    /**
     * @param value
     * @return
     */

    private boolean checkValue(String value) {

        boolean validity = false;
        if (null != value && !(value.isEmpty())) {
            validity = true;
        }
        return validity;
    }
}
