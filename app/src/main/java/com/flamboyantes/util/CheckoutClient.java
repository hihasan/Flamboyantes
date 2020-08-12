package com.flamboyantes.util;

import com.loopj.android.http.SyncHttpClient;

public class CheckoutClient {
    public static final String BASE_URL = "https://apps.ub-pay.net/merchantController/";
    private static SyncHttpClient client = new SyncHttpClient();

    public static SyncHttpClient getInstance() {
        return client;
    }
}
