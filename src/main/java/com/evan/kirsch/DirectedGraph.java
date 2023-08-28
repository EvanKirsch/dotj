package com.evan.kirsch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DirectedGraph<T> {

  private Map<T, List<T>> graph = new HashMap<>();
  private String name;

  public void addNode(T n) {
    graph.put(n, new LinkedList<T>());
  }

  public void addEdge(Edge<T> edge) {

    if(!graph.containsKey(edge.getSource())) {
      addNode(edge.getSource());
    }
    if(!graph.containsKey(edge.getDestination())) {
      addNode(edge.getDestination());
    }

    graph.get(edge.getSource()).add(edge.getDestination());
  }

}
