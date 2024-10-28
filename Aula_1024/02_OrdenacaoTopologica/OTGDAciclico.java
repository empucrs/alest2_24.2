/**
 * OTGDAciclico
 */
public class OTGDAciclico {

    private boolean isDAG;
    private Digraph dg;

    public OTGDAciclico(String filename) {
        dg = new Digraph(filename);
    }
}