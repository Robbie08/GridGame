public class LinkedList {

    public Node oHeadNode;

    public void addHeadNode(int iXPos, int iYPos){

        // Create an instance of the node class
        Node oNode = new Node();

        // set our Linked List's grid coordinate values
        oNode.xPosition = iXPos;
        oNode.yPosition = iYPos;

        // set our head node value
        oNode.nextNode = oHeadNode;

        // set our head node to our new node
        oHeadNode = oNode;

    }
    public Node removeHeadNode(){
        // Hold our head node value in a temp var
        Node oTemp = oHeadNode;

        // set our head node to the next node
        oHeadNode = oTemp.nextNode;

        return oTemp;
    }

    public Node getoHeadNode(){
        return oHeadNode;
    }

}

