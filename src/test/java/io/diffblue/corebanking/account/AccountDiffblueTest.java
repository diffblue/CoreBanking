package io.diffblue.corebanking.account;

import static org.junit.jupiter.api.Assertions.*;
import io.diffblue.corebanking.account.Account.AccountState;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.transaction.BankTransaction;
import io.diffblue.corebanking.transaction.Transaction;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AccountDiffblueTest {
  @Test
  void testAccountConstructor() throws Exception {
    Client client = new Client("Test Client");
    Account account = new Account(1234, client, 1000);
    assertEquals(1234, account.getAccountNumber());
    assertEquals(client, account.getClient());
    assertEquals(1000, account.getCurrentBalance());
  }

  @Test
  public void testAddToBalance() throws Exception {
    Client client = new Client("Test Client");
    Account account = new Account(1234, client, 1000);
    account.addToBalance(500);
    Assertions.assertEquals(1500, account.getCurrentBalance());
  }

  @Test
  public void testAddToBalanceThrowsExceptionWhenAccountClosed() throws Exception {
    Client client = new Client("Test Client");
    Account account = new Account(1234, client, 1000);
    account.closeAccount();
    Assertions.assertThrows(AccountException.class, () -> {
      account.addToBalance(500);
    });
  }

  @Test
  void takeFromBalance() throws Exception {
    Client client = new Client("Test Client");
    Account account = new Account(1234, client, 5000);
    account.takeFromBalance(1000);
    assertEquals(4000, account.getCurrentBalance());
    assertThrows(AccountException.class, () -> account.takeFromBalance(5000));
  }

  @Test
  void setAccountName() throws Exception {
    Client client = new Client("Test Client");
    Account account = new Account(1234, client, 1000);
    String newAccountName = "New Account Name";
    account.setAccountName(newAccountName);
    assertEquals(newAccountName, account.getAccountName());
  }

  @Test
  void closeAccountExceptionTest() throws Exception {
    Client client = new Client("Test Client");
    Account account = new Account(1234, client, 1000);
    account.closeAccount();
    assertThrows(AccountException.class, account::closeAccount);
  }

  @Test
  void closeAccountTest() throws Exception {
    Client client = new Client("Test Client");
    Account account = new Account(1234, client, 1000);
    assertEquals(Account.AccountState.OPEN, account.getAccountState());
    account.closeAccount();
    assertEquals(Account.AccountState.CLOSED, account.getAccountState());
  }

  @Test
  void addTransaction() throws Exception {
    Client client = new Client("Test Client");
    Account account = new Account(1234, client, 1000);
    Transaction transaction = new BankTransaction(500, new Date(), account, null);
    account.addTransaction(transaction);
    assertTrue(account.getAccountStatement().getTransactions().contains(transaction));
  }

  @Test
  void testEquals() throws Exception {
    Client client = new Client("John Doe");
    Account account1 = new Account(1234, client, 1000);
    Account account2 = new Account(1234, client, 1000);
    Account account3 = new Account(5678, client, 1000);
    assertTrue(account1.equals(account2));
    assertFalse(account1.equals(account3));
  }
}
