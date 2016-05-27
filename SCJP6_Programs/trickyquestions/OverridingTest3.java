package trickyquestions;

class Alpha {
	int over = 1;
}

class Beta extends Alpha {
	int over = 2;
}

public class OverridingTest3 extends Beta {
	int over = 3;

	public void go() {
		Beta b = new OverridingTest3();
		Alpha a = new OverridingTest3();
		System.out.println(super.over + " " + b.over + " " + a.over);
	}

	public static void main(String[] args) {
		new OverridingTest3().go();
	}
}
