package com.example.sample.androidsample.retrofit.github.models;

public class License {
    private String key;
    private String name;
    private String spdx_id;
    private String url;

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpdxId() {
        return this.spdx_id;
    }

    public void setSpdxId(String spdx_id) {
        this.spdx_id = spdx_id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
