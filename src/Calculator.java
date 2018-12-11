public class Calculator {

    private double add(double op1, double op2) {
        return op1 + op2;
    }

    private double subtract(double op1, double op2) {
        return op1 - op2;
    }

    private double divide(double op1, double op2) {
        return op1 / op2;
    }

    private double multiple(double op1, double op2) {
        return op1 * op2;
    }

    private double sin(double op1) {
        return Math.sin(op1);
    }

    private double cos(double op1) {
        return Math.cos(op1);
    }

    private double tan(double op1) {
        return Math.tan(op1);
    }

    private double cot(double op1) {
        return 1 / Math.tan(op1);
    }

    public double calculate(InputPacket inputPacket) {
        double op1 = inputPacket.getOperation1();
        double op2 = inputPacket.getOperation2();
        switch (inputPacket.getOperator()) {
            case ADD:
                return add(op1, op2);
            case SUBTRACT:
                return subtract(op1, op2);
            case DIVIDE:
                return divide(op1, op2);
            case MULTIPLY:
                return multiple(op1, op2);
            case SIN:
                return sin(op1);
            case COS:
                return cos(op1);
            case TAN:
                return tan(op1);
            case COT:
                return cot(op1);
        }
        return op1;
    }
}
