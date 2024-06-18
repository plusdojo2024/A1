package model;
import java.io.Serializable;

public class MarkerComFav implements Serializable{
	private int markerComNumber; /*理解度コメントリアクション通し番号*/
	private String userId; /*ユーザー通し番号*/
	private int markerComId; /*マーカーコメントＩＤ*/


    public MarkerComFav(int markerComNumber, String userId, int markerComId) {
		super();
		this.markerComNumber = markerComNumber;
		this.userId = userId;
		this.markerComId = markerComId;
	}
	public int getMarkerComNumber() {
        return markerComNumber;
    }
    public void setMarkerComNumber(int markerComNumber) {
        this.markerComNumber = markerComNumber;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public int getMarkerComId() {
        return markerComId;
    }
    public void setMarkerComId(int markerComId) {
        this.markerComId = markerComId;
    }



}
