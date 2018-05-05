package com.andoresu.rule34.posts.posts;

import android.support.annotation.NonNull;
import android.util.Log;

import com.andoresu.rule34.client.RetrofitClient;
import com.andoresu.rule34.posts.data.Post;
import com.andoresu.rule34.posts.data.PostsResponse;
import com.google.gson.Gson;

import java.io.IOException;

import fr.arnaudguyon.xmltojsonlib.XmlToJson;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class PostPresenter implements PostsContract.UserActionListener {

    String TAG = PostPresenter.class.getSimpleName();

    private final PostClient postClient;
    private final PostsContract.View postsView;

    public PostPresenter(@NonNull PostsContract.View postsView) {
        this.postsView = postsView;
        this.postClient = RetrofitClient.client.create(PostClient.class);
    }

    @Override
    public void loadPosts(boolean forceUpdate) {

        postClient.listPosts().enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try {
                        String xmlString = response.body().string();
                        XmlToJson xmlToJson = new XmlToJson.Builder(xmlString).build();
                        String jsonString = xmlToJson.toString();
                        Gson gson = new Gson();
                        PostsResponse postsResponse = gson.fromJson(jsonString, PostsResponse.class);
                        postsView.showPosts(postsResponse.posts.post);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Log.e(TAG, "onResponse: fail");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });

    }

    @Override
    public void openPostDetails(@NonNull Post post) {

    }
}
