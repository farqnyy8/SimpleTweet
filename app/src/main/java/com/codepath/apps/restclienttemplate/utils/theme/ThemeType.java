package com.codepath.apps.restclienttemplate.utils.theme;

import androidx.annotation.NonNull;

public enum ThemeType {

    LightMode{
        @NonNull
        public String toString() {
            return "LightMode";
        }
    },
    DarkMode{
        @NonNull
        public String toString() {
            return "DarkMode";
        }
    },
    DefaultMode{
        @NonNull
        public String toString() {
            return "DefaultMode";
        }
    };

    public static ThemeType getThemeType(String themeStr){
        ThemeType themeType;
        switch (themeStr) {
            case "LightMode":
                themeType = LightMode;
                break;
            case "DarkMode":
                themeType = DarkMode;
                break;
            default:
                themeType = DefaultMode;
        }
        return themeType;
    }
}
