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

class CashTransactionDiffblueTest {
  /**
   * Test {@link CashTransaction#CashTransaction(long, Date, Account)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return Source is {@code 1234567890}.
   * </ul>
   *
   * <p>Method under test: {@link CashTransaction#CashTransaction(long, Date, Account)}
   */
  @Test
  @DisplayName(
      "Test new CashTransaction(long, Date, Account); when minus one; then return Source is '1234567890'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CashTransaction.<init>(long, Date, Account)"})
  void testNewCashTransaction_whenMinusOne_thenReturnSourceIs1234567890() {
    // Arrange
    Date date =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    CashTransaction actualCashTransaction =
        new CashTransaction(-1L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Assert
    assertEquals("1234567890", actualCashTransaction.getSource());
    assertEquals("CASH", actualCashTransaction.getTarget());
    assertEquals(-1L, actualCashTransaction.getTransactionAmount());
    assertEquals(TransactionState.NOT_EXECUTED_YET, actualCashTransaction.getTransactionState());
  }

  /**
   * Test {@link CashTransaction#CashTransaction(long, Date, Account)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return Target is {@code 1234567890}.
   * </ul>
   *
   * <p>Method under test: {@link CashTransaction#CashTransaction(long, Date, Account)}
   */
  @Test
  @DisplayName(
      "Test new CashTransaction(long, Date, Account); when ten; then return Target is '1234567890'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CashTransaction.<init>(long, Date, Account)"})
  void testNewCashTransaction_whenTen_thenReturnTargetIs1234567890() {
    // Arrange
    Date date =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    CashTransaction actualCashTransaction =
        new CashTransaction(10L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Assert
    assertEquals("1234567890", actualCashTransaction.getTarget());
    assertEquals("CASH", actualCashTransaction.getSource());
    assertEquals(10L, actualCashTransaction.getTransactionAmount());
    assertEquals(TransactionState.NOT_EXECUTED_YET, actualCashTransaction.getTransactionState());
  }

  /**
   * Test {@link CashTransaction#getSource()}.
   *
   * <ul>
   *   <li>Then return {@code 1234567890}.
   * </ul>
   *
   * <p>Method under test: {@link CashTransaction#getSource()}
   */
  @Test
  @DisplayName("Test getSource(); then return '1234567890'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String CashTransaction.getSource()"})
  void testGetSource_thenReturn1234567890() {
    // Arrange
    Date date =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(
        "1234567890",
        new CashTransaction(-1L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L))
            .getSource());
  }

  /**
   * Test {@link CashTransaction#getSource()}.
   *
   * <ul>
   *   <li>Then return {@code CASH}.
   * </ul>
   *
   * <p>Method under test: {@link CashTransaction#getSource()}
   */
  @Test
  @DisplayName("Test getSource(); then return 'CASH'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String CashTransaction.getSource()"})
  void testGetSource_thenReturnCash() {
    // Arrange
    Date date =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(
        "CASH",
        new CashTransaction(10L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L))
            .getSource());
  }

  /**
   * Test {@link CashTransaction#getTarget()}.
   *
   * <ul>
   *   <li>Then return {@code 1234567890}.
   * </ul>
   *
   * <p>Method under test: {@link CashTransaction#getTarget()}
   */
  @Test
  @DisplayName("Test getTarget(); then return '1234567890'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String CashTransaction.getTarget()"})
  void testGetTarget_thenReturn1234567890() {
    // Arrange
    Date date =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(
        "1234567890",
        new CashTransaction(10L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L))
            .getTarget());
  }

  /**
   * Test {@link CashTransaction#getTarget()}.
   *
   * <ul>
   *   <li>Then return {@code CASH}.
   * </ul>
   *
   * <p>Method under test: {@link CashTransaction#getTarget()}
   */
  @Test
  @DisplayName("Test getTarget(); then return 'CASH'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String CashTransaction.getTarget()"})
  void testGetTarget_thenReturnCash() {
    // Arrange
    Date date =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(
        "CASH",
        new CashTransaction(-1L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L))
            .getTarget());
  }

  /**
   * Test {@link CashTransaction#executeTransaction()}.
   *
   * <p>Method under test: {@link CashTransaction#executeTransaction()}
   */
  @Test
  @DisplayName("Test executeTransaction()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CashTransaction.executeTransaction()"})
  void testExecuteTransaction() throws TransactionException {
    // Arrange
    Date date =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CashTransaction cashTransaction =
        new CashTransaction(10L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Act
    cashTransaction.executeTransaction();

    // Assert
    assertEquals(TransactionState.EXECUTED, cashTransaction.getTransactionState());
  }

  /**
   * Test {@link CashTransaction#executeTransaction()}.
   *
   * <p>Method under test: {@link CashTransaction#executeTransaction()}
   */
  @Test
  @DisplayName("Test executeTransaction()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CashTransaction.executeTransaction()"})
  void testExecuteTransaction2() throws TransactionException {
    // Arrange
    Date date =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CashTransaction cashTransaction =
        new CashTransaction(-1L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Act
    cashTransaction.executeTransaction();

    // Assert
    assertEquals(TransactionState.EXECUTED, cashTransaction.getTransactionState());
  }

  /**
   * Test {@link CashTransaction#executeTransaction()}.
   *
   * <p>Method under test: {@link CashTransaction#executeTransaction()}
   */
  @Test
  @DisplayName("Test executeTransaction()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CashTransaction.executeTransaction()"})
  void testExecuteTransaction3() throws TransactionException {
    // Arrange
    Date date =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CashTransaction cashTransaction =
        new CashTransaction(
            Long.MIN_VALUE, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Act
    cashTransaction.executeTransaction();

    // Assert
    assertEquals(TransactionState.FAILED, cashTransaction.getTransactionState());
  }
}
