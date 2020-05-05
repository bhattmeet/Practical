package com.meet.practical.model.entity.eventbus;


import com.meet.practical.model.entity.response.ModuleInfo;

import java.util.List;

public class ModuleSelection {
    private int id;
    private String name;
    private String image;
    private String ids;
    private int type;
    private boolean singleChoice;
    private List<ModuleInfo> moduleList;

    public ModuleSelection(int id, String name, String image, int type, boolean singleChoice, List<ModuleInfo> moduleList) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.type = type;
        this.singleChoice = singleChoice;
        this.moduleList = moduleList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isSingleChoice() {
        return singleChoice;
    }

    public void setSingleChoice(boolean singleChoice) {
        this.singleChoice = singleChoice;
    }

    public List<ModuleInfo> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<ModuleInfo> moduleList) {
        this.moduleList = moduleList;
    }

}
