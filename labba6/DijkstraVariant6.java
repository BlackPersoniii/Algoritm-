import java.util.*;

public class DijkstraVariant6 {

    // --------------------------------------------
    // Класс для ребра
    // --------------------------------------------
    static class Edge {
        int to;
        int weight;

        Edge(int t, int w) {
            this.to = t;
            this.weight = w;
        }
    }

    // --------------------------------------------
    // Алгоритм Дейкстры (аналог Python)
    // --------------------------------------------
    public static Map<String, Map<Integer, Integer>> dijkstra(
            Map<Integer, List<Edge>> graph, int start) {

        Map<Integer, Integer> dist = new HashMap<>();
        Map<Integer, Integer> pred = new HashMap<>();

        // Инициализация
        for (Integer node : graph.keySet()) {
            dist.put(node, Integer.MAX_VALUE);
            pred.put(node, null);
        }

        dist.put(start, 0);

        // PriorityQueue: (dist, node)
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[]{0, start});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curDist = cur[0];
            int curNode = cur[1];

            if (curDist > dist.get(curNode)) continue;

            for (Edge e : graph.get(curNode)) {
                int newDist = curDist + e.weight;

                if (newDist < dist.get(e.to)) {
                    dist.put(e.to, newDist);
                    pred.put(e.to, curNode);
                    pq.add(new int[]{newDist, e.to});
                }
            }
        }

        Map<String, Map<Integer, Integer>> result = new HashMap<>();
        result.put("dist", dist);
        result.put("pred", pred);
        return result;
    }

    // --------------------------------------------
    // Восстановление пути (аналог Python)
    // --------------------------------------------
    public static String reconstructPath(Map<Integer, Integer> pred, int start, int end) {
        if (start == end) return String.valueOf(start);

        List<Integer> path = new ArrayList<>();
        Integer cur = end;

        if (pred.get(end) == null) return "Шлях не знайдено";

        while (cur != null && cur != start) {
            path.add(cur);
            cur = pred.get(cur);
        }

        if (cur == null) return "Шлях не знайдено";

        path.add(start);
        Collections.reverse(path);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            sb.append(path.get(i));
            if (i + 1 < path.size()) sb.append(" -> ");
        }
        return sb.toString();
    }

    // --------------------------------------------
    // MAIN + граф варианта 6
    // --------------------------------------------
    public static void main(String[] args) {

        Map<Integer, List<Edge>> graph = new HashMap<>();

        for (int i = 1; i <= 8; i++) graph.put(i, new ArrayList<>());

        // Граф варианта 6 (неорієнтований)
        graph.get(1).addAll(List.of(new Edge(2, 2), new Edge(3, 5), new Edge(4, 1), new Edge(7, 1)));
        graph.get(2).addAll(List.of(new Edge(1, 2), new Edge(4, 3), new Edge(5, 9)));
        graph.get(3).addAll(List.of(new Edge(1, 5), new Edge(4, 9), new Edge(5, 2), new Edge(6, 4), new Edge(7, 4), new Edge(8, 6)));
        graph.get(4).addAll(List.of(new Edge(1, 1), new Edge(2, 3), new Edge(3, 9), new Edge(5, 5), new Edge(6, 5)));
        graph.get(5).addAll(List.of(new Edge(2, 9), new Edge(3, 2), new Edge(4, 5), new Edge(6, 3)));
        graph.get(6).addAll(List.of(new Edge(3, 4), new Edge(4, 5), new Edge(5, 3), new Edge(8, 1)));
        graph.get(7).addAll(List.of(new Edge(1, 1), new Edge(3, 4), new Edge(8, 6)));
        graph.get(8).addAll(List.of(new Edge(3, 6), new Edge(6, 1), new Edge(7, 6)));

        int start = 1;

        // Запуск Дейкстры
        Map<String, Map<Integer, Integer>> res = dijkstra(graph, start);
        Map<Integer, Integer> dist = res.get("dist");
        Map<Integer, Integer> pred = res.get("pred");

        // Вывод результатов
        System.out.println("Початкова вершина: " + start + "\n");

        System.out.println("Найкоротші відстані (dist):");
        dist.keySet().stream().sorted().forEach(v ->
                System.out.println("  Вершина " + v + ": " + dist.get(v))
        );

        System.out.println("\nПопередники (pred):");
        pred.keySet().stream().sorted().forEach(v ->
                System.out.println("  Вершина " + v + ": " + pred.get(v))
        );

        System.out.println("\nНайкоротші шляхи:");
        graph.keySet().stream().sorted().forEach(v -> {
            if (v == start)
                System.out.println("  До вершини " + v + ": " + v + " (Відстань: 0)");
            else
                System.out.println("  До вершини " + v + ": " +
                        reconstructPath(pred, start, v) +
                        " (Відстань: " + dist.get(v) + ")");
        });
    }
} 
