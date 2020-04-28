package io.diffblue.corebanking.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.transaction.BankTransaction;
import io.diffblue.corebanking.transaction.CashTransaction;
import java.util.Date;
import org.junit.Test;

public class AccountDiffblueTest {

  @Test
  public void testAddToBalance() throws AccountException {
    // Arrange
    Account account = new Account(1234567890L, new Client(""), 10L);

    // Act
    account.addToBalance(10L);

    // Assert
    assertEquals(20L, account.getCurrentBalance());
  }

  @Test
  public void testTakeFromBalance() throws AccountException {
    // Arrange
    Account account = new Account(1234567890L, new Client(""), 10L);

    // Act
    account.takeFromBalance(10L);

    // Assert
    assertEquals(0L, account.getCurrentBalance());
  }

  @Test
  public void testSetAccountName() throws AccountException {
    // Arrange
    Account account = new Account(1234567890L, new Client(""), 10L);

    // Act
    account.setAccountName("Mr John Doe");

    // Assert
    assertEquals("Mr John Doe", account.getAccountName());
  }

  @Test
  public void testCloseAccount() throws AccountException {
    // Arrange
    Account account = new Account(1234567890L, new Client(""), 10L);

    // Act
    account.closeAccount();

    // Assert
    assertEquals(Account.AccountState.CLOSED, account.getAccountState());
  }

  @Test
  public void testAddTransaction() throws AccountException {
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
}

