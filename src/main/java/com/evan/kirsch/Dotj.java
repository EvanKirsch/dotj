package com.evan.kirsch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Dotj {

    private static final String EDGE_SYMBOL = "->";

    public static void main( String[] args ) {
        try {
            System.out.println(parseDotFile(args[0]));
        } catch (Exception e) {
            System.out.println("Failed ");
            e.printStackTrace();
        }
    }

    public static DirectedGraph<String> parseDotFile(String filePath) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        DirectedGraph<String> graph = new DirectedGraph<>();

        try {
            String line = reader.readLine();

            while (line != null) {
                if(graph.getName() == null) {
                    graph.setName(line);
                } else if (isEdge(line)) {
                    Edge<String> edge = getEdge(line);
                    graph.addEdge(edge);
                }
                line = reader.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return graph;
    }

    private static Edge<String> getEdge(String line) {
        String[] nodes = line.split(EDGE_SYMBOL);
        String source = getNodeName(nodes[0]);
        String destination = getNodeName(nodes[1]);
        return new Edge<>(source, destination);
    }

    private static String getNodeName(String node) {
        String nName = node.trim();
        nName = nName.replace("\"", "");
        nName = nName.replace(";", "");
        nName = nName.replaceAll("\\(([^\\)]+)\\)", "");
        return nName;
    }

    private static boolean isEdge(String line) {
        return line.contains(EDGE_SYMBOL);
    }
}
