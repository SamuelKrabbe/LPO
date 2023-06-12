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
    public Endereco pegaEndereco() throws InputMismatchException {
        // lê o endereço de origem do usuário
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

    public Aeroporto pegaAeroporto(Endereco enderecoOrigem) {
        // lê o aeroporto de origem do usuário
        String codAeroporto;

        System.out.print("Digite o código IATA do aeroporto de sua escolha (ex.: GRU, SDU, CGH, etc): ");
        codAeroporto = sc.nextLine().toUpperCase();
        System.out.println();

        Aeroporto aeroportoOrigem = new Aeroporto(codAeroporto, enderecoOrigem);
        return aeroportoOrigem;
    }

    public CompanhiaAerea pegaCompanhiaAerea() {
        // lê a companhia aérea que o usuário escolheu
        String nomeCompanhia, codCompanhia;

        System.out.println("Qual a companhia aérea de sua preferência? ");
        System.out.print("Nome da companhia -> ");
        nomeCompanhia = sc.nextLine().toUpperCase();
        codCompanhia = geraCodCompanhia(nomeCompanhia);
        System.out.println();

        CompanhiaAerea companhiaAerea = new CompanhiaAerea(codCompanhia, nomeCompanhia);
        return companhiaAerea;
    }

    public String geraCodCompanhia(String nomeCompanhia) {
        // gera o código da companhia aérea escolhida pelo usuário
        String inicio = nomeCompanhia.substring(0, 1);
        String meio = nomeCompanhia.substring(nomeCompanhia.length() / 2, (nomeCompanhia.length() / 2) + 1);
        String fim = nomeCompanhia.substring(nomeCompanhia.length() - 1);
        String codigo = inicio + meio + fim;
        return codigo;
    }

    public Voo pegaVoo(Aeroporto aeroportoOrigem) {
        // lê as informações do voo do usuário
        String numVoo, codAeroportoDestino;
        int capacidade = (int) (Math.random() * 500);

        System.out.println("Escolha seu voo: ");
        numVoo = geraNumeroVoo();
        System.out.print("Código do aeroporto de destino -> ");
        codAeroportoDestino = sc.nextLine().toUpperCase();
        System.out.println();
        System.out.println("Digite seu endereço de destino: ");
        Aeroporto aeroportoDestino = new Aeroporto(codAeroportoDestino, pegaEndereco());

        Voo voo = new Voo(numVoo, aeroportoOrigem, aeroportoDestino, null, null, capacidade);
        return voo;
    }

    public String geraNumeroVoo() {
        // gera um número de voo aleatório
        int numeroVoo = (int) (Math.random() * 9000) + 1000;
        return String.valueOf(numeroVoo);
    }

    public Passageiro pegaPassageiro(Endereco enderecoOrigem) {
        // lê as informações dos passageiro
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

    public Passagem pegaPassagem(Voo vooIda, Passageiro passageiro, int ordemPassageiro) {
        // gera uma passagem para tal passageiro
        String numPassagem, assento;
        int capacidade = (int) (Math.random() * 500);

        numPassagem = geraNumPassagem(ordemPassageiro);
        assento = geraAssentoPassagem(ordemPassageiro);
        Voo vooVolta = new Voo(geraNumeroVoo(), vooIda.getDestino(), vooIda.getOrigem(), null, null, capacidade);
        Passagem passagem = new Passagem(numPassagem, vooIda, vooVolta, passageiro, assento);
        return passagem;
    }

    public String geraAssentoPassagem(int ordemPassageiro) {
        // gera o número do assento baseado na ordem de reservas
        String prefixoAssento = "A";
        return prefixoAssento + ordemPassageiro;
    }

    public String geraNumPassagem(int ordemPassageiro) {
        // gera o número da passagem baseado na ordem de reservas
        String prefixoPassagem = "P";
        return prefixoPassagem + ordemPassageiro;
    }

    public Bilhete<String> pegaBilhete(List<Passagem> passagens,
            CompanhiaAerea companhiaAerea, int qntDeReservas) {
        // gera um bilhete levando em conta a quantidade de passagens de cada bilhete
        String numBilhete;
        numBilhete = geraNumBilhete(qntDeReservas);
        double preco = Math.random() * 2000;

        Bilhete<String> bilhete = new Bilhete<>(numBilhete, passagens, preco, companhiaAerea);
        return bilhete;
    }

    public String geraNumBilhete(int qntDeReservas) {
        // gera o número do bilhete baseado na ordem de reservas
        String prefixoBilhete = "B";
        return prefixoBilhete + qntDeReservas;
    }

    public String menuInicial() {
        // layout do menu inicial
        String opMenu;
        System.out.println("===============================================");
        System.out.println("MENU INICIAL: ");
        System.out.println("Ver informações no(s) bilhete(s) - digite 1");
        System.out.println("Alterar informação no(s) bilhete(s) - digite 2");
        System.out.println("Sair - digite 3");
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
        System.out.println("MENU DE ALTERAÇÃO: ");
        System.out.println("-- Escolha o tipo de informação que deseja alterar --");
        System.out.println("Informações do Passageiro - digite 1");
        System.out.println("Informações do Bilhete - digite 2");
        System.out.println("Voltar ao menu inicial - digite 3");
        System.out.println("===============================================");

        System.out.println();
        System.out.print("-> ");
        opAlteracao = sc.nextLine();
        System.out.println();
        return opAlteracao;
    }

    public void alteraInfoPassageiro(List<Passageiro> passageiros) {
        // altera as informações do passageiro especificado pelo usuário
        System.out.print("Digite o nome do passageiro -> ");
        String nomePassageiro = sc.nextLine();
        System.out.println();

        Passageiro passageiroAlvo;
        for (Passageiro passageiro : passageiros) {
            if (passageiro.getNome().equals(nomePassageiro)) {
                passageiroAlvo = passageiro;
                System.out.println("Altere as informações do passageiro " + nomePassageiro + ": ");
                System.out.print("Novo nome -> ");
                passageiroAlvo.setNome(sc.nextLine());
                System.out.println();
                System.out.print("Novo CPF -> ");
                passageiroAlvo.setCpf(sc.nextLine());
                System.out.println();
                System.out.print("Novo RG -> ");
                passageiroAlvo.setRg(sc.nextLine());
                System.out.println();
                System.out.print("Novo Celular -> ");
                passageiroAlvo.setCelular(sc.nextLine());
                System.out.println();
                System.out.print("Novo Número do passaporte -> ");
                passageiroAlvo.setNumPassaporte(sc.nextLine());
                System.out.println();
                return;
            }
        }
        System.out.println("Nenhum passageiro com esse nome encontrado!");
        System.out.println();
    }

    public void alteraInfoPassagem(List<Passagem> listPassagem, List<Bilhete<String>> bilhetes) {
        // altera as informações da passagem especificada pelo usuário
        System.out.println("===============================================");
        System.out.println("-- Escolha uma operação --");
        System.out.println("Adicionar passagem - digite 1");
        System.out.println("Remover passagem - digite 2");
        System.out.println("Alterar passagem - digite 3");
        System.out.println("Voltar ao menu de alteração - digite 4");
        System.out.println("===============================================");
        System.out.println();
        String opAlteraPassagem = sc.nextLine().toLowerCase();
        String numPassagem;
        Endereco endereco;

        switch (opAlteraPassagem) {
            case "1":
                System.out.println("Digite o endereço de origem: ");
                System.out.println();
                endereco = pegaEndereco();
                listPassagem.add(pegaPassagem(pegaVoo(pegaAeroporto(endereco)), pegaPassageiro(endereco), bilhetes.size()));
                break;
            case "2":
                System.out.print("Digite o número da passagem que deseja remover -> ");
                numPassagem = sc.nextLine().toUpperCase();
                System.out.println();
                for (Passagem passagem : listPassagem) {
                    if (passagem.getNumero().equals(numPassagem)) {
                        listPassagem.remove(passagem);
                        break;
                    }
                }
                break;
            case "3":
                System.out.print("Digite o número da passagem que deseja alterar -> ");
                numPassagem = sc.nextLine().toUpperCase();
                System.out.println("Digite o endereço de origem: ");
                System.out.println();
                endereco = pegaEndereco();
                for (Passagem passagem : listPassagem) {
                    if (passagem.getNumero().equals(numPassagem)) {
                        passagem = pegaPassagem(pegaVoo(pegaAeroporto(endereco)), pegaPassageiro(endereco),
                                bilhetes.size());
                        break;
                    }
                }
                break;
            case "4":
                break;
            default:
                System.out.println("Opção Inválida! Digite 1, 2 ou 3.");
                break;
        }
    }

    public void alteraInfoBilhete(List<Bilhete<String>> bilhetes) {
        // altera as informações do bilhete especificado pelo usuário
        System.out.print("Digite o número do bilhete -> ");
        String numBilhete = sc.nextLine().toUpperCase();
        System.out.println();

        Bilhete<String> bilheteAlvo;
        for (Bilhete<String> bilhete : bilhetes) {
            if (bilhete.getNumBilhete().equals(numBilhete)) {
                bilheteAlvo = bilhete;
                System.out.println("Altere as informações do bilhete " + numBilhete + ": ");
                System.out.println();
                bilheteAlvo.setCompAerea(pegaCompanhiaAerea());
                double preco = Math.random() * 2000;
                bilheteAlvo.setPreco(preco);
                System.out.println("Deseja alterar a(s) passagem(ns) do bilhete?(s | n) ");
                String addOuRmv = sc.nextLine().toLowerCase();
                if (addOuRmv.equals("sim") || addOuRmv.equals("s"))
                    alteraInfoPassagem(bilheteAlvo.getListPassagem(), bilhetes);
                return;
            }
        }
        System.out.println("Nenhum bilhete com esse número encontrado!");
        System.out.println();
    }

    public void reservaFeita(boolean comSucesso) {
        // mensagem para quando a reserva foi feita com sucesso... ou não.
        if (comSucesso) {
            System.out.println("Voo reservado com sucesso!\nAgradeçemos a preferência, tenha um bom dia!");
            System.out.println();
        } else {
            System.out.println("Nenhum voo foi reservado...");
            System.out.println();
        }
    }

    public void alteracaoFeita() {
        // mensagem para quando a alteração foi feita.
        System.out.println("Alteração feita com sucesso!\nAgradeçemos a preferência, tenha um bom dia!");
        System.out.println();
    }
}
