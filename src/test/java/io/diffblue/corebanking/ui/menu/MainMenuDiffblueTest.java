package io.diffblue.corebanking.ui.menu;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import io.diffblue.corebanking.CoreBanking;
import org.junit.jupiter.api.Test;

public class MainMenuDiffblueTest {
  @Test
  public void testMainMenuConstructor() throws Exception {
    CoreBanking coreBanking = new CoreBanking();
    MainMenu mainMenu = new MainMenu(coreBanking);
    assertNotNull(mainMenu);
  }
}
