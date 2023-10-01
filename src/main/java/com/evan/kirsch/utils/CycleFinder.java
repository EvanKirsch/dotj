package com.evan.kirsch.utils;

import com.evan.kirsch.DirectedGraph;
import com.evan.kirsch.Node;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class CycleFinder<T> {

  public boolean hasCycle(DirectedGraph<T> graph) {
    return !getCycles(graph).isEmpty();
  }

  public Map<T, List<T>> getCycles(DirectedGraph<T> graph) {
    Map<T, List<T>> cycles = new HashMap<>();
    List<Node<T>> nodes = graph.getNodes();
    for (Node<T> node : nodes) {
      List<T> cycle = getCycleRecursive(node, graph, new Stack<>());
      if(cycle != null) {
        cycles.put(node.getLabel(), cycle);
      }
      graph.clearVisited();
    }
    return cycles;
  }

  private List<T> getCycleRecursive(Node<T> source, DirectedGraph<T> graph, Stack<T> stack) {
    source.setBeingVisited(true);
    stack.push(source.getLabel());

    for (T neighborLabel : source.getAdjacentNodes()) {
      Node<T> neighbor = graph.getNode(neighborLabel);
      if (neighbor.isBeingVisited()) {
        return stack;
      } else if (!neighbor.isVisited() && getCycleRecursive(neighbor, graph, stack) != null) {
        return stack;
      }
    }

    stack.pop();
    source.setBeingVisited(false);
    source.setVisited(true);
    return null;
  }

}
