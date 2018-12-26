package com.kieran.api.model;

public class NewProject {

    private String name;
    private String symLink;
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymLink() {
        return symLink;
    }

    public void setSymLink(String symLink) {
        this.symLink = symLink;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
