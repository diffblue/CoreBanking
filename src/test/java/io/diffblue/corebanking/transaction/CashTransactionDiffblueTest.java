package io.diffblue.corebanking.transaction;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.transaction.Transaction.TransactionState;
import java.util.Date;
import org.junit.jupiter.api.Test;

class CashTransactionDiffblueTest {
  @Test
  public void testGetSource() throws Exception {
    long amount = 1000L;
    Date date = new Date();
    Client client = new Client("John Doe");
    Account targetAccount = new Account(1234L, client, 5000L);
    CashTransaction cashTransaction = new CashTransaction(amount, date, targetAccount);
    String result = cashTransaction.getSource();
    assertEquals("CASH", result);
  }

  @Test
  public void testGetTarget() throws Exception {
    long accountNumber = 1234L;
    Client client = new Client("Test Client");
    Account targetAccount = new Account(accountNumber, client, 1000L);
    CashTransaction cashTransaction = new CashTransaction(500L, new Date(), targetAccount);
    String result = cashTransaction.getTarget();
    assertEquals(String.valueOf(accountNumber), result);
  }

  @Test
  void executeTransaction() throws Exception {
    Client client = new Client("Test Client");
    Account account = new Account(1234, client, 1000);
    CashTransaction cashTransaction = new CashTransaction(500, new Date(), account);
    try {
      cashTransaction.executeTransaction();
    } catch (TransactionException e) {
      fail("Transaction failed: " + e.getMessage());
    }
    assertEquals(Transaction.TransactionState.EXECUTED, cashTransaction.getTransactionState());
    assertEquals(1500, account.getCurrentBalance());
  }
}
