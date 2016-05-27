package dsapplications;

public class MatrixMultiplication {

	private int[][] matrix1 = { { 0, 4, 1 }, { 1, 3, 4 }, { 2, 7, 2 } };

	private int[][] matrix2 = { { 3, 2, 1 }, { 1, 0, 3 }, { 3, 7, 4 } };

	private int[][] matrix3;

	public MatrixMultiplication() {
		multiply();
	}

	private void multiply() {
		matrix3 = new int[matrix1[0].length][matrix2[0].length];
		for (int i = 0; i < matrix1.length; i++) {
			int rowInc = 0;
			int[] matrix3Array = new int[matrix1[0].length];
			int[] matrix1Array = matrix1[i];
			for (int x = 0; x < matrix1Array.length; x++) {
				int sum = 0;
				int colInc = 0;
				for (int j = 0; j < matrix2.length; j++) {
					int[] matrix2Array = matrix2[j];
					sum = sum + matrix1Array[colInc] * matrix2Array[rowInc];
					colInc++;
				}
				matrix3Array[x] = sum;
				rowInc++;
			}
			matrix3[i] = matrix3Array;
		}
		System.out.println("The output matrix is: ");
		for (int i = 0; i < matrix3.length; i++) {
			for (int j = 0; j < matrix3[i].length; j++) {
				System.out.print(matrix3[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		new MatrixMultiplication();
	}

}
