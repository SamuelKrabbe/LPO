package application;

import java.util.Scanner;

public abstract class Pessoa implements Verificavel {
    private String nome;
    private String cpf;
    private String rg;
    private Endereco endereco;
    private String celular;
    private String dataNascimento;

    public Pessoa(String nome, String cpf, String rg, Endereco endereco, String celular) {
        this.nome = nome;
        this.rg = rg;
        this.endereco = endereco;
        this.celular = celular;

        validar(cpf);
    }

    @Override
    public boolean validar(String cpf) {
        boolean cpfValido = true;

        try {
            this.cpf = cpf;
        } catch (Exception E) {
            System.out.println("CPF inválido! Digite um novo valor:");
            solicitarNovo();
        }
        return cpfValido;
    }

    @Override
    public void solicitarNovo() {
        Scanner sc = new Scanner(System.in);
        String novoCpf = sc.nextLine();
        validar(novoCpf);
        sc.close();
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
