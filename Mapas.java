import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;

public class Mapas{

    public Mapas(){
    }
    public static void main(String[] args){
        Map<String, String> mapa = new TreeMap<>();
        Scanner sc = new Scanner(System.in);
        String chave, valor;

        for (int i = 0; i < 5; i++){
            chave = sc.next();
            valor = sc.next();
            mapa.put(chave, valor);
        }
        try {
            chave = sc.next();
            System.out.println(String.format("Valor da chave(%s)=%s", chave, mapa.get(chave)));
            System.out.println(String.format("Quantidade de elementos: %d", mapa.size()));
        } catch (NullPointerException npe) {
            
        }
        sc.close();
    }
}
