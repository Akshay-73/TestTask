package com.elfstudio.testtask.model;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Hit extends BaseObservable {

    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("points")
    @Expose
    private Integer points;
    @SerializedName("story_text")
    @Expose
    private String storyText;
    @SerializedName("comment_text")
    @Expose
    private String commentText;
    @SerializedName("num_comments")
    @Expose
    private Integer numComments;
    @SerializedName("story_id")
    @Expose
    private Integer storyId;
    @SerializedName("story_title")
    @Expose
    private String storyTitle;
    @SerializedName("story_url")
    @Expose
    private String storyUrl;
    @SerializedName("parent_id")
    @Expose
    private Integer parentId;
    @SerializedName("created_at_i")
    @Expose
    private Integer createdAtI;
    @SerializedName("_tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("objectID")
    @Expose
    private String objectID;
    @SerializedName("_highlightResult")
    @Expose
    private HighlightResult highlightResult;

    private boolean isChecked;

    public String getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getPoints() {
        return points;
    }

    public String getStoryText() {
        return storyText;
    }

    public String getCommentText() {
        return commentText;
    }

    public Integer getNumComments() {
        return numComments;
    }

    public Integer getStoryId() {
        return storyId;
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public String getStoryUrl() {
        return storyUrl;
    }

    public Integer getParentId() {
        return parentId;
    }

    public Integer getCreatedAtI() {
        return createdAtI;
    }

    public List<String> getTags() {
        if (tags == null) {
            tags = new ArrayList<>();
        }
        return tags;
    }

    public String getObjectID() {
        return objectID;
    }

    public HighlightResult getHighlightResult() {
        if (highlightResult == null) {
            highlightResult = new HighlightResult();
        }
        return highlightResult;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
