package model;
import java.io.Serializable;
import java.sql.Date;

public class KingBoss implements Serializable {
    private int allComId;
    private String allComContents;
    private Date allComDatetime;
    private int allComFavNum;
    private String userId;
    private Date allComFavDatetime;
    private int boardId;
    private String boardContents;
    private Date blackBoardDatetime;
    private int markerId;
    private String markerContents;
    private Date markerDatetime;
    private int markerComId;
    private String markerComContents;
    private Date markerComDatetime;
    private int markerComNumber; /*理解度コメントリアクション通し番号*/
    private int markerRecNumber ; /* 理解度リアクション通し番号 */
	private int flagVeryGood; /*フラグよくわかった*/
	private int flagGood; /*フラグ　わかった*/
	private int flagBad; /*フラグ　あまりわからない*/
	private int flagVeryBad; /*フラグ　よくわからない*/
	private Date markerRecDatetime; /*日付*/
	private String userName ; /*ユーザー氏名*/
	private String mail ; /*メールアドレス*/
	private String pass; /*パスワード*/
	private int checkStudent ; /*生徒判別フラッグ*/

    public int getAllComId() {
        return allComId;
    }
    public void setAllComId(int allComId) {
        this.allComId = allComId;
    }
    public String getAllComContents() {
        return allComContents;
    }
    public void setAllComContents(String allComContents) {
        this.allComContents = allComContents;
    }
    public Date getAllComDatetime() {
        return allComDatetime;
    }
    public void setAllComDatetime(Date allComDatetime) {
        this.allComDatetime = allComDatetime;
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
    public Date getAllComFavDatetime() {
        return allComFavDatetime;
    }
    public void setAllComFavDatetime(Date allComFavDatetime) {
        this.allComFavDatetime = allComFavDatetime;
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
    public Date getMarkerDatetime() {
        return markerDatetime;
    }
    public void setMarkerDatetime(Date markerDatetime) {
        this.markerDatetime = markerDatetime;
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
    public Date getMarkerComDatetime() {
        return markerComDatetime;
    }
    public void setMarkerComDatetime(Date markerComDatetime) {
        this.markerComDatetime = markerComDatetime;
    }
    public int getMarkerComNumber() {
        return markerComNumber;
    }
    public void setMarkerComNumber(int markerComNumber) {
        this.markerComNumber = markerComNumber;
    }
    public int getMarkerRecNumber() {
        return markerRecNumber;
    }
    public void setMarkerRecNumber(int markerRecNumber) {
        this.markerRecNumber = markerRecNumber;
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
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public int getCheckStudent() {
        return checkStudent;
    }
    public void setCheckStudent(int checkStudent) {
        this.checkStudent = checkStudent;
    }
}
