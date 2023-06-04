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
        String querReservar;
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

        System.out.print("Quer reservar um voo?(sim - s | não - n) -> ");
        querReservar = sc.nextLine().toLowerCase();
        System.out.println();

        while (querReservar.equals("s") || querReservar.equals("sim")) {
            System.out.println("Para reservar seu voo complete os campos abaixo.");
            System.out.println();

            //Endereço ====================================================
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
                    System.out.println("-- ATENÇÃO! As passagens são por passageiro e não por voo --");
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

            // Reserva mais voos
            System.out.println("Quer reservar outro voo?(sim - s | não - n) -> ");
            querReservar = sc.nextLine().toLowerCase();
            qntDeReservas++;
        }

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

        System.out.println("\nPassageiros de cada bilhete em ordem alfabética:");
        for (Bilhete<String> bilhete : bilhetes) {
            System.out.println("Bilhete " + bilhete.getNumBilhete() + ":");
            bilhete.imprimirBilhete();
            System.out.println();
        }
        sc.close();
    }
}
