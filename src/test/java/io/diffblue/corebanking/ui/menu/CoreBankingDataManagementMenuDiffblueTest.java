package io.diffblue.corebanking.ui.menu;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import io.diffblue.corebanking.CoreBanking;
import org.junit.jupiter.api.Test;

public class CoreBankingDataManagementMenuDiffblueTest {
  @Test
  public void testCoreBankingDataManagementMenuConstructor() throws Exception {
    CoreBanking coreBanking = new CoreBanking();
    CoreBankingDataManagementMenu menu = new CoreBankingDataManagementMenu(coreBanking);
    assertNotNull(menu);
  }
}
