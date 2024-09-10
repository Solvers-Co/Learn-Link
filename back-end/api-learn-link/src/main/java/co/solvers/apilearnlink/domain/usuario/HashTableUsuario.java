package co.solvers.apilearnlink.domain.usuario;

import co.solvers.apilearnlink.listaEncadeada.ListaEncadeada;
import co.solvers.apilearnlink.listaEncadeada.Node;

public class HashTableUsuario {
    private ListaEncadeada<Usuario>[] tab;

    @SuppressWarnings("unchecked")
    public HashTableUsuario(int tamanho) {
        tab = new ListaEncadeada[tamanho];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = new ListaEncadeada<>();
        }
    }

    public int funcaoHash(Character letra) {
        return Math.abs(letra.hashCode()) % tab.length;
    }

    public void insere(Usuario usuario) {
        String nome = usuario.getNome();
        int index = funcaoHash(nome.charAt(0));
        tab[index].insereNode(usuario);
    }

    public Usuario busca(String nome) {
        int index = funcaoHash(nome.charAt(0));
        ListaEncadeada<Usuario> lista = tab[index];

        Node<Usuario> atual = lista.getHead().getNext();

        while (atual != null) {
            if (atual.getInfo().getNome().equalsIgnoreCase(nome)) {
                return atual.getInfo();
            } else {
                atual = atual.getNext();
            }
        }

        return null;
    }


    public boolean remove(String nome) {
        Usuario usuario = busca(nome);
        int index = funcaoHash(usuario.getNome().charAt(0));
        return tab[index].removeNode(usuario);
    }

    // Exibe a tabela hash
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

    // Verifica se a tabela está vazia
    public boolean isEmpty() {
        for (ListaEncadeada<Usuario> lista : tab) {
            if (lista.getHead().getNext() != null) {
                return false;
            }
        }
        return true;
    }
}