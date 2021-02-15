package chap03;

import java.time.LocalDate;

public class PayData {

    private LocalDate firstBillingDate;

    private LocalDate billingDate;

    private int payAmount;

    private PayData() {

    }

    public PayData(LocalDate firstBillingDate, LocalDate billingDate, int payAmount) {
        this.firstBillingDate = firstBillingDate;
        this.billingDate = billingDate;
        this.payAmount = payAmount;
    }

    public LocalDate getFirstBillingDate() {
        return firstBillingDate;
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private LocalDate firstBillingDate;
        private LocalDate billingDate;
        private int payAmount;

        private Builder() {
        }

        public Builder firstBilingDate(LocalDate firstBillingDate) {
            this.firstBillingDate = firstBillingDate;
            return this;
        }

        public Builder billingDate(LocalDate billingDate) {
            this.billingDate = billingDate;
            return this;
        }

        public Builder payAmount(int payAmount) {
            this.payAmount = payAmount;
            return this;
        }

        public PayData build() {
            return new PayData(firstBillingDate, billingDate, payAmount);
        }

    }
}
