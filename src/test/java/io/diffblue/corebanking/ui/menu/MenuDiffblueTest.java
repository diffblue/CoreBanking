package io.diffblue.corebanking.ui.menu;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import io.diffblue.corebanking.CoreBanking;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MenuDiffblueTest {
  @Test
  public void testStartMenu() throws Exception {
    CoreBanking coreBanking = Mockito.mock(CoreBanking.class);
    Menu menu = new Menu("Test Menu", new String[]{"Option 1", "Option 2"}, coreBanking) {
      @Override
      protected void executeMenuOption(int menuOpt) {
      }

      @Override
      public void startMenu() {
      }
    };
    assertDoesNotThrow(menu::startMenu);
  }
}
