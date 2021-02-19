package chap03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("서비스 납부 테스트")
class ExpiryDateCalculatorTest {

    //서비스를 사용하려면 매월 만원을 선불로 납부 한다. 납부일을 기준으로 한달 뒤가 만료일이 된다.
    //2개월 이상 요금을 납부 할 수 있다.
    //10만원을 납부하면 서비스를 1년 제공 한다.

    //테스트 작성 순서
    //1. 쉬운것 부터 테스트
    //1-1. 구현하기 쉬운것 부터 테스트
    //1-2. 예외 상황을 먼저 테스트
    //2. 예를 추가하면서 구현을 일반화

    @Test
    @DisplayName("만원 납부시 한달 뒤가 만료일")
    public void pay_10000_won() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2020, 3,1))
                        .payAmount(10_000)
                        .build()
                , LocalDate.of(2020, 4,1));

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2020, 5,5))
                        .payAmount(10_000)
                        .build()
                , LocalDate.of(2020, 6,5));
    }

    @Test
    @DisplayName("납부일과 한달 뒤 일자가 같지 않음")
    public void days_not_equal() {
        assertExpiryDate(
                PayData.builder()
                    .billingDate(LocalDate.of(2020,1,31))
                    .payAmount(10_000)
                    .build()
                , LocalDate.of(2020,2,29));
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2020,1,30))
                        .payAmount(10_000)
                        .build()
                , LocalDate.of(2020,2,29));
        assertExpiryDate(
                PayData.builder()
                    .billingDate(LocalDate.of(2020,5,31))
                    .payAmount(10_000)
                    .build()
                , LocalDate.of(2020,6,30));
    }

    @Test
    @DisplayName("월의 마지막날 납부할때 만료일의 일자와 다를 경우 첫 납부일의 일자를 기준으로 만료일의 일자를 정함")
    public void expiry_date_different() {
        //첫 납부일의 만료일자와 납부일의 만료일의 일자가 다를때 1만원 납부시 첫 납부일 기준으로 만료일을 정함
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2020, 1, 31))
                .billingDate(LocalDate.of(2020, 2, 29))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData, LocalDate.of(2020, 3, 31));

        PayData payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2020, 1, 30))
                .billingDate(LocalDate.of(2020, 2, 29))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData2, LocalDate.of(2020, 3, 30));
    }

    @Test
    @DisplayName("첫 납부일과 만료일자가 다를때 2만원 이상 납부")
    public void expiry_date_different_pay_20_000() {
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2020,1,31))
                .billingDate(LocalDate.of(2020, 2, 29))
                .payAmount(20_000)
                .build();

        assertExpiryDate(payData, LocalDate.of(2020, 4, 30));

        PayData payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2020,3,31))
                .billingDate(LocalDate.of(2020,4,30))
                .payAmount(30_000)
                .build();

        assertExpiryDate(payData2, LocalDate.of(2020,7, 31));
    }

    @Test
    @DisplayName("2만원 이상 납부시 비례하여 만료일 계산")
    public void calculate_proportionally() {
        PayData payData = PayData.builder()
                .billingDate(LocalDate.of(2020,1,1))
                .payAmount(20_000)
                .build();

        assertExpiryDate(payData, LocalDate.of(2020, 3,1));

        PayData payData2 = PayData.builder()
                .billingDate(LocalDate.of(2020, 1,1))
                .payAmount(30_000)
                .build();

        assertExpiryDate(payData2, LocalDate.of(2020, 4,1));
    }

    //중복 제거한 메서드
    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator calculator = new ExpiryDateCalculator();
        LocalDate expiryDate = calculator.calculateExpiryDate(payData);

        assertEquals(expectedExpiryDate, expiryDate);
    }

}