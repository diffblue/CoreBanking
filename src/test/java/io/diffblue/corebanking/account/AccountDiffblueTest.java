package io.diffblue.corebanking.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.transaction.CashTransaction;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class AccountDiffblueTest {
  @Test
  public void addToBalanceTest() throws AccountException {
    // Arrange
    Account account = new Account(1234567890L, new Client(""), 10L);

    // Act
    account.addToBalance(10L);

    // Assert
    assertEquals(20L, account.getCurrentBalance());
  }

  @Test
  public void addTransactionTest() throws AccountException {
    // Arrange
    Account account = new Account(1234567890L, new Client(""), 10L);
    Date date = new Date(1L);

    // Act
    account.addTransaction(new CashTransaction(10L, date, new Account(1234567890L, new Client(""), 10L)));

    // Assert
    assertEquals("Transaction: | 70.01.01\t| Source: CASH\t| Target:"
        + " 1234567890\t| Amount: 10\t| Balance: 0\t| Transaction" + " state: NOT_EXECUTED_YET\t|\n",
        account.getAccountStatement().toString());
  }

  @Test
  public void closeAccountTest() throws AccountException {
    // Arrange
    Account account = new Account(1234567890L, new Client(""), 10L);

    // Act
    account.closeAccount();

    // Assert
    assertEquals(Account.AccountState.CLOSED, account.getAccountState());
  }

  @Test
  public void constructorTest() {
    // Arrange and Act
    Account actualAccount = new Account(1234567890L, new Client(""), 10L);

    // Assert
    assertEquals("Account: | Acc #: 1234567890\t | Acc name: Current"
        + "\t | Acc holder: \t | Acc balance: 10\t | Acc state:" + " OPEN\t |\n" + "Account statement empty.",
        actualAccount.toString());
    assertEquals(1234567890L, actualAccount.getAccountNumber());
    assertEquals("Current", actualAccount.getAccountName());
    assertEquals(Account.AccountState.OPEN, actualAccount.getAccountState());
    assertEquals(10L, actualAccount.getCurrentBalance());
    assertEquals("Account statement empty.", actualAccount.getAccountStatement().toString());
  }

  @Test
  public void equalsTest() {
    // Arrange, Act and Assert
    assertFalse((new Account(1234567890L, new Client(""), 10L)).equals("Current"));
  }

  @Test
  public void setAccountNameTest() throws AccountException {
    // Arrange
    Account account = new Account(1234567890L, new Client(""), 10L);

    // Act
    account.setAccountName("Mr John Doe");

    // Assert
    assertEquals("Mr John Doe", account.getAccountName());
  }

  @Test
  public void takeFromBalanceTest() throws AccountException {
    // Arrange
    Account account = new Account(1234567890L, new Client(""), 10L);

    // Act
    account.takeFromBalance(10L);

    // Assert
    assertEquals(0L, account.getCurrentBalance());
  }

  @Test
  public void toStringTest() {
    // Arrange, Act and Assert
    assertEquals("Account: | Acc #: 1234567890\t | Acc name: Current"
        + "\t | Acc holder: \t | Acc balance: 10\t | Acc state:" + " OPEN\t |\n" + "Account statement empty.",
        (new Account(1234567890L, new Client(""), 10L)).toString());
  }
}

