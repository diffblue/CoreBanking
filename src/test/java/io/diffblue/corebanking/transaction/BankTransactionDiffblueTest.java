package io.diffblue.corebanking.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BankTransactionDiffblueTest {
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  /**
  * Method under test: {@link BankTransaction#BankTransaction(long, Date, Account, Account)}
  */
  @Test
  public void testConstructor() {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
    Account sourceAcc = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act
    BankTransaction actualBankTransaction = new BankTransaction(10L, fromResult, sourceAcc,
        new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Assert
    assertEquals("1234567890", actualBankTransaction.getSource());
    assertEquals(Transaction.TransactionState.NOT_EXECUTED_YET, actualBankTransaction.getTransactionState());
    assertSame(fromResult, actualBankTransaction.getTransactionDate());
    assertEquals(10L, actualBankTransaction.getTransactionAmount());
    assertEquals("1234567890", actualBankTransaction.getTarget());
  }

  /**
   * Method under test: {@link BankTransaction#getSource()}
   */
  @Test
  public void testGetSource() {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    Date date = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
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
  public void testGetTarget() {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    Date date = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
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
  public void testExecuteTransaction() throws TransactionException {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    Date date = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
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
  public void testExecuteTransaction2() throws TransactionException {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    Date date = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
    Account sourceAcc = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act and Assert
    thrown.expect(TransactionException.class);
    (new BankTransaction(Long.MAX_VALUE, date, sourceAcc, new Account(1234567890L, new Client("Dr Jane Doe"), 10L)))
        .executeTransaction();
  }
}

