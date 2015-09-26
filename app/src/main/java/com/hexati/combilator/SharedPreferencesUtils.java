package com.hexati.combilator;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tomek on 18.08.15.
 */
public class SharedPreferencesUtils {

    public static final String SHARED_PREFERENCES_KEY = "com.hexati.shared_preferences";
    public static final String MY_APPS_JSON_OBJECTS_KEY = "com.hexati.my_apps_json_key";
    public static final String MY_APPS_SIZE_KEY = "com.hexati.my_apps_size_key";


    private static final String PREFS_DEFAULT_STRING_VALUE = "";
    public static final int PREFS_DEFAULT_INT_VALUE = 0;

    public static void putStringInSharedPreferences(Context context, String key, String value) {
        SharedPreferences prefs =
                context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
        prefs
                .edit()
                .putString(key, value)
                .commit();
    }

    public static String getStringFromSharedPreference(Context context, String key) {
        SharedPreferences prefs =
                context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
        return prefs.getString(key, PREFS_DEFAULT_STRING_VALUE);
    }
}
