package model;
import java.io.Serializable;

public class MarkerComFav implements Serializable{
	private int markerComNumber; /*理解度コメントリアクション通し番号*/
	private int userId; /*ユーザー通し番号*/
	private int markerComId; /*マーカーコメントＩＤ*/
    
    public int getMarkerComNumber() {
        return markerComNumber;
    }
    public void setMarkerComNumber(int markerComNumber) {
        this.markerComNumber = markerComNumber;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getMarkerComId() {
        return markerComId;
    }
    public void setMarkerComId(int markerComId) {
        this.markerComId = markerComId;
    }



}
