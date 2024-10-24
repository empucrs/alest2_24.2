import java.util.HashSet;
import java.util.Set;

public class DCDirigido {

    private enum estado {WHITE, GREY, BLACK};

    private Digraph g;
    private estado[] estadoDosVertices;

    public DCDirigido(String filename){
        g = new Digraph(new In(filename));
    }

    public boolean contemCiclo(){
        estadoDosVertices = new estado[g.V()];

        for(int i=0; i<g.V(); i++)
          estadoDosVertices[i]=estado.WHITE;

        for(int v=0; v<g.V(); v++)
          if(estadoDosVertices[v]==estado.WHITE && contemCiclo(g,v))
                return true;
        return false;
    }

    private boolean contemCiclo(Digraph g, int v){
        estadoDosVertices[v]=estado.GREY;

        for (int u : g.adj(v)) {
            if(estadoDosVertices[u]==estado.GREY)
                return true;
            else if(estadoDosVertices[u]==estado.WHITE){
                if(contemCiclo(g, u)) return true;
            }
        }
        estadoDosVertices[v]=estado.BLACK;
        return false;
    }

    public static void main(String[] args) {

        if(args.length!=1){
            System.out.println("java DCDirigido <caminhoSoArquivo>/<nomeDoArquivo>");
            System.exit(0);
        }

        DCDirigido dcd = new DCDirigido(args[0]);

        if(dcd.contemCiclo())
            System.out.println("O grafo dirigido contem ciclos");
        else
            System.out.println("O grafo dirigido está livre de ciclos");
        
    }

    
}