package co.solvers.apilearnlink.hashTable;

import co.solvers.apilearnlink.listaEncadeada.ListaEncadeada;

public class HashTableInt {
    private ListaEncadeada[] tab;

    public HashTableInt(int valor) {
        tab = new ListaEncadeada[valor];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = new ListaEncadeada();
        }
    }
    public int funcaoHash(int x){
        return x % tab.length;
    }

    public void insere(int x) {
        int index = funcaoHash(x);
        tab[index].insereNode(x);
    }

    public boolean busca(int x) {
        int index = funcaoHash(x);
        return tab[index].buscarNode(x) != null;
    }
    public boolean remove(int x) {
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
