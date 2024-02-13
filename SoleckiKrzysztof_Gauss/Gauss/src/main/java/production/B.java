package production;

public class B extends AbstractProduction{

    private final int i;
    private final int j;
    private final int k;
    private final double[][] matrix;
    private final double[][] vectors;

    public B(int i, int j, int k, double[][] matrix, double[][] vectors) {
        super();
        this.i = i;
        this.j = j;
        this.k = k;
        this.matrix = matrix;
        this.vectors = vectors;
    }

    public void apply() {
        vectors[k][j] = matrix[i][j] * matrix[k][i];
    }
}
