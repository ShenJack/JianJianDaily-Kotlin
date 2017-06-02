package com.example.shenjack.zhihudailyreader.data;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;


/**
 * Created by ShenJack on 2017/5/29.
 */
@Entity
public class Post {

    @Id
    private Long id;

    @NotNull
    private Date date;
    private String Content;
    private String  contentUrl;
    private String  thumbnailUrl;
    private boolean hasBeenReaden;
    @Generated(hash = 1377701131)
    public Post(Long id, @NotNull Date date, String Content, String contentUrl,
            String thumbnailUrl, boolean hasBeenReaden) {
        this.id = id;
        this.date = date;
        this.Content = Content;
        this.contentUrl = contentUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.hasBeenReaden = hasBeenReaden;
    }
    @Generated(hash = 1782702645)
    public Post() {

    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getContent() {
        return this.Content;
    }
    public void setContent(String Content) {
        this.Content = Content;
    }
    public String getContentUrl() {
        return this.contentUrl;
    }
    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }
    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
    public boolean getHasBeenReaden() {
        return this.hasBeenReaden;
    }
    public void setHasBeenReaden(boolean hasBeenReaden) {
        this.hasBeenReaden = hasBeenReaden;
    }

}
