package io.diffblue.corebanking.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.transaction.CashTransaction;
import io.diffblue.corebanking.transaction.Transaction;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AccountDiffblueTest {
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  /**
  * Methods under test: 
  * 
  * <ul>
  *   <li>{@link Account#Account(long, Client, long)}
  *   <li>{@link Account#toString()}
  *   <li>{@link Account#getAccountName()}
  *   <li>{@link Account#getAccountNumber()}
  *   <li>{@link Account#getAccountState()}
  *   <li>{@link Account#getClient()}
  *   <li>{@link Account#getCurrentBalance()}
  * </ul>
  */
  @Test
  public void testConstructor() {
    // Arrange
    Client client = new Client("Dr Jane Doe");

    // Act
    Account actualAccount = new Account(1234567890L, client, 10L);
    String actualToStringResult = actualAccount.toString();

    // Assert
    assertEquals("Current", actualAccount.getAccountName());
    assertEquals(1234567890L, actualAccount.getAccountNumber());
    assertEquals(Account.AccountState.OPEN, actualAccount.getAccountState());
    assertSame(client, actualAccount.getClient());
    assertEquals(10L, actualAccount.getCurrentBalance());
    assertEquals(
        "Account: | Acc #: 1234567890\t | Acc name: Current\t | Acc holder: Dr Jane Doe\t | Acc balance: 10\t | Acc"
            + " state: OPEN\t |\n" + "Account statement empty.",
        actualToStringResult);
  }

  /**
   * Method under test: {@link Account#Account(long, Client, long)}
   */
  @Test
  public void testConstructor2() {
    // Arrange
    Client client = new Client("Dr Jane Doe");

    // Act
    Account actualAccount = new Account(1234567890L, client, 10L);

    // Assert
    assertEquals("Current", actualAccount.getAccountName());
    assertEquals(10L, actualAccount.getCurrentBalance());
    Client client1 = actualAccount.getClient();
    assertSame(client, client1);
    assertEquals(1234567890L, actualAccount.getAccountNumber());
    assertEquals(Account.AccountState.OPEN, actualAccount.getAccountState());
    List<Account> expectedTransactions = client1.getAccounts();
    assertEquals(expectedTransactions, actualAccount.getAccountStatement().getTransactions());
  }

  /**
   * Method under test: {@link Account#addToBalance(long)}
   */
  @Test
  public void testAddToBalance() throws AccountException {
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
  public void testTakeFromBalance() throws AccountException {
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
  public void testTakeFromBalance2() throws AccountException {
    // Arrange, Act and Assert
    thrown.expect(AccountException.class);
    (new Account(1234567890L, new Client("Dr Jane Doe"), Long.MAX_VALUE)).takeFromBalance(10L);
  }

  /**
   * Method under test: {@link Account#setAccountName(String)}
   */
  @Test
  public void testSetAccountName() throws AccountException {
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
  public void testCloseAccount() throws AccountException {
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
  public void testAddTransaction() throws AccountException {
    // Arrange
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    Date date = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());

    // Act
    account.addTransaction(new CashTransaction(10L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L)));

    // Assert
    assertEquals(1, account.getAccountStatement().getTransactions().size());
  }

  /**
   * Method under test: {@link Account#equals(Object)}
   */
  @Test
  public void testEquals() {
    // Arrange
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act and Assert
    assertEquals(account, account);
    int expectedHashCodeResult = account.hashCode();
    assertEquals(expectedHashCodeResult, account.hashCode());
  }
}

