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

class AccountsMenuDiffblueTest {
  /**
   * Test {@link AccountsMenu#AccountsMenu(CoreBanking)}.
   *
   * <p>Method under test: {@link AccountsMenu#AccountsMenu(CoreBanking)}
   */
  @Test
  @DisplayName("Test new AccountsMenu(CoreBanking)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccountsMenu.<init>(CoreBanking)"})
  void testNewAccountsMenu() throws TransactionException {
    // Arrange, Act and Assert
    CoreBanking coreBanking = new AccountsMenu(ReadFromDB.readFromDB()).coreBanking;
    assertEquals(3, coreBanking.getClients().size());
    assertEquals(6, coreBanking.getAccounts().size());
  }
}
