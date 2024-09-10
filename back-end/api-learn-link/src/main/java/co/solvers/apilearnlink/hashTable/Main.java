package co.solvers.apilearnlink.hashTable;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable = new HashTable(5);
        hashTable.insere(15);
        hashTable.insere(46);
        hashTable.insere(7);
        hashTable.insere(33);
        hashTable.insere(9);
        System.out.println(hashTable.funcaoHash(46));
        hashTable.insere(21);
        System.out.println(hashTable.funcaoHash(21));
        System.out.println(hashTable.busca(7));
        System.out.println(hashTable.remove(9));
        hashTable.exibe();
    }
}
