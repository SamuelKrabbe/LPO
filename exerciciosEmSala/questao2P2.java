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

public class questao2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Especialidade> especialidades = new ArrayList<>();

        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            int codigo = scanner.nextInt();
            String descricao = scanner.next();

            Especialidade especialidade = new Especialidade(codigo, descricao);
            especialidades.add(especialidade);
        }

        Collections.sort(especialidades);

        System.out.print("[");
        for (Especialidade especialidade : especialidades) {
            System.out.print(especialidade);
            if (especialidades.get(n - 1).getCodigo() == especialidade.getCodigo())
                System.out.print("]");
            else    
                System.out.print(", ");
        }
        System.out.println();

        Especialidade menorEspecialidade = especialidades.get(0);
        System.out.println("Menor " + menorEspecialidade);

        int novoCodigo = scanner.nextInt();
        String novaDescricao = scanner.next();

        Especialidade novaEspecialidade = new Especialidade(novoCodigo, novaDescricao);
        especialidades.set(0, novaEspecialidade);

        System.out.print("[");
        for (Especialidade especialidade : especialidades) {
            System.out.print(especialidade);
            if (especialidades.get(n - 1).getCodigo() == especialidade.getCodigo())
                System.out.print("]");
            else    
                System.out.print(", ");
        }
        System.out.println();
        scanner.close();
    }
}
