package com.flamboyantes.model.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("picture_id")
    @Expose
    private Integer pictureId;
    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("src")
    @Expose
    private String src;
    @SerializedName("attachment")
    @Expose
    private Object attachment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Object getAttachment() {
        return attachment;
    }

    public void setAttachment(Object attachment) {
        this.attachment = attachment;
    }

}