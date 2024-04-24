package gad.maze;

public class SquareMatrix {
    public double[][] matrix;

    public SquareMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public double[] multiplyVector(double[] vector) {
        double[] out = {matrix[0][0] * vector[0] + matrix[1][0] * vector[1],
                matrix[0][1] * vector[0] + matrix[1][1] * vector[1]};
        return out;
    }

    public double getDeterminant() {
        return matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
    }

    public SquareMatrix getInvers() {
        double det = this.getDeterminant();
        return new SquareMatrix(new double[][]{{matrix[1][1] / det, -matrix[0][1] / det}, {-matrix[1][0] / det, matrix[0][0] / det}});
    }
}
