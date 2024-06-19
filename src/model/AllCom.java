package model;

import java.io.Serializable;
import java.sql.Date;

public class AllCom implements Serializable {
private int allComId; //全体コメントID
private String allComContents; //全体コメント内容
private Date allComDatetime; //日付

public AllCom(int allComId, String allComContents, Date allComDatetime) {
	super();
	this.allComId = allComId;
	this.allComContents = allComContents;
	this.allComDatetime = allComDatetime;
}

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
}