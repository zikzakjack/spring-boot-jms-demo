package zik.zak.jack.SpringBootDemoJms.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class TransactionAlert implements Serializable {

  private static final long serialVersionUID = -8806522230262847830L;

  public TransactionAlert() {}

  public enum TransactionType {
    INVALID(-1), CREDIT(0), DEBIT(1);

    private int transactionCode;

    private TransactionType(final int transactionCode) {
      this.transactionCode = transactionCode;
    }

    public int getTransactionCode() {
      return transactionCode;
    }

    // Lookup table
    private static final Map<Integer, TransactionType> lookup = new HashMap<Integer, TransactionType>();

    // Populate the lookup table on loading time
    static {
      for (TransactionType transaction : EnumSet.allOf(TransactionType.class))
        lookup.put(transaction.getTransactionCode(), transaction);
    }

    // This method can be used for reverse lookup purpose
    public static TransactionType get(int transactionCode) {
      return lookup.get(transactionCode);
    }

  }

  TransactionType transactionType;
  double amount;
  Date transactionTimestamp;

  public TransactionAlert(TransactionType transactionType, double amount, Date transactionTimestamp) {
    this.transactionType = transactionType;
    this.amount = amount;
    this.transactionTimestamp = transactionTimestamp;
  }

  public TransactionType getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(TransactionType transactionType) {
    this.transactionType = transactionType;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public Date getTransactionTimestamp() {
    return transactionTimestamp;
  }

  public void setTransactionTimestamp(Date transactionTimestamp) {
    this.transactionTimestamp = transactionTimestamp;
  }

  @Override
  public String toString() {
    return "TransactionAlert [transactionType=" + transactionType + ", amount=" + amount + ", transactionTimestamp=" + transactionTimestamp + "]";
  }

}

