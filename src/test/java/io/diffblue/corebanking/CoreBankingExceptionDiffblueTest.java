package io.diffblue.corebanking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class CoreBankingExceptionDiffblueTest {
  /**
  * Method under test: {@link CoreBankingException#CoreBankingException(String)}
  */
  @Test
  public void testConstructor() {
    // Arrange and Act
    CoreBankingException actualCoreBankingException = new CoreBankingException("An error occurred");

    // Assert
    assertNull(actualCoreBankingException.getCause());
    assertEquals(0, actualCoreBankingException.getSuppressed().length);
    assertEquals("An error occurred", actualCoreBankingException.getMessage());
    assertEquals("An error occurred", actualCoreBankingException.getLocalizedMessage());
  }
}

