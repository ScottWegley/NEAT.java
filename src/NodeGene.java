package src;
public class NodeGene {

    private int innovationNumber;
    private TYPE type;

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public NodeGene(int innovationNumber, TYPE type) {
        this(innovationNumber);
        this.type = type;
    }

    public NodeGene(int innovaitonNumber){
        this.innovationNumber = innovaitonNumber;
    }

    public int getInnovationNumber(){
        return innovationNumber;
    }

    public enum TYPE {
        INPUT_NODE,
        HIDDEN_NODE,
        OUTPUT_NODE
    }

}
