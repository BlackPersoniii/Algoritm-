public class QuickSortTrace {

    public static void quickSort(int[] a, int left, int right) {
        System.out.println("\nВиклик quickSort(" + left + ", " + right + ")");

        if (left < right) {
            int q = partition(a, left, right);
            System.out.println("Розбиття завершено. Вихід-індекс = " + q);
            System.out.println("Поточний стан масиву: " + arrayToString(a));

            quickSort(a, left, q);
            quickSort(a, q + 1, right);
        } else {
            System.out.println("-> Повертаємо, l >= r");
        }
    }

    public static int partition(int[] a, int left, int right) {

        int pivot = a[left];
        System.out.println("Partition(" + left + ", " + right + "), pivot = " + pivot);

        int i = left - 1;
        int j = right + 1;

        while (true) {

            do {
                i++;
            } while (a[i] < pivot);

            do {
                j--;
            } while (a[j] > pivot);

            if (i >= j) {
                System.out.println("-> Повертаємо j = " + j);
                return j;
            }

            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;

            System.out.println("Обмін a[" + i + "] і a[" + j + "]: " + arrayToString(a));
        }
    }

    public static void main(String[] args) {

        int[] a = {58, 5, 50, 99, 61, 32, 27, 45, 75};

        System.out.println("Початковий масив: " + arrayToString(a));

        quickSort(a, 0, a.length - 1);

        System.out.println("\nВідсортований масив: " + arrayToString(a));
    }

    private static String arrayToString(int[] a) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]);
            if (i < a.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
