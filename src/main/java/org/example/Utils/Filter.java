package org.example.Utils;

import org.example.DoublyLinkedList.DoublyList;
import org.example.DoublyLinkedList.Node;
import org.example.Entity.Condition;

import java.util.function.Predicate;

public class Filter {
    public void filter(String filter) {
        String[] conditions = filter.split("[&|\\||(|)]");
        String[] preparedConditions = new String[20];
        int counter = 0;
        for (String c : conditions) {
            if (c.contains("column[")) {
                preparedConditions[counter++] = c;
            }
        }
        String ex = filter;
        for (String c : preparedConditions) {
            if (c != null) {
                ex = ex.replace(c, "p");
            }
        }
        char[] c = ex.toCharArray();
        DoublyList doublyList = new DoublyList();
        int deep = 0;
        boolean isAnd = false;
        int currCond = 0;
        boolean canOperate = false;
        for (int j = 0; j < c.length; j++) {
            switch (c[j]) {
                case '(':
                    Predicate<String> predicate;
                    deep++;
                    canOperate = true;
                    break;
                case ')':
                    predicate = doublyList.listToPredicate();
                    deep--;
                    canOperate = false;
                    break;
                case '&':
                    isAnd = true;
                    canOperate = true;
                    break;
                case '|':
                    isAnd = false;
                    if (c[j + 1] != '|')
                        throw new RuntimeException("Unrecognized operation in filter");
                    j++;
                    canOperate = true;
                    break;
                case 'p':
                    Condition condition = readCondition(preparedConditions[currCond]);
                    Node node = new Node(condition, isAnd, deep);
                    if (canOperate || node.getPrevious() != null) {
                        //add item to linked list.
                        doublyList.addNode(node);
                    }
                    currCond++;
                    canOperate = true;
                    break;
            }
        }
        doublyList.printNodes();
    }

    private Condition readCondition(String condition) {
        int idxColR = condition.indexOf("]");
        String operation = readOperation(idxColR, condition);
        int columnIndex = Integer.parseInt(condition.substring(condition.indexOf("[") + 1, condition.indexOf("]")));
        int operationSize = operation.equals("<>") ? 2 : 1;
        String value = condition.substring(condition.indexOf(operation) + operationSize);
        return new Condition(columnIndex, operation, value);
    }

    private String readOperation(int idx, String str) {
        char operation = str.charAt(idx + 1);
        switch (operation) {
            case '>':
                return ">";
            case '=':
                return "=";
            case '<':
                char secondChar = str.charAt(idx + 2);
                return secondChar == '>' ? "<>" : "<";
            default:
                throw new RuntimeException("Undefined operation");
        }

    }
}
