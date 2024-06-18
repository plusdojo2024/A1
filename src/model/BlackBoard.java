package model;
import java.io.Serializable;
import java.sql.Date;

public class BlackBoard implements Serializable {
    private int boardId;                //板書番号
    private String boardContents;       //板書内容
    private Date blackBoardDatetime;    //日付


    public BlackBoard(int boardId, String boardContents, Date blackBoardDatetime) {
		super();
		this.boardId = boardId;
		this.boardContents = boardContents;
		this.blackBoardDatetime = blackBoardDatetime;
	}

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

    public Date getBlackBoardDatetime() {
        return blackBoardDatetime;
    }

    public void setBlackBoardDatetime(Date blackBoardDatetime) {
        this.blackBoardDatetime = blackBoardDatetime;
    }
}
