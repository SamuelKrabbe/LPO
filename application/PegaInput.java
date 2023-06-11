package application;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class PegaInput {
    public Scanner sc;

    // CONSTRUTOR
    public PegaInput() {
        this.sc = new Scanner(System.in);
    }

    // MÉTODOS DA CLASSE
    public Endereco getEndereco() throws InputMismatchException {
        //lê o endereço de origem do usuário
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
        //lê o aeroporto de origem do usuário
        String codAeroporto;

        System.out.print("Digite o código IATA do aeroporto de sua escolha (ex.: GRU, SDU, CGH, etc): ");
        codAeroporto = sc.nextLine().toUpperCase();
        System.out.println();

        Aeroporto aeroportoOrigem = new Aeroporto(codAeroporto, enderecoOrigem);
        return aeroportoOrigem;
    }

    public CompanhiaAerea getCompanhiaAerea() {
        //lê a companhia aérea que o usuário escolheu
        String nomeCompanhia, codCompanhia;

        System.out.println("Qual a companhia aérea de sua preferência? ");
        System.out.print("Nome da companhia -> ");
        nomeCompanhia = sc.nextLine().toUpperCase();
        System.out.println("");
        codCompanhia = geraCodCompanhia(nomeCompanhia);
        System.out.println();

        CompanhiaAerea companhiaAerea = new CompanhiaAerea(codCompanhia, nomeCompanhia);
        return companhiaAerea;
    }

    public String geraCodCompanhia(String nomeCompanhia) {
        //gera o código da companhia aérea escolhida pelo usuário
        String inicio = nomeCompanhia.substring(0, 1);
        String meio = nomeCompanhia.substring(nomeCompanhia.length() / 2, (nomeCompanhia.length() / 2) + 1);
        String fim = nomeCompanhia.substring(nomeCompanhia.length() - 1);
        String codigo = inicio + meio + fim;
        return codigo;
    }

    public Voo getVoo(Aeroporto aeroportoOrigem) {
        //lê as informações do voo do usuário
        String numVoo, codAeroportoDestino;
        int capacidade = (int) (Math.random() * 500);

        System.out.println("Escolha seu voo: ");
        numVoo = geraNumeroVoo();
        System.out.println();
        System.out.print("Código do aeroporto de destino -> ");
        codAeroportoDestino = sc.nextLine().toUpperCase();
        System.out.println();
        System.out.println("Digite seu endereço de destino: ");
        Aeroporto aeroportoDestino = new Aeroporto(codAeroportoDestino, getEndereco());

        Voo voo = new Voo(numVoo, aeroportoOrigem, aeroportoDestino, null, null, capacidade);
        return voo;
    }

    public String geraNumeroVoo() {
        //gera um número de voo aleatório
        int numeroVoo = (int) (Math.random() * 9000) + 1000;
        return String.valueOf(numeroVoo);
    }

    public Passageiro getPassageiro(Endereco enderecoOrigem) {
        //lê as informações dos passageiro
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
        //gera uma passagem para tal passageiro
        String numPassagem, assento;

        numPassagem = geraNumPassagem(qntDeReservas);

        assento = geraAssentoPassagem(qntDeReservas);
        Passagem passagem = new Passagem(numPassagem, vooIda, null, passageiro, assento);
        return passagem;
    }

    public String geraAssentoPassagem(int qntDeReservas) {
        //gera o número do assento baseado na ordem de reservas
        String prefixoAssento = "A";
        return prefixoAssento + qntDeReservas;
    }

    public String geraNumPassagem(int qntDeReservas) {
        //gera o número da passagem baseado na ordem de reservas
        String prefixoPassagem = "P";
        return prefixoPassagem + qntDeReservas;
    }

    public Bilhete<String> getBilhete(List<Passagem> passagens, int limiteInferior, int limiteSuperior,
            CompanhiaAerea companhiaAerea, int qntDeReservas) {
        /*gera um bilhete levando em conta a quantidade de passagens de cada bilhete, 
        usa o limite inferior e superior para separar a lista de passagens de acordo
        com a quantidade de reservas feitas
        */
        String numBilhete;
        numBilhete = geraNumBilhete(qntDeReservas);
        double preco = Math.random() * 2000;
        List<Passagem> passagensSubLista = passagens.subList(limiteInferior, limiteSuperior);

        Bilhete<String> bilhete = new Bilhete<>(numBilhete, passagensSubLista, preco, companhiaAerea);
        return bilhete;
    }

    public String geraNumBilhete(int qntDeReservas) {
        //gera o número do bilhete baseado na ordem de reservas
        String prefixoBilhete = "B";
        return prefixoBilhete + qntDeReservas;
    }

    public String menuInicial() {
        //layout do menu inicial
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

    public String menuAlteraInfo() {
        // layout do menu para a alteração de informações
        String opAlteracao;
        System.out.println("===============================================");
        System.out.println("Escolha o tipo de informação que deseja alterar:");
        System.out.println("Informações do Passageiro - digite 1");
        System.out.println("Informações do Voo - digite 2");
        System.out.println("Informações do Bilhete - digite 3");
        System.out.println("Voltar ao menu principal - digite 4");
        System.out.println("===============================================");

        System.out.println();
        System.out.print("-> ");
        opAlteracao = sc.nextLine();
        System.out.println();
        return opAlteracao;
    }

    public void reservaFeita(boolean comSucesso) {
        //mensagem para quando a reserva foi feita com sucesso... ou não.
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
