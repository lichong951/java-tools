package com.glodon.tool;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;
/**
 * @deprecated
 * */
public class SharedPreferencesUtil {
    private String KEY = "com.glodon.glm.gateface";
    private SharedPreferences mSharedPreferences;

    public static String GATE_TYPE = "GATE_TYPE"; //闸机类型 进/出

    public static String GATE_ACCESS_CORE = "GATE_ACCESS_CORE"; //闸机通过最低分数

    public static String REMOTE_DEVICE_ID = "REMOTE_DEVICE_ID"; //远程设备id

    public static String GATE_WIDE_ANGLE= "GATE_WIDE_ANGLE"; //广角距离

    public static String PROJECT_ID = "PROJECT_ID"; //项目id

    public static String SYN_TIME = "SYN_TIME"; //白名单同步时间

    private static SharedPreferencesUtil _instance = new SharedPreferencesUtil();

    public static SharedPreferencesUtil Instance() {
        return _instance;
    }

    public void init(Context context) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getApplicationContext().getSharedPreferences(KEY, 0);
        }
    }

    /**
     * str: md5+isTracing
     */
    public void put(String id, String str) {
        mSharedPreferences.edit().putString(id, str).commit();
    }

    public String get(String id) {
        return mSharedPreferences.getString(id, "");
    }

    public String getMd5(String id) {
        String str = mSharedPreferences.getString(id, "");
        if (!"".equals(str)) {
            return str.substring(0, str.length() - 1);
        }
        return "";
    }

    public int getIsTracing(String id) {
        String str = mSharedPreferences.getString(id, "");
        if (str != null && str.length() > 0) {
            return Integer.valueOf(str.substring(str.length() - 1), str.length());
        }
        return 1;
    }

    public void remove(String id) {
        mSharedPreferences.edit().remove(id).commit();
    }

    public Set<String> getIds() {
        return mSharedPreferences.getAll().keySet();
    }

    public void clear() {
        mSharedPreferences.edit().clear().commit();
    }

    public void putBoolean(String key, boolean b) {
        mSharedPreferences.edit().putBoolean(key, b).commit();
    }

    public boolean getBoolean(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }

    public boolean getBooleanDefaultFalse(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }

    public boolean putInt(String mainPort, int port) {
        return mSharedPreferences.edit().putInt(mainPort, port).commit();
    }

    public int getInt(String key) {
        return mSharedPreferences.getInt(key, 0);
    }



    public boolean putFloat(String mainPort, float port) {
        return mSharedPreferences.edit().putFloat(mainPort, port).commit();
    }

    public float getFloat(String key) {
        return mSharedPreferences.getFloat(key, 0);
    }
}
