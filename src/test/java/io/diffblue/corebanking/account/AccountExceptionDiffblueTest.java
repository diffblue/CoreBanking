package io.diffblue.corebanking.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class AccountExceptionDiffblueTest {
  /**
   * Test {@link AccountException#AccountException(String)}.
   *
   * <p>Method under test: {@link AccountException#AccountException(String)}
   */
  @Test
  @DisplayName("Test new AccountException(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccountException.<init>(String)"})
  void testNewAccountException() {
    // Arrange and Act
    AccountException actualAccountException = new AccountException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualAccountException.getMessage());
    assertNull(actualAccountException.getCause());
    assertEquals(0, actualAccountException.getSuppressed().length);
  }
}
