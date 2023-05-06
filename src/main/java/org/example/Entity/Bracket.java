package org.example.Entity;

import org.example.DoublyLinkedList.DoublyList;
import org.example.DoublyLinkedList.Node;

import java.util.function.Predicate;

public class Bracket implements Predicate<String> {
    DoublyList doublyList;


    public Bracket () {
        doublyList = new DoublyList();
    }
    public void resolveConditions() {
        doublyList.resolveAnds();
        doublyList.resolveOrs();
        Node p = doublyList.getHead();
        if(p.getItem() instanceof Bracket){
            Bracket bracket = (Bracket) p.getItem();
            DoublyList doublyList1 = bracket.doublyList;
            doublyList1.resolveAnds();
            doublyList1.resolveOrs();

        }
    }
    public boolean isSingle() {
        return doublyList.getHead().getNext()==null;
    }


    public void addElement(Node node) {
        doublyList.addNode(node);
    }

    public DoublyList getDoublyList() {
        return doublyList;
    }

    public void setDoublyList(DoublyList doublyList) {
        this.doublyList = doublyList;
    }

    @Override
    public boolean test(String s) {
        return doublyList.getHead().getItem().test(s);
    }
    @Override
    public Predicate<String> and(Predicate<? super String> other) {
        return Predicate.super.and(other);
    }

    @Override
    public Predicate<String> negate() {
        return Predicate.super.negate();
    }

    @Override
    public Predicate<String> or(Predicate<? super String> other) {
        return Predicate.super.or(other);
    }

}
