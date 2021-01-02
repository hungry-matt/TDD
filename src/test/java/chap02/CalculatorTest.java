package chap02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    //@Test 어노테이션을 사용하게 되면 아래의 plus 메서드가 테스트 메서드로 인식한다.
    //테스트 메서드는 기능을 검증하는 코드를 담고 있다.
    public void plus() {
        int result = Calculator.plus(1, 2);

        //assertEqulas : 값을 검증하는 메서드 이다.
        //첫 번째 인자는 기대 값, 두 번째 인자는 실제 값 이다. 즉, 기대 값과 실제 값이 동일한지 비교 한다.
        assertEquals(3, result);

        //덧셈 기능 검증 코드 추가
        assertEquals(5, Calculator.plus(4, 1));
    }
}
