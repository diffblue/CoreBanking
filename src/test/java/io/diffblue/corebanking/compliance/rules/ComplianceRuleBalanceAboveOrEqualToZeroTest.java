package io.diffblue.corebanking.compliance.rules;

import com.diffblue.deeptestutils.Reflector;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.account.Account.AccountState;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.transaction.Transaction;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

public class ComplianceRuleBalanceAboveOrEqualToZeroTest {
  @Rule
  public final Timeout globalTimeout = new Timeout(10000);

  @Rule
  public final ExpectedException thrown = ExpectedException.none();

  // Test written by Diffblue Cover.
  @Test
  public void validateAccountComplianceInputNotNullOutputVoid001ad4c9bd31cb98f70() {

    // Arrange
    final ComplianceRuleBalanceAboveOrEqualToZero complianceRuleBalanceAboveOrEqualToZero = new ComplianceRuleBalanceAboveOrEqualToZero();
    final Client client = new Client("");
    final Account account = new Account(0L, client, -9_223_372_036_854_775_807L);

    // Act
    complianceRuleBalanceAboveOrEqualToZero.validateAccountCompliance(account);

    // Assert side effects
    Assert.assertNotNull(complianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts());
    Assert.assertEquals(1, complianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts().size());
    Assert.assertNotNull(complianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts().get(0));
    Assert.assertNotNull(
        ((Account) complianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts().get(0)).getClient());
    final ArrayList<Account> arrayList = new ArrayList<Account>();
    Assert.assertEquals(arrayList,
        ((Account) complianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts().get(0)).getClient().getAccounts());
    Assert.assertEquals("", ((Account) complianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts().get(0))
        .getClient().getClientName());
    Assert.assertEquals("Current",
        ((Account) complianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts().get(0)).getAccountName());
    Assert.assertNotNull(
        ((Account) complianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts().get(0)).getAccountStatement());
    Assert.assertEquals(complianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts().get(0),
        Reflector.getInstanceField(
            ((Account) complianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts().get(0)).getAccountStatement(),
            "this$0"));
    final ArrayList<Transaction> arrayList1 = new ArrayList<Transaction>();
    Assert.assertEquals(arrayList1, ((Account) complianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts().get(0))
        .getAccountStatement().getTransactions());
    Assert.assertEquals(AccountState.OPEN,
        ((Account) complianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts().get(0)).getAccountState());
    Assert.assertEquals(0L,
        ((Account) complianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts().get(0)).getAccountNumber());
    Assert.assertEquals(-9_223_372_036_854_775_807L,
        ((Account) complianceRuleBalanceAboveOrEqualToZero.getNonCompliantAccounts().get(0)).getCurrentBalance());

  }

  // Test written by Diffblue Cover.
  @Test
  public void validateAccountComplianceInputNotNullOutputVoid0008fc6c7480f674b14() {

    // Arrange
    final ComplianceRuleBalanceAboveOrEqualToZero complianceRuleBalanceAboveOrEqualToZero = new ComplianceRuleBalanceAboveOrEqualToZero();
    final Client client = new Client("");
    final Account account = new Account(0L, client, 0L);

    // Act
    complianceRuleBalanceAboveOrEqualToZero.validateAccountCompliance(account);

    // Assert side effects
    Assert.assertNotNull(complianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts());
    Assert.assertEquals(1, complianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts().size());
    Assert.assertNotNull(complianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts().get(0));
    Assert.assertNotNull(((Account) complianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts().get(0)).getClient());
    final ArrayList<Account> arrayList = new ArrayList<Account>();
    Assert.assertEquals(arrayList,
        ((Account) complianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts().get(0)).getClient().getAccounts());
    Assert.assertEquals("",
        ((Account) complianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts().get(0)).getClient().getClientName());
    Assert.assertEquals("Current",
        ((Account) complianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts().get(0)).getAccountName());
    Assert.assertNotNull(
        ((Account) complianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts().get(0)).getAccountStatement());
    Assert.assertEquals(complianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts().get(0),
        Reflector.getInstanceField(
            ((Account) complianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts().get(0)).getAccountStatement(),
            "this$0"));
    final ArrayList<Transaction> arrayList1 = new ArrayList<Transaction>();
    Assert.assertEquals(arrayList1, ((Account) complianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts().get(0))
        .getAccountStatement().getTransactions());
    Assert.assertEquals(AccountState.OPEN,
        ((Account) complianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts().get(0)).getAccountState());
    Assert.assertEquals(0L,
        ((Account) complianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts().get(0)).getAccountNumber());
    Assert.assertEquals(0L,
        ((Account) complianceRuleBalanceAboveOrEqualToZero.getCompliantAccounts().get(0)).getCurrentBalance());

  }

  // Test written by Diffblue Cover.
  @Test
  public void constructorOutputNotNull0002b453495013526ae() {

    // Act, creating object to test constructor
    final ComplianceRuleBalanceAboveOrEqualToZero actual = new ComplianceRuleBalanceAboveOrEqualToZero();

    // Assert result
    Assert.assertNotNull(actual);
    final ArrayList<Account> arrayList = new ArrayList<Account>();
    Assert.assertEquals(arrayList, actual.getCompliantAccounts());
    final ArrayList<Account> arrayList1 = new ArrayList<Account>();
    Assert.assertEquals(arrayList1, actual.getNonCompliantAccounts());

  }
}
