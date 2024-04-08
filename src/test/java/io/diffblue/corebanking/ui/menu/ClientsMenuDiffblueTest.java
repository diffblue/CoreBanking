package io.diffblue.corebanking.ui.menu;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.diffblue.corebanking.CoreBanking;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class ClientsMenuDiffblueTest {
  /**
   * Method under test: {@link ClientsMenu#executeMenuOption(int)}
   */
  @Test
  void testExecuteMenuOption() {
    // Arrange
    CoreBanking coreBanking = mock(CoreBanking.class);
    when(coreBanking.getClients()).thenReturn(new ArrayList<>());

    // Act
    (new ClientsMenu(coreBanking)).executeMenuOption(1);

    // Assert
    verify(coreBanking).getClients();
  }

  /**
   * Method under test: {@link ClientsMenu#ClientsMenu(CoreBanking)}
   */
  @Test
  void testNewClientsMenu() {
    // Arrange
    CoreBanking coreBanking = new CoreBanking();

    // Act and Assert
    assertSame((new ClientsMenu(coreBanking)).coreBanking, coreBanking);
  }
}
