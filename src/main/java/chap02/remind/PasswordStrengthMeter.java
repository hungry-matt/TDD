package chap02.remind;

import chap02.PasswordStrength;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String password) {

        if (password == null || password.isEmpty()) return PasswordStrength.INVALID;

        //암호 강도 기준
        int meetCounts = getMeetCriteriaCounts(password);

        //위 항목중 하나의 조건 해당시 암호 강도 WEAK
        if (meetCounts <= 1) return PasswordStrength.WEAK;

        //위 항목중 두개의 조건 해당시 암호 강도 NORMAL
        if (meetCounts == 2) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

    public int getMeetCriteriaCounts(String password) {
        int meetCounts = 0;

        if (password.length() >= 8) meetCounts++;
        if (isContainingNum(password)) meetCounts++;
        if (isContainingUpperCase(password)) meetCounts++;

        return meetCounts;
    }

    private boolean isContainingUpperCase(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean isContainingNum(String password) {
        for (char c : password.toCharArray()) {
            if (c >= '0' && c <= '9') {
                return true;
            }
        }
        return false;
    }
}
