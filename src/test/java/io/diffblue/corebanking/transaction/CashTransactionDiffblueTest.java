package io.diffblue.corebanking.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import org.junit.jupiter.api.Test;

class CashTransactionDiffblueTest {
  /**
  * Method under test: {@link CashTransaction#CashTransaction(long, Date, Account)}
  */
  @Test
  void testConstructor() {
    // Arrange
    Date date = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    CashTransaction actualCashTransaction = new CashTransaction(10L, date,
        new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Assert
    assertEquals("CASH", actualCashTransaction.getSource());
    assertEquals(Transaction.TransactionState.NOT_EXECUTED_YET, actualCashTransaction.getTransactionState());
    assertSame(date, actualCashTransaction.getTransactionDate());
    assertEquals(10L, actualCashTransaction.getTransactionAmount());
    assertEquals("1234567890", actualCashTransaction.getTarget());
  }

  /**
   * Method under test: {@link CashTransaction#CashTransaction(long, Date, Account)}
   */
  @Test
  void testConstructor2() {
    // Arrange
    Date date = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    CashTransaction actualCashTransaction = new CashTransaction(-1L, date,
        new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Assert
    assertEquals("1234567890", actualCashTransaction.getSource());
    assertEquals(Transaction.TransactionState.NOT_EXECUTED_YET, actualCashTransaction.getTransactionState());
    assertSame(date, actualCashTransaction.getTransactionDate());
    assertEquals(-1L, actualCashTransaction.getTransactionAmount());
    assertEquals("CASH", actualCashTransaction.getTarget());
  }

  /**
   * Method under test: {@link CashTransaction#CashTransaction(long, java.util.Date, Account)}
   */
  @Test
  void testConstructor3() {
    // Arrange
    java.sql.Date date = mock(java.sql.Date.class);

    // Act
    CashTransaction actualCashTransaction = new CashTransaction(10L, date,
        new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Assert
    assertEquals("CASH", actualCashTransaction.getSource());
    assertEquals(Transaction.TransactionState.NOT_EXECUTED_YET, actualCashTransaction.getTransactionState());
    assertSame(date, actualCashTransaction.getTransactionDate());
    assertEquals(10L, actualCashTransaction.getTransactionAmount());
    assertEquals("1234567890", actualCashTransaction.getTarget());
  }

  /**
   * Method under test: {@link CashTransaction#getSource()}
   */
  @Test
  void testGetSource() {
    // Arrange
    Date date = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals("CASH",
        (new CashTransaction(10L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L))).getSource());
  }

  /**
   * Method under test: {@link CashTransaction#getSource()}
   */
  @Test
  void testGetSource2() {
    // Arrange
    Date date = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals("1234567890",
        (new CashTransaction(-1L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L))).getSource());
  }

  /**
   * Method under test: {@link CashTransaction#getSource()}
   */
  @Test
  void testGetSource3() {
    // Arrange
    java.sql.Date date = mock(java.sql.Date.class);

    // Act and Assert
    assertEquals("CASH",
        (new CashTransaction(10L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L))).getSource());
  }

  /**
   * Method under test: {@link CashTransaction#getTarget()}
   */
  @Test
  void testGetTarget() {
    // Arrange
    Date date = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals("1234567890",
        (new CashTransaction(10L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L))).getTarget());
  }

  /**
   * Method under test: {@link CashTransaction#getTarget()}
   */
  @Test
  void testGetTarget2() {
    // Arrange
    Date date = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals("CASH",
        (new CashTransaction(-1L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L))).getTarget());
  }

  /**
   * Method under test: {@link CashTransaction#getTarget()}
   */
  @Test
  void testGetTarget3() {
    // Arrange
    java.sql.Date date = mock(java.sql.Date.class);

    // Act and Assert
    assertEquals("1234567890",
        (new CashTransaction(10L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L))).getTarget());
  }

  /**
   * Method under test: {@link CashTransaction#executeTransaction()}
   */
  @Test
  void testExecuteTransaction() throws TransactionException {
    // Arrange
    Date date = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CashTransaction cashTransaction = new CashTransaction(10L, date,
        new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Act
    cashTransaction.executeTransaction();

    // Assert
    assertEquals(Transaction.TransactionState.EXECUTED, cashTransaction.getTransactionState());
  }

  /**
   * Method under test: {@link CashTransaction#executeTransaction()}
   */
  @Test
  void testExecuteTransaction2() throws TransactionException {
    // Arrange
    Date date = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CashTransaction cashTransaction = new CashTransaction(-1L, date,
        new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Act
    cashTransaction.executeTransaction();

    // Assert
    assertEquals(Transaction.TransactionState.EXECUTED, cashTransaction.getTransactionState());
  }

  /**
   * Method under test: {@link CashTransaction#executeTransaction()}
   */
  @Test
  void testExecuteTransaction3() throws TransactionException {
    // Arrange
    Date date = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CashTransaction cashTransaction = new CashTransaction(Long.MIN_VALUE, date,
        new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Act
    cashTransaction.executeTransaction();

    // Assert
    assertEquals(Transaction.TransactionState.FAILED, cashTransaction.getTransactionState());
  }

  /**
   * Method under test: {@link CashTransaction#executeTransaction()}
   */
  @Test
  void testExecuteTransaction4() throws TransactionException {
    // Arrange
    java.sql.Date date = mock(java.sql.Date.class);
    CashTransaction cashTransaction = new CashTransaction(10L, date,
        new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Act
    cashTransaction.executeTransaction();

    // Assert
    assertEquals(Transaction.TransactionState.EXECUTED, cashTransaction.getTransactionState());
  }
}

