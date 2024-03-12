package main;

import paralelism.ConcurentBlockRunner;
import sprawdzarka.MyMatrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Executor e = null;
        try {
            e = new Executor(new ConcurentBlockRunner(), new MyMatrix(new BufferedReader(new FileReader("src/main/resources/input.txt"))).getMyMatrix());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        e.start();
    }
}
