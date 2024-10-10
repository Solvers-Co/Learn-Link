package co.solvers.apilearnlink.listaEncadeada;

public class ListaEncadeada<T> {

    private Node<T> head;

    public ListaEncadeada() {
        this.head = new Node(null);
    }

    public void insereNode(T objeto) {
        Node<T> novo = new Node(objeto);

        novo.setNext(head.getNext());
        head.setNext(novo);
    }

    public void exibe() {
        Node<T> atual = head.getNext();

        while (atual != null) {
            System.out.println(atual.getInfo());
            atual = atual.getNext();
        }
    }

    public Node<T> buscarNode(T objeto) {
        Node<T> atual = head.getNext();

        while (atual != null) {
            if (atual.getInfo() == objeto) {
                return atual;
            } else {
                atual = atual.getNext();
            }
        }

        return null;
    }

    public boolean removeNode(T objeto) {
        Node anterior = head;
        Node atual = head.getNext();

        while (atual != null) {
            if (atual.getInfo() == objeto) {
                anterior.setNext(atual.getNext());
                return true;
            } else {
                anterior = atual;
                atual = atual.getNext();
            }
        }

        return false;
    }

    public int getTamanho() {
        Node<T> atual = head.getNext();
        int tamanho = 0;

        while (atual != null) {
            tamanho++;
            atual = atual.getNext();
        }

        return tamanho;
    }

    public Node<T> getHead() {
        return head;
    }

}
