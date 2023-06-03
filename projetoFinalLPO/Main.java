package projetoFinalLPO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        PegaInput input = new PegaInput();
        Scanner sc = new Scanner(System.in);
        int variavelDeControle = 0;
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

        System.out.println("Quer reservar um voo?(sim - s | não - n) -> ");
        querReservar = sc.nextLine();

        while (querReservar == "s" || querReservar == "sim") {
            System.out.println("Para reservar seu voo complete os campos abaixo.");

            // Endereço ==================================================
            System.out.println("Digite seu endereço de origem: ");
            enderecos.add(input.getEndereco());

            // Aeroporto ==================================================
            aeroportos.add(input.getAeroporto(enderecos.get(variavelDeControle)));

            // Companhia Aérea ============================================
            companhiasAereas.add(input.getCompanhiaAerea());

            // Voo ========================================================
            voos.add(input.getVoo(aeroportos.get(variavelDeControle)));

            // Passageiros =================================================
            while (!intValido) {
                try {
                    System.out.print("Digite a quantidade de passageiros: ");
                    System.out.println("ATENÇÃO! As passagens são por passageiro.");
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
                passageiros.add(input.getPassageiro());

            // Passagens ================================================
            for (int j = 0; j < quantPassageiros; j++)
                passagens.add(input.getPassagem(voos.get(variavelDeControle), passageiros.get(j), variavelDeControle));

            // Reserva mais voos
            System.out.println("Quer reservar outro voo?(sim - s | não - n) -> ");
            querReservar = sc.nextLine();
            variavelDeControle++;
        }

        for (Passagem passagem : passagens) {
            if (passagem.verificarCapacidade()) {
                Voo vooVolta = voos.get((voos.indexOf(passagem.getVooIda()) + 1) % voos.size());
                passagem.setVooVolta(vooVolta);
            }
        }
        
        for (int k = 0; k < qntPassageirosPorVoo.size(); k++) {
            bilhetes.add(input.getBilhete(passagens, limiteInferior, qntPassageirosPorVoo.get(k), companhiasAereas.get(k), variavelDeControle));
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
