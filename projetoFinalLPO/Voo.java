package projetoFinalLPO;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Voo {
    private String numVoo;
    private Aeroporto origem;
    private Aeroporto destino;
    private Calendar dataHoraOrigem;
    private Calendar dataHoraDestino;
    private int capacidade;

    public Voo(String numVoo, Aeroporto origem, Aeroporto destino, Calendar dataHoraOrigem,
            Calendar dataHoraDestino, int capacidade) {
        this.numVoo = numVoo;
        this.origem = origem;
        this.destino = destino;
        this.dataHoraOrigem = dataHoraOrigem;
        this.dataHoraDestino = dataHoraDestino;
        this.capacidade = capacidade;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        String strDataHoraOrigem = sdf.format(dataHoraOrigem.getTime());
        String strDataHoraDestino = sdf.format(dataHoraDestino.getTime());

        return "Número do Voo: " + numVoo + "\n" +
                "Origem: " + origem.toString() + "\n" +
                "Destino: " + destino.toString() + "\n" +
                "Data/Hora Origem: " + strDataHoraOrigem + "\n" +
                "Data/Hora Destino: " + strDataHoraDestino + "\n" +
                "Capacidade: " + capacidade;
    }

    // Getters e Setters
    public String getNumVoo() {
        return numVoo;
    }

    public void setNumVoo(String numVoo) {
        this.numVoo = numVoo;
    }

    public Aeroporto getOrigem() {
        return origem;
    }

    public void setOrigem(Aeroporto origem) {
        this.origem = origem;
    }

    public Aeroporto getDestino() {
        return destino;
    }

    public void setDestino(Aeroporto destino) {
        this.destino = destino;
    }

    public Calendar getDataHoraOrigem() {
        return dataHoraOrigem;
    }

    public void setDataHoraOrigem(Calendar dataHoraOrigem) {
        this.dataHoraOrigem = dataHoraOrigem;
    }

    public Calendar getDataHoraDestino() {
        return dataHoraDestino;
    }

    public void setDataHoraDestino(Calendar dataHoraDestino) {
        this.dataHoraDestino = dataHoraDestino;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
}
