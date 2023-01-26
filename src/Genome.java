package src;

import src.NodeGene;
import src.utils.CustomHashSet;

public class Genome {
    CustomHashSet<NodeGene> nodes = new CustomHashSet<NodeGene>();
    CustomHashSet<ConnectionGene> connections = new CustomHashSet<ConnectionGene>();
    Network network;

    public Genome(Network parentNetwork) {
        this.network = parentNetwork;
    }

    /**
     * Take two nodes that have not been connected and connect them, giving them a random weight.
     */
    public void mutateAddConnection(){
        
    }

    /**
     * Take an existing connection and add a node in the middle.  Connect the original input to the new node with a weight of 1. Connect the new node to the original output with the same weight as the original connection.  Disables the original connection.
     */
    public void mutateAddNode(){
        ConnectionGene toSplit = connections.getElement((int)Math.random() * connections.getSize());
        toSplit.setExpressed(false);
        NodeGene newNodeGene = network.generateNode();
        newNodeGene.setType(NodeGene.TYPE.HIDDEN_NODE);
        ConnectionGene fromConnection = new ConnectionGene(toSplit.getInputNodeID(), newNodeGene.getID());
    }
}