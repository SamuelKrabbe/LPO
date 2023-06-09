package application;

import java.util.List;
import java.util.ArrayList;

public class Bilhete<T> {
    private T numBilhete;
    private List<Passagem> listPassagem;
    private double preco;
    private CompanhiaAerea compAerea;

    // CONSTRUTOR
    public Bilhete(T numBilhete, List<Passagem> listPassagem, double preco, CompanhiaAerea compAerea) {
        this.numBilhete = numBilhete;
        this.listPassagem = listPassagem;
        this.preco = preco;
        this.compAerea = compAerea;
    }

    // MÉTODOS DA CLASSE
    public void imprimirBilhete() {
        //imprime as informações do bilhete
        System.out.println("- Número do Bilhete: " + numBilhete);
        System.out.println("- Companhia Aérea: " + compAerea.getNomeCompanhia());
        System.out.println(String.format("- Preço: R$%.2f", preco));
        System.out.println("- Passagem(ns):");

        // cria uma copia da lista de passagens
        List<Passagem> passagemCopy = new ArrayList<>(listPassagem);

        // imprime cada passagem do bilhete
        synchronized (passagemCopy) {
        	for (Passagem passagem : passagemCopy) {
                System.out.println("  -> " + passagem + ";");
            }
        }
    }

    // Getters e Setters
    public T getNumBilhete() {
        return numBilhete;
    }

    public void setNumBilhete(T numBilhete) {
        this.numBilhete = numBilhete;
    }

    public List<Passagem> getListPassagem() {
        return listPassagem;
    }

    public void setListPassagem(List<Passagem> listPassagem) {
        this.listPassagem = listPassagem;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public CompanhiaAerea getCompAerea() {
        return compAerea;
    }

    public void setCompAerea(CompanhiaAerea compAerea) {
        this.compAerea = compAerea;
    }
}
