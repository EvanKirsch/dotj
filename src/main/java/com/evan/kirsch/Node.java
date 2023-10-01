package com.evan.kirsch;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Node<T> {

  private T label;
  private List<T> adjacentNodes;
  private boolean beingVisited;
  private boolean visited;

  @Override
  public int hashCode() {
    return label.hashCode();
  }

  public void clearVisited() {
    this.beingVisited = false;
    this.visited = false;
  }

}