package io.diffblue.corebanking.datamanagement;

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
import io.diffblue.corebanking.account.AccountException;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.transaction.Transaction;
import io.diffblue.corebanking.transaction.TransactionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ReadFromDBDiffblueTest {
  /**
   * Test {@link ReadFromDB#readFromDB()}.
   *
   * <p>Method under test: {@link ReadFromDB#readFromDB()}
   */
  @Test
  @DisplayName("Test readFromDB()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"CoreBanking ReadFromDB.readFromDB()"})
  void testReadFromDB() throws TransactionException {
    // Arrange and Act
    CoreBanking actualReadFromDBResult = ReadFromDB.readFromDB();

    // Assert
    assertEquals(3, actualReadFromDBResult.getClients().size());
    assertEquals(6, actualReadFromDBResult.getAccounts().size());
  }

  /**
   * Test {@link ReadFromDB#readFromDB(CoreBanking)} with {@code CoreBanking}.
   *
   * <p>Method under test: {@link ReadFromDB#readFromDB(CoreBanking)}
   */
  @Test
  @DisplayName("Test readFromDB(CoreBanking) with 'CoreBanking'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ReadFromDB.readFromDB(CoreBanking)"})
  void testReadFromDBWithCoreBanking() throws TransactionException {
    // Arrange
    CoreBanking coreBanking = mock(CoreBanking.class);
    when(coreBanking.openNewAccount(Mockito.<Client>any(), anyLong()))
        .thenReturn(new Account(1234567890L, new Client("Dr Jane Doe"), 10L));
    when(coreBanking.registerNewClient(Mockito.<Client>any()))
        .thenReturn(new Client("Dr Jane Doe"));
    doNothing().when(coreBanking).purgeCoreBanking();

    // Act
    ReadFromDB.readFromDB(coreBanking);

    // Assert
    verify(coreBanking, atLeast(1)).openNewAccount(Mockito.<Client>any(), eq(100L));
    verify(coreBanking).purgeCoreBanking();
    verify(coreBanking, atLeast(1)).registerNewClient(Mockito.<Client>any());
  }

  /**
   * Test {@link ReadFromDB#readFromDB(CoreBanking)} with {@code CoreBanking}.
   *
   * <p>Method under test: {@link ReadFromDB#readFromDB(CoreBanking)}
   */
  @Test
  @DisplayName("Test readFromDB(CoreBanking) with 'CoreBanking'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ReadFromDB.readFromDB(CoreBanking)"})
  void testReadFromDBWithCoreBanking2() throws TransactionException {
    // Arrange
    CoreBanking coreBanking = mock(CoreBanking.class);
    when(coreBanking.openNewAccount(Mockito.<Client>any(), anyLong()))
        .thenReturn(new Account(1234567890L, new Client("Dr Jane Doe"), Long.MAX_VALUE));
    when(coreBanking.registerNewClient(Mockito.<Client>any()))
        .thenReturn(new Client("Dr Jane Doe"));
    doNothing().when(coreBanking).purgeCoreBanking();

    // Act
    ReadFromDB.readFromDB(coreBanking);

    // Assert
    verify(coreBanking, atLeast(1)).openNewAccount(Mockito.<Client>any(), eq(100L));
    verify(coreBanking).purgeCoreBanking();
    verify(coreBanking, atLeast(1)).registerNewClient(Mockito.<Client>any());
  }

  /**
   * Test {@link ReadFromDB#readFromDB(CoreBanking)} with {@code CoreBanking}.
   *
   * <ul>
   *   <li>Then calls {@link Account#addToBalance(long)}.
   * </ul>
   *
   * <p>Method under test: {@link ReadFromDB#readFromDB(CoreBanking)}
   */
  @Test
  @DisplayName("Test readFromDB(CoreBanking) with 'CoreBanking'; then calls addToBalance(long)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ReadFromDB.readFromDB(CoreBanking)"})
  void testReadFromDBWithCoreBanking_thenCallsAddToBalance()
      throws AccountException, TransactionException {
    // Arrange
    Account account = mock(Account.class);
    when(account.getCurrentBalance()).thenReturn(42L);
    doNothing().when(account).addToBalance(anyLong());
    doNothing().when(account).addTransaction(Mockito.<Transaction>any());
    doNothing().when(account).takeFromBalance(anyLong());
    when(account.getAccountState()).thenReturn(AccountState.OPEN);
    CoreBanking coreBanking = mock(CoreBanking.class);
    when(coreBanking.openNewAccount(Mockito.<Client>any(), anyLong())).thenReturn(account);
    when(coreBanking.registerNewClient(Mockito.<Client>any()))
        .thenReturn(new Client("Dr Jane Doe"));
    doNothing().when(coreBanking).purgeCoreBanking();

    // Act
    ReadFromDB.readFromDB(coreBanking);

    // Assert
    verify(coreBanking, atLeast(1)).openNewAccount(Mockito.<Client>any(), eq(100L));
    verify(coreBanking).purgeCoreBanking();
    verify(coreBanking, atLeast(1)).registerNewClient(Mockito.<Client>any());
    verify(account, atLeast(1)).addToBalance(100L);
    verify(account, atLeast(1)).addTransaction(Mockito.<Transaction>any());
    verify(account, atLeast(1)).getAccountState();
    verify(account, atLeast(1)).getCurrentBalance();
    verify(account).takeFromBalance(-500L);
  }
}
