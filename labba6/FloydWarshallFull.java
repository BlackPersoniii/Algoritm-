import java.util.Arrays;

public class FloydWarshallFull {

    static final double INF = Double.POSITIVE_INFINITY;

    static void printMatrix(double[][] matrix, String title) {
        System.out.println("\n=== " + title + " ===");

        String[] headers = {"", "1(a)", "2(b)", "3(c)", "4(d)", "5(e)", "6(f)", "7(g)", "8(h)"};

        System.out.printf("%5s", "");
        for (int i = 1; i < headers.length; i++) {
            System.out.printf("%6s", headers[i]);
        }
        System.out.println();

        String[] labels = {"1(a)", "2(b)", "3(c)", "4(d)", "5(e)", "6(f)", "7(g)", "8(h)"};

        for (int i = 0; i < matrix.length; i++) {
            System.out.printf("%5s", labels[i]);
            for (int j = 0; j < matrix[i].length; j++) {
                if (Double.isInfinite(matrix[i][j])) {
                    System.out.printf("%6s", "∞");
                } else {
                    System.out.printf("%6.0f", matrix[i][j]);
                }
            }
            System.out.println();
        }
    }

    static double[][] floydWarshall(double[][] W) {
        int n = W.length;
        double[][] dist = new double[n][n];

        for (int i = 0; i < n; i++)
            dist[i] = Arrays.copyOf(W[i], n);

        printMatrix(dist, "Початкова матриця W");

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    if (!Double.isInfinite(dist[i][k]) && !Double.isInfinite(dist[k][j])) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {

        System.out.println("АЛГОРИТМ ФЛОЙДА-УОРШЕЛЛА");

        double INF = Double.POSITIVE_INFINITY;

        double[][] matrix = {
                {0,    2,    5,    1,    INF, INF, 1,    INF},
                {2,    0,    INF,  3,    9,    INF, INF, INF},
                {5,    INF,  0,    9,    2,    4,    4,    6},
                {1,    3,    9,    0,    5,    5,    INF, INF},
                {INF,  9,    2,    5,    0,    3,    INF, INF},
                {INF,  INF,  4,    5,    3,    0,    INF, 1},
                {1,    INF,  4,    INF,  INF,  INF, 0,    6},
                {INF,  INF,  6,    INF,  INF,  1,    6,    0}
        };

        double[][] result = floydWarshall(matrix);
        printMatrix(result, "Фінальна матриця D^(8)");

        double[][] manual = {
                {0, 2, 5, 1, 6, 6, 1, 7},
                {2, 0, 7, 3, 8, 8, 3, 9},
                {5, 7, 0, 6, 2, 4, 4, 5},
                {1, 3, 6, 0, 5, 5, 2, 6},
                {6, 8, 2, 5, 0, 3, 6, 4},
                {6, 8, 4, 5, 3, 0, 7, 1},
                {1, 3, 4, 2, 6, 7, 0, 6},
                {7, 9, 5, 6, 4, 1, 6, 0}
        };

        int matches = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Math.abs(result[i][j] - manual[i][j]) < 1e-9)
                    matches++;
            }
        }

        System.out.println("\n✓ Відповідність: " + matches + "/64 = " + (matches * 100.0 / 64) + "%");

        System.out.println("\nОсновні шляхи:");
        System.out.println("3→8: 5 (через 6)");
        System.out.println("4→8: 6 (через 6)");
        System.out.println("5→8: 4 (через 6)");
        System.out.println("5→7: 6 (через 3)");
        System.out.println("6→7: 7 (через 4→1)");
    }
} 
