package io.diffblue.corebanking.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.account.AccountException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ClientDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Client#Client(String)}
   *   <li>{@link Client#getAccounts()}
   *   <li>{@link Client#getClientName()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Client.<init>(String)",
    "List Client.getAccounts()",
    "String Client.getClientName()"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    Client actualClient = new Client("Dr Jane Doe");
    List<Account> actualAccounts = actualClient.getAccounts();

    // Assert
    assertEquals("Dr Jane Doe", actualClient.getClientName());
    assertTrue(actualAccounts.isEmpty());
  }

  /**
   * Test {@link Client#addAccount(Account)}.
   *
   * <p>Method under test: {@link Client#addAccount(Account)}
   */
  @Test
  @DisplayName("Test addAccount(Account)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Client.addAccount(Account)"})
  void testAddAccount() {
    // Arrange
    Client client = new Client("Dr Jane Doe");
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

    // Act
    client.addAccount(account);

    // Assert
    List<Account> accounts = client.getAccounts();
    assertEquals(1, accounts.size());
    assertSame(account, accounts.get(0));
  }

  /**
   * Test {@link Client#toString()}.
   *
   * <p>Method under test: {@link Client#toString()}
   */
  @Test
  @DisplayName("Test toString()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String Client.toString()"})
  void testToString() throws AccountException {
    // Arrange
    Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);
    account.addTransaction(null);

    Client client = new Client("Dr Jane Doe");
    client.addAccount(account);

    // Act and Assert
    assertEquals(
        "Client name: Dr Jane Doe\n"
            + "Account: | Acc #: 1234567890\t | Acc name: Current\t | Acc holder: Dr Jane Doe\t | Acc balance: 10\t | Acc"
            + " state: OPEN\t |\n"
            + "null\n"
            + "\n",
        client.toString());
  }

  /**
   * Test {@link Client#toString()}.
   *
   * <ul>
   *   <li>Given {@link Client#Client(String)} with clientName is {@code Dr Jane Doe}.
   *   <li>Then return {@code Client name: Dr Jane Doe}.
   * </ul>
   *
   * <p>Method under test: {@link Client#toString()}
   */
  @Test
  @DisplayName(
      "Test toString(); given Client(String) with clientName is 'Dr Jane Doe'; then return 'Client name: Dr Jane Doe'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String Client.toString()"})
  void testToString_givenClientWithClientNameIsDrJaneDoe_thenReturnClientNameDrJaneDoe() {
    // Arrange, Act and Assert
    assertEquals("Client name: Dr Jane Doe\n", new Client("Dr Jane Doe").toString());
  }

  /**
   * Test {@link Client#toString()}.
   *
   * <ul>
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link Client#toString()}
   */
  @Test
  @DisplayName("Test toString(); then return a string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String Client.toString()"})
  void testToString_thenReturnAString() {
    // Arrange
    Client client = new Client("Dr Jane Doe");
    client.addAccount(new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

    // Act and Assert
    assertEquals(
        "Client name: Dr Jane Doe\n"
            + "Account: | Acc #: 1234567890\t | Acc name: Current\t | Acc holder: Dr Jane Doe\t | Acc balance: 10\t | Acc"
            + " state: OPEN\t |\n"
            + "Account statement empty.\n",
        client.toString());
  }
}
