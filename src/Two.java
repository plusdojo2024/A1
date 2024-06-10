
public class Two {
	public static void main(String args[]) {

		Animal an = new Animal();
		an.showName("yamada");

		String ms = an.getName();
		System.out.println(ms);

		String msg = an.reMessage("こんにちは");
		System.out.println(msg);

		int n = an.getNumber();
		System.out.println(n);

	}
}










