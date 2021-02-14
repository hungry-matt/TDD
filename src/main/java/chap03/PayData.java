package chap03;

import java.time.LocalDate;

public class PayData {

    private LocalDate billingDate;

    private int payAmount;

    private PayData() {

    }

    public PayData(LocalDate billingDate, int payAmount) {
        this.billingDate = billingDate;
        this.payAmount = payAmount;
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
        private LocalDate billingDate;
        private int payAmount;

        private Builder() {
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
            return new PayData(billingDate, payAmount);
        }
    }
}
