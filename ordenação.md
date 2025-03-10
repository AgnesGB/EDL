## **Resumo dos Algoritmos de Ordenação**

### **1. Bubble Sort (Ordenação por Bolha)**
- **Descrição**: Compara pares adjacentes e troca se estiverem fora de ordem.
- **Complexidade**: \( O(n^2) \) (ineficiente para grandes entradas).
- **Código em Java**:
```java
public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 2, 3, 1};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
```

---

### **2. Selection Sort (Ordenação por Seleção)**
- **Descrição**: Encontra o menor elemento e troca com o primeiro.
- **Complexidade**: \( O(n^2) \).
- **Código em Java**:
```java
public class SelectionSort {
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 2, 3, 1};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
```

---

### **3. Insertion Sort (Ordenação por Inserção)**
- **Descrição**: Insere cada elemento na posição correta de um subconjunto ordenado.
- **Complexidade**: \( O(n^2) \) (eficiente para pequenas entradas).
- **Código em Java**:
```java
public class InsertionSort {
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 2, 3, 1};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
```

---

### **4. Heap Sort**
- **Descrição**: Usa um **Heap** para ordenar elementos.
- **Complexidade**: \( O(n \log n) \) (rápido para grandes entradas).
- **Código em Java**:
```java
import java.util.Arrays;

public class HeapSort {
    public static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 2, 3, 1};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
```

---

### **5. Merge Sort**
- **Descrição**: Divide o array em duas partes, ordena e junta.
- **Complexidade**: \( O(n \log n) \) (eficiente para grandes entradas).
- **Código em Java**:
```java
public class MergeSort {
    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] left = new int[n1];
        int[] right = new int[n2];
        System.arraycopy(arr, l, left, 0, n1);
        System.arraycopy(arr, m + 1, right, 0, n2);
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            arr[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        }
        while (i < n1) arr[k++] = left[i++];
        while (j < n2) arr[k++] = right[j++];
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 2, 3, 1};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
```

---

### **6. Quick Sort**
- **Descrição**: Escolhe um **pivô**, particiona os elementos e ordena recursivamente.
- **Complexidade**:
  - **Melhor caso**: \( O(n \log n) \).
  - **Pior caso**: \( O(n^2) \) (se a escolha do pivô for ruim).
- **Código em Java**:
```java
public class QuickSort {
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 2, 3, 1};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
```

---

## **Conclusão**
- **Quick Sort** e **Heap Sort** são os mais eficientes na maioria dos casos.
- **Merge Sort** é ideal para grandes volumes de dados.
- **Bubble Sort, Selection Sort e Insertion Sort** são mais didáticos do que práticos.