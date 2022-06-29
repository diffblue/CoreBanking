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

public class TransactionDiffblueTest {
  /**
  * Method under test: {@link Transaction#getTransactionAmount()}
  */
  @Test
  public void testGetTransactionAmount() {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    Date date = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());

    // Act and Assert
    assertEquals(10L,
        (new CashTransaction(10L, date, new Account(987654321L, new Client("Peter"), 10L))).getTransactionAmount());
  }

  /**
   * Method under test: {@link Transaction#getTransactionDate()}
   */
  @Test
  public void testGetTransactionDate() {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());

    // Act and Assert
    assertSame(fromResult,
        (new CashTransaction(10L, fromResult, new Account(987654321L, new Client("Peter"), 10L))).getTransactionDate());
  }

  /**
   * Method under test: {@link Transaction#getTransactionState()}
   */
  @Test
  public void testGetTransactionState() {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    Date date = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());

    // Act and Assert
    assertEquals(Transaction.TransactionState.NOT_EXECUTED_YET,
        (new CashTransaction(10L, date, new Account(987654321L, new Client("Peter"), 10L))).getTransactionState());
  }

  /**
   * Method under test: {@link Transaction#setCurrentStateFailed()}
   */
  @Test
  public void testSetCurrentStateFailed() throws TransactionException {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    Date date = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
    CashTransaction cashTransaction = new CashTransaction(10L, date, new Account(987654321L, new Client("Peter"), 10L));

    // Act
    cashTransaction.setCurrentStateFailed();

    // Assert
    assertEquals(Transaction.TransactionState.FAILED, cashTransaction.getTransactionState());
  }

  /**
   * Method under test: {@link Transaction#markTransactionAsExecuted()}
   */
  @Test
  public void testMarkTransactionAsExecuted() {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    Date date = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
    CashTransaction cashTransaction = new CashTransaction(10L, date, new Account(987654321L, new Client("Peter"), 10L));

    // Act
    cashTransaction.markTransactionAsExecuted();

    // Assert
    assertEquals(Transaction.TransactionState.EXECUTED, cashTransaction.getTransactionState());
  }
}

