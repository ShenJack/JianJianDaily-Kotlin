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
import java.util.List;
import java.util.SimpleTimeZone;

import org.greenrobot.greendao.annotation.Generated;


/**
 * Created by ShenJack on 2017/5/29.
 */
@Entity
public class Post {

    @Id
    protected Long id;


    protected String date;

    @NotNull
    protected String title;
    protected String ga_prefix;
    protected int type;
    protected String  thumbnailUrl;
    protected boolean hasBeenReaden;


    protected int popularity;


    @Generated(hash = 580308733)
    public Post(Long id, String date, @NotNull String title, String ga_prefix,
            int type, String thumbnailUrl, boolean hasBeenReaden, int popularity) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.ga_prefix = ga_prefix;
        this.type = type;
        this.thumbnailUrl = thumbnailUrl;
        this.hasBeenReaden = hasBeenReaden;
        this.popularity = popularity;
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


    public String getDate() {
        return this.date;
    }


    public void setDate(String date) {
        this.date = date;
    }


    public String getTitle() {
        return this.title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getGa_prefix() {
        return this.ga_prefix;
    }


    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }


    public int getType() {
        return this.type;
    }


    public void setType(int type) {
        this.type = type;
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


    public int getPopularity() {
        return this.popularity;
    }


    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

}