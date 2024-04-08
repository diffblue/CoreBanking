package io.diffblue.corebanking.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankTransactionDiffblueTest {
  @Test
  public void testBankTransactionConstructor() throws Exception {
    long amount = 1000L;
    Date date = new Date();
    Client client = new Client("John Doe");
    Account sourceAcc = new Account(1234L, client, 5000L);
    Account targetAcc = new Account(5678L, client, 2000L);
    BankTransaction bankTransaction = new BankTransaction(amount, date, sourceAcc, targetAcc);
    assertEquals(amount, bankTransaction.getTransactionAmount());
    assertEquals(date, bankTransaction.getTransactionDate());
    assertEquals(sourceAcc.getAccountNumber(), Long.parseLong(bankTransaction.getSource()));
    assertEquals(targetAcc.getAccountNumber(), Long.parseLong(bankTransaction.getTarget()));
  }

  @Test
  public void testGetSource() throws Exception {
    Client client = new Client("Test Client");
    Account sourceAccount = new Account(1234, client, 1000);
    Account targetAccount = new Account(5678, client, 2000);
    BankTransaction bankTransaction = new BankTransaction(500, new Date(), sourceAccount, targetAccount);
    String result = bankTransaction.getSource();
    Assertions.assertEquals("1234", result);
  }

  @Test
  public void testGetTarget() throws Exception {
    long accountNumber = 1234L;
    Client client = new Client("Test Client");
    Account sourceAccount = new Account(accountNumber, client, 1000L);
    Account targetAccount = new Account(accountNumber + 1, client, 2000L);
    BankTransaction bankTransaction = new BankTransaction(500L, new Date(), sourceAccount, targetAccount);
    String target = bankTransaction.getTarget();
    Assertions.assertEquals(String.valueOf(accountNumber + 1), target);
  }
}
