package io.diffblue.corebanking.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.diffblue.corebanking.client.Client;
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
    assertTrue(
        ((new Account(1000L, new Client("John Doe"), 1234L)).new AccountStatement()).getTransactions().isEmpty());
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
    Client client = new Client("John Doe");
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
        "Account: | Acc #: 1234567890\t | Acc name: Current\t | Acc holder: John Doe\t | Acc balance: 10\t | Acc"
            + " state: OPEN\t |\n" + "Account statement empty.",
        actualToStringResult);
    assertEquals("Current", actualAccountName);
    assertEquals(10L, account.getCurrentBalance());
    assertEquals(1234567890L, actualAccountNumber);
    assertEquals(Account.AccountState.OPEN, actualAccountState);
    assertSame(client, actualClient);
  }
}
