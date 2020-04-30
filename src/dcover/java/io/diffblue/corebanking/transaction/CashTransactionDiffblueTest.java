package io.diffblue.corebanking.transaction;

import static org.junit.Assert.assertEquals;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import java.util.Date;
import org.junit.Test;

public class CashTransactionDiffblueTest {


  @Test
  public void testGetSource() {
    // Arrange
    Date date = new Date(1L);

    // Act and Assert
    assertEquals("1234567890",
        (new CashTransaction(-1L, date, new Account(1234567890L, new Client(""), 10L))).getSource());
  }

  @Test
  public void testGetSource2() {
    // Arrange
    Date date = new Date(1L);

    // Act and Assert
    assertEquals("CASH", (new CashTransaction(10L, date, new Account(1234567890L, new Client(""), 10L))).getSource());
  }

  @Test
  public void testGetTarget() {
    // Arrange
    Date date = new Date(1L);

    // Act and Assert
    assertEquals("CASH", (new CashTransaction(-1L, date, new Account(1234567890L, new Client(""), 10L))).getTarget());
  }

  @Test
  public void testGetTarget2() {
    // Arrange
    Date date = new Date(1L);

    // Act and Assert
    assertEquals("1234567890",
        (new CashTransaction(10L, date, new Account(1234567890L, new Client(""), 10L))).getTarget());
  }

  @Test
  public void testExecuteTransaction() throws TransactionException {
    // Arrange
    Date date = new Date(1L);
    CashTransaction cashTransaction = new CashTransaction(10L, date, new Account(1234567890L, new Client(""), 10L));

    // Act
    cashTransaction.executeTransaction();

    // Assert
    assertEquals(Transaction.TransactionState.EXECUTED, cashTransaction.getTransactionState());
  }

  @Test
  public void testExecuteTransaction2() throws TransactionException {
    // Arrange
    Date date = new Date(1L);
    CashTransaction cashTransaction = new CashTransaction(-9223372036854775808L, date,
        new Account(1234567890L, new Client(""), 10L));

    // Act
    cashTransaction.executeTransaction();

    // Assert
    assertEquals(Transaction.TransactionState.FAILED, cashTransaction.getTransactionState());
  }

  @Test
  public void testExecuteTransaction3() throws TransactionException {
    // Arrange
    Date date = new Date(1L);
    CashTransaction cashTransaction = new CashTransaction(-1L, date, new Account(1234567890L, new Client(""), 10L));

    // Act
    cashTransaction.executeTransaction();

    // Assert
    assertEquals(Transaction.TransactionState.EXECUTED, cashTransaction.getTransactionState());
  }
}

