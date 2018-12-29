package model;

public enum OperatorType {
    ADD(0),
    SUBTRACT(1),
    DIVIDE(2),
    MULTIPLY(3),
    SIN(4),
    COS(5),
    TAN(6),
    COT(7);

    private int type;

    OperatorType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
