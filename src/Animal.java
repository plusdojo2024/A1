public class Animal {
	//フィールドひとつ
	String name;

	//引数あり、戻り値なし
	public void showName(String name) {
		this.name=name;
		System.out.println(this.name+"だよ");
	}
	//引数なし、戻り値あり
	public String getName() {
		return this.name;
	}
	//引数あり、戻り値あり
	public String reMessage(String ms) {
		String msg = name+":"+ms;
		return msg;

	}

	public int getNumber() {
		return 10;
	}
}
