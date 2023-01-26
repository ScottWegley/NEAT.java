package src;
public class NodeGene {

    private int id;
    private TYPE type;

    public enum TYPE {
        INPUT_NODE,
        HIDDEN_NODE,
        OUTPUT_NODE
    }
}
