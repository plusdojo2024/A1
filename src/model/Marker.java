package model;
import java.io.Serializable;
import java.sql.Date;

public class Marker implements Serializable {
    private int markerId;           //マーカーID
    private String markerContents;  //マーカーの内容
    private int boardId;            //板書番号
	private Date markerDatetime;    //日付


	public Marker(int markerId, String markerContents, int boardId, Date markerDatetime) {
		super();
		this.markerId = markerId;
		this.markerContents = markerContents;
		this.boardId = boardId;
		this.markerDatetime = markerDatetime;
	}

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

    public Date getMarkerDatetime() {
        return markerDatetime;
    }

    public void setMarkerDatetime(Date markerDatetime) {
        this.markerDatetime = markerDatetime;
    }
}
