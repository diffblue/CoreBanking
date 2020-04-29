package io.diffblue.corebanking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import org.junit.Test;

public class CoreBankingDiffblueTest {

  @Test
  public void testOpenNewAccount() {
    // Arrange
    CoreBanking coreBanking = new CoreBanking();
    Client client = new Client("");

    // Act
    Account actualOpenNewAccountResult = coreBanking.openNewAccount(client, 10L);

    // Assert
    assertEquals("Current", actualOpenNewAccountResult.getAccountName());
    assertEquals(Account.AccountState.OPEN, actualOpenNewAccountResult.getAccountState());
    assertSame(client, actualOpenNewAccountResult.getClient());
    assertEquals(10L, actualOpenNewAccountResult.getCurrentBalance());
  }

  @Test
  public void testRegisterNewClient() {
    // Arrange
    Client client = new Client("");
    CoreBanking coreBanking = new CoreBanking();

    // Act and Assert
    assertSame(client, coreBanking.registerNewClient(client));
  }
}

