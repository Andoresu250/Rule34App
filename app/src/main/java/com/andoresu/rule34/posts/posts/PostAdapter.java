package com.andoresu.rule34.posts.posts;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.andoresu.rule34.R;
import com.andoresu.rule34.posts.data.Post;
import com.andoresu.rule34.posts.postdetail.PostDetailActivity;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    String TAG = PostAdapter.class.getSimpleName();

    private final Context context;
    private final List<Post> posts;


    public PostAdapter(@NonNull Context context,@NonNull List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PostViewHolder holder, int position) {
        final Post post = posts.get(position);
        Glide.with(context).load(post.sampleUrl).into(holder.postImageView);

        holder.setPostItemClickListener(new PostItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick){
//                    Toast.makeText(view.getContext(), "Long " + post.id, Toast.LENGTH_SHORT).show();
                }else{
//                    Toast.makeText(view.getContext(), "Short " + post.id, Toast.LENGTH_SHORT).show();
                    Intent postDetailIntent = new Intent(context, PostDetailActivity.class);
                    postDetailIntent.putExtra("post", post);
                    view.getContext().startActivity(postDetailIntent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }



    public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        @BindView(R.id.postImageView) ImageView postImageView;

        private PostItemClickListener postItemClickListener;

        public PostViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setPostItemClickListener(PostItemClickListener postItemClickListener) {
            this.postItemClickListener = postItemClickListener;
        }

        @Override
        public void onClick(View view) {
            postItemClickListener.onClick(view, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            postItemClickListener.onClick(view, getAdapterPosition(), true);
            return true;
        }
    }

}
