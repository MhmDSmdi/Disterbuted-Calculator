import java.io.Serializable;

public class Packet implements Serializable {
    private OperatorType operator;
    private double operation1;
    private double operation2;

    public Packet(OperatorType type, double op1, double op2) {
        this.operator = type;
        this.operation1 = op1;
        this.operation2 = op2;
    }

    public OperatorType getOperator() {
        return operator;
    }

    public void setOperator(OperatorType operator) {
        this.operator = operator;
    }

    public double getOperation1() {
        return operation1;
    }

    public void setOperation1(double operation1) {
        this.operation1 = operation1;
    }

    public double getOperation2() {
        return operation2;
    }

    public void setOperation2(double operation2) {
        this.operation2 = operation2;
    }
}
