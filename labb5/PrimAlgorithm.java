public class PrimAlgorithm {

    static final int INF = Integer.MAX_VALUE;
    static final int V = 8;

    static int[][] G = {
        {0,   2,   5,   3,   INF, INF, 1,   INF},   // 1
        {2,   0,   INF, 3,   9,   INF, INF, INF},   // 2
        {5,   INF, 0,   5,   2,   4,   4,   6},      // 3
        {3,   3,   5,   0,   9,   5,   INF, INF},    // 4
        {INF, 9,   2,   9,   0,   3,   INF, 2},      // 5
        {INF, INF, 4,   5,   3,   0,   1,   1},      // 6
        {1,   INF, 4,   INF, INF, 1,   0,   1},      // 7
        {INF, INF, 6,   INF, 2,   1,   1,   0}       // 8
    };

    public static void primMST(int[][] graph) {

        boolean[] selected = new boolean[V];
        selected[0] = true;  // починаємо з вершини 1

        int noEdge = 0;

        System.out.println("Edge : Weight\n");

        while (noEdge < V - 1) {

            int minWeight = INF;
            int u = 0, v = 0;

            for (int i = 0; i < V; i++) {
                if (selected[i]) {
                    for (int j = 0; j < V; j++) {
                        if (!selected[j] && graph[i][j] != INF && graph[i][j] != 0) {
                            if (graph[i][j] < minWeight) {
                                minWeight = graph[i][j];
                                u = i;
                                v = j;
                            }
                        }
                    }
                }
            }

            System.out.println((u + 1) + " - " + (v + 1) + " : " + minWeight);

            selected[v] = true;
            noEdge++;
        }
    }

    public static void main(String[] args) {
        primMST(G);
    }
}
