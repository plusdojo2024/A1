package model;

import java.io.Serializable;

public class Marker implements Serializable {
    private int markerId;           //マーカーID
    private String markerContents;  //マーカーの内容
    private int boardId;            //板書番号
    private date markerDatetime;    //日付

    
    public int getMarkerId() {
        return markerId;
    }

    public void setMarkerId(int markerId) {
        this.markerId = markerId;
    }

    public String getMarkerContents() {
        return markerContents;
    }

    public void setMarkerContents(String markerContents) {
        this.markerContents = markerContents;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public date getMarkerDatetime() {
        return markerDatetime;
    }

    public void setMarkerDatetime(date markerDatetime) {
        this.markerDatetime = markerDatetime;
    }
}
