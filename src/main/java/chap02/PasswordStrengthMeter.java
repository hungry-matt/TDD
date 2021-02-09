package chap02;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String password) {

        if (password == null || password.isEmpty()) return PasswordStrength.INVALID;

        int meetCounts = getMeetCriteriaCounts(password);

        //위 항목중 하나의 조건에 충족할 경우 암호 강도 WEAK
        //또는 아무 조건도 충족하지 않는 경우 암호 강도 WEAK
        if (meetCounts <= 1) return PasswordStrength.WEAK;

        //위 항목중 두개의 조건에 충족할 경우 암호 강도 NORMAL
        if (meetCounts == 2) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

    private int getMeetCriteriaCounts(String password) {
        int meetCounts = 0;

        if (password.length() >= 8) meetCounts++;
        if (meetsContainingUppercaseCriteria(password)) meetCounts++;
        if (meetsContainingNumberCriteria(password)) meetCounts++;

        return meetCounts;
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
