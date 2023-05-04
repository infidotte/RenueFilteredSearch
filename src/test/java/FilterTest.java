import org.example.Condition;
import org.example.DoublyList;
import org.example.Node;
import org.junit.Test;

public class FilterTest {
    private final String filterTest =
            "(column[1]>10&column[5]='GKA'&column[2]>123)||(column[1]<10000&column[11]=0||(column[2]>100||column[7]<>100))";

    @Test
    public void zxc() {
        String[] conditions = filterTest.split("[&|\\||(|)]");
        String[] preparedConditions = new String[20];
        int counter = 0;
        for (String c : conditions) {
            if (c.contains("column[")) {
                preparedConditions[counter++] = c;
            }
        }
        String ex = filterTest;
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
        Node head = null;
        Node curr = null;
        Node prev = null;
        boolean canOperate = false;
        for (int j = 0; j < c.length; j++) {
            switch (c[j]) {
                case '(':
                    deep++;
                    canOperate = true;
                    break;
                case ')':
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
                    if (canOperate || prev != null) {
                        //add item to linked list.
                        doublyList.addNode(condition, isAnd, deep);
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
