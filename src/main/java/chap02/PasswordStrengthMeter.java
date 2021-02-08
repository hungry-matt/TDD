package chap02;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String password) {

        if (password == null || password.isEmpty()) return PasswordStrength.INVALID;

        boolean lengthEnough = password.length() >= 8;
        boolean containsUpperCase = meetsContainingUppercaseCriteria(password);
        boolean containsNum = meetsContainingNumberCriteria(password);

        int metCounts = 0;

        //8글자 이상인 조건만 충족
        if (lengthEnough) metCounts++;
        //숫자인 조건만 충족
        if (containsNum) metCounts++;
        //대문자인 조건만 충족
        if (containsUpperCase) metCounts++;

        //위 항목중 하나의 조건에 충족할 경우 암호 강도 WEAK
        if (metCounts == 1) return PasswordStrength.WEAK;

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
