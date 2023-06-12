import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Collections;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// INTERFACE
interface Verificavel {
    boolean validar(String codigo);

    void solicitarNovo();
}

// MAIN
public class tudoJunto {
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
        System.out.println("--- BEM VINDO AO K&P RESERVAS DE VOO OFFLINE!!! ---");
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

// CLASSES
class Aeroporto {
    private String codAeroporto;
    private Endereco endereco;

    // CONSTRUTOR
    public Aeroporto(String codAeroporto, Endereco endereco) {
        this.codAeroporto = codAeroporto;
        this.endereco = endereco;
    }

    // MÉTODOS DA CLASSE
    @Override
    public String toString() {
        return "Código Aeroporto: " + codAeroporto + ", Cidade: " + endereco.getCidade();
    }

    // Getters e Setters
    public String getCodAeroporto() {
        return codAeroporto;
    }

    public void setCodAeroporto(String codAeroporto) {
        this.codAeroporto = codAeroporto;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}

class Bilhete<T> {
    private T numBilhete;
    private List<Passagem> listPassagem;
    private double preco;
    private CompanhiaAerea compAerea;

    // CONSTRUTOR
    public Bilhete(T numBilhete, List<Passagem> listPassagem, double preco, CompanhiaAerea compAerea) {
        this.numBilhete = numBilhete;
        this.listPassagem = listPassagem;
        this.preco = preco;
        this.compAerea = compAerea;
    }

    // MÉTODOS DA CLASSE
    public void imprimirBilhete() {
        //imprime as informações do bilhete
        System.out.println("- Número do Bilhete: " + numBilhete);
        System.out.println("- Companhia Aérea: " + compAerea.getNomeCompanhia());
        System.out.println(String.format("- Preço: R$%.2f", preco));
        System.out.println("- Passagem(ns):");

        // cria uma copia da lista de passagens
        List<Passagem> passagemCopy = new ArrayList<>(listPassagem);

        if (passagemCopy.isEmpty())
            System.out.println("Nenhuma passagem...");
        else {
            // imprime cada passagem do bilhete
            synchronized (passagemCopy) {
        	    for (Passagem passagem : passagemCopy) {
                    System.out.println("  -> " + passagem + ";");
                }
            }
        }
    }

    // Getters e Setters
    public T getNumBilhete() {
        return numBilhete;
    }

    public void setNumBilhete(T numBilhete) {
        this.numBilhete = numBilhete;
    }

    public List<Passagem> getListPassagem() {
        return listPassagem;
    }

    public void setListPassagem(List<Passagem> listPassagem) {
        this.listPassagem = listPassagem;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public CompanhiaAerea getCompAerea() {
        return compAerea;
    }

    public void setCompAerea(CompanhiaAerea compAerea) {
        this.compAerea = compAerea;
    }
}

class CompanhiaAerea {
    private String codCompanhia;
    private String nomeCompanhia;

    // CONSTRUTOR
    public CompanhiaAerea(String codCompanhia, String nomeCompanhia) {
        this.codCompanhia = codCompanhia;
        this.nomeCompanhia = nomeCompanhia;
    }

    // MÉTODOS DA CLASSE
    @Override
    public String toString() {
        return "Código da Companhia Aérea: " + codCompanhia + "\n" +
                "Nome da Companhia Aérea: " + nomeCompanhia;
    }

    // Getters e Setters
    public String getCodCompanhia() {
        return codCompanhia;
    }

    public void setCodCompanhia(String codCompanhia) {
        this.codCompanhia = codCompanhia;
    }

    public String getNomeCompanhia() {
        return nomeCompanhia;
    }

    public void setNomeCompanhia(String nomeCompanhia) {
        this.nomeCompanhia = nomeCompanhia;
    }
}

class Endereco {
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String cep;

    // CONSTRUTORES
    // Construtor para endereço completo
    public Endereco(String rua, int numero, String bairro, String cidade, String cep) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
    }

    // Construtor para endereço onde será fornecida somente a cidade
    public Endereco(String cidade) {
        this.cidade = cidade;
    }

    // MÉTODOS DA CLASSE
    // Getters e Setters
    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}

class Passageiro extends Pessoa {
    private String numPassaporte;

    // CONSTRUTOR
    public Passageiro(String nome, String cpf, String rg, Endereco endereco, String celular, String numPassaporte) {
        super(nome, cpf, rg, endereco, celular);
        this.numPassaporte = numPassaporte;
    }

    // MÉTODOS DA CLASSE
    @Override
    public String toString() {
        return "Nome: " + super.getNome() + ", RG: " + super.getRg();
    }

    // Getters e Setters
    public String getNumPassaporte() {
        return numPassaporte;
    }

    public void setNumPassaporte(String numPassaporte) {
        this.numPassaporte = numPassaporte;
    }
}

class Passagem implements Comparable<Passagem> {
    private String numero;
    private Voo vooIda;
    private Voo vooVolta;
    private Passageiro passageiro;
    private String assento;

    // CONSTRUTORES
    // contrutor com de passagem com voo de volta
    public Passagem(String numero, Voo vooIda, Voo vooVolta, Passageiro passageiro, String assento) {
        this.numero = numero;
        this.vooIda = vooIda;
        this.vooVolta = vooVolta;
        this.passageiro = passageiro;
        this.assento = assento;
    }

    // contrutor com de passagem sem voo de volta
    public Passagem(String numero, Voo vooIda, Passageiro passageiro, String assento) {
        this.numero = numero;
        this.vooIda = vooIda;
        this.passageiro = passageiro;
        this.assento = assento;
    }

    // MÉTODOS DA CLASSE
    public boolean verificarCapacidade() {
        if (vooIda != null && vooIda.getCapacidade() <= 0) {
            System.out.println("Voo de ida sem disponibilidade de assentos.");
            return false;
        }

        if (vooVolta != null && vooVolta.getCapacidade() <= 0) {
            System.out.println("Voo de volta sem disponibilidade de assentos.");
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        String strVooVolta = vooVolta != null ? vooVolta.getNumVoo() : "N/A";

        return "      Passagem " + numero + ": " + "\n" +
                "      -- Voo de Ida: " + vooIda.getNumVoo() + "\n" +
                "      -- Voo de Volta: " + strVooVolta + "\n" +
                "      -- Passageiro: " + passageiro.getNome() + "\n" +
                "      -- Assento: " + assento;
    }

    @Override
    public int compareTo(Passagem outraPassagem) {
        String nomePassageiroAtual = this.getPassageiro().getNome();
        String nomeOutraPassagem = outraPassagem.getPassageiro().getNome();

        return nomePassageiroAtual.compareTo(nomeOutraPassagem);
    }

    // Getters e Setters
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Voo getVooIda() {
        return vooIda;
    }

    public void setVooIda(Voo vooIda) {
        this.vooIda = vooIda;
    }

    public Voo getVooVolta() {
        return vooVolta;
    }

    public void setVooVolta(Voo vooVolta) {
        this.vooVolta = vooVolta;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    public String getAssento() {
        return assento;
    }

    public void setAssento(String assento) {
        this.assento = assento;
    }
}

abstract class Pessoa implements Verificavel {
    private String nome;
    private String cpf;
    private String rg;
    private Endereco endereco;
    private String celular;
    private String dataNascimento;

    // CONSTRUTOR
    public Pessoa(String nome, String cpf, String rg, Endereco endereco, String celular) {
        this.nome = nome;
        this.rg = rg;
        this.endereco = endereco;
        this.celular = celular;

        validar(cpf);
    }

    // MÉTODOS DA CLASSE
    @Override
    public boolean validar(String cpf) {
        boolean cpfValido = true;

        try {
            this.cpf = cpf;
        } catch (Exception E) {
            System.out.println("CPF inválido! Digite um novo valor:");
            solicitarNovo();
        }
        return cpfValido;
    }

    @Override
    public void solicitarNovo() {
        Scanner sc = new Scanner(System.in);
        String novoCpf = sc.nextLine();
        validar(novoCpf);
        sc.close();
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}

class Voo {
    private String numVoo;
    private Aeroporto origem;
    private Aeroporto destino;
    private Calendar dataHoraOrigem;
    private Calendar dataHoraDestino;
    private int capacidade;

    // CONSTRUTOR
    public Voo(String numVoo, Aeroporto origem, Aeroporto destino, Calendar dataHoraOrigem,
            Calendar dataHoraDestino, int capacidade) {
        this.numVoo = numVoo;
        this.origem = origem;
        this.destino = destino;
        this.dataHoraOrigem = dataHoraOrigem;
        this.dataHoraDestino = dataHoraDestino;
        this.capacidade = capacidade;
    }

    // MÉTODOS DA CLASSE
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        String strDataHoraOrigem = sdf.format(dataHoraOrigem.getTime());
        String strDataHoraDestino = sdf.format(dataHoraDestino.getTime());

        return "Número do Voo: " + numVoo + "\n" +
                "Origem: " + origem.toString() + "\n" +
                "Destino: " + destino.toString() + "\n" +
                "Data/Hora Origem: " + strDataHoraOrigem + "\n" +
                "Data/Hora Destino: " + strDataHoraDestino + "\n" +
                "Capacidade: " + capacidade;
    }

    // Getters e Setters
    public String getNumVoo() {
        return numVoo;
    }

    public void setNumVoo(String numVoo) {
        this.numVoo = numVoo;
    }

    public Aeroporto getOrigem() {
        return origem;
    }

    public void setOrigem(Aeroporto origem) {
        this.origem = origem;
    }

    public Aeroporto getDestino() {
        return destino;
    }

    public void setDestino(Aeroporto destino) {
        this.destino = destino;
    }

    public Calendar getDataHoraOrigem() {
        return dataHoraOrigem;
    }

    public void setDataHoraOrigem(Calendar dataHoraOrigem) {
        this.dataHoraOrigem = dataHoraOrigem;
    }

    public Calendar getDataHoraDestino() {
        return dataHoraDestino;
    }

    public void setDataHoraDestino(Calendar dataHoraDestino) {
        this.dataHoraDestino = dataHoraDestino;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
}

// PEGA INPUT
class PegaInput {
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
        System.out.print("Digite o nome do passageiro que deseja alterar -> ");
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
