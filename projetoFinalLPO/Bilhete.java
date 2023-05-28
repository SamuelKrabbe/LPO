package projetoFinalLPO;

import java.util.Collections;
import java.util.List;

public class Bilhete<T> {
    private T numBilhete;
    private List<Passagem> listPassagem;
    private double preco;
    private CompanhiaAerea compAerea;
    private String tipoReserva;

    public Bilhete(T numBilhete, List<Passagem> listPassagem, double preco, CompanhiaAerea compAerea,
            String tipoReserva) {
        this.numBilhete = numBilhete;
        this.listPassagem = listPassagem;
        this.preco = preco;
        this.compAerea = compAerea;
        this.tipoReserva = tipoReserva;
    }

    public void imprimirBilhete() {
        System.out.println("- Número do Bilhete: " + numBilhete);
        System.out.println("- Companhia Aérea: " + compAerea.getNomeCompanhia());
        System.out.println("- Tipo de reserva: " + tipoReserva);
        System.out.println("- Preço: R$" + preco);

        System.out.println("- Passageiros:");
        Collections.sort(listPassagem); // Ordena a lista de passagens em ordem alfabética

        for (Passagem passagem : listPassagem) {
            System.out.println("  -> " + passagem.getPassageiro().getNome());
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

    public String getTipoReserva() {
        return tipoReserva;
    }

    public void setTipoReserva(String tipoReserva) {
        this.tipoReserva = tipoReserva;
    }
}
