package org.maktab.woocommercemarket.data.remote;

import java.util.HashMap;
import java.util.Map;

public class NetworkParams {
    public static final String BASE_PATH = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";
    public static final String CONSUMER_SECRET ="cs_7629c656bb0986c85666ad8e9c9badf720b707f6";
    public static final String CONSUMER_KEY = "ck_e3c3b1c099fa02c53717f52a92a0d47855e1e4e5";
    public static final String DATE_PROPERTIES = "date";
    public static final String RATING_PROPERTIES = "rating";
    public static final String POPULARITY_PROPERTIES = "popularity";
    public static final String SPECIAL_TAG = "48";

    public static Map<String, String> BASE_OPTIONS = new HashMap<String, String>() {{
        put("consumer_key" , CONSUMER_KEY);
        put("consumer_secret", CONSUMER_SECRET);
    }};


    public static Map<String, String> getNewestOptions() {
        Map<String, String> map = new HashMap<>(BASE_OPTIONS);
        map.put("orderby", DATE_PROPERTIES);
        return map;
    }
    public static Map<String, String> getNewestOptions(int page) {
        Map<String, String> map = new HashMap<>(BASE_OPTIONS);
        map.put("orderby", DATE_PROPERTIES);
        map.put("page", String.valueOf(page));
        return map;
    }


    public static Map<String, String> getPopularOptions() {
        Map<String, String> map = new HashMap<>(BASE_OPTIONS);
        map.put("orderby", POPULARITY_PROPERTIES);
        return map;
    }
    public static Map<String, String> getPopularOptions(int page) {
        Map<String, String> map = new HashMap<>(BASE_OPTIONS);
        map.put("orderby", POPULARITY_PROPERTIES);
        map.put("page", String.valueOf(page));
        return map;
    }

    public static Map<String, String> getMostPointsOptions() {
        Map<String, String> map = new HashMap<>(BASE_OPTIONS);
        map.put("orderby", RATING_PROPERTIES);
        return map;
    }
    public static Map<String, String> getMostPointsOptions(int page) {
        Map<String, String> map = new HashMap<>(BASE_OPTIONS);
        map.put("orderby", RATING_PROPERTIES);
        map.put("page", String.valueOf(page));
        return map;
    }


    public static Map<String, String> getSpecialOptions() {
        Map<String, String> map = new HashMap<>(BASE_OPTIONS);
        map.put("tag", SPECIAL_TAG);
        return map;
    }

    public static Map<String, String> getCategoriesOptions() {
        return BASE_OPTIONS;
    }
}
