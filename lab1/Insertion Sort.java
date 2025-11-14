public class InsertionSortDemo {

    public static void main(String[] args) {

        int[] A = {58, 5, 50, 99, 61, 32, 27, 45, 75};

        System.out.println("Insertion Sort:");

        for (int i = 1; i < A.length; i++) {

            int key = A[i];
            int j = i - 1;

            while (j >= 0 && A[j] > key) {
                A[j + 1] = A[j];
                j--;
            }

            A[j + 1] = key;

            System.out.print("Крок " + i + ":  key = " + key + ",  Масив = [");
            for (int k = 0; k < A.length; k++) {
                System.out.print(A[k]);
                if (k < A.length - 1) System.out.print(", ");
            }
            System.out.println("]");
        }
    }
}
