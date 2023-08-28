package com.evan.kirsch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DirectedGraph<T> {

  private Map<T, List<T>> graph = new HashMap<>();

  public void addNode(T n) {
    graph.put(n, new LinkedList<T>());
  }

  public void addEdge(T source, T destination) {

    if(!graph.containsKey(source)) {
      graph.put(source, new LinkedList<T>());
    }
    if(!graph.containsKey(destination)) {
      graph.put(destination, new LinkedList<T>());
    }

    graph.get(source).add(destination);
  }

}
