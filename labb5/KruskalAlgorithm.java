import java.util.*;

public class KruskalAlgorithm {

    static class Edge {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    static class Graph {
        int V;
        List<Edge> G = new ArrayList<>();
        List<Edge> T = new ArrayList<>();
        int[] parent;

        Graph(int V) {
            this.V = V;
            parent = new int[V];
            for (int i = 0; i < V; i++)
                parent[i] = i;
        }

        void AddWeightedEdge(int u, int v, int w) {
            G.add(new Edge(u, v, w));
        }

        int find_set(int i) {
            if (i == parent[i])
                return i;
            parent[i] = find_set(parent[i]);
            return parent[i];
        }

        boolean union_set(int u, int v) {
            int uRep = find_set(u);
            int vRep = find_set(v);

            if (uRep != vRep) {
                parent[uRep] = vRep;
                return true;
            }
            return false;
        }

        void kruskal() {
            G.sort(Comparator.comparingInt(e -> e.w));

            for (Edge e : G) {
                int uRep = find_set(e.u);
                int vRep = find_set(e.v);

                if (uRep != vRep) {
                    T.add(e);
                    union_set(e.u, e.v);
                }
            }
        }

        void print_mst() {
            int total = 0;

            System.out.println("Edge : Weight\n");
            for (Edge e : T) {
                System.out.println((e.u + 1) + " - " + (e.v + 1) + " : " + e.w);
                total += e.w;
            }

            System.out.println("\nЗагальна вартість MST: " + total);
        }
    }

    public static void main(String[] args) {

        int V = 8;
        Graph g = new Graph(V);

        // Ребра графа варіанта 6 (u, v, weight)
        int[][] edges = {
            {1, 7, 1}, {7, 6, 1}, {6, 8, 1},
            {1, 2, 2}, {5, 3, 2}, {8, 5, 2},
            {1, 4, 3}, {2, 4, 3}, {5, 6, 3},
            {3, 6, 4}, {7, 3, 4},
            {1, 3, 5}, {4, 6, 5},
            {3, 8, 6},
            {2, 5, 9}, {4, 5, 9}
        };

        // додавання ребер (у нумерацію 0–7)
        for (int[] e : edges) {
            g.AddWeightedEdge(e[0] - 1, e[1] - 1, e[2]);
        }

        System.out.println("--- АЛГОРИТМ КРУСКАЛА (Варіант 6) ---");

        g.kruskal();
        g.print_mst();
    }
}
