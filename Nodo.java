class Nodo {
    char simbolo;
    int frecuencia;
    Nodo izquierda, derecha;

    public Nodo(char simbolo, int frecuencia) {
        this.simbolo = simbolo;
        this.frecuencia = frecuencia;
    }

    public Nodo(int frecuencia, Nodo izquierda, Nodo derecha) {
        this.simbolo = '\0';
        this.frecuencia = frecuencia;
        this.izquierda = izquierda;
        this.derecha = derecha;
    }
}