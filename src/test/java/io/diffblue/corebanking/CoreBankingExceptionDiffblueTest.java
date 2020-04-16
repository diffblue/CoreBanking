package io.diffblue.corebanking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class CoreBankingExceptionDiffblueTest {
  @Test
  public void testConstructor() {
    // Arrange and Act
    CoreBankingException actualCoreBankingException = new CoreBankingException("An error occurred");

    // Assert
    assertEquals("io.diffblue.corebanking.CoreBankingException: An" + " error occurred",
        actualCoreBankingException.toString());
    assertEquals("An error occurred", actualCoreBankingException.getLocalizedMessage());
    assertNull(actualCoreBankingException.getCause());
    assertEquals("An error occurred", actualCoreBankingException.getMessage());
    assertEquals(0, actualCoreBankingException.getSuppressed().length);
  }
}

