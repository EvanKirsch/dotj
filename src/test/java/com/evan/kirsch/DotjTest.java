package com.evan.kirsch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.evan.kirsch.utils.CycleFinder;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class DotjTest {

  @Test
  public void simpleTest() {
    try {
      DirectedGraph<String> dj = Dotj.parseDotFile("src/test/resources/summary.dot");

      List<String> adjacentNodes = dj.getNode("com.evan.kirsch").getAdjacentNodes();

      assertEquals(4, adjacentNodes.size());
      assertTrue(adjacentNodes.contains("java.lang"));
      assertTrue(adjacentNodes.contains("org.springframework.boot"));
      assertTrue(adjacentNodes.contains("org.springframework.boot.autoconfigure"));
      assertTrue(adjacentNodes.contains("org.springframework.context"));

      System.out.println(dj);
    } catch (Exception e) {
      e.printStackTrace();
      fail(e.getMessage());
    }
  }

  @Test
  public void cycleTest() {
    CycleFinder<String> cycleFinder = new CycleFinder<>();

    try {

      DirectedGraph<String> noCycle = Dotj.parseDotFile("src/test/resources/summary.dot");
      assertFalse(cycleFinder.hasCycle(noCycle));

      DirectedGraph<String> yesCycle = Dotj.parseDotFile("src/test/resources/cycle_summary.dot");
      assertTrue(cycleFinder.hasCycle(yesCycle));

      Map<String, List<String>> cycles = cycleFinder.getCycles(yesCycle);
      assertTrue(cycles.containsKey("com.evan.kirsch --> org.springframework.context"));
      assertEquals(1, cycles.size());

      List<String> cycle = cycles.get("com.evan.kirsch --> org.springframework.context");
      assertTrue(cycle.contains("org.springframework.context"));
      assertTrue(cycle.contains("com.evan.kirsch"));
      assertEquals(2, cycle.size());

      System.out.println(cycles);
    } catch (Exception e) {
      e.printStackTrace();
      fail(e.getMessage());
    }
  }

  @Test
  public void longCycleTest() {
    CycleFinder<String> cycleFinder = new CycleFinder<>();

    try {

      DirectedGraph<String> graph = Dotj.parseDotFile("src/test/resources/long_cycle.dot");
      assertTrue(cycleFinder.hasCycle(graph));

      Map<String, List<String>> cycles = cycleFinder.getCycles(graph);
      assertEquals(1, cycles.size());

      List<String> cycle = cycles.get("a --> d");
      assertTrue(cycle.contains("a"));
      assertTrue(cycle.contains("b"));
      assertTrue(cycle.contains("c"));
      assertTrue(cycle.contains("d"));
      assertEquals(4, cycle.size());

      System.out.println(cycles);
    } catch (Exception e) {
      e.printStackTrace();
      fail(e.getMessage());
    }
  }

}
