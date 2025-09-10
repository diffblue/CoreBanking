package io.diffblue.corebanking.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.diffblue.corebanking.account.Account.AccountState;
import io.diffblue.corebanking.account.Account.AccountStatement;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.transaction.CashTransaction;
import io.diffblue.corebanking.transaction.Transaction;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class AccountDiffblueTest {
  /**
   * Test AccountStatement getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AccountStatement#AccountStatement(Account)}
   *   <li>{@link AccountStatement#getTransactions()}
   * </ul>
   */
  @Test
  @DisplayName("Test AccountStatement getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AccountStatement.<init>(Account)",
    "List AccountStatement.getTransactions()"
  })
  void testAccountStatementGettersAndSetters() {
    // Arrange
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act and Assert
    assertTrue(account.new AccountStatement().getTransactions().isEmpty());
  }

  /**
   * Test AccountStatement {@link AccountStatement#toString()}.
   *
   * <p>Method under test: {@link AccountStatement#toString()}
   */
  @Test
  @DisplayName("Test AccountStatement toString()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String AccountStatement.toString()"})
  void testAccountStatementToString() {
    // Arrange
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act and Assert
    assertEquals("Account statement empty.", account.new AccountStatement().toString());
  }

  /**
   * Test {@link Account#Account(long, Client, long)}.
   *
   * <p>Method under test: {@link Account#Account(long, Client, long)}
   */
  @Test
  @DisplayName("Test new Account(long, Client, long)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Account.<init>(long, Client, long)"})
  void testNewAccount() {
    // Arrange
    Client client = new Client("Dr Jane Doe");

    // Act
    Account actualAccount = new Account(1234567890L, client, 10L);

    // Assert
    assertEquals("Current", actualAccount.getAccountName());
    assertEquals(10L, actualAccount.getCurrentBalance());
    assertEquals(1234567890L, actualAccount.getAccountNumber());
    assertEquals(AccountState.OPEN, actualAccount.getAccountState());
    assertTrue(actualAccount.getAccountStatement().getTransactions().isEmpty());
    assertSame(client, actualAccount.getClient());
  }

  /**
   * Test {@link Account#addToBalance(long)}.
   *
   * <p>Method under test: {@link Account#addToBalance(long)}
   */
  @Test
  @DisplayName("Test addToBalance(long)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Account.addToBalance(long)"})
  void testAddToBalance() throws AccountException {
    // Arrange
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act
    account.addToBalance(10L);

    // Assert
    assertEquals(20L, account.getCurrentBalance());
  }

  /**
   * Test {@link Account#takeFromBalance(long)}.
   *
   * <p>Method under test: {@link Account#takeFromBalance(long)}
   */
  @Test
  @DisplayName("Test takeFromBalance(long)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Account.takeFromBalance(long)"})
  void testTakeFromBalance() throws AccountException {
    // Arrange
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act
    account.takeFromBalance(10L);

    // Assert
    assertEquals(0L, account.getCurrentBalance());
  }

  /**
   * Test {@link Account#takeFromBalance(long)}.
   *
   * <ul>
   *   <li>Then throw {@link AccountException}.
   * </ul>
   *
   * <p>Method under test: {@link Account#takeFromBalance(long)}
   */
  @Test
  @DisplayName("Test takeFromBalance(long); then throw AccountException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Account.takeFromBalance(long)"})
  void testTakeFromBalance_thenThrowAccountException() throws AccountException {
    // Arrange
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 1L);

    // Act and Assert
    assertThrows(AccountException.class, () -> account.takeFromBalance(10L));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Account#toString()}
   *   <li>{@link Account#getAccountName()}
   *   <li>{@link Account#getAccountNumber()}
   *   <li>{@link Account#getAccountState()}
   *   <li>{@link Account#getAccountStatement()}
   *   <li>{@link Account#getClient()}
   *   <li>{@link Account#getCurrentBalance()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String Account.getAccountName()",
    "long Account.getAccountNumber()",
    "AccountState Account.getAccountState()",
    "AccountStatement Account.getAccountStatement()",
    "Client Account.getClient()",
    "long Account.getCurrentBalance()",
    "String Account.toString()"
  })
  void testGettersAndSetters() {
    // Arrange
    Client client = new Client("Dr Jane Doe");
    Account account = new Account(1234567890L, client, 10L);

    // Act
    String actualToStringResult = account.toString();
    String actualAccountName = account.getAccountName();
    long actualAccountNumber = account.getAccountNumber();
    AccountState actualAccountState = account.getAccountState();
    AccountStatement actualAccountStatement = account.getAccountStatement();
    Client actualClient = account.getClient();

    // Assert
    assertEquals(
        "Account: | Acc #: 1234567890\t | Acc name: Current\t | Acc holder: Dr Jane Doe\t | Acc balance: 10\t | Acc"
            + " state: OPEN\t |\n"
            + "Account statement empty.",
        actualToStringResult);
    assertEquals("Current", actualAccountName);
    assertEquals(10L, account.getCurrentBalance());
    assertEquals(1234567890L, actualAccountNumber);
    assertEquals(AccountState.OPEN, actualAccountState);
    assertTrue(actualAccountStatement.getTransactions().isEmpty());
    assertSame(client, actualClient);
  }

  /**
   * Test {@link Account#setAccountName(String)}.
   *
   * <p>Method under test: {@link Account#setAccountName(String)}
   */
  @Test
  @DisplayName("Test setAccountName(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Account.setAccountName(String)"})
  void testSetAccountName() throws AccountException {
    // Arrange
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act
    account.setAccountName("Dr Jane Doe");

    // Assert
    assertEquals("Dr Jane Doe", account.getAccountName());
  }

  /**
   * Test {@link Account#closeAccount()}.
   *
   * <p>Method under test: {@link Account#closeAccount()}
   */
  @Test
  @DisplayName("Test closeAccount()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Account.closeAccount()"})
  void testCloseAccount() throws AccountException {
    // Arrange
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act
    account.closeAccount();

    // Assert
    assertEquals(AccountState.CLOSED, account.getAccountState());
  }

  /**
   * Test {@link Account#addTransaction(Transaction)}.
   *
   * <p>Method under test: {@link Account#addTransaction(Transaction)}
   */
  @Test
  @DisplayName("Test addTransaction(Transaction)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Account.addTransaction(Transaction)"})
  void testAddTransaction() throws AccountException {
    // Arrange
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);
    Date date =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Account targetAccount = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    CashTransaction transaction = new CashTransaction(10L, date, targetAccount);

    // Act
    account.addTransaction(transaction);

    // Assert
    List<Transaction> transactions = account.getAccountStatement().getTransactions();
    assertEquals(1, transactions.size());
    assertSame(transaction, transactions.get(0));
  }

  /**
   * Test {@link Account#equals(Object)}, and {@link Account#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link Account#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Account.equals(Object)"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);
    Account account2 = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act and Assert
    assertEquals(account, account2);
    assertNotEquals(account.hashCode(), account2.hashCode());
  }

  /**
   * Test {@link Account#equals(Object)}, and {@link Account#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link Account#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Account.equals(Object)"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act and Assert
    assertEquals(account, account);
    int expectedHashCodeResult = account.hashCode();
    assertEquals(expectedHashCodeResult, account.hashCode());
  }

  /**
   * Test {@link Account#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Account#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Account.equals(Object)"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Account account = new Account(3L, new Client("Dr Jane Doe"), 10L);

    // Act and Assert
    assertNotEquals(account, new Account(1234567890L, new Client("Dr Jane Doe"), 10L));
  }

  /**
   * Test {@link Account#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Account#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Account.equals(Object)"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new Account(1234567890L, new Client("Dr Jane Doe"), 10L), null);
  }

  /**
   * Test {@link Account#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Account#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Account.equals(Object)"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new Account(1234567890L, new Client("Dr Jane Doe"), 10L), "Different type to Account");
  }
}
