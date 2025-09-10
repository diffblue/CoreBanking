package io.diffblue.corebanking.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

class TransactionDiffblueTest {
  /**
   * Test {@link Transaction#getTransactionAmount()}.
   *
   * <p>Method under test: {@link Transaction#getTransactionAmount()}
   */
  @Test
  @DisplayName("Test getTransactionAmount()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long Transaction.getTransactionAmount()"})
  void testGetTransactionAmount() {
    // Arrange
    Date date =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Account targetAccount = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    CashTransaction cashTransaction = new CashTransaction(10L, date, targetAccount);

    // Act and Assert
    assertEquals(10L, cashTransaction.getTransactionAmount());
  }

  /**
   * Test {@link Transaction#getTransactionState()}.
   *
   * <p>Method under test: {@link Transaction#getTransactionState()}
   */
  @Test
  @DisplayName("Test getTransactionState()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"TransactionState Transaction.getTransactionState()"})
  void testGetTransactionState() {
    // Arrange
    Date date =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Account targetAccount = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    CashTransaction cashTransaction = new CashTransaction(10L, date, targetAccount);

    // Act and Assert
    assertEquals(TransactionState.NOT_EXECUTED_YET, cashTransaction.getTransactionState());
  }

  /**
   * Test {@link Transaction#setCurrentStateFailed()}.
   *
   * <p>Method under test: {@link Transaction#setCurrentStateFailed()}
   */
  @Test
  @DisplayName("Test setCurrentStateFailed()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Transaction.setCurrentStateFailed()"})
  void testSetCurrentStateFailed() throws TransactionException {
    // Arrange
    Date date =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Account targetAccount = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    CashTransaction cashTransaction = new CashTransaction(10L, date, targetAccount);

    // Act
    cashTransaction.setCurrentStateFailed();

    // Assert
    assertEquals(TransactionState.FAILED, cashTransaction.getTransactionState());
  }

  /**
   * Test {@link Transaction#markTransactionAsExecuted()}.
   *
   * <p>Method under test: {@link Transaction#markTransactionAsExecuted()}
   */
  @Test
  @DisplayName("Test markTransactionAsExecuted()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Transaction.markTransactionAsExecuted()"})
  void testMarkTransactionAsExecuted() {
    // Arrange
    Date date =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Account targetAccount = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    CashTransaction cashTransaction = new CashTransaction(10L, date, targetAccount);

    // Act
    cashTransaction.markTransactionAsExecuted();

    // Assert
    assertEquals(TransactionState.EXECUTED, cashTransaction.getTransactionState());
  }
}
