package org.maktab.woocommercemarket.utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.maktab.woocommercemarket.data.model.Product;

import java.util.HashSet;
import java.util.Set;


public class ChartPreferences {

    private static final String PREF_KEY_CHART = "chartQuery";

    public static Set<String> getChart(Context context) {
        SharedPreferences preferences = getDefaultSharedPreferences(context);
        return preferences.getStringSet(PREF_KEY_CHART, new HashSet<>());
    }

    public static void addToChart(Context context, String id) {
        SharedPreferences preferences = getDefaultSharedPreferences(context);
        HashSet<String> newSet = new HashSet<>(getChart(context));
        newSet.add(id);
        preferences
                .edit()
                .putStringSet(PREF_KEY_CHART, newSet)
                .apply();
    }

    public static void removeFromChart(Context context, String id) {
        SharedPreferences preferences = getDefaultSharedPreferences(context);
        HashSet<String> newSet = new HashSet<>(getChart(context));
        newSet.remove(id);
        preferences
                .edit()
                .putStringSet(PREF_KEY_CHART, newSet)
                .apply();
    }

    public static boolean isInChart(Context context,String id){
        Set<String> set = getChart(context);
        return set.contains(id);

    }
    private static SharedPreferences getDefaultSharedPreferences(Context context) {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }
}
