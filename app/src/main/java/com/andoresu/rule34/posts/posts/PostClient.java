package com.andoresu.rule34.posts.posts;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface PostClient {

    @GET("index.php")
    Call<ResponseBody> listPosts(@QueryMap Map<String, String> options);

}
