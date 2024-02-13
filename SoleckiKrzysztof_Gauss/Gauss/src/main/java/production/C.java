package production;

public class C extends AbstractProduction{

    private final int i;
    private final int j;
    private final int k;
    private final double[][] matrix;
    private final double[][] vectors;

    public C(int i, int j, int k, double[][] matrix, double[][] vectors) {
        super();
        this.i = i;
        this.j = j;
        this.k = k;
        this.matrix = matrix;
        this.vectors = vectors;
    }

    @Override
    public void apply() {
        matrix[k][j] -= vectors[k][j];
    }
}
