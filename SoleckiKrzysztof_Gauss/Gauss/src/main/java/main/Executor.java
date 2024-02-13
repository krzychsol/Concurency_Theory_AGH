package main;

import paralelism.BlockRunner;
import production.A;
import production.B;
import production.C;

import java.util.Arrays;

public class Executor extends Thread {

    private final BlockRunner runner;
    private final double[][] matrix;

    public Executor(BlockRunner _runner, double[][] matrix) {
        this.runner = _runner;
        this.matrix = matrix;
    }

    @Override
    public void run() {
        int n = 15;
        double[][] vectors = new double[n][n + 1];
        initializeMatrix(vectors);

        performGaussianElimination(n, vectors);

        displayResults();
    }

    private void initializeMatrix(double[][] vectors) {
        for (double[] row : vectors) {
            Arrays.fill(row, 0);
        }
    }

    private void performGaussianElimination(int n, double[][] vectors) {
        for (int i = 0; i < n; i++) {
            createAndAddThreadA(i);
            runner.startAll();

            createAndAddThreadsB(i, n, vectors);
            runner.startAll();

            createAndAddThreadsC(i, n, vectors);
            runner.startAll();
        }

        performBackSubstitution(n);
    }

    private void createAndAddThreadA(int i) {
        for (int j = i; j < matrix.length + 1; j++) {
            A a = new A(i, j, matrix, matrix[i][i]);
            runner.addThread(a);
        }
    }

    private void createAndAddThreadsB(int i, int n, double[][] vectors) {
        for (int j = i; j < matrix.length + 1; j++) {
            for (int k = i + 1; k < n; k++) {
                B b = new B(i, j, k, matrix, vectors);
                runner.addThread(b);
            }
        }
    }

    private void createAndAddThreadsC(int i, int n, double[][] vectors) {
        for (int j = i; j < matrix.length + 1; j++) {
            for (int k = i + 1; k < n; k++) {
                C c = new C(i, j, k, matrix, vectors);
                runner.addThread(c);
            }
        }
    }

    private void performBackSubstitution(int n) {
        for (int a = n - 1; a >= 0; a--) {
            for (int b = a - 1; b >= 0; b--) {
                matrix[b][n] -= matrix[b][a] * matrix[a][n];
                matrix[b][a] = 0;
            }
        }
    }

    private void displayResults() {
        for (double[] doubles : matrix) {
            System.out.println(Arrays.toString(doubles));
        }
    }
}
