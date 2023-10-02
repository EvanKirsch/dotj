package com.evan.kirsch.utils;

import com.evan.kirsch.DirectedGraph;
import com.evan.kirsch.Node;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

public class CycleFinder<T> {

  public boolean hasCycle(DirectedGraph<T> graph) {
    return !getCycles(graph).isEmpty();
  }

  public Map<String, List<T>> getCycles(DirectedGraph<T> graph) {
    Map<T, List<T>> cycles = new HashMap<>();
    List<Node<T>> nodes = graph.getNodes();
    for (Node<T> node : nodes) {
      List<T> cycle = getCycleRecursive(node, graph, new Stack<>());
      if (cycle != null) {
        cycles.put(node.getLabel(), cycle);
      }
      graph.clearVisited();
    }
    return filterCycles(cycles);
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

  private Map<String, List<T>> filterCycles(Map<T, List<T>> nodeCycles) {
    Map<String, List<T>> namedCycles = new HashMap<>();
    for (T node : nodeCycles.keySet()) {
      List<T> curCycle = nodeCycles.get(node);

      if (!containsGraph(curCycle, namedCycles)) {
        String cycleName = curCycle.get(0).toString()
            + " --> "
            + curCycle.get(curCycle.size() - 1).toString();

        namedCycles.put(cycleName, curCycle);
      }
    }
    return namedCycles;
  }

  private boolean containsGraph(List<T> graph, Map<?, List<T>> graphs) {
    return graphs.values().stream()
        .filter(elt -> elt.containsAll(graph) && elt.size() == graph.size()).count() > 0;
    }

}
