public class Three{
	public static void main(String args[]) {

	Employee a = new Employee ();
	a.setName("山下");

	String nam =a.getName();

	System.out.println(nam +"だよ。");

	int sm = a.calc(10,20);
	System.out.println(sm + "が合計値です。");

	}
}
