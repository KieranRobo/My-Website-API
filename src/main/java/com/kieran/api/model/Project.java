package com.kieran.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "projects")
public class Project {

    @Column(name = "id", nullable = false)
    private @Id @GeneratedValue int id;

    @Column(name = "date_created", nullable = false)
    private @GeneratedValue Date dateCreated;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "last_modified", nullable = false)
    private @GeneratedValue Date lastModified;

    @Column(name = "display_name", nullable = false)
    private String name;

    @Column(name = "link_name", nullable = false)
    private String symLink;

    @Column(name = "display_content", nullable = false)
    private String content;

    public Project(int id, Date dateCreated, Date lastModified, String name, String symLink, String content) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.lastModified = lastModified;

        this.name = name;
        this.symLink = symLink;
        this.content = content;
    }

    public Project() {
    }

    public int getId() {
        return id;
    }


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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
