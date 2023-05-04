package org.example.DoublyLinkedList;

import java.util.function.Predicate;

public class DoublyList {
    private Node head, tail;

    public DoublyList() {
        this.head = null;
        this.tail = null;
    }

    public void addNode(Node node) {
        //Create a new node
        //if list is empty, head and tail points to newNode
        if (head == null) {
            head = tail = node;
            //head's previous will be null
            head.setPrevious(null);
            //tail's next will be null
            tail.setNext(null);
        } else {
            //add newNode to the end of list. tail->next set to newNode
            tail.setNext(node);
            //newNode->previous set to tail
            node.setPrevious(tail);
            //newNode becomes new tail
            tail = node;
            //tail's next point to null
            tail.setNext(null);
        }
    }

    public Predicate<String> listToPredicate() {
        Predicate<String> predicate = null;
        Node current = head;
        if (head == null) {
            System.out.println("List is empty");
            return null;
        }
        while (current != null) {
            if (current.getPrevious() != null) {
                predicate = current.isAnd() ? current.getPrevious().getItem().and(current.getItem()) : current.getPrevious().getItem().or(current.getItem());
            }
            current = current.getNext();
        }
        return predicate;
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
