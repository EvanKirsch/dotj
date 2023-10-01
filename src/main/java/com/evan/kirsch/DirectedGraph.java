package com.evan.kirsch;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DirectedGraph<T> {

  private List<Node<T>> nodes = new LinkedList<>();
  private String name;

  public void addNode(T n) {
    nodes.add(new Node<>(n, new LinkedList<>(), false, false));
  }

  public void addEdge(Edge<T> edge) {
    List<T> currentNodeLabels = nodes.stream().map(Node::getLabel).collect(Collectors.toList());

    if(!currentNodeLabels.contains(edge.getSource())) {
      addNode(edge.getSource());
    }
    if(!currentNodeLabels.contains(edge.getDestination())) {
      addNode(edge.getDestination());
    }

    Node<T> currentNode = getNode(edge.getSource());
    currentNode.getAdjacentNodes().add(edge.getDestination());
  }

  public Node<T> getNode(T label) {
    return nodes.stream()
        .filter(elt -> label.equals(elt.getLabel()))
        .findFirst()
        .orElse(null);
  }

  public void clearVisited() {
    nodes.forEach(Node::clearVisited);
  }

}
