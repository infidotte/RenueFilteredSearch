package org.example.DoublyLinkedList;

import lombok.Data;
import org.example.Entity.Condition;

import java.util.function.Predicate;

@Data
public class Node {

    //wrap to predicate
    private Predicate<String> item;
    ///

    private boolean isAnd;
    private Node next;
    private Node previous;

    public Node(Predicate<String> item, boolean isAnd) {
        this.item = item;
        this.isAnd = isAnd;
    }
}
