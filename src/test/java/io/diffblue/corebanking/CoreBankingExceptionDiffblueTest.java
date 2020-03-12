package io.diffblue.corebanking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class CoreBankingExceptionDiffblueTest {
  @Test
  public void constructorTest() {
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

