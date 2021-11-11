package io.diffblue.corebanking.compliance.rules;

import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.transaction.Transaction;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ComplianceRuleLargeCashDeposits extends ComplianceRule {

  private static final Comparator<Transaction> compareDate = new Comparator<Transaction>() {
    @Override
    public int compare(Transaction t1, Transaction t2) {
      return t1.getTransactionDate().compareTo(t2.getTransactionDate());
    }
  };

  private static final long LARGE_DEPOSIT_THRESHOLD = 100000L;
  private static final long MIN_OCCURRENCE = 5L;
  private static final long PERIOD = 90L;

  /**
   * Checks if the passed account passes or fails this rule.
   *
   * @param account The account to verify compliance.
   */
  @Override
  public void validateAccountCompliance(Account account) {

    // Make sure the account does not belong to any list.
    this.removeFromComplianceLists(account);

    // Get all large cash deposits and order them by date
    List<Transaction> largeCashDeposits =
      account.getAccountStatement().getTransactions().stream()
        .filter(transaction ->
          "CASH".equals(transaction.getSource()) &&
          transaction.getTransactionAmount() > LARGE_DEPOSIT_THRESHOLD)
        .sorted(compareDate)
        .collect(Collectors.toList());

    // Check whether there are at least MIN_OCCURRENCE
    // large cash deposits within PERIOD on average.
    if (largeCashDeposits.size() >= MIN_OCCURRENCE) {
      long duration = ChronoUnit.DAYS.between(
          toLocalDateTime(largeCashDeposits.get(0)),
          toLocalDateTime(largeCashDeposits.get(largeCashDeposits.size() - 1)));
      if ((largeCashDeposits.size() * PERIOD) / duration >= MIN_OCCURRENCE) {
        addToNonCompliantAccounts(account);
        return;
      }
    }
    addToCompliantAccounts(account);
  }

  private static LocalDateTime toLocalDateTime(Transaction transaction) {
    return LocalDateTime.ofInstant(
        transaction.getTransactionDate().toInstant(),
        ZoneId.systemDefault());
  }
}
