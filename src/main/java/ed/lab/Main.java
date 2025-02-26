package ed.lab;
import java.util.Random;
import java.util.Arrays;
//String
public class Main {
    private static final ArrayGenerator<String> sortedArrayGenerator =lenght -> {
        String[] array =  new String[lenght]; // Reemplácelo por una función lambda
        for (int i = 0; i < lenght; i++) {
            array[i] = String.valueOf(i);}
        return array;
    };
    private static final ArrayGenerator<String> invertedArrayGenerator = lenght -> {
        String[] array =  new String[lenght];
        for (int i = 0; i < lenght; i++) {
            array[i] = String.valueOf(lenght-(i+1));
        }
        return array;
    }; // Reemplácelo por una función lambda

    private static final ArrayGenerator<String> randomArrayGenerator = length -> {
        String[] array = new String[length];
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            array[i] = String.valueOf(rand.nextInt(1000));
        }
        return array;
    }; // Reemplácelo por una función lambda

    private static final SortingAlgorithms<String> sortingAlgorithms = new SortingAlgorithms<>();

    private static final QuickSort<String> highPivotQuickSort = sortingAlgorithms::highPivotQuickSort;// Reemplácelo por una referencia a un método

    private static final QuickSort<String> lowPivotQuickSort = sortingAlgorithms::lowPivotQuickSort; // Reemplácelo por una referencia a un método

    private static final QuickSort<String> randomPivotQuickSort = sortingAlgorithms::randomPivotQuickSort; // Reemplácelo por una referencia a un método

    public static QuickSort<String> getHighPivotQuickSort() {
        return highPivotQuickSort;
    }

    public static QuickSort<String> getLowPivotQuickSort() {
        return lowPivotQuickSort;
    }

    public static QuickSort<String> getRandomPivotQuickSort() {
        return randomPivotQuickSort;
    }

    public static ArrayGenerator<String> getSortedArrayGenerator() {
        return sortedArrayGenerator;
    }

    public static ArrayGenerator<String> getInvertedArrayGenerator() {
        return invertedArrayGenerator;
    }

    public static ArrayGenerator<String> getRandomArrayGenerator() {
        return randomArrayGenerator;
    }

    public static void main(String[] args) {
        final SortingTester<String> tester = new SortingTester<>();

        System.out.println("Ordenando un arreglo ordenado:");
        System.out.println("\tUtilizando el último elemento como pivote: ");
        tester.testSorting(sortedArrayGenerator, highPivotQuickSort);
        System.out.println("\tUtilizando el primer elemento como pivote: ");
        tester.testSorting(sortedArrayGenerator, lowPivotQuickSort);
        System.out.println("\tUtilizando un elemento aleatorio como pivote: ");
        tester.testSorting(sortedArrayGenerator, randomPivotQuickSort);
        System.out.println("================================");

        System.out.println("Ordenando un arreglo invertido:");
        System.out.println("\tUtilizando el último elemento como pivote: ");
        tester.testSorting(invertedArrayGenerator, highPivotQuickSort);
        System.out.println("\tUtilizando el primer elemento como pivote: ");
        tester.testSorting(invertedArrayGenerator, lowPivotQuickSort);
        System.out.println("\tUtilizando un elemento aleatorio como pivote: ");
        tester.testSorting(invertedArrayGenerator, randomPivotQuickSort);
        System.out.println("================================");

        System.out.println("Ordenando un arreglo aleatorio:");
        System.out.println("\tUtilizando el último elemento como pivote: ");
        tester.testSorting(randomArrayGenerator, highPivotQuickSort);
        System.out.println("\tUtilizando el primer elemento como pivote: ");
        tester.testSorting(randomArrayGenerator, lowPivotQuickSort);
        System.out.println("\tUtilizando un elemento aleatorio como pivote: ");
        tester.testSorting(randomArrayGenerator, randomPivotQuickSort);
        System.out.println("================================");
    }
};
class SortingAlgorithms<T extends Comparable<T>>{
    //Agarrar un pivote alto HighPivot
    int partitionH(T[] array, int Primero, int Ultimo) {
        T pivot = array[Ultimo];
        int i = Primero-1;
        for (int j = Primero; j < Ultimo; j++) {
            if (array[j].compareTo(pivot) < 0) {
                i++;
                T aux = array[i];
                array[i] = array[j];
                array[j] = aux;
            }
        }
        T aux = array[i+1];
        array[i+1] = array[Ultimo];
        array[Ultimo] = aux;
        return i+1;
    }
    void quicksortH(T[] array, int Primero, int Ultimo) {
        if (Primero > Ultimo) {
            return;
        }
        int Pivote = partitionH(array, Primero, Ultimo);
        quicksortH(array, Primero, Pivote-1);
        quicksortH(array, Pivote+1, Ultimo);
    }
    void highPivotQuickSort(T[] array) {
        quicksortH(array,0,array.length-1);
    }

    //Agarrar un pivote bajo LowPivot
    int partitionL(T[] array, int primero, int ultimo) {
        T pivot = array[primero];
        int i = primero + 1 ;
        for (int j = primero + 1; j <= ultimo; j++) {
            if (array[j].compareTo(pivot) < 0) {
                T aux = array[i];
                array[i] = array[j];
                array[j] = aux;
                i++;
            }
        }
        T aux = array[primero];
        array[primero] = array[i-1];
        array[i-1] = aux;
        return i-1;
    }

    void quicksortL(T[] array,int primero,int ultimo )
    {
        if (primero >= ultimo)
        {
            return;
        }
        int Pivote = partitionL(array, primero, ultimo);
        quicksortL(array, primero, Pivote-1);
        quicksortL(array, Pivote+1, ultimo);
    }

    void lowPivotQuickSort(T[] array)
    {
        quicksortL(array,0, array.length-1);
    }

    //Agarrar un pivote random
    int partitionR(T[] array, int Primero, int Ultimo) {

        Random rand = new Random();
        int Pivote =rand.nextInt(Ultimo-Primero+1)+Primero;
        T auxiliar = array[Pivote];
        array[Pivote]= array[Ultimo];
        array[Ultimo] = auxiliar;

        T pivot = array[Ultimo];
        int i = Primero-1;
        for (int j = Primero; j < Ultimo; j++) {
            if (array[j].compareTo(pivot) < 0) {
                i++;
                T aux = array[i];
                array[i] = array[j];
                array[j] = aux;
            }
        }
        T aux = array[i+1];
        array[i+1] = array[Ultimo];
        array[Ultimo] = aux;
        return i+1;
    };
    void quicksortR(T[] array,int Primero, int Ultimo)
    {
        if (Primero > Ultimo)
        {
            return;
        }
        int Pivote = partitionR(array, Primero, Ultimo);
        quicksortR(array, Primero, Pivote-1);
        quicksortR(array, Pivote+1, Ultimo);
    };
    void randomPivotQuickSort(T[] array)
    {
        quicksortR(array, 0, array.length-1);
    }
}