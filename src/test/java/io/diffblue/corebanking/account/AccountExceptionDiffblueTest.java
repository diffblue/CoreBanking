package io.diffblue.corebanking.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class AccountExceptionDiffblueTest {
  @Test
  public void constructorTest() {
    // Arrange and Act
    AccountException actualAccountException = new AccountException("An error occurred");

    // Assert
    assertEquals("io.diffblue.corebanking.account.AccountException:" + " An error occurred",
        actualAccountException.toString());
    assertEquals("An error occurred", actualAccountException.getLocalizedMessage());
    assertNull(actualAccountException.getCause());
    assertEquals("An error occurred", actualAccountException.getMessage());
    assertEquals(0, actualAccountException.getSuppressed().length);
  }
}

