package exerciciosEmSala;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Especialidade implements Comparable<Especialidade> {
    private int codigo;
    private String descricao;

    public Especialidade(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public int compareTo(Especialidade outraEspecialidade) {
        return descricao.compareTo(outraEspecialidade.getDescricao());
    }

    @Override
    public String toString() {
        return this.codigo + ": " + this.descricao;
    }
}

public class questao2P2_Perin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Especialidade> especialidades = new ArrayList<>();
        List<Especialidade> especialidadesReverse = new ArrayList<>();

        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            int codigo = scanner.nextInt();
            String descricao = scanner.next();

            Especialidade especialidade = new Especialidade(codigo, descricao);
            especialidades.add(especialidade);
        }

        for (int i = n - 1; i >= 0; i--) {
            especialidadesReverse.add(especialidades.get(i));
        }

        System.out.print("[");
        for (Especialidade especialidadeReverse : especialidadesReverse) {
            System.out.print(especialidadeReverse);
            if (especialidadesReverse.get(n - 1).getCodigo() == especialidadeReverse.getCodigo())
                System.out.print("]");
            else    
                System.out.print(", ");
        }
        System.out.println();

        System.out.print("[");
        for (Especialidade especialidade : especialidades) {
            System.out.print(especialidade);
            if (especialidades.get(n - 1).getCodigo() == especialidade.getCodigo())
                System.out.print("]");
            else    
                System.out.print(", ");
        }
        System.out.println();

        Especialidade maiorEspecialidade = Collections.max(especialidadesReverse);
        System.out.println("Maior " + maiorEspecialidade);
        n--;

        System.out.print("[");
        for (int j = 0; j < n; j++) {
            System.out.print(especialidades.get(j));
            if (especialidades.get(n - 1).getCodigo() == especialidades.get(j).getCodigo())
                System.out.print("]");
            else    
                System.out.print(", ");
        }
        System.out.println();
        scanner.close();
    }
}
