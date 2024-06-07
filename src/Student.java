
public class Student {

	//フィールド
	private int no;
	private String name;

	//newされたときに動くコンストラクタ（フィールドの値を設定する）
	public Student(int no,String name) {
		this.no=no;
		this.name=name;
	}

	//自分のnoとnameを言う（表示する）メソッド
	public void showContents() {
		System.out.println("出席番号："+this.no+"番！"+this.name+"です！");
	}

	//セッターゲッター
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}





}
