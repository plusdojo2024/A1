package model;

import java.io.Serializable;

public class MarkerCom implements Serializable {
    private int markerComId;            //マーカーコメントＩＤ
    private String markerComContents;   //マーカーコメント内容
    private int markerId;               //マーカーID
    private date markerComDatetime;     //日付
    
    public int getMarkerComId() {
        return markerComId;
    }

    public void setMarkerComId(int markerComId) {
        this.markerComId = markerComId;
    }

    public String getMarkerComContents() {
        return markerComContents;
    }

    public void setMarkerComContents(String markerComContents) {
        this.markerComContents = markerComContents;
    }

    public int getMarkerId() {
        return markerId;
    }

    public void setMarkerId(int markerId) {
        this.markerId = markerId;
    }

    public date getMarkerComDatetime() {
        return markerComDatetime;
    }

    public void setMarkerComDatetime(date markerComDatetime) {
        this.markerComDatetime = markerComDatetime;
    }
}
