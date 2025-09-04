package io.diffblue.corebanking.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.transaction.Transaction.TransactionState;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BankTransactionDiffblueTest {
  /**
   * Test {@link BankTransaction#BankTransaction(long, Date, Account, Account)}.
   *
   * <p>Method under test: {@link BankTransaction#BankTransaction(long, Date, Account, Account)}
   */
  @Test
  @DisplayName("Test new BankTransaction(long, Date, Account, Account)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BankTransaction.<init>(long, Date, Account, Account)"})
  void testNewBankTransaction() {
    // Arrange
    Date date =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Account sourceAcc = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act
    BankTransaction actualBankTransaction =
        new BankTransaction(
            10L, date, sourceAcc, new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Assert
    assertEquals("1234567890", actualBankTransaction.getSource());
    assertEquals("1234567890", actualBankTransaction.getTarget());
    assertEquals(10L, actualBankTransaction.getTransactionAmount());
    assertEquals(TransactionState.NOT_EXECUTED_YET, actualBankTransaction.getTransactionState());
  }

  /**
   * Test {@link BankTransaction#getSource()}.
   *
   * <ul>
   *   <li>Then return {@code 1234567890}.
   * </ul>
   *
   * <p>Method under test: {@link BankTransaction#getSource()}
   */
  @Test
  @DisplayName("Test getSource(); then return '1234567890'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String BankTransaction.getSource()"})
  void testGetSource_thenReturn1234567890() {
    // Arrange
    Date date =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Account sourceAcc = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act and Assert
    assertEquals(
        "1234567890",
        new BankTransaction(
                10L, date, sourceAcc, new Account(1234567890L, new Client("Dr Jane Doe"), 10L))
            .getSource());
  }

  /**
   * Test {@link BankTransaction#getTarget()}.
   *
   * <ul>
   *   <li>Then return {@code 1234567890}.
   * </ul>
   *
   * <p>Method under test: {@link BankTransaction#getTarget()}
   */
  @Test
  @DisplayName("Test getTarget(); then return '1234567890'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String BankTransaction.getTarget()"})
  void testGetTarget_thenReturn1234567890() {
    // Arrange
    Date date =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Account sourceAcc = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act and Assert
    assertEquals(
        "1234567890",
        new BankTransaction(
                10L, date, sourceAcc, new Account(1234567890L, new Client("Dr Jane Doe"), 10L))
            .getTarget());
  }

  /**
   * Test {@link BankTransaction#executeTransaction()}.
   *
   * <p>Method under test: {@link BankTransaction#executeTransaction()}
   */
  @Test
  @DisplayName("Test executeTransaction()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BankTransaction.executeTransaction()"})
  void testExecuteTransaction() throws TransactionException {
    // Arrange
    Date date =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Account sourceAcc = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    BankTransaction bankTransaction =
        new BankTransaction(
            10L, date, sourceAcc, new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Act
    bankTransaction.executeTransaction();

    // Assert
    assertEquals(TransactionState.EXECUTED, bankTransaction.getTransactionState());
  }

  /**
   * Test {@link BankTransaction#executeTransaction()}.
   *
   * <ul>
   *   <li>Then throw {@link TransactionException}.
   * </ul>
   *
   * <p>Method under test: {@link BankTransaction#executeTransaction()}
   */
  @Test
  @DisplayName("Test executeTransaction(); then throw TransactionException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BankTransaction.executeTransaction()"})
  void testExecuteTransaction_thenThrowTransactionException() throws TransactionException {
    // Arrange
    Date date =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Account sourceAcc = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act and Assert
    assertThrows(
        TransactionException.class,
        () ->
            new BankTransaction(
                    Long.MAX_VALUE,
                    date,
                    sourceAcc,
                    new Account(1234567890L, new Client("Dr Jane Doe"), 10L))
                .executeTransaction());
  }
}
