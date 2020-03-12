package io.diffblue.corebanking.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class BankTransactionDiffblueTest {
  @Test
  public void constructorTest() {
    // Arrange
    Date date = new Date(1L);

    // Act
    BankTransaction actualBankTransaction = new BankTransaction(10L, date,
        new Account(1234567890L, new Client(""), 10L), new Account(1234567890L, new Client(""), 10L));

    // Assert
    assertEquals(Transaction.TransactionState.NOT_EXECUTED_YET, actualBankTransaction.getTransactionState());
    assertEquals("1234567890", actualBankTransaction.getSource());
    assertEquals(
        "Transaction: | 70.01.01\t| Source: 1234567890\t| Target:"
            + " 1234567890\t| Amount: 10\t| Balance: 0\t| Transaction" + " state: NOT_EXECUTED_YET\t|",
        actualBankTransaction.toString());
    assertEquals("1234567890", actualBankTransaction.getTarget());
    assertEquals(10L, actualBankTransaction.getTransactionAmount());
  }

  @Test
  public void doBadTransactionTest() throws TransactionException {
    // Arrange
    Date date = new Date(1L);

    // Act and Assert
    assertThrows(TransactionException.class,
        () -> (new BankTransaction(10L, date, new Account(1234567890L, new Client(""), 10L),
            new Account(1234567890L, new Client(""), 10L))).doBadTransaction());
  }

  @Test
  public void executeTransactionTest() throws TransactionException {
    // Arrange
    Date date = new Date(1L);
    BankTransaction bankTransaction = new BankTransaction(10L, date, new Account(1234567890L, new Client(""), 10L),
        new Account(1234567890L, new Client(""), 10L));

    // Act
    bankTransaction.executeTransaction();

    // Assert
    assertEquals(Transaction.TransactionState.EXECUTED, bankTransaction.getTransactionState());
    assertEquals(
        "Transaction: | 70.01.01\t| Source: 1234567890\t| Target:"
            + " 1234567890\t| Amount: 10\t| Balance: 10\t| Transaction" + " state: EXECUTED\t|",
        bankTransaction.toString());
  }

  @Test
  public void getSourceTest() {
    // Arrange
    Date date = new Date(1L);

    // Act and Assert
    assertEquals("1234567890", (new BankTransaction(10L, date, new Account(1234567890L, new Client(""), 10L),
        new Account(1234567890L, new Client(""), 10L))).getSource());
  }

  @Test
  public void getTargetTest() {
    // Arrange
    Date date = new Date(1L);

    // Act and Assert
    assertEquals("1234567890", (new BankTransaction(10L, date, new Account(1234567890L, new Client(""), 10L),
        new Account(1234567890L, new Client(""), 10L))).getTarget());
  }
}

