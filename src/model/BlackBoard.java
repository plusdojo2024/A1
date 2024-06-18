package model;

import java.io.Serializable;

public class BlackBoard implements Serializable {
    private int boardId;                //板書番号
    private String boardContents;       //板書内容
    private date blackBoardDatetime;    //日付

    
    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getBoardContents() {
        return boardContents;
    }

    public void setBoardContents(String boardContents) {
        this.boardContents = boardContents;
    }

    public date getBlackBoardDatetime() {
        return blackBoardDatetime;
    }

    public void setBlackBoardDatetime(date blackBoardDatetime) {
        this.blackBoardDatetime = blackBoardDatetime;
    }
}
