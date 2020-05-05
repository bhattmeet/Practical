package com.meet.practical.model.entity.response;

import java.util.ArrayList;

public class PracticalResponse{

    private MetaInfo _meta;
    private ArrayList<PracticalInfo> result;

    public MetaInfo get_meta() {
        return _meta;
    }

    public void set_meta(MetaInfo _meta) {
        this._meta = _meta;
    }

    public ArrayList<PracticalInfo> getResult() {
        return result;
    }

    public void setResult(ArrayList<PracticalInfo> result) {
        this.result = result;
    }
}
