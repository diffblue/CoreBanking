package io.diffblue.corebanking.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ClientDiffblueTest {
  /**
   * Methods under test:
   * 
   * <ul>
   *   <li>{@link Client#Client(String)}
   *   <li>{@link Client#getAccounts()}
   *   <li>{@link Client#getClientName()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange and Act
    Client actualClient = new Client("Dr Jane Doe");
    actualClient.getAccounts();

    // Assert
    assertEquals("Dr Jane Doe", actualClient.getClientName());
  }
}
