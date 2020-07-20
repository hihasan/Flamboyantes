package com.flamboyantes.util;

import com.loopj.android.http.SyncHttpClient;

public class APIClient {
    public static final String BASE_URL = "https://flamboyantes.net//api/";
    private static SyncHttpClient client = new SyncHttpClient();

    public static SyncHttpClient getInstance() {
        return client;
    }
}
