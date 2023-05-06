package org.example.DoublyLinkedList;

import lombok.Data;

import java.util.function.Predicate;

@Data
public class DoublyList {
    private Node head, tail;

    public DoublyList() {
        this.head = null;
        this.tail = null;
    }

    public void addNode(Node node) {
        if (head == null) {
            head = tail = node;
            head.setPrevious(null);
            tail.setNext(null);
        } else {
            tail.setNext(node);
            node.setPrevious(tail);
            tail = node;
            tail.setNext(null);
        }
    }

    public void resolveAnds() {
        Predicate<String> predicate = null;
        Node curr = head;
        while (curr.getNext() != null) {

            if (curr.getNext().isAnd()) {
                predicate = curr.getItem().and(curr.getNext().getItem());
                extracted(curr, predicate);
            } else curr = curr.getNext();
        }
    }

    public void resolveOrs() {
        Predicate<String> predicate = null;
        Node curr = head;
        while (curr.getNext() != null) {
            if (!curr.getNext().isAnd()) {
                predicate = curr.getItem().or(curr.getNext().getItem());
                extracted(curr, predicate);
            } else curr = curr.getNext();
        }
    }

    private void extracted(Node curr, Predicate<String> predicate) {
        curr.setItem(predicate);
        if (curr.getNext().getNext() == null) {
            curr.getNext().setPrevious(null);
            curr.setNext(null);
        } else {
            curr.getNext().getNext().setPrevious(curr);
            curr.setNext(curr.getNext().getNext());
        }
    }
}
