package com.andoresu.rule34.posts.posts;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PostClient {

    @GET("index.php?page=dapi&s=post&q=index&tags=yoko_littner+&limit=2&pid=0")
    Call<ResponseBody> listPosts();

}
