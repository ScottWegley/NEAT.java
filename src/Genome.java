package src;

import src.utils.CustomHashSet;

public class Genome {
    CustomHashSet<NodeGene> nodes = new CustomHashSet<NodeGene>();
    CustomHashSet<ConnectionGene> connections = new CustomHashSet<ConnectionGene>();

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