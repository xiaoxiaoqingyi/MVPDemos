package com.magicing.social3d.model.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/28.
 */

public class Note implements Serializable {

    private int id;
    private String content;
    private String path;
    private String domain;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
