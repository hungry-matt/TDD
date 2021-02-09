package chap02.remind;

import chap02.PasswordStrength;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("암호 측정")
public class PasswordStrengthTest {

    //조건
    //대문자 포함
    //0~9 숫자 포함
    //길이가 8글자 이상
    //암호강도는 STRONG, NORMAL, WEAK로 정의

    private PasswordStrengthMeter meter =  new PasswordStrengthMeter();

    private void assertStrength(PasswordStrength strength, String password) {
        PasswordStrength result = meter.meter(password);
        assertEquals(strength, result);
    }

    @Test
    @DisplayName("모든 조건에 충족")
    public void meetAllConditions_Then_Strong() {
        assertStrength(PasswordStrength.STRONG, "Abcdefghi12");
    }

    @Test
    @DisplayName("길이가 8글자 미만")
    public void lengthNotEnough_Then_Normal() {
        assertStrength(PasswordStrength.NORMAL, "Aabcd12");
    }

    @Test
    @DisplayName("숫자를 포함하지 않고 충족")
    public void containingNotNum_Then_Normal() {
        assertStrength(PasswordStrength.NORMAL, "Aabcdefghi");
    }

    @Test
    @DisplayName("대문자를 포함하지 않고 충족")
    public void containingNotUpperCase_Then_Normal() {
        assertStrength(PasswordStrength.NORMAL, "abcdefg12");
    }

    @Test
    @DisplayName("null인 경우")
    public void nullInput_Then_Weak() {
        assertStrength(PasswordStrength.INVALID, null);
    }

    @Test
    @DisplayName("빈값인 경우")
    public void emptyInput_Then_Weak() {
        assertStrength(PasswordStrength.INVALID, "");
    }

    @Test
    @DisplayName("길이가 8글자 이상인 조건만 충족")
    public void meetOnlyLengthCriteria_Then_Weak() {
        assertStrength(PasswordStrength.WEAK, "abcdefghi");
    }

    @Test
    @DisplayName("대문자 포함 조건만 충족")
    public void meetOnlyUpperCaseCriteria_Then_Weak() {
        assertStrength(PasswordStrength.WEAK, "Abcdef");
    }

    @Test
    @DisplayName("숫자 포함 조건만 충족")
    public void meetOnlyNumberCriteria_Then_Weak() {
        assertStrength(PasswordStrength.WEAK, "abc12");
    }

    @Test
    @DisplayName("아무 조건도 충족하지 않는 경우")
    public void meetNoCriteria_Then_Weak() {
        assertStrength(PasswordStrength.WEAK, "aaa");
    }
}
