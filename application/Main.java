package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PegaInput input = new PegaInput(); //para ler as entradas sem poluir o método main
        int qntDeReservas = 0; //para o controle da quantidade de voos reservados
        String opMenu; //para escolher qual operação deseja fazer no menu inicial
        int quantPassageiros = 0, limiteInferior = 0; //para o controle dos passageiros em cada bilhete
        boolean intValido = false; //para verificar as entradas com números inteiros

        //lista para guardar quantos passageiros vão em cada bilhete
        List<Integer> qntPassageirosPorVoo = new ArrayList<Integer>();

        //listas de cada classe como pedido no trabalho
        List<Endereco> enderecos = new ArrayList<>();
        List<Aeroporto> aeroportos = new ArrayList<>();
        List<CompanhiaAerea> companhiasAereas = new ArrayList<>();
        List<Voo> voos = new ArrayList<>();
        List<Passageiro> passageiros = new ArrayList<>();
        List<Passagem> passagens = new ArrayList<>();
        List<Bilhete<String>> bilhetes = new ArrayList<>();

        do {
            do {
                //lendo a operação que o usuário deseja fazer
                //reserva - 1 | mostrar bilhete(s) - 2 | alterar informação(ões) - 3 | sair - 4
                opMenu = input.menu();

                if (!opMenu.equals("1") && !opMenu.equals("2") && !opMenu.equals("3") && !opMenu.equals("4"))
                    System.out.println("Opção Inválida! Digite 1, 2 ou 3 conforme abaixo...");
            } while (!opMenu.equals("1") && !opMenu.equals("2") && !opMenu.equals("3") && !opMenu.equals("4"));

            switch (opMenu) {
                case "1":
                    //no caso 1 fazemos a reserva do voo
                    System.out.println("Preencha os campos abaixo.");
                    System.out.println();

                    // Endereço ====================================================
                    System.out.println("Digite o endereço de origem: ");
                    enderecos.add(input.getEndereco());
                    System.out.println();

                    // Aeroporto ==================================================
                    aeroportos.add(input.getAeroporto(enderecos.get(qntDeReservas)));

                    // Companhia Aérea ============================================
                    companhiasAereas.add(input.getCompanhiaAerea());

                    // Voo ========================================================
                    voos.add(input.getVoo(aeroportos.get(qntDeReservas)));

                    // Passageiros =================================================
                    while (!intValido) {
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
                    qntPassageirosPorVoo.add(quantPassageiros);

                    for (int i = 0; i < quantPassageiros; i++)
                        passageiros.add(input.getPassageiro(enderecos.get(qntDeReservas)));

                    // Passagens ================================================
                    for (int j = 0; j < quantPassageiros; j++)
                        passagens.add(input.getPassagem(voos.get(qntDeReservas), passageiros.get(j), qntDeReservas));

                    // Colocando o voo de volta em cada passagem
                    for (Passagem passagem : passagens) {
                        if (passagem.verificarCapacidade()) {
                            Voo vooVolta = voos.get((voos.indexOf(passagem.getVooIda()) + 1) % voos.size());
                            passagem.setVooVolta(vooVolta);
                        }
                    }

                    // Bilhetes 
                    for (int k = 0; k < qntPassageirosPorVoo.size(); k++) {
                        bilhetes.add(input.getBilhete(passagens, limiteInferior, qntPassageirosPorVoo.get(k),
                                companhiasAereas.get(k), qntDeReservas));
                        limiteInferior = qntPassageirosPorVoo.get(k);
                    }

                    input.reservaFeita(true);
                    for (Bilhete<String> bilhete : bilhetes) {
                        System.out.println("Bilhete " + bilhete.getNumBilhete() + ":");
                        bilhete.imprimirBilhete();
                        System.out.println();
                    }
                    qntDeReservas++;
                    break;

                case "2":
                    //no caso 2 imprimimos os bilhetes dos voos reservados
                    if (bilhetes.isEmpty()) {
                        input.reservaFeita(false);
                    } else {
                        for (Bilhete<String> bilhete : bilhetes) {
                            System.out.println("Bilhete " + bilhete.getNumBilhete() + ":");
                            bilhete.imprimirTodoBilhete();
                            System.out.println();
                        }
                    }
                    break;
                case "3":
                    //no caso 3 permitimos a alteração de informações
                    System.out.println("Em manutenção, volte mais tarde...");
                    System.out.println();
                    break;
                case "4":
                    //no caso 4 encerramos o programa
                    break;

            }
        } while (!opMenu.equals("4"));

        System.out.println("Agradeçemos a preferência, tenha um bom dia!");
        sc.close();
    }
}
