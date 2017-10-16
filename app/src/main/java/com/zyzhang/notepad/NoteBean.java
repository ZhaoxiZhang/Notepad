package com.zyzhang.notepad;


import org.litepal.crud.DataSupport;

import java.io.Serializable;

public class NoteBean extends DataSupport implements Serializable {
    private int id;
    private String content;
    private String modifyTime;

    public NoteBean(){

    }

    public NoteBean(int id,String content){
        this.id = id;
        this.content = content;
    }

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

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}
