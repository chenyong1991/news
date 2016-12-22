package com.example.news.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by c8469 on 2016/12/13.
 */

public class CacheUtil {

    public static String CONFIG = "config";
    public static String IS_FIRST = "is_first";
    public static String READED = "readed";

    public static void putBooleanIntoSp(Context context, String key, boolean b){

        SharedPreferences sp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,b).commit();

    }

    public static boolean getBooleanFromSp(Context context,String key,boolean b){

        SharedPreferences sp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);

        return sp.getBoolean(key,b);

    }

    public static void putStringIntoSp(Context context, String key, String s){

        SharedPreferences sp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        sp.edit().putString(key,s).commit();

    }

    public static String getStringFromSp(Context context,String key){

        SharedPreferences sp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);

        return sp.getString(key,"");// ""  null

    }


}
