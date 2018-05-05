package com.andoresu.rule34.posts.posts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.andoresu.rule34.R;
import com.andoresu.rule34.posts.data.Post;
import com.bumptech.glide.Glide;

import java.util.List;

public class PostsActivity extends AppCompatActivity implements PostsContract.View{

    private PostsContract.UserActionListener userActionListener;

    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        userActionListener = new PostPresenter(this);

        userActionListener.loadPosts(true);

        imageView = findViewById(R.id.imageView);

    }

    @Override
    public void setProgressIndicator(boolean active) {

    }

    @Override
    public void showPosts(List<Post> posts) {

        if(!posts.isEmpty()){
            Glide.with(this).load(posts.get(0).fileUrl).into(imageView);
        }

    }
}
