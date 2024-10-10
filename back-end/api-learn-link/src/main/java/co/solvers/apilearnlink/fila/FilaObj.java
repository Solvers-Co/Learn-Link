package co.solvers.apilearnlink.fila;

public class FilaObj<T> {
    // Atributos
    private int tamanho;
    private T[] fila;

    // Construtor
    public FilaObj(int capacidade) {
        fila = (T[]) new Object[capacidade];
    }

    // Métodos

    /* Método isEmpty() - retorna true se a fila está vazia e false caso contrário */
    public boolean isEmpty() {
        if (tamanho == 0) {
            return true;
        } else {
            return false;
        }
    }

    /* Método isFull() - retorna true se a fila está cheia e false caso contrário */
    public boolean isFull() {

        if (tamanho == fila.length) {
            return true;
        } else {
            return false;
        }
    }

    /* Método insert - recebe um elemento e insere esse elemento na fila
                       no índice tamanho, e incrementa tamanho
                       Lançar IllegalStateException caso a fila esteja cheia
     */
    public void insert(T info) {
        if (isFull()) {
            throw new IllegalStateException("Lista Cheia!");
        } else {
            fila[tamanho++] = info;
        }
    }

    /* Método peek - retorna o primeiro elemento da fila, sem removê-lo */
    public T peek() {
        return fila[0];
    }

    /* Método poll - remove e retorna o primeiro elemento da fila, se a fila não estiver
       vazia. Quando um elemento é removido, a fila "anda", e tamanho é decrementado
       Depois que a fila andar, "limpar" o ex-último elemento da fila, atribuindo null
     */
    public T poll() {
        if (!isEmpty()) {
            T primeiro = fila[0];

            andaFila();
            fila[--tamanho] = null;
            return primeiro;
        } else {
            throw new IllegalStateException("Lista vazia");
        }
    }

    public void andaFila() {
        for (int i = 0; i < tamanho - 1; i++) {
            fila[i] = fila[i + 1];
        }
    }

    /* Método exibe() - exibe o conteúdo da fila */
    public void exibe() {
        for (T object : fila) {
            if (object != null) {
                System.out.println(object);
            }
        }
    }

    /* Usado nos testes  - complete para que fique certo */
    public int getTamanho() {
        return tamanho;
    }
}

