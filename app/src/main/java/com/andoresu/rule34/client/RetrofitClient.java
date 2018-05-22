package com.andoresu.rule34.client;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class RetrofitClient {

    private static final String BASE_URL = "https://rule34.xxx/";

    public static final Retrofit client = getClient();

    private static Retrofit getClient(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(logging);

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(builder.build())
                .build();
    }

}
