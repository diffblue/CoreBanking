package io.diffblue.corebanking.transaction;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.transaction.Transaction.TransactionState;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransactionDiffblueTest {
  @Test
  public void testGetTransactionAmount() throws Exception {
    long amount = 1000L;
    Date date = new Date();
    Client client = new Client("Test Client");
    Account sourceAcc = new Account(1234L, client, 5000L);
    Account targetAcc = new Account(5678L, client, 2000L);
    BankTransaction bankTransaction = new BankTransaction(amount, date, sourceAcc, targetAcc);
    long result = bankTransaction.getTransactionAmount();
    assertEquals(amount, result);
  }

  @Test
  void testGetTransactionDate() throws Exception {
    Date date = new Date();
    BankTransaction bankTransaction = new BankTransaction(1000, date, null, null);
    assertEquals(date, bankTransaction.getTransactionDate());
  }

  @Test
  public void testGetTransactionState() throws Exception {
    long amount = 1000L;
    Date date = new Date();
    Client client = new Client("Test Client");
    Account sourceAcc = new Account(1234L, client, 5000L);
    Account targetAcc = new Account(5678L, client, 2000L);
    BankTransaction bankTransaction = new BankTransaction(amount, date, sourceAcc, targetAcc);
    Transaction.TransactionState result = bankTransaction.getTransactionState();
    Assertions.assertEquals(Transaction.TransactionState.NOT_EXECUTED_YET, result);
  }

  @Test
  void setAccountBalanceAfterTransactionTest() throws Exception {
    BankTransaction bankTransaction = new BankTransaction(1000, new Date(), null, null);
    assertDoesNotThrow(() -> bankTransaction.setAccountBalanceAfterTransaction(500));
  }
}
