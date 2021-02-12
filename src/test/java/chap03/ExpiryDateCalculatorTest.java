package chap03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertExpiryDate(LocalDate.of(2020,3,1), 10_000, LocalDate.of(2020, 4,1));
        assertExpiryDate(LocalDate.of(2020,5,5), 10_000, LocalDate.of(2020, 6, 5));
    }

    //중복 제거한 메서드
    private void assertExpiryDate(LocalDate billingDate, int payAmount, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator calculator = new ExpiryDateCalculator();
        LocalDate expiryDate = calculator.calculateExpiryDate(billingDate, payAmount);

        assertEquals(expectedExpiryDate, expiryDate);
    }

}