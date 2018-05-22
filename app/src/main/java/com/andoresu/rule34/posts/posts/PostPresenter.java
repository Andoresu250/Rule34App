package com.andoresu.rule34.posts.posts;

import android.support.annotation.NonNull;
import android.util.Log;

import com.andoresu.rule34.client.RetrofitClient;
import com.andoresu.rule34.posts.data.Post;
import com.andoresu.rule34.posts.data.PostsResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        if(forceUpdate){
            postsView.setProgressIndicator(true);
            postClient.listPosts(defaultOptions()).enqueue(new retrofit2.Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    if(response.isSuccessful()){
                        try {
                            String xmlString = response.body().string();
                            XmlToJson xmlToJson = new XmlToJson.Builder(xmlString).build();
                            String jsonString = xmlToJson.toString();
                            Log.i(TAG, "onResponse: " + jsonString);
                            Gson gson = new Gson();
                            PostsResponse postsResponse = gson.fromJson(jsonString, PostsResponse.class);
                            for(Post post : postsResponse.posts.post){
                                Log.i(TAG, "onResponse: " + post.fileUrl);
                            }
                            postsView.showPosts(postsResponse.posts.post);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Log.e(TAG, "onResponse: fail");
                    }
                    postsView.setProgressIndicator(false);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e(TAG, "onFailure: ", t);
                    postsView.setProgressIndicator(false);
                }
            });
        }



    }

    @Override
    public void openPostDetails(@NonNull Post post) {

    }

    private Map<String, String> defaultOptions(String tags, String limit, String page){
        Map<String, String> options = new HashMap<>();
        options.put("page", "dapi");
        options.put("s", "post");
        options.put("q", "index");
        if(tags != null && !tags.isEmpty()){
            options.put("tags", tags);
        }
        options.put("limit", limit != null && !limit.isEmpty() ? limit : "20");
        options.put("pid", page != null && !page.isEmpty() ? page : "0");
        return options;
    }
    private Map<String, String> defaultOptions(){
        return defaultOptions(null, null, null);
    }
}
