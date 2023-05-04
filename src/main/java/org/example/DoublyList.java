package org.example;

public class DoublyList {
    private Node head, tail;

    public DoublyList() {
        this.head = null;
        this.tail = null;
    }

    public void addNode(Condition item, boolean isAnd, int rDeep) {
        //Create a new node
        Node newNode = new Node(item, isAnd, rDeep);
        //if list is empty, head and tail points to newNode
        if (head == null) {
            head = tail = newNode;
            //head's previous will be null
            head.setPrevious(null);
            //tail's next will be null
            tail.setNext(null);
        } else {
            //add newNode to the end of list. tail->next set to newNode
            tail.setNext(newNode);
            //newNode->previous set to tail
            newNode.setPrevious(tail);
            //newNode becomes new tail
            tail = newNode;
            //tail's next point to null
            tail.setNext(null);
        }
    }

    public void printNodes() {
        Node current = head;
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        System.out.println("Nodes: ");
        while (current != null) {
            System.out.println(current.getItem().toString() + " deep: " + current.getRDepp() + " isAnd: " + current.isAnd());
            current = current.getNext();
        }
    }
}
