package school.sptech.consumoapicep;

import school.sptech.consumoapicep.domain.usuario.Usuario;

public class ListaEstatica<T> {

    private T[] vetor;
    private int nroElem;

    public ListaEstatica(int tamVet) {
        this.vetor = (T[]) new Object[tamVet];
        this.nroElem = 0;
    }

    public T get(int i){
        if (i > nroElem || i < 0) return null;

        return vetor[i];
    }

    public void set(int i, T object){
        if (i >= 0 && i < nroElem){
            vetor[i] = object;
        }
    }

    public void adiciona(T object) {

        if (nroElem >= vetor.length) {
            throw new IllegalStateException("Lista cheia!");
        } else{
            vetor[nroElem++] = object;
        }
    }

    public void exibe(){

        if (vetor.length == 0){
            System.out.println("Vetor vazio!");
            return;
        }

        for (int i = 0 ; i < nroElem ; i++){
            System.out.print(vetor[i] + " ");
        }
        System.out.println();
    }

    public int busca(T object){
        if (vetor.length == 0){
            return -1;
        }

        for (int i = 0 ; i < vetor.length ; i++){
            if (vetor[i] == object){
                return i;
            }
        }

        return -1;
    }

    public boolean removePeloIndice(int i){

        if (i < 0 || i > vetor.length-1){
            return false;
        }

        vetor[i] = null;
        nroElem--;
        organizaVetor(i);
        return true;
    }

    public boolean removeElemento(T object){

        int indiceNum = busca(object);

        if (indiceNum == -1){
            return false;
        } else {
            removePeloIndice(indiceNum);
            return true;
        }
    }

    public boolean substitui(T objectAntigo, T objectNovo){
        int indNumAntigo = busca(objectAntigo);

        if (indNumAntigo == -1){
            System.out.println("Número não encontrado!");
            return false;
        }

        vetor[indNumAntigo] = objectNovo;
        return true;
    }

    public int contaOcorrencias (T object){
        int ocorrencias = 0;

        for (int i = 0 ; i < nroElem ; i++){
            if (vetor[i] == object){
                ocorrencias++;
            }
        }

        return ocorrencias;
    }

    public boolean adicionaNoInicio(T object) {

        if (nroElem >= vetor.length) {
            System.out.println("Lista cheia!");
            return false;
        }

        for (int i = nroElem ; i > 0 ; i--){
            vetor[i] = vetor[i-1];
        }

        vetor[0] = object;
        nroElem++;

        return true;
    }

    public boolean isEmpty(){
        if (nroElem == 0){
            return true;
        } else {
            return false;
        }
    }

    public boolean isFull(){
        if (nroElem >= vetor.length){
            return true;
        } else {
            return false;
        }
    }

    public void organizaVetor(int indiceBuraco){

        for (int i = indiceBuraco ; i <= vetor.length-1 ; i++){
            if (i == vetor.length-1){
                vetor[i] = null;
            } else {
                vetor[i] = vetor[i+1];
            }
        }
    }

/*    public void ordenaVetorPorCep(){
        T aux = null;

        for (int i = 0 ; i < nroElem-1 ; i++){
            for (int x = 1; x < nroElem - i; x++){

                if (Integer.parseInt(vetor[x-1].getEndereco().getCep().replaceAll("[^0-9]", "")) > Integer.parseInt(vetor[x].getEndereco().getCep().replaceAll("[^0-9]", ""))){
                    aux = vetor[x];
                    vetor[x]= vetor[x-1];
                    vetor[x-1] = aux;
                }
            }
        }
    }*/

  /*  public int pesquisaBinaria(String cep){
        int iInicio = 0;
        int iFim = nroElem-1;

        int x = Integer.parseInt(cep.replaceAll("[^0-9]", ""));

        while (iInicio <= iFim){

            int iMeio = (iInicio+iFim)/2;

            if (Integer.parseInt(vetor[iMeio].getEndereco().getCep().replaceAll("[^0-9]", "")) == x) {
                return iMeio;

            } else if (x < Integer.parseInt(vetor[iMeio].getEndereco().getCep().replaceAll("[^0-9]", ""))) {
                iFim = iMeio-1;
            } else  {
                iInicio = iMeio+1;
            }
        }

      *//*  if (iInicio > iFim){
            return -1;
        }*//*

        return -1;
    }*/
    public int getNroElem() {
        return nroElem;
    }

    public T[] getVetor() {
        return vetor;
    }

}
