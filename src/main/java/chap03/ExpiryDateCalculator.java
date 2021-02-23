package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {

    private static int DEFAULT_SERVICE_MONTHS = 12; //10만 원 납부시 제공되는 월 수
    private static int DEFAULT_SERVICE_BASE_AMOUNT = 100_000; //일정 금액 이상 납부시 제공 되는 월 수의 기준 금액
    private static int DEFAULT_MONTHLY_PAY_AMOUNT = 10_000; //서비스의 한달 이용료

    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonths = calculateMonth(payData);

        if (payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonths);
        } else {
            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }

    //서비스 한달 이용료는 만원이므로 납부 금액에 비례해서 개월수 계산함
    //10만원을 납부하면 1년 제공
    public int calculateMonth(PayData payData) {
        int months = 0;

        if (payData.getPayAmount() > DEFAULT_SERVICE_BASE_AMOUNT) {
            int balance = payData.getPayAmount() - DEFAULT_SERVICE_BASE_AMOUNT;
            months = DEFAULT_SERVICE_MONTHS + convertMonths(PayData.builder().payAmount(balance).build());
        } else if (payData.getPayAmount() == DEFAULT_SERVICE_BASE_AMOUNT) {
            months = DEFAULT_SERVICE_MONTHS;
        } else {
            months = convertMonths(payData);
        }

        return months;
    }

    public int convertMonths(PayData payData) {
        return payData.getPayAmount() / DEFAULT_MONTHLY_PAY_AMOUNT;
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
        //후보 만료일
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);

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

    //첫 납부일의 일자와 납부일의 만료일자와 다를 경우
    //예) 첫 납부일 : 2020-1-31
    //납부일 : 2020-2-29
    //납부일 기준 만료일 : 2020-3-29
    //첫 납부일 기준 만료일 : 2020-3-31
    private boolean isSameDayOfMonth(LocalDate firstBillingDate, LocalDate candidate) {
        return firstBillingDate.getDayOfMonth() != candidate.getDayOfMonth();
    }

    //월의 마지막 일자 반환
    private int lastDayOfMonth(LocalDate candidate) {
        return YearMonth.from(candidate).lengthOfMonth();
    }
}
