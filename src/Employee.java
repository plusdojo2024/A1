public class Employee {

	//フィールド
	private String name;

	//nameに値を設定するメソッド
	public void setName(String name) {
		this.name=name;
	}

	//名前を返すメソッド
	public String getName() {
		return this.name;
	}

	//2つの引数を受け取り、足し算して返すメソッド
	public int calc(int num1,int num2) {
		int sum = num1+num2;
		return sum;
	}

}