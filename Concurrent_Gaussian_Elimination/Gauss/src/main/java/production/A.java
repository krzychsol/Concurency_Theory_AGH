package production;

public class A extends AbstractProduction{

    private final int i;
    private final int j;
    private final double divider;
    private final double[][] matrix;

    public A(int i, int j, double[][] matrix, double divider) {
        super();
        this.i = i;
        this.j = j;
        this.matrix = matrix;
        this.divider = divider;
    }

    @Override
    public void apply() {
        matrix[i][j] /= divider;
    }

}
