package io.diffblue.corebanking.transaction;

import static org.junit.Assert.assertEquals;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import java.util.Date;
import org.junit.Test;

public class BankTransactionDiffblueTest {

  @Test
  public void testGetSource() {
    // Arrange
    Date date = new Date(1L);

    // Act and Assert
    assertEquals("1234567890", (new BankTransaction(10L, date, new Account(1234567890L, new Client(""), 10L),
        new Account(1234567890L, new Client(""), 10L))).getSource());
  }

  @Test
  public void testGetTarget() {
    // Arrange
    Date date = new Date(1L);

    // Act and Assert
    assertEquals("1234567890", (new BankTransaction(10L, date, new Account(1234567890L, new Client(""), 10L),
        new Account(1234567890L, new Client(""), 10L))).getTarget());
  }

  @Test
  public void testExecuteTransaction() throws TransactionException {
    // Arrange
    Date date = new Date(1L);
    BankTransaction bankTransaction = new BankTransaction(10L, date, new Account(1234567890L, new Client(""), 10L),
        new Account(1234567890L, new Client(""), 10L));

    // Act
    bankTransaction.executeTransaction();

    // Assert
    assertEquals(Transaction.TransactionState.EXECUTED, bankTransaction.getTransactionState());
  }
}

