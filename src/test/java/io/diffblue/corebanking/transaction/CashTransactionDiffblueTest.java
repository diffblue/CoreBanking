package io.diffblue.corebanking.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.junit.Test;

public class CashTransactionDiffblueTest {
  /**
  * Method under test: {@link CashTransaction#CashTransaction(long, Date, Account)}
  */
  @Test
  public void testConstructor() {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());

    // Act
    CashTransaction actualCashTransaction = new CashTransaction(10L, fromResult,
        new Account(987654321L, new Client("Peter"), 10L));

    // Assert
    assertEquals("CASH", actualCashTransaction.getSource());
    assertEquals(Transaction.TransactionState.NOT_EXECUTED_YET, actualCashTransaction.getTransactionState());
    assertSame(fromResult, actualCashTransaction.getTransactionDate());
    assertEquals(10L, actualCashTransaction.getTransactionAmount());
    assertEquals("987654321", actualCashTransaction.getTarget());
  }

  /**
   * Method under test: {@link CashTransaction#CashTransaction(long, Date, Account)}
   */
  @Test
  public void testConstructor2() {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());

    // Act
    CashTransaction actualCashTransaction = new CashTransaction(-1L, fromResult,
        new Account(987654321L, new Client("Peter"), 10L));

    // Assert
    assertEquals("987654321", actualCashTransaction.getSource());
    assertEquals(Transaction.TransactionState.NOT_EXECUTED_YET, actualCashTransaction.getTransactionState());
    assertSame(fromResult, actualCashTransaction.getTransactionDate());
    assertEquals(-1L, actualCashTransaction.getTransactionAmount());
    assertEquals("CASH", actualCashTransaction.getTarget());
  }

  /**
   * Method under test: {@link CashTransaction#getSource()}
   */
  @Test
  public void testGetSource() {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    Date date = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());

    // Act and Assert
    assertEquals("CASH",
        (new CashTransaction(10L, date, new Account(987654321L, new Client("Peter"), 10L))).getSource());
  }

  /**
   * Method under test: {@link CashTransaction#getSource()}
   */
  @Test
  public void testGetSource2() {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    Date date = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());

    // Act and Assert
    assertEquals("987654321",
        (new CashTransaction(-1L, date, new Account(987654321L, new Client("Peter"), 10L))).getSource());
  }

  /**
   * Method under test: {@link CashTransaction#getTarget()}
   */
  @Test
  public void testGetTarget() {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    Date date = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());

    // Act and Assert
    assertEquals("987654321",
        (new CashTransaction(10L, date, new Account(987654321L, new Client("Peter"), 10L))).getTarget());
  }

  /**
   * Method under test: {@link CashTransaction#getTarget()}
   */
  @Test
  public void testGetTarget2() {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    Date date = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());

    // Act and Assert
    assertEquals("CASH",
        (new CashTransaction(-1L, date, new Account(987654321L, new Client("Peter"), 10L))).getTarget());
  }

  /**
   * Method under test: {@link CashTransaction#executeTransaction()}
   */
  @Test
  public void testExecuteTransaction() throws TransactionException {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    Date date = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
    CashTransaction cashTransaction = new CashTransaction(10L, date, new Account(987654321L, new Client("Peter"), 10L));

    // Act
    cashTransaction.executeTransaction();

    // Assert
    assertEquals(Transaction.TransactionState.EXECUTED, cashTransaction.getTransactionState());
  }

  /**
   * Method under test: {@link CashTransaction#executeTransaction()}
   */
  @Test
  public void testExecuteTransaction2() throws TransactionException {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    Date date = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
    CashTransaction cashTransaction = new CashTransaction(-1L, date, new Account(987654321L, new Client("Peter"), 10L));

    // Act
    cashTransaction.executeTransaction();

    // Assert
    assertEquals(Transaction.TransactionState.EXECUTED, cashTransaction.getTransactionState());
  }

  /**
   * Method under test: {@link CashTransaction#executeTransaction()}
   */
  @Test
  public void testExecuteTransaction3() throws TransactionException {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    Date date = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
    CashTransaction cashTransaction = new CashTransaction(Long.MIN_VALUE, date,
        new Account(987654321L, new Client("Peter"), 10L));

    // Act
    cashTransaction.executeTransaction();

    // Assert
    assertEquals(Transaction.TransactionState.FAILED, cashTransaction.getTransactionState());
  }
}

