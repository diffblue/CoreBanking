package io.diffblue.corebanking.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class AccountExceptionDiffblueTest {
  /**
   * Method under test: {@link AccountException#AccountException(String)}
   */
  @Test
  void testNewAccountException() {
    // Arrange and Act
    AccountException actualAccountException = new AccountException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualAccountException.getLocalizedMessage());
    assertEquals("An error occurred", actualAccountException.getMessage());
    assertNull(actualAccountException.getCause());
    assertEquals(0, actualAccountException.getSuppressed().length);
  }
}
