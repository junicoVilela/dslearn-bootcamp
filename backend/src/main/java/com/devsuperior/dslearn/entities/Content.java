package com.devsuperior.dslearn.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table
@Entity(name = "tb_content")
public class Content extends Lesson{
    private static final long serialVersionUID = 65410897630579702L;

    private String textContent;
    private String videoUri;

    public Content() {
    }

    public Content(Long id, String title, Integer position, Section section, String textContent, String videoUri) {
        super(id, title, position, section);
        this.textContent = textContent;
        this.videoUri = videoUri;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(String videoUri) {
        this.videoUri = videoUri;
    }

}
