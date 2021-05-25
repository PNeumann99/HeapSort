import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HeapSort {

    public static void sort(int[] A) {
        // Schritt 1: Wir verwenden das gesamte Array als Heap und stellen die Heapeigenschaft mit createHeap(A) her.
        createHeap(A);

        int n = A.length;

        /*
         Schritt 2: Das maximale Element im Heap wird entfernt und kommt vor den Anfang des bereits sortierten Bereichs.
         An die Wurzel des Heaps kommt das aktuell letzte Element des Heaps.
        */
        for (int last = n - 1; last > 0; last--) {
            int temp = A[0];
            A[0] = A[last];
            A[last] = temp;

            /* Nach dem Vertauschen muss die Heap-Eigenschaft in dem um ein Element verkürzten Heap
            wieder hergestellt werden. */
            heapify(A, last, 0);
        }
    }

    private static void createHeap(int[] A) {
        int n = A.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(A, n, i);
        }
    }

    private static void heapify(int[] A, int n, int i) {
        int largest = i; // initialisiere das größte Element mit der Wurzel
        int left = 2 * i + 1; // links = 2 * i + 1 - linker Sohn
        int right = 2 * i + 2; // rechts = 2 * i + 2 - rechter Sohn

        // falls der linke Sohn größer ist als die Wurzel
        if (left < n && A[left] > A[largest]) largest = left;

        // falls der rechte Sohn größer ist als die Wurzel
        if (right < n && A[right] > A[largest]) largest = right;

        // falls A[child] > A[i], also falls das größte Element nicht die Wurzel ist
        if (largest != i) {
            int temp = A[i];
            A[i] = A[largest];
            A[largest] = temp;

            //heapify rekursiv auf child anwenden
            heapify(A, n, largest);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> list = new ArrayList<Integer>();

        // Datei einlesen
        String path = args[0];
        Scanner read = new Scanner(new File(path));
        while (read.hasNextLine()) {
            list.add(Integer.valueOf(read.nextLine()));
        }
        read.close();

        // arrayList für HeapSort in normales Array kopieren
        int[] A = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            A[i] = list.get(i);
        }

        // Array sortieren und Laufzeit messen
        final long timeStart = System.nanoTime();
        sort(A);
        final long timeEnd = System.nanoTime();

        // Sortiertes Array Zeilenweise auf dem Bildschirm ausgeben
        for (int i : A) {
            System.out.println(i);
        }
        // Laufzeit ausgeben
        System.out.println("Laufzeit des Sortieralgorithmus: " + (timeEnd - timeStart) + " Nanosek.");
    }
}
