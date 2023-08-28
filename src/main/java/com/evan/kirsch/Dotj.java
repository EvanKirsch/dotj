package com.evan.kirsch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Dotj {

    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        try {
            System.out.println(parseDotFile(args[0]));
        } catch (Exception e) {
            System.out.println("Failed ");
            e.printStackTrace();
        }
    }

    public static DirectedGraph<String> parseDotFile(String filePath) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        DirectedGraph<String> graph = null;

        try {

        } catch (Exception e) {
            System.out.println(e);
        }

        return graph;
    }
}
