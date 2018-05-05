package com.andoresu.rule34.posts.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("file_url")
    @Expose
    public String fileUrl;
    @SerializedName("rating")
    @Expose
    public String rating;
    @SerializedName("score")
    @Expose
    public String score;
    @SerializedName("source")
    @Expose
    public String source;
    @SerializedName("has_children")
    @Expose
    public String hasChildren;
    @SerializedName("change")
    @Expose
    public String change;
    @SerializedName("preview_width")
    @Expose
    public String previewWidth;
    @SerializedName("parent_id")
    @Expose
    public String parentId;
    @SerializedName("height")
    @Expose
    public String height;
    @SerializedName("preview_height")
    @Expose
    public String previewHeight;
    @SerializedName("has_notes")
    @Expose
    public String hasNotes;
    @SerializedName("preview_url")
    @Expose
    public String previewUrl;
    @SerializedName("sample_height")
    @Expose
    public String sampleHeight;
    @SerializedName("has_comments")
    @Expose
    public String hasComments;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("sample_width")
    @Expose
    public String sampleWidth;
    @SerializedName("width")
    @Expose
    public String width;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("sample_url")
    @Expose
    public String sampleUrl;
    @SerializedName("tags")
    @Expose
    public String tags;
    @SerializedName("md5")
    @Expose
    public String md5;
    @SerializedName("creator_id")
    @Expose
    public String creatorId;

}