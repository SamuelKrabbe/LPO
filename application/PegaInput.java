package application;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class PegaInput {
    public Scanner sc;

    public PegaInput() {
        this.sc = new Scanner(System.in);
    }

    public <T> boolean verificaInteiro(T valor) {
        return valor instanceof Integer;
    }

    public Endereco getEndereco() throws InputMismatchException {
        String rua, bairro, cidade, cep;
        boolean intValido = false;
        int numero = 0;

        System.out.print("Rua -> ");
        rua = sc.nextLine();
        System.out.println();
        while (!intValido) {
            try {
                System.out.print("Número -> ");
                numero = sc.nextInt();
                System.out.println();
                intValido = true;
            } catch (InputMismatchException e) {
                System.out.println("Número inválido! Digite novamente!");
                sc.nextLine();
            }
        }
        sc.nextLine();
        System.out.print("Bairro -> ");
        bairro = sc.nextLine();
        System.out.println();
        System.out.print("Cidade -> ");
        cidade = sc.nextLine();
        System.out.println();
        System.out.print("Cep -> ");
        cep = sc.nextLine();
        System.out.println();

        Endereco endereco = new Endereco(rua, numero, bairro, cidade, cep);
        return endereco;
    }

    public Aeroporto getAeroporto(Endereco enderecoOrigem) {
        String codAeroporto;

        System.out.print("Digite o código IATA do aeroporto de sua escolha (ex.: GRU, SDU, CGH, etc): ");
        codAeroporto = sc.nextLine().toUpperCase();
        System.out.println();

        Aeroporto aeroportoOrigem = new Aeroporto(codAeroporto, enderecoOrigem);
        return aeroportoOrigem;
    }

    public CompanhiaAerea getCompanhiaAerea() {
        String nomeCompanhia, codCompanhia;

        System.out.println("Qual a companhia aérea de sua preeferência? ");
        System.out.print("Nome da companhia -> ");
        nomeCompanhia = sc.nextLine().toUpperCase();
        System.out.println("");
        System.out.print("Código da companhia -> ");
        codCompanhia = sc.nextLine().toUpperCase();
        System.out.println();

        CompanhiaAerea companhiaAerea = new CompanhiaAerea(codCompanhia, nomeCompanhia);
        return companhiaAerea;
    }

    public Voo getVoo(Aeroporto aeroportoOrigem) {
        String numVoo, codAeroportoDestino;
        int capacidade = (int) (Math.random() * 500);

        System.out.println("Escolha seu voo: ");
        System.out.print("Número do voo -> ");
        numVoo = sc.nextLine();
        System.out.println();
        System.out.print("Código do aeroporto de destino -> ");
        codAeroportoDestino = sc.nextLine().toUpperCase();
        System.out.println();
        System.out.println("Digite seu endereço de destino: ");
        Aeroporto aeroportoDestino = new Aeroporto(codAeroportoDestino, getEndereco());

        Voo voo = new Voo(numVoo, aeroportoOrigem, aeroportoDestino, null, null, capacidade);
        return voo;
    }

    public Passageiro getPassageiro(Endereco enderecoOrigem) {
        String nome, cpf, rg, celular, numPassaporte;

        System.out.println("Digite as informações do passageiro: ");
        System.out.print("Nome -> ");
        nome = sc.nextLine();
        System.out.println();
        System.out.print("CPF -> ");
        cpf = sc.nextLine();
        System.out.println();
        System.out.print("RG -> ");
        rg = sc.nextLine();
        System.out.println();
        System.out.print("Celular -> ");
        celular = sc.nextLine();
        System.out.println();
        System.out.print("Número do passaporte -> ");
        numPassaporte = sc.nextLine();
        System.out.println();
        Passageiro passageiro = new Passageiro(nome, cpf, rg, enderecoOrigem, celular, numPassaporte);
        return passageiro;
    }

    public Passagem getPassagem(Voo vooIda, Passageiro passageiro, int qntDeReservas) {
        String numPassagem, assento;

        numPassagem = geraNumPassagem(qntDeReservas);

        System.out.print("Digite o código do assento de preferência: ");
        assento = sc.nextLine();
        Passagem passagem = new Passagem(numPassagem, vooIda, null, passageiro, assento);
        return passagem;
    }

    public String geraNumPassagem(int qntDeReservas) {
        String prefixoPassagem = "P";
        return prefixoPassagem + qntDeReservas;
    }

    public Bilhete<String> getBilhete(List<Passagem> passagens, int limiteInferior, int limiteSuperior,
            CompanhiaAerea companhiaAerea, int qntDeReservas) {
        String numBilhete;
        numBilhete = geraNumBilhete(qntDeReservas);
        double preco = Math.random() * 2000;
        List<Passagem> passagensSubLista = passagens.subList(limiteInferior, limiteSuperior);

        Bilhete<String> bilhete = new Bilhete<>(numBilhete, passagensSubLista, preco, companhiaAerea);
        return bilhete;
    }

    public String geraNumBilhete(int qntDeReservas) {
        String prefixoBilhete = "B";
        return prefixoBilhete + qntDeReservas;
    }

    public String menu() {
        String opMenu;
        System.out.println("===============================================");
        System.out.println("MENU: ");
        System.out.println("Reservar um voo - digite 1");
        System.out.println("Ver informações no(s) bilhete(s) - digite 2");
        System.out.println("Alterar informação no(s) bilhete(s) - digite 3");
        System.out.println("Sair - digite 4");
        System.out.println("===============================================");

        System.out.println();
        System.out.print("-> ");
        opMenu = sc.nextLine();
        System.out.println();
        return opMenu;
    }

    public void reservaFeita(boolean comSucesso) {
        if (comSucesso) {
            System.out.println();
            System.out.println("Voo reservado com sucesso!\nAgradeçemos a preferência, tenha um bom dia!");
            System.out.println();
        } else {
            System.out.println("Nenhum voo foi reservado...");
            System.out.println();
        }
    }
}
