package application;

public class CompanhiaAerea {
    private String codCompanhia;
    private String nomeCompanhia;

    public CompanhiaAerea(String codCompanhia, String nomeCompanhia) {
        this.codCompanhia = codCompanhia;
        this.nomeCompanhia = nomeCompanhia;
    }

    @Override
    public String toString() {
        return "Código da Companhia Aérea: " + codCompanhia + "\n" +
                "Nome da Companhia Aérea: " + nomeCompanhia;
    }

    // Getters e Setters
    public String getCodCompanhia() {
        return codCompanhia;
    }

    public void setCodCompanhia(String codCompanhia) {
        this.codCompanhia = codCompanhia;
    }

    public String getNomeCompanhia() {
        return nomeCompanhia;
    }

    public void setNomeCompanhia(String nomeCompanhia) {
        this.nomeCompanhia = nomeCompanhia;
    }
}
