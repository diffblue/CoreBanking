package io.diffblue.corebanking.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import org.junit.jupiter.api.Test;

class TransactionDiffblueTest {
  /**
  * Method under test: {@link Transaction#getTransactionAmount()}
  */
  @Test
  void testGetTransactionAmount() {
    // Arrange
    Date date = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(10L, (new CashTransaction(10L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L)))
        .getTransactionAmount());
  }

  /**
   * Method under test: {@link Transaction#getTransactionAmount()}
   */
  @Test
  void testGetTransactionAmount2() {
    // Arrange
    java.sql.Date date = mock(java.sql.Date.class);

    // Act and Assert
    assertEquals(10L, (new CashTransaction(10L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L)))
        .getTransactionAmount());
  }

  /**
   * Method under test: {@link Transaction#getTransactionDate()}
   */
  @Test
  void testGetTransactionDate() {
    // Arrange
    Date date = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertSame(date, (new CashTransaction(10L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L)))
        .getTransactionDate());
  }

  /**
   * Method under test: {@link Transaction#getTransactionState()}
   */
  @Test
  void testGetTransactionState() {
    // Arrange
    Date date = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(Transaction.TransactionState.NOT_EXECUTED_YET,
        (new CashTransaction(10L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L)))
            .getTransactionState());
  }

  /**
   * Method under test: {@link Transaction#getTransactionState()}
   */
  @Test
  void testGetTransactionState2() {
    // Arrange
    java.sql.Date date = mock(java.sql.Date.class);

    // Act and Assert
    assertEquals(Transaction.TransactionState.NOT_EXECUTED_YET,
        (new CashTransaction(10L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L)))
            .getTransactionState());
  }

  /**
   * Method under test: {@link Transaction#setCurrentStateFailed()}
   */
  @Test
  void testSetCurrentStateFailed() throws TransactionException {
    // Arrange
    Date date = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CashTransaction cashTransaction = new CashTransaction(10L, date,
        new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Act
    cashTransaction.setCurrentStateFailed();

    // Assert
    assertEquals(Transaction.TransactionState.FAILED, cashTransaction.getTransactionState());
  }

  /**
   * Method under test: {@link Transaction#setCurrentStateFailed()}
   */
  @Test
  void testSetCurrentStateFailed2() throws TransactionException {
    // Arrange
    java.sql.Date date = mock(java.sql.Date.class);
    CashTransaction cashTransaction = new CashTransaction(10L, date,
        new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Act
    cashTransaction.setCurrentStateFailed();

    // Assert
    assertEquals(Transaction.TransactionState.FAILED, cashTransaction.getTransactionState());
  }

  /**
   * Method under test: {@link Transaction#markTransactionAsExecuted()}
   */
  @Test
  void testMarkTransactionAsExecuted() {
    // Arrange
    Date date = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CashTransaction cashTransaction = new CashTransaction(10L, date,
        new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Act
    cashTransaction.markTransactionAsExecuted();

    // Assert
    assertEquals(Transaction.TransactionState.EXECUTED, cashTransaction.getTransactionState());
  }

  /**
   * Method under test: {@link Transaction#markTransactionAsExecuted()}
   */
  @Test
  void testMarkTransactionAsExecuted2() {
    // Arrange
    java.sql.Date date = mock(java.sql.Date.class);
    CashTransaction cashTransaction = new CashTransaction(10L, date,
        new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Act
    cashTransaction.markTransactionAsExecuted();

    // Assert
    assertEquals(Transaction.TransactionState.EXECUTED, cashTransaction.getTransactionState());
  }

  /**
   * Method under test: {@link Transaction#toString()}
   */
  @Test
  void testToString() {
    // Arrange
    java.sql.Date date = mock(java.sql.Date.class);
    when(date.getTime()).thenReturn(10L);

    // Act
    (new CashTransaction(10L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L))).toString();

    // Assert
    verify(date).getTime();
  }
}

