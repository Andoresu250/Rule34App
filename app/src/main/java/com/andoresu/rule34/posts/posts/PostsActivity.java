package com.andoresu.rule34.posts.posts;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.andoresu.rule34.R;
import com.andoresu.rule34.posts.data.Post;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostsActivity extends AppCompatActivity implements
        SwipeRefreshLayout.OnRefreshListener,
        RecyclerView.OnItemTouchListener,
        PostsContract.View{

    String TAG = PostsActivity.class.getSimpleName();

    private PostsContract.UserActionListener userActionListener;

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.postRecyclerView)
    RecyclerView postRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        ButterKnife.bind(this);

        postRecyclerView.setLayoutManager(new LinearLayoutManager(this));

//        postRecyclerView.addOnItemTouchListener(new PostAdapter.);

        refreshLayout.setOnRefreshListener(this);

        userActionListener = new PostPresenter(this);

        userActionListener.loadPosts(true);

    }

    @Override
    public void setProgressIndicator(final boolean active) {
        Log.i(TAG, "setProgressIndicator: active " + active);
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(active);
            }
        });
    }

    @Override
    public void showPosts(List<Post> posts) {
        postRecyclerView.setAdapter(new PostAdapter(this, posts));
    }


    @Override
    public void onRefresh() {
        userActionListener.loadPosts(true);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
