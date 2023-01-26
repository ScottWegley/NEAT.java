package src;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Genome {
    Map<Integer, NodeGene> nodeGeneMap = new HashMap<Integer,NodeGene>();
    Map<Integer, ConnectionGene> connectionGeneMap = new HashMap<Integer,ConnectionGene>();
    Random r = new Random();

    /**
     * Take two nodes that have not been connected and connect them, giving them a random weight.
     */
    public void mutateAddConnection(){

    }

    /**
     * Take an existing connection and add a node in the middle.  Connect the original input to the new node with a weight of 1. Connect the new node to the original output with the same weight as the original connection.  Disables the original connection.
     */
    public void mutateAddNode(){
        
    }
}