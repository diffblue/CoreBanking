package io.diffblue.corebanking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class CoreBankingExceptionDiffblueTest {
  /**
   * Method under test: {@link CoreBankingException#CoreBankingException(String)}
   */
  @Test
  void testNewCoreBankingException() {
    // Arrange and Act
    CoreBankingException actualCoreBankingException = new CoreBankingException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualCoreBankingException.getLocalizedMessage());
    assertEquals("An error occurred", actualCoreBankingException.getMessage());
    assertNull(actualCoreBankingException.getCause());
    assertEquals(0, actualCoreBankingException.getSuppressed().length);
  }
}
