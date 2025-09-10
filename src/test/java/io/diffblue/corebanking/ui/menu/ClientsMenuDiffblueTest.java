package io.diffblue.corebanking.ui.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.diffblue.corebanking.CoreBanking;
import io.diffblue.corebanking.datamanagement.ReadFromDB;
import io.diffblue.corebanking.transaction.TransactionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ClientsMenuDiffblueTest {
  /**
   * Test {@link ClientsMenu#ClientsMenu(CoreBanking)}.
   *
   * <p>Method under test: {@link ClientsMenu#ClientsMenu(CoreBanking)}
   */
  @Test
  @DisplayName("Test new ClientsMenu(CoreBanking)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClientsMenu.<init>(CoreBanking)"})
  void testNewClientsMenu() throws TransactionException {
    // Arrange, Act and Assert
    CoreBanking coreBanking = new ClientsMenu(ReadFromDB.readFromDB()).coreBanking;
    assertEquals(3, coreBanking.getClients().size());
    assertEquals(6, coreBanking.getAccounts().size());
  }
}
