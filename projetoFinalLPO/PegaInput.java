package projetoFinalLPO;

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

    public Aeroporto getAeroporto(Endereco endereco) {
        String codAeroporto;

        System.out.print("Digite o código IATA do aeroporto de sua escolha (ex.: GRU, SDU, CGH, etc): ");
        codAeroporto = sc.nextLine();
        System.out.println();

        Aeroporto aeroportoOrigem = new Aeroporto(codAeroporto, endereco);
        return aeroportoOrigem;
    }

    public CompanhiaAerea getCompanhiaAerea() {
        String nomeCompanhia, codCompanhia;

        System.out.println("Qual a companhia aérea de sua preeferência? ");
        System.out.print("Nome da companhia -> ");
        nomeCompanhia = sc.nextLine();
        System.out.println("");
        System.out.print("Código da companhia -> ");
        codCompanhia = sc.nextLine();
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
        codAeroportoDestino = sc.nextLine();
        System.out.println();
        System.out.println("Digite seu endereço de destino: ");
        Aeroporto aeroportoDestino = new Aeroporto(codAeroportoDestino, getEndereco());

        Voo voo = new Voo(numVoo, aeroportoOrigem, aeroportoDestino, null, null, capacidade);
        return voo;
    }

    public Passageiro getPassageiro() {
        String nome, cpf, rg, celular, numPassaporte;
        Endereco endereco;

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
        System.out.println("Digite o endereço do passageiro: ");
        endereco = getEndereco();
        System.out.print("Celular -> ");
        celular = sc.nextLine();
        System.out.println();
        System.out.print("Número do passaporte -> ");
        numPassaporte = sc.nextLine();
        System.out.println();
        Passageiro passageiro = new Passageiro(nome, cpf, rg, endereco, celular, numPassaporte);
        return passageiro;
    }

    public Passagem getPassagem(Voo vooIda, Passageiro passageiro, int variavelDeControle) {
        String numPassagem, assento;

        numPassagem = geraNumPassagem(variavelDeControle);

        System.out.print("Digite o código do assento de preferência: ");
        assento = sc.nextLine();
        Passagem passagem = new Passagem(numPassagem, vooIda, null, passageiro, assento);
        return passagem;
    }

    public String geraNumPassagem(int variavelDeControle) {
        String prefixoPassagem = "P";
        return prefixoPassagem + variavelDeControle;
    }

    public Bilhete<String> getBilhete(List<Passagem> passagens, int limiteInferior, int limiteSuperior,
            CompanhiaAerea companhiaAerea, int variavelDeControle) {
        String numBilhete;
        numBilhete = geraNumBilhete(variavelDeControle);
        double preco = Math.random() * 2000;
        List<Passagem> passagensSubLista = passagens.subList(limiteInferior, limiteSuperior);

        Bilhete<String> bilhete = new Bilhete<>(numBilhete, passagensSubLista, preco, companhiaAerea);
        return bilhete;
    }

    public String geraNumBilhete(int variavelDeControle) {
        String prefixoBilhete = "B";
        return prefixoBilhete + variavelDeControle;
    }
}
