package org.example.Utils;

import org.example.DoublyLinkedList.DoublyList;
import org.example.DoublyLinkedList.Node;
import org.example.Entity.Bracket;
import org.example.Entity.Condition;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Predicate;

public class Filter {
    public static Predicate<String> prepareFilter(String filterTest) {
        String[] conditions = filterTest.split("[&|\\||(|)]");
        List<String> preparedConditions = new ArrayList<>();
        for (String c : conditions) {
            if (c.contains("column[")) {
                preparedConditions.add(c);
            }
        }
        String ex = filterTest;
        for (String c : preparedConditions) {
            if (c != null) {
                ex = ex.replace(c, "p");
            }
        }
        char[] c = ex.toCharArray();
        boolean isAnd = false;
        int currCond = 0;
        Bracket mainBracket = new Bracket();
        Bracket bracket = new Bracket();
        boolean isClose = false;
        Stack<Bracket> bracketStack = new Stack<>();
        for (int j = 0; j < c.length; j++) {
            switch (c[j]) {
                case '(':
                    bracket = new Bracket();
                    if (!isClose)
                        bracketStack.add(bracket);
                    break;
                case ')':
                    Bracket tmpBracket = bracketStack.pop();
                    Node tmpNode = new Node(tmpBracket, isAnd);
                    if (bracketStack.size() == 0) {
                        mainBracket.addElement(tmpNode);
                    } else {
                        bracketStack.peek().addElement(tmpNode);

                    }
                    isClose = true;
                    bracket = new Bracket();
                    break;
                case '&':
                    isAnd = true;
                    isClose = false;
                    break;
                case '|':
                    isAnd = false;
                    isClose = false;
                    if (c[j + 1] != '|')
                        throw new RuntimeException("Unrecognized operation in filter");
                    j++;
                    break;
                case 'p':
                    Condition condition = readCondition(preparedConditions.get(currCond));
                    currCond++;
                    Node node = new Node(condition, isAnd);
                    bracket.addElement(node);
                    break;
            }
        }
        mainBracket.resolveConditions();
        return mainBracket;
    }

    private static Condition readCondition(String condition) {
        int idxColR = condition.indexOf("]");
        String operation = readOperation(idxColR, condition);
        int columnIndex = Integer.parseInt(condition.substring(condition.indexOf("[") + 1, condition.indexOf("]")));
        int operationSize = operation.equals("<>") ? 2 : 1;
        String value = condition.substring(condition.indexOf(operation) + operationSize);
        return new Condition(columnIndex, operation, value);
    }

    private static String readOperation(int idx, String str) {
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
