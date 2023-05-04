package org.example;

import lombok.Data;

@Data
public class Node {

    //wrap to predicate
    private Condition item;
    ///

    private boolean isAnd;
    private int rDepp;
    private Node next;
    private Node previous;

    public Node(Condition item, boolean isAnd, int rDeep) {
        this.item = item;
        this.isAnd = isAnd;
        this.rDepp = rDeep;
    }
}
