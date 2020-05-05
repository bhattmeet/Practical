package com.meet.practical.model.entity.response;

import org.parceler.Parcel;

@Parcel
public class ModuleInfo {
    int id;
    String title;
    String name;
    String image;
    String description;
    String duration;
    boolean check;

    public void copyData(ModuleInfo data) {
        this.id = data.getId();
        this.title = data.getTitle();
        this.name = data.getName();
        this.image = data.getImage();
        this.description = data.getDescription();
        this.duration = data.getDuration();
        this.check = data.isCheck();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
