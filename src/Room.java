import java.util.ArrayList;

public class Room {

	//フィールド
	private String roomName;
	//Studentの実体しか入らないArrayListの型
	private ArrayList<Student> list;

	//newされたときに動くメソッド
	public Room() {
		//ArrayListを作成する（中身はまだない）
		this.list = new ArrayList<Student>();
	}

	//生徒を追加するメソッド
	public void addStudent(Student st) {
		list.add(st);
	}

	//生徒を全員表示するメソッド
	public void showList() {
		for(Student s : list) {
			s.showContents();
		}
	}

	//ゲッターセッター
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public ArrayList<Student> getList() {
		return list;
	}

	public void setList(ArrayList<Student> list) {
		this.list = list;
	}






}
