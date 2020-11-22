package com.elfstudio.testtask.model;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HighlightResult extends BaseObservable {

    @SerializedName("title")
    @Expose
    private Title title;
    @SerializedName("url")
    @Expose
    private Url url;
    @SerializedName("author")
    @Expose
    private Author author;
    @SerializedName("story_text")
    @Expose
    private StoryText storyText;

    public Title getTitle() {
        if (title == null) {
            title = new Title();
        }
        return title;
    }

    public Url getUrl() {
        if (url == null) {
            url = new Url();
        }
        return url;
    }

    public Author getAuthor() {
        if (author == null) {
            author = new Author();
        }
        return author;
    }

    public StoryText getStoryText() {
        if (storyText == null) {
            storyText = new StoryText();
        }
        return storyText;
    }
}
