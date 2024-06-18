package model;
//
import java.io.Serializable;

public class Users implements Serializable{
	private int userId ; /*ユーザー通し番号*/
	private String userName ; /*ユーザー氏名*/
	private String mail ; /*メールアドレス*/
	private String pass; /*パスワード*/
	private int checkStudent ; /*生徒判別フラッグ*/

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
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
