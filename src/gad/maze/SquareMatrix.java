package gad.maze;

public class SquareMatrix {
    public int[][] matrix;

    public SquareMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int[] multiplyVector(int[] vector) {
        int[] out = {matrix[0][0] * vector[0] + matrix[1][0] * vector[1],
                matrix[0][1] * vector[0] + matrix[1][1] * vector[1]};
        return out;
    }
    public static boolean compVectors(int[] v1, int[] v2) {
        if (v1.length != v2.length) return false;
        for (int i = 0; i < v1.length; i++) {
            if (v1[i] != v2[i]) {
                return false;
            }
        }
        return true;
    }
    public static int[] addVectors(int[] v1, int[] v2) throws Exception {
        if (v1.length != v2.length) {
            throw new Exception("Vectors not of same size");
        }
        int[] output = new int[v1.length];
        for (int i = 0; i < v1.length; i++) {
            output[i] = v1[i]+v2[i];
        }
        System.out.println("Output: "+output[0]+", "+output[1]);
        return output;
    }

    public int getDeterminant() {
        return matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
    }

    public SquareMatrix getInvers() {
        int det = this.getDeterminant();
        return new SquareMatrix(new int[][]{{matrix[1][1] / det, -matrix[0][1] / det}, {-matrix[1][0] / det, matrix[0][0] / det}});
    }
}