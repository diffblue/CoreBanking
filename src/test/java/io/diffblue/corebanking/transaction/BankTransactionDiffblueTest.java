package io.diffblue.corebanking.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import org.junit.jupiter.api.Test;

class BankTransactionDiffblueTest {
  /**
  * Method under test: {@link BankTransaction#BankTransaction(long, Date, Account, Account)}
  */
  @Test
  void testConstructor() {
    // Arrange
    Date date = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Account sourceAcc = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act
    BankTransaction actualBankTransaction = new BankTransaction(10L, date, sourceAcc,
        new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Assert
    assertEquals("1234567890", actualBankTransaction.getSource());
    assertEquals(Transaction.TransactionState.NOT_EXECUTED_YET, actualBankTransaction.getTransactionState());
    assertSame(date, actualBankTransaction.getTransactionDate());
    assertEquals(10L, actualBankTransaction.getTransactionAmount());
    assertEquals("1234567890", actualBankTransaction.getTarget());
  }

  /**
   * Method under test: {@link BankTransaction#BankTransaction(long, java.util.Date, Account, Account)}
   */
  @Test
  void testConstructor2() {
    // Arrange
    java.sql.Date date = mock(java.sql.Date.class);
    Account sourceAcc = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act
    BankTransaction actualBankTransaction = new BankTransaction(10L, date, sourceAcc,
        new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Assert
    assertEquals("1234567890", actualBankTransaction.getSource());
    assertEquals(Transaction.TransactionState.NOT_EXECUTED_YET, actualBankTransaction.getTransactionState());
    assertSame(date, actualBankTransaction.getTransactionDate());
    assertEquals(10L, actualBankTransaction.getTransactionAmount());
    assertEquals("1234567890", actualBankTransaction.getTarget());
  }

  /**
   * Method under test: {@link BankTransaction#getSource()}
   */
  @Test
  void testGetSource() {
    // Arrange
    Date date = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Account sourceAcc = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act and Assert
    assertEquals("1234567890",
        (new BankTransaction(10L, date, sourceAcc, new Account(1234567890L, new Client("Dr Jane Doe"), 10L)))
            .getSource());
  }

  /**
   * Method under test: {@link BankTransaction#getSource()}
   */
  @Test
  void testGetSource2() {
    // Arrange
    java.sql.Date date = mock(java.sql.Date.class);
    Account sourceAcc = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act and Assert
    assertEquals("1234567890",
        (new BankTransaction(10L, date, sourceAcc, new Account(1234567890L, new Client("Dr Jane Doe"), 10L)))
            .getSource());
  }

  /**
   * Method under test: {@link BankTransaction#getTarget()}
   */
  @Test
  void testGetTarget() {
    // Arrange
    Date date = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Account sourceAcc = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act and Assert
    assertEquals("1234567890",
        (new BankTransaction(10L, date, sourceAcc, new Account(1234567890L, new Client("Dr Jane Doe"), 10L)))
            .getTarget());
  }

  /**
   * Method under test: {@link BankTransaction#getTarget()}
   */
  @Test
  void testGetTarget2() {
    // Arrange
    java.sql.Date date = mock(java.sql.Date.class);
    Account sourceAcc = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act and Assert
    assertEquals("1234567890",
        (new BankTransaction(10L, date, sourceAcc, new Account(1234567890L, new Client("Dr Jane Doe"), 10L)))
            .getTarget());
  }

  /**
   * Method under test: {@link BankTransaction#executeTransaction()}
   */
  @Test
  void testExecuteTransaction() throws TransactionException {
    // Arrange
    Date date = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Account sourceAcc = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    BankTransaction bankTransaction = new BankTransaction(10L, date, sourceAcc,
        new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Act
    bankTransaction.executeTransaction();

    // Assert
    assertEquals(Transaction.TransactionState.EXECUTED, bankTransaction.getTransactionState());
  }

  /**
   * Method under test: {@link BankTransaction#executeTransaction()}
   */
  @Test
  void testExecuteTransaction2() throws TransactionException {
    // Arrange
    Date date = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Account sourceAcc = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act and Assert
    assertThrows(TransactionException.class, () -> (new BankTransaction(Long.MAX_VALUE, date, sourceAcc,
        new Account(1234567890L, new Client("Dr Jane Doe"), 10L))).executeTransaction());
  }

  /**
   * Method under test: {@link BankTransaction#executeTransaction()}
   */
  @Test
  void testExecuteTransaction3() throws TransactionException {
    // Arrange
    java.sql.Date date = mock(java.sql.Date.class);
    Account sourceAcc = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    BankTransaction bankTransaction = new BankTransaction(10L, date, sourceAcc,
        new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Act
    bankTransaction.executeTransaction();

    // Assert
    assertEquals(Transaction.TransactionState.EXECUTED, bankTransaction.getTransactionState());
  }
}

