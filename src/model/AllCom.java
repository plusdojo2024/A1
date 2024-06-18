package model;

import java.io.Serializable;

public class AllCom implements Serializable {
    private int allComId;           //全体コメントID
    private String allComContents;  //全体コメント内容
    private date allComDatetime;    //日付

    public int getAllComId() {
        return allComId;
    }

    public void setAllComId(int allComId) {
        this.allComId = allComId;
    }

    public String getAllComContents() {
        return allComContents;
    }

    public void setAllComContents(String allComContents) {
        this.allComContents = allComContents;
    }

    public date getAllComDatetime() {
        return allComDatetime;
    }

    public void setAllComDatetime(date allComDatetime) {
        this.allComDatetime = allComDatetime;
    }

    

    
}
