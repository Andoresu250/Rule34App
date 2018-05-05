package com.andoresu.rule34.client;

import retrofit2.Retrofit;

public class RetrofitClient {

    private static final String BASE_URL = "https://rule34.xxx/";

    public static final Retrofit client =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .build();

}
