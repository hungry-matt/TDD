package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(PayData payData) {
        //서비스 한달 이용료는 만원이므로 납부 금액에 비례해서 개월수 계산함
        int addedMonths = payData.getPayAmount() / 10_000;

        if (payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonths);
        } else {
            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
        //후보 만료일
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);

        //첫 납부일의 일자와 납부일의 만료일자와 다를 경우
        //예) 첫 납부일 : 2020-1-31
        //납부일 : 2020-2-29
        //납부일 기준 만료일 : 2020-3-29
        //첫 납부일 기준 만료일 : 2020-3-31
        if (isSameDayOfMonth(payData.getFirstBillingDate(), candidateExp)) {
            //getDayOfMonth : 월의 일자 반환
            //withDayOfMonth : 일자를 변경 하여 반환

            //후보 만료일의 마지막 일자
            final int dayLenOfCandiMon = lastDayOfMonth(candidateExp);
            //첫 납부일의 일자
            final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();

            //후보 만료일의 일자 보다 첫 납부일의 일자가 클 경우
            if (dayLenOfCandiMon < dayOfFirstBilling) {
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }

            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        } else {
            return candidateExp;
        }
    }

    private boolean isSameDayOfMonth(LocalDate firstBillingDate, LocalDate candidate) {
        return firstBillingDate.getDayOfMonth() != candidate.getDayOfMonth();
    }

    private int lastDayOfMonth(LocalDate candidate) {
        return YearMonth.from(candidate).lengthOfMonth();
    }
}
