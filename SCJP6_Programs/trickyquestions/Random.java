package trickyquestions;

public class Random {
	public static void main(String[] args) {
		System.out.println("Your permutation is being worked out...");
		int oneCount = 0;
		for (int i = 0; i <= 1000000000; i++) {
			if ((int) (Math.random() * 100) >= 50) {
				oneCount += 1;
			}
		}
		System.out.println(oneCount);
		if(oneCount > 500000000) {
			System.out.println("Go ahead");
		} else {
			System.out.println("Nopes");
		}
	}

}
