class MinHeap {
    Nodo[] heap;
    int size;

    public MinHeap(int capacidad) {
        heap = new Nodo[capacidad];
        size = 0;
    }

    public void insertar(Nodo nodo) {
        heap[size] = nodo;
        subir(size);
        size++;
    }

    private void subir(int i) {
        while (i > 0 && heap[i].frecuencia < heap[(i - 1) / 2].frecuencia) {
            intercambiar(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    public Nodo extraerMin() {
        Nodo min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        bajar(0);
        return min;
    }

    private void bajar(int i) {
        int menor = i;
        int izq = 2 * i + 1;
        int der = 2 * i + 2;

        if (izq < size && heap[izq].frecuencia < heap[menor].frecuencia)
            menor = izq;

        if (der < size && heap[der].frecuencia < heap[menor].frecuencia)
            menor = der;

        if (menor != i) {
            intercambiar(i, menor);
            bajar(menor);
        }
    }

    private void intercambiar(int i, int j) {
        Nodo temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public int getSize() {
        return size;
    }

    public static void generarCodigos(Nodo nodo, String codigo) {
        if (nodo == null) return;

        if (nodo.izquierda == null && nodo.derecha == null) {
            System.out.println(nodo.simbolo + ": " + codigo);
        }

        generarCodigos(nodo.izquierda, codigo + "0");
        generarCodigos(nodo.derecha, codigo + "1");
    }
}