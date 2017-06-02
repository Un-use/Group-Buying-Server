package com.unuse.file.api;

import java.io.Serializable;

/**
 * Created by Unuse on 2017/6/2.
 */

public class FileData implements Serializable {

    private String name;

    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
