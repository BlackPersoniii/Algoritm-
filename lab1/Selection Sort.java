public class SelectionSortDemo {

    public static void main(String[] args) {

        int[] A = {58, 5, 50, 99, 61, 32, 27, 45, 75};

        System.out.println("Selection Sort:");

        for (int i = 0; i < A.length - 1; i++) {

            int minIndex = i;

            for (int j = i + 1; j < A.length; j++) {
                if (A[j] < A[minIndex]) {
                    minIndex = j;
                }
            }

            
            int temp = A[i];
            A[i] = A[minIndex];
            A[minIndex] = temp;

            System.out.print("Крок " + i + ":  Мін = " + A[i] + ",  Масив = [");
            for (int k = 0; k < A.length; k++) {
                System.out.print(A[k]);
                if (k < A.length - 1) System.out.print(", ");
            }
            System.out.println("]");
        }
    }
}
