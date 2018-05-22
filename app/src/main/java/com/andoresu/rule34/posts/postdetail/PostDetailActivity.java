package com.andoresu.rule34.posts.postdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.andoresu.rule34.R;
import com.andoresu.rule34.posts.data.Post;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostDetailActivity  extends AppCompatActivity{

    @BindView(R.id.postDetailImageView) ImageView postDetailImageView;

    private Post post;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            post = (Post) bundle.getSerializable("post");
            loadPostImage();
        }else{
            //TODO: go back
        }

    }

    private void loadPostImage(){
        Glide.with(this).load(post.fileUrl).into(postDetailImageView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
