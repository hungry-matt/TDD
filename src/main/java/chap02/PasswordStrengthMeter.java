package chap02;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String password) {

        //8글자 미만 체크
        if (password.length() <= 8) {
            return PasswordStrength.NORMAL;
        }

        //숫자 포함 유무 체크
        boolean containsNum = meetsContainingNumberCriteria(password);

        if (!containsNum) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }

    private boolean meetsContainingNumberCriteria(String password) {

        for (char c : password.toCharArray()) {
            if (c >= '0' && c <= '9') {
                return true;
            }
        }

        return false;
    }
}
