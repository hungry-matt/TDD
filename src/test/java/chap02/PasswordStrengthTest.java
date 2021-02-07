package chap02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//테스트 이름을 원하는 대로 쉽게 정할 수 있다.
@DisplayName("암호 검사")
class PasswordStrengthTest {

    //모든 조건이 충족 하는 경우
    //모든 조건에 충족 되지 않는 경우

    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr, result);
    }

    @Test
    @DisplayName("모든 조건 충족")
    public void 모든_조건_충족시_암호강도_강함() {
        assertStrength("Aa1#35117", PasswordStrength.STRONG);
    }

    @Test
    @DisplayName("길이가 8글자 미만")
    public void 길이가_8글자_미만시_암호강도_보통() {
        assertStrength("Aa1#35", PasswordStrength.NORMAL);
        assertStrength("aaa124", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("숫자를 포함하지 않고 조건 충족")
    public void 숫자를_포함하지_않고_조건_충족시_암호강도_보통() {
        assertStrength("aa@#aksaa", PasswordStrength.NORMAL);
    }

}