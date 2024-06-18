package model;

import java.io.Serializable;

public class AllComFav implements Serializable {
    private int allComFavNum;       //全体コメントリアクション通し番号
    private String userId;          //ユーザー通し番号
    private int allComId;           //全体コメントID
    private date allComFavDatetime; //日付

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

    public date getAllComFavDatetime() {
        return allComFavDatetime;
    }

    public void setAllComFavDatetime(date allComFavDatetime) {
        this.allComFavDatetime = allComFavDatetime;
    }


}
