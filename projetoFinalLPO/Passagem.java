package projetoFinalLPO;

public class Passagem implements Comparable<Passagem> {
    private String numero;
    private Voo vooIda;
    private Voo vooVolta;
    private Passageiro passageiro;
    private String assento;

    public Passagem(String numero, Voo vooIda, Voo vooVolta, Passageiro passageiro, String assento) {
        this.numero = numero;
        this.vooIda = vooIda;
        this.vooVolta = vooVolta;
        this.passageiro = passageiro;
        this.assento = assento;
    }

    public Passagem(String numero, Voo vooIda, Passageiro passageiro, String assento) {
        this.numero = numero;
        this.vooIda = vooIda;
        this.passageiro = passageiro;
        this.assento = assento;
    }

    public boolean verificarCapacidade() {
        if (vooIda != null && vooIda.getCapacidade() <= 0) {
            System.out.println("Voo de ida sem disponibilidade de assentos.");
            return false;
        }

        if (vooVolta != null && vooVolta.getCapacidade() <= 0) {
            System.out.println("Voo de volta sem disponibilidade de assentos.");
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        String strVooVolta = vooVolta != null ? vooVolta.getNumVoo() : "N/A";

        return "Número da Passagem: " + numero + "\n" +
                "Voo de Ida: " + vooIda.getNumVoo() + "\n" +
                "Voo de Volta: " + strVooVolta + "\n" +
                "Passageiro: " + passageiro.getNome() + "\n" +
                "Assento: " + assento;
    }

    @Override
    public int compareTo(Passagem outraPassagem) {
        String nomePassageiroAtual = this.getPassageiro().getNome();
        String nomeOutraPassagem = outraPassagem.getPassageiro().getNome();

        return nomePassageiroAtual.compareTo(nomeOutraPassagem);
    }

    // Getters e Setters
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Voo getVooIda() {
        return vooIda;
    }

    public void setVooIda(Voo vooIda) {
        this.vooIda = vooIda;
    }

    public Voo getVooVolta() {
        return vooVolta;
    }

    public void setVooVolta(Voo vooVolta) {
        this.vooVolta = vooVolta;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    public String getAssento() {
        return assento;
    }

    public void setAssento(String assento) {
        this.assento = assento;
    }
}
