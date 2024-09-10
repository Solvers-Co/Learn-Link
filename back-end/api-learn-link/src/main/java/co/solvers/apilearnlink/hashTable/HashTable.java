package co.solvers.apilearnlink.hashTable;

import co.solvers.apilearnlink.listaEncadeada.ListaEncadeada;

public class HashTable<T> {
    private ListaEncadeada<T>[] tab;

    @SuppressWarnings("unchecked")
    public HashTable(int valor) {
        tab = new ListaEncadeada[valor];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = new ListaEncadeada<>();
        }
    }

    public int funcaoHash(T x) {
        return Math.abs(x.hashCode()) % tab.length;
    }

    public void insere(T x) {
        int index = funcaoHash(x);
        tab[index].insereNode(x);
    }

    public boolean busca(T x) {
        int index = funcaoHash(x);
        return tab[index].buscarNode(x) != null;
    }

    public boolean remove(T x) {
        int index = funcaoHash(x);
        return tab[index].removeNode(x);
    }

    public void exibe() {
        for (int i = 0; i < tab.length; i++) {
            System.out.print("Entrada " + i + ": ");
            if (tab[i].getHead().getNext() == null) {
                System.out.println("Lista vazia");
            } else {
                tab[i].exibe();
            }
        }
    }
}