import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class questao4 {
    public static void main(String[] args) {
        Map<String, String> mapa = new TreeMap<>();

        Scanner sc = new Scanner(System.in);


        for (int i = 0; i < 5; i++) {
            String linha = sc.nextLine();
            String[] partes = linha.split(" ");

            String chave = partes[0];
            String valor = partes[1];
            mapa.put(chave, valor);
        }
        
        
        String chaveRemover = sc.next();
        
        System.out.println(mapa);

        String valorRemovido = mapa.remove(chaveRemover);

        System.out.println(mapa);

        System.out.println("Quantidade de elementos: " + mapa.size());
        sc.close();
    }
}
