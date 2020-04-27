package io.diffblue.corebanking.transaction;

import static org.junit.Assert.assertEquals;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import java.util.Date;
import org.junit.Test;

public class CashTransactionDiffblueTest {
  @Test
  public void testConstructor() {
    // Arrange
    Date date = new Date(1L);

    // Act
    CashTransaction actualCashTransaction = new CashTransaction(-1L, date,
        new Account(1234567890L, new Client(""), 10L));

    // Assert
    assertEquals(Transaction.TransactionState.NOT_EXECUTED_YET, actualCashTransaction.getTransactionState());
    assertEquals("1234567890", actualCashTransaction.getSource());
    assertEquals(
        "Transaction: | 70.01.01\t| Source: 1234567890\t| Target:"
            + " CASH\t| Amount: -1\t| Balance: 0\t| Transaction state:" + " NOT_EXECUTED_YET\t|",
        actualCashTransaction.toString());
    assertEquals("CASH", actualCashTransaction.getTarget());
    assertEquals(-1L, actualCashTransaction.getTransactionAmount());
  }

  @Test
  public void testConstructor2() {
    // Arrange
    Date date = new Date(1L);

    // Act
    CashTransaction actualCashTransaction = new CashTransaction(10L, date,
        new Account(1234567890L, new Client(""), 10L));

    // Assert
    assertEquals(Transaction.TransactionState.NOT_EXECUTED_YET, actualCashTransaction.getTransactionState());
    assertEquals("CASH", actualCashTransaction.getSource());
    assertEquals("Transaction: | 70.01.01\t| Source: CASH\t| Target:"
        + " 1234567890\t| Amount: 10\t| Balance: 0\t| Transaction" + " state: NOT_EXECUTED_YET\t|",
        actualCashTransaction.toString());
    assertEquals("1234567890", actualCashTransaction.getTarget());
    assertEquals(10L, actualCashTransaction.getTransactionAmount());
  }

  @Test
  public void testGetSource() {
    // Arrange
    Date date = new Date(1L);

    // Act and Assert
    assertEquals("CASH", (new CashTransaction(10L, date, new Account(1234567890L, new Client(""), 10L))).getSource());
  }

  @Test
  public void testGetSource2() {
    // Arrange
    Date date = new Date(1L);

    // Act and Assert
    assertEquals("1234567890",
        (new CashTransaction(-1L, date, new Account(1234567890L, new Client(""), 10L))).getSource());
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
    assertEquals("Transaction: | 70.01.01\t| Source: CASH\t| Target:"
        + " 1234567890\t| Amount: 10\t| Balance: 0\t| Transaction" + " state: EXECUTED\t|", cashTransaction.toString());
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
    assertEquals(
        "Transaction: | 70.01.01\t| Source: 1234567890\t| Target:"
            + " CASH\t| Amount: -9223372036854775808\t| Balance: 10" + "\t| Transaction state: FAILED\t|",
        cashTransaction.toString());
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
    assertEquals("Transaction: | 70.01.01\t| Source: 1234567890\t| Target:"
        + " CASH\t| Amount: -1\t| Balance: 11\t| Transaction state:" + " EXECUTED\t|", cashTransaction.toString());
  }
}

