package org.maktab.woocommercemarket.data.remote;

import java.util.HashMap;
import java.util.Map;

public class NetworkParams {
    public static final String BASE_PATH = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/products/";
    public static final String CONSUMER_SECRET ="cs_7629c656bb0986c85666ad8e9c9badf720b707f6";
    public static final String CONSUMER_KEY = "ck_e3c3b1c099fa02c53717f52a92a0d47855e1e4e5";

    public static Map<String, String> BASE_OPTIONS = new HashMap<String, String>() {{
        put("consumer_key" , CONSUMER_KEY);
        put("consumer_secret", CONSUMER_SECRET);

    }};


    public static Map<String, String> getNewestOptions() {
        Map<String,String> map = new HashMap<>();
        map.putAll(BASE_OPTIONS);
        return map;
    }
}
