package chap02;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String password) {

        if (password == null || password.isEmpty()) return PasswordStrength.INVALID;

        int metCounts = 0;

        if (password.length() >= 8) metCounts++;
        if (meetsContainingUppercaseCriteria(password)) metCounts++;
        if (meetsContainingNumberCriteria(password)) metCounts++;

        //위 항목중 하나의 조건에 충족할 경우 암호 강도 WEAK
        //또는 아무 조건도 충족하지 않는 경우 암호 강도 WEAK
        if (metCounts <= 1) return PasswordStrength.WEAK;

        //위 항목중 두개의 조건에 충족할 경우 암호 강도 NORMAL
        if (metCounts == 2) return PasswordStrength.NORMAL;

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
