package io.diffblue.corebanking.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.transaction.CashTransaction;
import io.diffblue.corebanking.transaction.Transaction;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;

class AccountDiffblueTest {
  /**
   * Methods under test:
   * 
   * <ul>
   *   <li>{@link Account.AccountStatement#AccountStatement(Account)}
   *   <li>{@link Account.AccountStatement#getTransactions()}
   * </ul>
   */
  @Test
  void testAccountStatementGettersAndSetters() {
    // Arrange, Act and Assert
    assertTrue(((new Account(1234567890L, new Client("Dr Jane Doe"), 10L)).new AccountStatement()).getTransactions()
        .isEmpty());
  }

  /**
   * Method under test: {@link Account.AccountStatement#toString()}
   */
  @Test
  void testAccountStatementToString() {
    // Arrange, Act and Assert
    assertEquals("Account statement empty.",
        ((new Account(1234567890L, new Client("Dr Jane Doe"), 10L)).new AccountStatement()).toString());
  }

  /**
   * Method under test: {@link Account#addToBalance(long)}
   */
  @Test
  void testAddToBalance() throws AccountException {
    // Arrange
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act
    account.addToBalance(10L);

    // Assert
    assertEquals(20L, account.getCurrentBalance());
  }

  /**
   * Method under test: {@link Account#takeFromBalance(long)}
   */
  @Test
  void testTakeFromBalance() throws AccountException {
    // Arrange
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act
    account.takeFromBalance(10L);

    // Assert
    assertEquals(0L, account.getCurrentBalance());
  }

  /**
   * Method under test: {@link Account#takeFromBalance(long)}
   */
  @Test
  void testTakeFromBalance2() throws AccountException {
    // Arrange, Act and Assert
    assertThrows(AccountException.class,
        () -> (new Account(1234567890L, new Client("Dr Jane Doe"), 1L)).takeFromBalance(10L));
  }

  /**
   * Method under test: {@link Account#setAccountName(String)}
   */
  @Test
  void testSetAccountName() throws AccountException {
    // Arrange
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act
    account.setAccountName("Dr Jane Doe");

    // Assert
    assertEquals("Dr Jane Doe", account.getAccountName());
  }

  /**
   * Method under test: {@link Account#closeAccount()}
   */
  @Test
  void testCloseAccount() throws AccountException {
    // Arrange
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act
    account.closeAccount();

    // Assert
    assertEquals(Account.AccountState.CLOSED, account.getAccountState());
  }

  /**
   * Method under test: {@link Account#addTransaction(Transaction)}
   */
  @Test
  void testAddTransaction() throws AccountException {
    // Arrange
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);
    Date date = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    account.addTransaction(new CashTransaction(10L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L)));

    // Assert
    assertEquals(1, account.getAccountStatement().getTransactions().size());
  }

  /**
   * Method under test: {@link Account#addTransaction(Transaction)}
   */
  @Test
  void testAddTransaction2() throws AccountException {
    // Arrange
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);
    java.sql.Date date = mock(java.sql.Date.class);

    // Act
    account.addTransaction(new CashTransaction(10L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L)));

    // Assert
    assertEquals(1, account.getAccountStatement().getTransactions().size());
  }

  /**
   * Method under test: {@link Account#equals(Object)}
   */
  @Test
  void testEquals() {
    // Arrange, Act and Assert
    assertNotEquals(new Account(1234567890L, new Client("Dr Jane Doe"), 10L), null);
    assertNotEquals(new Account(1234567890L, new Client("Dr Jane Doe"), 10L), "Different type to Account");
  }

  /**
   * Method under test: {@link Account#equals(Object)}
   */
  @Test
  void testEquals2() {
    // Arrange
    Account account = new Account(3L, new Client("Dr Jane Doe"), 10L);

    // Act and Assert
    assertNotEquals(account, new Account(1234567890L, new Client("Dr Jane Doe"), 10L));
  }

  /**
   * Method under test: {@link Account#equals(Object)}
   */
  @Test
  void testEqualsAndHashCode() {
    // Arrange
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act and Assert
    assertEquals(account, account);
    int expectedHashCodeResult = account.hashCode();
    assertEquals(expectedHashCodeResult, account.hashCode());
  }

  /**
   * Method under test: {@link Account#equals(Object)}
   */
  @Test
  void testEqualsAndHashCode2() {
    // Arrange
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);
    Account account2 = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act and Assert
    assertEquals(account, account2);
    int notExpectedHashCodeResult = account.hashCode();
    assertNotEquals(notExpectedHashCodeResult, account2.hashCode());
  }

  /**
   * Method under test: {@link Account#equals(Object)}
   */
  @Test
  void testEqualsAndHashCode3() {
    // Arrange
    Account account = new Account(1234567890L, mock(Client.class), 10L);
    Account account2 = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act and Assert
    assertEquals(account, account2);
    int notExpectedHashCodeResult = account.hashCode();
    assertNotEquals(notExpectedHashCodeResult, account2.hashCode());
  }

  /**
   * Methods under test:
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
  void testGettersAndSetters() {
    // Arrange
    Client client = new Client("Dr Jane Doe");
    Account account = new Account(1234567890L, client, 10L);

    // Act
    String actualToStringResult = account.toString();
    String actualAccountName = account.getAccountName();
    long actualAccountNumber = account.getAccountNumber();
    Account.AccountState actualAccountState = account.getAccountState();
    account.getAccountStatement();
    Client actualClient = account.getClient();

    // Assert
    assertEquals(
        "Account: | Acc #: 1234567890\t | Acc name: Current\t | Acc holder: Dr Jane Doe\t | Acc balance: 10\t | Acc"
            + " state: OPEN\t |\n" + "Account statement empty.",
        actualToStringResult);
    assertEquals("Current", actualAccountName);
    assertEquals(10L, account.getCurrentBalance());
    assertEquals(1234567890L, actualAccountNumber);
    assertEquals(Account.AccountState.OPEN, actualAccountState);
    assertSame(client, actualClient);
  }

  /**
   * Method under test: {@link Account#Account(long, Client, long)}
   */
  @Test
  void testNewAccount() {
    // Arrange
    Client client = new Client("Dr Jane Doe");

    // Act
    Account actualAccount = new Account(1234567890L, client, 10L);

    // Assert
    assertEquals("Current", actualAccount.getAccountName());
    assertEquals(10L, actualAccount.getCurrentBalance());
    assertEquals(1234567890L, actualAccount.getAccountNumber());
    assertEquals(Account.AccountState.OPEN, actualAccount.getAccountState());
    Client client2 = actualAccount.getClient();
    List<Account> expectedTransactions = client2.getAccounts();
    assertEquals(expectedTransactions, actualAccount.getAccountStatement().getTransactions());
    assertSame(client, client2);
  }
}
