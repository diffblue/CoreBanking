package io.diffblue.corebanking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class CoreBankingExceptionDiffblueTest {
  /**
  * Method under test: {@link CoreBankingException#CoreBankingException(String)}
  */
  @Test
  void testConstructor() {
    // Arrange and Act
    CoreBankingException actualCoreBankingException = new CoreBankingException("An error occurred");

    // Assert
    assertNull(actualCoreBankingException.getCause());
    assertEquals(0, actualCoreBankingException.getSuppressed().length);
    assertEquals("An error occurred", actualCoreBankingException.getMessage());
    assertEquals("An error occurred", actualCoreBankingException.getLocalizedMessage());
  }
}

