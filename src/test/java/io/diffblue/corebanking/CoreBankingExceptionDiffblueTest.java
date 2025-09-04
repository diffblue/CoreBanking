package io.diffblue.corebanking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CoreBankingExceptionDiffblueTest {
  /**
   * Test {@link CoreBankingException#CoreBankingException(String)}.
   *
   * <p>Method under test: {@link CoreBankingException#CoreBankingException(String)}
   */
  @Test
  @DisplayName("Test new CoreBankingException(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CoreBankingException.<init>(String)"})
  void testNewCoreBankingException() {
    // Arrange and Act
    CoreBankingException actualCoreBankingException = new CoreBankingException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualCoreBankingException.getMessage());
    assertNull(actualCoreBankingException.getCause());
    assertEquals(0, actualCoreBankingException.getSuppressed().length);
  }
}
