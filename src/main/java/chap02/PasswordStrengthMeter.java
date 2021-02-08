package chap02;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String password) {

        if (password == null || password.isEmpty()) return PasswordStrength.INVALID;

        boolean lengthEnough = password.length() >= 8;
        boolean containsUpperCase = meetsContainingUppercaseCriteria(password);
        boolean containsNum = meetsContainingNumberCriteria(password);

        //8글자 이상인 조건만 충족
        if (lengthEnough && !containsUpperCase && !containsNum) return PasswordStrength.WEAK;
        //숫자인 조건만 충족
        if (!lengthEnough && !containsUpperCase && containsNum) return PasswordStrength.WEAK;
        //대문자인 조건만 충족
        if (!lengthEnough && containsUpperCase && !containsNum) return PasswordStrength.WEAK;

        if (!lengthEnough) return PasswordStrength.NORMAL;
        if (!containsUpperCase) return PasswordStrength.NORMAL;
        if (!containsNum) return PasswordStrength.NORMAL;

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

    private boolean meetsContainingUppercaseCriteria(String password) {

        for (char c: password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }

        return false;
    }
}
