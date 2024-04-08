package io.diffblue.corebanking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.diffblue.corebanking.account.Account;
import java.util.List;
import org.junit.jupiter.api.Test;

class CoreBankingDiffblueTest {
  /**
   * Methods under test:
   * 
   * <ul>
   *   <li>default or parameterless constructor of {@link CoreBanking}
   *   <li>{@link CoreBanking#getAccounts()}
   *   <li>{@link CoreBanking#getClients()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange and Act
    CoreBanking actualCoreBanking = new CoreBanking();
    List<Account> actualAccounts = actualCoreBanking.getAccounts();

    // Assert
    assertEquals(actualAccounts, actualCoreBanking.getClients());
  }

  /**
   * Method under test: {@link CoreBanking#purgeCoreBanking()}
   */
  @Test
  void testPurgeCoreBanking() {
    // Arrange
    CoreBanking coreBanking = new CoreBanking();

    // Act
    coreBanking.purgeCoreBanking();

    // Assert
    assertTrue(coreBanking.getAccounts().isEmpty());
    assertTrue(coreBanking.getClients().isEmpty());
  }

  /**
   * Method under test: {@link CoreBanking#toString()}
   */
  @Test
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("", (new CoreBanking()).toString());
  }
}
