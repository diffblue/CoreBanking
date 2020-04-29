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
}

