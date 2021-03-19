package io.diffblue.corebanking;

import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.account.AccountException;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.transaction.BankTransaction;
import io.diffblue.corebanking.transaction.CashTransaction;
import io.diffblue.corebanking.transaction.Transaction;
import io.diffblue.corebanking.transaction.TransactionException;
import java.util.Date;

public class RisExample {

  private static final long INITIAL_ACCOUNT_VALUE = 1000;

  private static void setUpAccounts(final CoreBanking coreBanking) {
    final Client firstClient = new Client("Bradley Johansen");
    coreBanking.registerNewClient(firstClient);
    coreBanking.openNewAccount(firstClient, INITIAL_ACCOUNT_VALUE);

    final Client secondClient = new Client("Melanie Rescorai");
    coreBanking.registerNewClient(secondClient);
    coreBanking.openNewAccount(secondClient, INITIAL_ACCOUNT_VALUE);
  }

  private static void initialiseAccountValues(final CoreBanking coreBanking) {
    for (Account account : coreBanking.getAccounts()) {
      try {
        account.addToBalance(INITIAL_ACCOUNT_VALUE);
      } catch (final AccountException e) {
        e.printStackTrace();
      }
    }
  }

  private static void doSomeDepositsWithdrawls(final CoreBanking coreBanking) {
    final Transaction deposit =
        new CashTransaction(100, new Date(), coreBanking.getAccounts().get(0));
    try {
      deposit.executeTransaction();
    } catch (final TransactionException e) {
      e.printStackTrace();
    }

    final Transaction withdrawl =
        new CashTransaction(-250, new Date(), coreBanking.getAccounts().get(1));
    try {
      withdrawl.executeTransaction();
    } catch (final TransactionException e) {
      e.printStackTrace();
    }

    try {
      deposit.executeTransaction();
    } catch (final TransactionException e) {
      e.printStackTrace();
    }
  }

  private static void doSomeBankTransactions(final CoreBanking coreBanking) {
    final Transaction forward = new BankTransaction(75, new Date(),
        coreBanking.getAccounts().get(0), coreBanking.getAccounts().get(1));
    try {
      forward.executeTransaction();
    } catch (final TransactionException e) {
      e.printStackTrace();
    }

    final Transaction reverse = new BankTransaction(5432, new Date(),
        coreBanking.getAccounts().get(1), coreBanking.getAccounts().get(0));
    try {
      reverse.executeTransaction();
    } catch (final TransactionException e) {
      e.printStackTrace();
    }
  }

  private static void closeAccounts(final CoreBanking coreBanking) {
    for (final Account account : coreBanking.getAccounts()) {
      try {
        account.closeAccount();
        account.closeAccount();
      } catch (final AccountException e) {
        e.printStackTrace();
      }
    }

    for (final Account account : coreBanking.getAccounts()) {
      try {
        account.addToBalance(150);
      } catch (final AccountException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(final String[] args) {
    final CoreBanking coreBanking = new CoreBanking();
    setUpAccounts(coreBanking);
    initialiseAccountValues(coreBanking);
    doSomeDepositsWithdrawls(coreBanking);
    doSomeBankTransactions(coreBanking);
    closeAccounts(coreBanking);
  }
}
