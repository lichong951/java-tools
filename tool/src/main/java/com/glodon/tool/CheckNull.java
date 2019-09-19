package com.glodon.tool;

import android.text.TextUtils;

public class CheckNull {

    public static boolean isNull(String value) {
        return TextUtils.isEmpty(value) ? true : false;
    }

    public static String getSelfOrBlank(String value) {
        return isNull(value) ? "" : value;
    }

    public static boolean isNotNull(String value) {
        return !isNull(value);
    }

}
