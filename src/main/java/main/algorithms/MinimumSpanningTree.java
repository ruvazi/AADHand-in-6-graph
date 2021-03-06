package main.algorithms;

import main.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinimumSpanningTree {
    public List<Edge> getMST(Graph graph) {
        List<Edge> allEdges = new ArrayList<Edge>();
        for (Node node : graph.getNodes()) {
            for (Edge edge : node.getEdges()) {
                allEdges.add(edge);
            }
        }
        EdgeComparator comparator = new EdgeComparator();

        Collections.sort(allEdges, comparator);
        DisjointSet disjointSet = new DisjointSet();

        for (Node node : graph.getNodes()) {
            disjointSet.makeSet(node.getId());
        }

        List<Edge> resultEdges = new ArrayList<Edge>();

        for (Edge edge : allEdges) {
            long root1 = disjointSet.findSet(edge.getFrom().getId());
            long root2 = disjointSet.findSet(edge.getTo().getId());

            if(root1 == root2) {
                continue;
            } else {
                resultEdges.add(edge);
                disjointSet.union(edge.getFrom().getId(), edge.getTo().getId());
            }
        }
        return resultEdges;
    }

}
