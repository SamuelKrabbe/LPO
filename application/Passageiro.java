package application;

public class Passageiro extends Pessoa {
    private String numPassaporte;

    // CONSTRUTOR
    public Passageiro(String nome, String cpf, String rg, Endereco endereco, String celular, String numPassaporte) {
        super(nome, cpf, rg, endereco, celular);
        this.numPassaporte = numPassaporte;
    }

    // MÉTODOS DA CLASSE
    @Override
    public String toString() {
        return "Nome: " + super.getNome() + ", RG: " + super.getRg();
    }

    // Getters e Setters
    public String getNumPassaporte() {
        return numPassaporte;
    }

    public void setNumPassaporte(String numPassaporte) {
        this.numPassaporte = numPassaporte;
    }
}
