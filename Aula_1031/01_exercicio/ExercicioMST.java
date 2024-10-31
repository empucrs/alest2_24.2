import java.nio.file.Paths;
import java.util.HashMap;

public class ExercicioMST {


    public static void main(String[] args) {

        if(args.length!=1){
            System.out.println("Informe o caminho do arquivo csv que contem os pares de ponto");
        }

        Mapa mp = new Mapa(args[0]);
    }
    
}