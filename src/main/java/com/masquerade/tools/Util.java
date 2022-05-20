package com.masquerade.tools;


public final class Util {
    public enum SettingLanguage {
        EN, FR
    }
    static public SettingLanguage getLanguage(String value) {
        if ("FR".equals(value)) {
            return SettingLanguage.FR;
        }
        return SettingLanguage.EN;
    }
}