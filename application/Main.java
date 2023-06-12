package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PegaInput input = new PegaInput(); // para ler as entradas sem poluir o método main
        int qntDeReservas; // para o controle da quantidade de voos reservados
        String opMenu; // para escolher qual operação deseja fazer no menu inicial
        int quantPassageiros = 0; // para o controle dos passageiros em cada bilhete
        boolean intValido; // para verificar as entradas com números inteiros

        // listas de cada classe como pedido no trabalho
        List<Endereco> enderecos = new ArrayList<>();
        List<Aeroporto> aeroportos = new ArrayList<>();
        List<CompanhiaAerea> companhiasAereas = new ArrayList<>();
        List<Voo> voos = new ArrayList<>();
        List<Passageiro> passageiros = new ArrayList<>();
        List<Passagem> passagens = new ArrayList<>();
        List<Bilhete<String>> bilhetes = new ArrayList<>();

        // INTRODUÇÃO AO SISTEMA
        System.out.println("--- BEM VINDO AO K.P. RESERVAS DE VOO OFFLINE!!! ---");
        System.out.println("-- AQUI O SEU ATENDIMENTO É NOSSA PRIORIDADE! --");
        System.out.println();
        System.out.println("- Para fazer reservas responda às perguntas abaixo -");
        System.out.println();
        System.out.println("Quantos voos deseja reservar?");
        System.out.print("-> ");
        qntDeReservas = sc.nextInt();
        System.out.println();

        // faz as reservas baseado na quantidade de reservas definida pelo usuário
        for (int k = 0; k < qntDeReservas; k++) {
            List<Passagem> passagensSubLista = new ArrayList<Passagem>();
            intValido = false;
            System.out.println("Reserva " + (k + 1) + ": ");

            // lê o endereço ====================================================
            System.out.println("Digite o endereço de origem: ");
            enderecos.add(input.pegaEndereco());
            System.out.println();

            // lê o aeroporto ==================================================
            aeroportos.add(input.pegaAeroporto(enderecos.get(k)));

            // lê a companhia Aérea ============================================
            companhiasAereas.add(input.pegaCompanhiaAerea());

            // lê o voo ========================================================
            voos.add(input.pegaVoo(aeroportos.get(k)));

            // lê a quantidade de passageiros =================================================
            while (!intValido) {
                // verifica se a quantidade de passageiros é válida
                try {
                    System.out.println("Digite a quantidade de passageiros: ");
                    System.out.println("-- ATENÇÃO! As passagens são uma por pessoa! --");
                    System.out.print("-> ");
                    quantPassageiros = sc.nextInt();
                    System.out.println();

                    intValido = true;
                } catch (InputMismatchException e) {
                    System.out.println("Número inválido! Digite novamente!");
                    sc.nextLine();
                }
            }
            sc.nextLine();

            // lê cada passageiro baseado na quantidade de passageiros dada
            for (int i = 0; i < quantPassageiros; i++)
                passageiros.add(input.pegaPassageiro(enderecos.get(k)));

            // cria as passagens ================================================
            for (int j = 0; j < quantPassageiros; j++) {
                passagens.add(input.pegaPassagem(voos.get(k), passageiros.get(j), j));
                passagensSubLista.add(input.pegaPassagem(voos.get(k), passageiros.get(j), j));
            }

            // cria os bilhetes
            Collections.sort(passagensSubLista); // Ordena a lista de passagens em ordem alfabética
            bilhetes.add(input.pegaBilhete(passagensSubLista, companhiasAereas.get(k), k));

            // Reserva feita
            input.reservaFeita(true);
        }

        // MENU INICIAL
        do {
            opMenu = input.menuInicial();
            switch (opMenu) {
            case "1":
                // no caso 1 imprimimos os bilhetes dos voos reservados
                if (bilhetes.isEmpty()) {
                    input.reservaFeita(false);
                } else {
                    // cria uma cópia da lista de bilhetes
                    List<Bilhete<String>> bilhetesCopia = new ArrayList<>(bilhetes);

                    for (Bilhete<String> bilhete : bilhetesCopia) {
                        System.out.println("Bilhete " + bilhete.getNumBilhete() + ":");
                        bilhete.imprimirBilhete();
                        System.out.println();
                    }
                }
                break;
            case "2":
                // no caso 2, permitimos a alteração de informações
                if (qntDeReservas == 0) {
                    input.reservaFeita(false);
                    break;
                }
                //MENU DE ALTERAÇÃO
                String opAlteracao;
                do {
                    opAlteracao = input.menuAlteraInfo();
                    switch (opAlteracao) {
                        case "1":
                            // caso 1: altera as informações de um determinado passageiro
                            input.alteraInfoPassageiro(passageiros);
                            input.alteracaoFeita();
                            break;
                        case "2":
                            // caso 2: altera as informações de um determinado bilhete
                            input.alteraInfoBilhete(bilhetes);
                            input.alteracaoFeita();
                            break;
                        case "3":
                            // Volta ao menu inicial
                            break;
                        default:
                            System.out.println("Opção Inválida! Digite 1, 2 ou 3.");
                            break;
                    }
                } while (!opAlteracao.equals("3"));
                break;
            case "3":
                // caso 3: encerra o programa
                break;
            default:
                // default: lida com entradas inválidas
                System.out.println("Opção Inválida! Digite 1, 2 ou 3.");
                break;
        }
        } while (!opMenu.equals("3"));

        // MENSAGEM DE ENCERRAMENTO DO PROGRAMA
        System.out.println("Agradeçemos a preferência, tenha um bom dia!");
        sc.close();
    }
}
