import java.util.*;

public class ClosedHashTableUA {

    static final int M = 13;
    static final double A = (Math.sqrt(5) - 1) / 2;

    String[] table = new String[M];

    static final Map<Character, Integer> alphabet = Map.ofEntries(
        Map.entry('А',1), Map.entry('Б',2), Map.entry('В',3), Map.entry('Г',4),
        Map.entry('Ґ',5), Map.entry('Д',6), Map.entry('Е',7), Map.entry('Є',8),
        Map.entry('Ж',9), Map.entry('З',10), Map.entry('И',11), Map.entry('І',12),
        Map.entry('Ї',13), Map.entry('Й',14), Map.entry('К',15), Map.entry('Л',16),
        Map.entry('М',17), Map.entry('Н',18), Map.entry('О',19), Map.entry('П',20),
        Map.entry('Р',21), Map.entry('С',22), Map.entry('Т',23), Map.entry('У',24),
        Map.entry('Ф',25), Map.entry('Х',26), Map.entry('Ц',27), Map.entry('Ч',28),
        Map.entry('Ш',29), Map.entry('Щ',30), Map.entry('Ь',31), Map.entry('Ю',32),
        Map.entry('Я',33)
    );

    int keyFromWord(String word) {
        int sum = 0;
        for (char ch : word.toUpperCase().toCharArray())
            if (alphabet.containsKey(ch)) sum += alphabet.get(ch);
        return sum;
    }

    int hashDivision(int key) {
        return key % M;
    }

    int hashMultiplication(int key) {
        double fractional = (key * A) % 1;
        return (int) Math.floor(16 * fractional);
    }

    void insert(String word, boolean useDivision) {
        int key = keyFromWord(word);
        int index = useDivision ? hashDivision(key) : hashMultiplication(key);
        int i = 0;
        while (table[(index + i) % M] != null)
            i++;
        table[(index + i) % M] = word;
    }

    String search(String word, boolean useDivision) {
        int key = keyFromWord(word);
        int index = useDivision ? hashDivision(key) : hashMultiplication(key);
        int i = 0;
        while (table[(index + i) % M] != null) {
            if (table[(index + i) % M].equals(word))
                return word;
            i++;
        }
        return "не знайдено";
    }

    void printTable() {
        for (int i = 0; i < M; i++)
            System.out.println("Індекс " + i + ": " + table[i]);
    }

    public static void main(String[] args) {
        ClosedHashTableUA h = new ClosedHashTableUA();

        String[] words = {
            "Скільки", "вовка", "не", "годуй", "а",
            "він", "усе", "в", "ліс", "дивиться"
        };

        for (String w : words)
            h.insert(w, true);

        h.printTable();
    }
}
