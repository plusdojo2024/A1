package model;
import java.io.Serializable;
import java.sql.Date;

public class AllComFav implements Serializable {
	private int allComFavNum;       //全体コメントリアクション通し番号
    private String userId;          //ユーザー通し番号
    private int allComId;           //全体コメントID
    private Date allComFavDatetime; //日付


    public AllComFav(int allComFavNum, String userId, int allComId, Date allComFavDatetime) {
		super();
		this.allComFavNum = allComFavNum;
		this.userId = userId;
		this.allComId = allComId;
		this.allComFavDatetime = allComFavDatetime;
	}

    public int getAllComFavNum() {
        return allComFavNum;
    }

    public void setAllComFavNum(int allComFavNum) {
        this.allComFavNum = allComFavNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getAllComId() {
        return allComId;
    }

    public void setAllComId(int allComId) {
        this.allComId = allComId;
    }

    public Date getAllComFavDatetime() {
        return allComFavDatetime;
    }

    public void setAllComFavDatetime(Date allComFavDatetime) {
        this.allComFavDatetime = allComFavDatetime;
    }


}
