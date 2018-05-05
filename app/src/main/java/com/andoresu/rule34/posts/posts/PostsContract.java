package com.andoresu.rule34.posts.posts;

import android.support.annotation.NonNull;

import com.andoresu.rule34.posts.data.Post;

import java.util.List;

public interface PostsContract {

    interface View{

        void setProgressIndicator(boolean active);

        void showPosts(List<Post> posts);

    }

    interface UserActionListener {

        void loadPosts(boolean forceUpdate);

        void openPostDetails(@NonNull Post post);

    }

}
