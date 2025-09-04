package io.diffblue.corebanking.ui.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.diffblue.corebanking.CoreBanking;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.account.Account.AccountState;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.datamanagement.ReadFromDB;
import io.diffblue.corebanking.transaction.TransactionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CoreBankingDataManagementMenuDiffblueTest {
  /**
   * Test {@link CoreBankingDataManagementMenu#CoreBankingDataManagementMenu(CoreBanking)}.
   *
   * <p>Method under test: {@link
   * CoreBankingDataManagementMenu#CoreBankingDataManagementMenu(CoreBanking)}
   */
  @Test
  @DisplayName("Test new CoreBankingDataManagementMenu(CoreBanking)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CoreBankingDataManagementMenu.<init>(CoreBanking)"})
  void testNewCoreBankingDataManagementMenu() throws TransactionException {
    // Arrange, Act and Assert
    CoreBanking coreBanking =
        new CoreBankingDataManagementMenu(ReadFromDB.readFromDB()).coreBanking;
    assertEquals(3, coreBanking.getClients().size());
    assertEquals(6, coreBanking.getAccounts().size());
  }

  /**
   * Test {@link CoreBankingDataManagementMenu#executeMenuOption(int)}.
   *
   * <p>Method under test: {@link CoreBankingDataManagementMenu#executeMenuOption(int)}
   */
  @Test
  @DisplayName("Test executeMenuOption(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CoreBankingDataManagementMenu.executeMenuOption(int)"})
  void testExecuteMenuOption() {
    // Arrange
    CoreBanking coreBanking = mock(CoreBanking.class);
    when(coreBanking.openNewAccount(Mockito.<Client>any(), anyLong()))
        .thenReturn(new Account(1234567890L, new Client("Dr Jane Doe"), 10L));
    when(coreBanking.registerNewClient(Mockito.<Client>any()))
        .thenReturn(new Client("Dr Jane Doe"));
    doNothing().when(coreBanking).purgeCoreBanking();

    // Act
    new CoreBankingDataManagementMenu(coreBanking).executeMenuOption(2);

    // Assert
    verify(coreBanking, atLeast(1)).openNewAccount(Mockito.<Client>any(), eq(100L));
    verify(coreBanking).purgeCoreBanking();
    verify(coreBanking, atLeast(1)).registerNewClient(Mockito.<Client>any());
  }

  /**
   * Test {@link CoreBankingDataManagementMenu#executeMenuOption(int)}.
   *
   * <p>Method under test: {@link CoreBankingDataManagementMenu#executeMenuOption(int)}
   */
  @Test
  @DisplayName("Test executeMenuOption(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CoreBankingDataManagementMenu.executeMenuOption(int)"})
  void testExecuteMenuOption2() {
    // Arrange
    CoreBanking coreBanking = mock(CoreBanking.class);
    when(coreBanking.openNewAccount(Mockito.<Client>any(), anyLong()))
        .thenReturn(new Account(1234567890L, new Client("Dr Jane Doe"), Long.MAX_VALUE));
    when(coreBanking.registerNewClient(Mockito.<Client>any()))
        .thenReturn(new Client("Dr Jane Doe"));
    doNothing().when(coreBanking).purgeCoreBanking();

    // Act
    new CoreBankingDataManagementMenu(coreBanking).executeMenuOption(2);

    // Assert
    verify(coreBanking, atLeast(1)).openNewAccount(Mockito.<Client>any(), eq(100L));
    verify(coreBanking).purgeCoreBanking();
    verify(coreBanking, atLeast(1)).registerNewClient(Mockito.<Client>any());
  }

  /**
   * Test {@link CoreBankingDataManagementMenu#executeMenuOption(int)}.
   *
   * <ul>
   *   <li>Then calls {@link Account#getAccountState()}.
   * </ul>
   *
   * <p>Method under test: {@link CoreBankingDataManagementMenu#executeMenuOption(int)}
   */
  @Test
  @DisplayName("Test executeMenuOption(int); then calls getAccountState()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CoreBankingDataManagementMenu.executeMenuOption(int)"})
  void testExecuteMenuOption_thenCallsGetAccountState() {
    // Arrange
    Account account = mock(Account.class);
    when(account.getAccountState()).thenReturn(AccountState.CLOSED);
    CoreBanking coreBanking = mock(CoreBanking.class);
    when(coreBanking.openNewAccount(Mockito.<Client>any(), anyLong())).thenReturn(account);
    when(coreBanking.registerNewClient(Mockito.<Client>any()))
        .thenReturn(new Client("Dr Jane Doe"));
    doNothing().when(coreBanking).purgeCoreBanking();

    // Act
    new CoreBankingDataManagementMenu(coreBanking).executeMenuOption(2);

    // Assert
    verify(coreBanking, atLeast(1)).openNewAccount(Mockito.<Client>any(), eq(100L));
    verify(coreBanking).purgeCoreBanking();
    verify(coreBanking, atLeast(1)).registerNewClient(Mockito.<Client>any());
    verify(account).getAccountState();
  }
}
