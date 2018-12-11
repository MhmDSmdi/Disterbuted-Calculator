public enum OperatorType {
    Add(0),
    Subtract(1),
    Divide(2),
    Multiply(3),
    Sin(4),
    Cos(5),
    Tan(6),
    Cot(7);

    private int type;

    OperatorType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
