package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        PegaInput input = new PegaInput();
        Scanner sc = new Scanner(System.in);
        int qntDeReservas = 0;
        String opMenu;
        int quantPassageiros = 0, limiteInferior = 0;
        boolean intValido = false;

        List<Integer> qntPassageirosPorVoo = new ArrayList<Integer>();
        List<Endereco> enderecos = new ArrayList<>();
        List<Aeroporto> aeroportos = new ArrayList<>();
        List<CompanhiaAerea> companhiasAereas = new ArrayList<>();
        List<Voo> voos = new ArrayList<>();
        List<Passageiro> passageiros = new ArrayList<>();
        List<Passagem> passagens = new ArrayList<>();
        List<Bilhete<String>> bilhetes = new ArrayList<>();

        do {
            do {
                opMenu = input.menu();

                if (!opMenu.equals("1") && !opMenu.equals("2") && !opMenu.equals("3") && !opMenu.equals("4"))
                    System.out.println("Opção Inválida! Digite 1, 2 ou 3 conforme abaixo...");
            } while (!opMenu.equals("1") && !opMenu.equals("2") && !opMenu.equals("3") && !opMenu.equals("4"));

            switch (opMenu) {
                case "1":
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

                    for (Passagem passagem : passagens) {
                        if (passagem.verificarCapacidade()) {
                            Voo vooVolta = voos.get((voos.indexOf(passagem.getVooIda()) + 1) % voos.size());
                            passagem.setVooVolta(vooVolta);
                        }
                    }

                    for (int k = 0; k < qntPassageirosPorVoo.size(); k++) {
                        bilhetes.add(input.getBilhete(passagens, limiteInferior, qntPassageirosPorVoo.get(k),
                                companhiasAereas.get(k), qntDeReservas));
                        limiteInferior = qntPassageirosPorVoo.get(k);
                    }

                    input.reservaFeita(true);
                    qntDeReservas++;
                    break;
                case "2":
                    if (bilhetes.isEmpty()) {
                        input.reservaFeita(false);
                    } else {
                        for (Bilhete<String> bilhete : bilhetes) {
                            System.out.println("Bilhete " + bilhete.getNumBilhete() + ":");
                            bilhete.imprimirBilhete();
                            System.out.println();
                        }
                    }
                    break;
                case "3":
                    System.out.println("Em manutenção, volte mais tarde...");
                    System.out.println();
                    break;
                case "4":
                    break;

            }
        } while (!opMenu.equals("4"));

        System.out.println("Agradeçemos a preferência, tenha um bom dia!");
        sc.close();
    }
}
