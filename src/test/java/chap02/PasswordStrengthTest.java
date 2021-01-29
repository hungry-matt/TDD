package chap02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PasswordStrengthTest {

    //모든 조건이 충족 하는 경우
    //모든 조건에 충족 되지 않는 경우

    @Test
    public void 모든_조건_충족시_암호강도_강함() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("Aa1#35117");
        assertEquals(PasswordStrength.STRONG, result);
    }

    @Test
    public void 길이가_8글자_미만시_암호강도_보통() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("Aa1#35");
        assertEquals(PasswordStrength.NORMAL, result);
    }

}