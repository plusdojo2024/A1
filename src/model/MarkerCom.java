package model;
import java.io.Serializable;
import java.sql.Date;

public class MarkerCom implements Serializable {
    private int markerComId;            //マーカーコメントＩＤ
    private String markerComContents;   //マーカーコメント内容
    private int markerId;               //マーカーID
    private Date markerComDatetime;//日付
    private String markerContents;
    public MarkerCom() {};
    public MarkerCom(int markerComId, String markerComContents, int markerId, Date markerComDatetime) {
		super();
		this.markerComId = markerComId;
		this.markerComContents = markerComContents;
		this.markerId = markerId;
		this.markerComDatetime = markerComDatetime;
	}
    public MarkerCom(int markerComId, String markerComContents, int markerId, Date markerComDatetime, String markerContents) {
        super();
        this.markerComId = markerComId;
        this.markerComContents = markerComContents;
        this.markerId = markerId;
        this.markerComDatetime = markerComDatetime;
        this.markerContents = markerContents; // 追加
    }

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

    public Date getMarkerComDatetime() {
        return markerComDatetime;
    }

    public void setMarkerComDatetime(Date markerComDatetime) {
        this.markerComDatetime = markerComDatetime;
    }
    public String getMarkerContents() {
        return markerContents;
    }

    public void setMarkerContents(String markerContents) {
        this.markerContents = markerContents;
    }
}
