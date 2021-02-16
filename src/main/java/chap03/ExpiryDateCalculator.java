package chap03;

import java.time.LocalDate;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonths = 1;

        if (payData.getFirstBillingDate() != null) {
            LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
            //첫 납부일의 일자와 납부일의 만료일자와 다를 경우
            //예) 첫 납부일 : 2020-1-31
            //납부일 : 2020-2-29
            //납부일 기준 만료일 : 2020-3-29
            //첫 납부일 기준 만료일 : 2020-3-31
            if (payData.getFirstBillingDate().getDayOfMonth() != candidateExp.getDayOfMonth()) {
                //getDayOfMonth : 월의 일자 반환
                //withDayOfMonth : 일자를 변경 하여 반환
                return candidateExp.withDayOfMonth(payData.getFirstBillingDate().getDayOfMonth());
            }
        }

        return payData.getBillingDate().plusMonths(addedMonths);
    }
}
