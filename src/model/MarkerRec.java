package model;
import java.io.Serializable;
import java.util.Date;

public class MarkerRec implements Serializable {
	private int markerRecNumber ; /* 理解度リアクション通し番号 */
	private int userId ; /*ユーザー通し番号*/
	private int markerId; /*マーカーID*/
	private int flagVeryGood; /*フラグよくわかった*/
	private int flagGood; /*フラグ　わかった*/
	private int flagBad; /*フラグ　あまりわからない*/
	private int flagVeryBad; /*フラグ　よくわからない*/
	private Date markerRecDatetime; /*日付*/


    public MarkerRec(int markerRecNumber, int userId, int markerId, int flagVeryGood, int flagGood, int flagBad,
			int flagVeryBad, Date markerRecDatetime) {
		super();
		this.markerRecNumber = markerRecNumber;
		this.userId = userId;
		this.markerId = markerId;
		this.flagVeryGood = flagVeryGood;
		this.flagGood = flagGood;
		this.flagBad = flagBad;
		this.flagVeryBad = flagVeryBad;
		this.markerRecDatetime = markerRecDatetime;
	}



	public int getMarkerRecNumber() {
        return markerRecNumber;
    }
    public void setMarkerRecNumber(int markerRecNumber) {
        this.markerRecNumber = markerRecNumber;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getMarkerId() {
        return markerId;
    }
    public void setMarkerId(int markerId) {
        this.markerId = markerId;
    }
    public int getFlagVeryGood() {
        return flagVeryGood;
    }
    public void setFlagVeryGood(int flagVeryGood) {
        this.flagVeryGood = flagVeryGood;
    }
    public int getFlagGood() {
        return flagGood;
    }
    public void setFlagGood(int flagGood) {
        this.flagGood = flagGood;
    }
    public int getFlagBad() {
        return flagBad;
    }
    public void setFlagBad(int flagBad) {
        this.flagBad = flagBad;
    }
    public int getFlagVeryBad() {
        return flagVeryBad;
    }
    public void setFlagVeryBad(int flagVeryBad) {
        this.flagVeryBad = flagVeryBad;
    }
    public Date getMarkerRecDatetime() {
        return markerRecDatetime;
    }
    public void setMarkerRecDatetime(Date markerRecDatetime) {
        this.markerRecDatetime = markerRecDatetime;
    }

    //－－－－－－－－－－－－－－－－－－－－－－－－－－

	public MarkerRec() {

	}
	public void setMarkerContents(String string) {

	}



}
