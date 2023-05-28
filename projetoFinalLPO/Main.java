package projetoFinalLPO;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Inserindo endereços
        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(new Endereco("Rua A", 123, "Bairro A", "Cidade A", "CEP A"));
        enderecos.add(new Endereco("Rua B", 456, "Bairro B", "Cidade B", "CEP B"));
        enderecos.add(new Endereco("Rua C", 789, "Bairro C", "Cidade C", "CEP C"));
        enderecos.add(new Endereco("Rua D", 987, "Bairro D", "Cidade D", "CEP D"));
        enderecos.add(new Endereco("Rua E", 654, "Bairro E", "Cidade E", "CEP E"));
        enderecos.add(new Endereco("Rua F", 321, "Bairro F", "Cidade F", "CEP F"));

        // Inserindo aeroportos
        List<Aeroporto> aeroportos = new ArrayList<>();
        aeroportos.add(new Aeroporto("A001", enderecos.get(0)));
        aeroportos.add(new Aeroporto("A002", enderecos.get(1)));
        aeroportos.add(new Aeroporto("A003", enderecos.get(2)));
        aeroportos.add(new Aeroporto("A004", enderecos.get(3)));

        // Inserindo companhias aéreas
        List<CompanhiaAerea> companhiasAereas = new ArrayList<>();
        companhiasAereas.add(new CompanhiaAerea("C001", "Latam Airlines"));
        companhiasAereas.add(new CompanhiaAerea("C002", "Emirates"));
        companhiasAereas.add(new CompanhiaAerea("C003", "Air France"));
        companhiasAereas.add(new CompanhiaAerea("C004", "Lufthansa"));

        // Inserindo voos
        List<Voo> voos = new ArrayList<>();
        voos.add(new Voo("V001", aeroportos.get(0), aeroportos.get(1), null, null, 100));
        voos.add(new Voo("V002", aeroportos.get(1), aeroportos.get(2), null, null, 150));
        voos.add(new Voo("V003", aeroportos.get(2), aeroportos.get(3), null, null, 200));
        voos.add(new Voo("V004", aeroportos.get(3), aeroportos.get(0), null, null, 120));

        // Inserindo passageiros
        List<Passageiro> passageiros = new ArrayList<>();
        passageiros.add(new Passageiro("Ana", "CPF1", "RG1", enderecos.get(0), "Celular1", "AB1234567"));
        passageiros.add(new Passageiro("Pedro", "CPF2", "RG2", enderecos.get(1), "Celular2", "CD2345678"));
        passageiros.add(new Passageiro("Sofia", "CPF3", "RG3", enderecos.get(2), "Celular3", "EF3456789"));
        passageiros.add(new Passageiro("Lucas", "CPF4", "RG4", enderecos.get(3), "Celular4", "GH4567890"));
        passageiros.add(new Passageiro("Laura", "CPF5", "RG5", enderecos.get(4), "Celular5", "IJ5678901"));
        passageiros.add(new Passageiro("Gabriel", "CPF6", "RG6", enderecos.get(5), "Celular6", "KL6789012"));
        passageiros.add(new Passageiro("Alice", "CPF7", "RG7", enderecos.get(0), "Celular7", "MN7890123"));
        passageiros.add(new Passageiro("João", "CPF8", "RG8", enderecos.get(1), "Celular8", "OP8901234"));
        passageiros.add(new Passageiro("Isabella", "CPF9", "RG9", enderecos.get(2), "Celular9", "QR9012345"));
        passageiros.add(new Passageiro("Miguel", "CPF10", "RG10", enderecos.get(3), "Celular10", "ST0123456"));

        // Inserindo passagens
        List<Passagem> passagens = new ArrayList<>();
        passagens.add(new Passagem("P001", voos.get(0), null, passageiros.get(0), "Assento 001"));
        passagens.add(new Passagem("P002", voos.get(1), null, passageiros.get(1), "Assento 002"));
        passagens.add(new Passagem("P003", voos.get(2), null, passageiros.get(2), "Assento 003"));
        passagens.add(new Passagem("P004", voos.get(3), null, passageiros.get(3), "Assento 004"));
        passagens.add(new Passagem("P005", voos.get(0), null, passageiros.get(4), "Assento 005"));
        passagens.add(new Passagem("P006", voos.get(1), null, passageiros.get(5), "Assento 006"));
        passagens.add(new Passagem("P007", voos.get(2), null, passageiros.get(6), "Assento 007"));
        passagens.add(new Passagem("P008", voos.get(3), null, passageiros.get(7), "Assento 008"));
        passagens.add(new Passagem("P009", voos.get(0), null, passageiros.get(8), "Assento 009"));
        passagens.add(new Passagem("P010", voos.get(1), null, passageiros.get(9), "Assento 010"));

        // Verificando capacidade dos voos e atribuindo a volta para algumas passagens
        for (Passagem passagem : passagens) {
            if (passagem.verificarCapacidade()) {
                Voo vooVolta = voos.get((voos.indexOf(passagem.getVooIda()) + 1) % voos.size());
                passagem.setVooVolta(vooVolta);
            }
        }

        // Inserindo bilhetes
        List<Bilhete<String>> bilhetes = new ArrayList<>();
        bilhetes.add(new Bilhete<>("B001", passagens.subList(0, 2), 599.9, companhiasAereas.get(0), "Economica"));
        bilhetes.add(new Bilhete<>("B002", passagens.subList(2, 4), 699.9, companhiasAereas.get(1), "Economica"));
        bilhetes.add(new Bilhete<>("B003", passagens.subList(4, 6), 799.9, companhiasAereas.get(2), "Executiva"));
        bilhetes.add(new Bilhete<>("B004", passagens.subList(6, 8), 899.9, companhiasAereas.get(3), "Primeira Classe"));

        // Imprimindo informações dos bilhetes reservados
        // System.out.println("Informações dos Bilhetes Reservados:");
        // for (Bilhete<String> bilhete : bilhetes) {
        // System.out.println(bilhete);
        // }

        // Listando os passageiros de cada bilhete em ordem alfabética
        System.out.println("\nPassageiros de cada bilhete em ordem alfabética:");
        for (Bilhete<String> bilhete : bilhetes) {
            System.out.println("Bilhete " + bilhete.getNumBilhete() + ":");
            bilhete.imprimirBilhete();
            System.out.println();
        }
    }
}
