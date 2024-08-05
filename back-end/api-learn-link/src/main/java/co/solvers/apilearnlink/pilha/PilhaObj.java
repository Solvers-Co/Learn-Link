package co.solvers.apilearnlink.pilha;

public class PilhaObj <T>{

    // 01) Atributos
    private T[] pilha;
    private int topo;

    // 02) Construtor
    public PilhaObj(int capacidade) {
        this.pilha = (T[]) new Object[capacidade];
        this.topo = -1;
    }

    // 03) MÃ©todo isEmpty
    public Boolean isEmpty() {

        if (topo == -1){
            return true;
        } else {
            return false;
        }
    }

    // 04) MÃ©todo isFull
    public Boolean isFull() {
        if (topo+1 == pilha.length){
            return true;
        } else {
            return false;
        }
    }

    // 05) MÃ©todo push
    public void push(T info) {
        if (isFull()) {
            throw new IllegalStateException("Pilha cheia!");
        } else {
            pilha[++topo] = info;
        }
    }

    // 06) MÃ©todo pop
    public T pop() {
        if (isEmpty()){
            return null;
        } else {
            T aux = pilha[topo--];
            return aux;
        }
    }

    // 07) MÃ©todo peek
    public T peek() {
        if (isEmpty()){
            return null;
        } else {
            return pilha[topo];
        }
    }

    // 08) MÃ©todo exibe
    public void exibe() {
        for (int i = topo ; i >= 0 ; i--){
            System.out.println(pilha[i]);
        }
    }


    //Getters & Setters (manter)
    public int getTopo() {
        return topo;
    }
}