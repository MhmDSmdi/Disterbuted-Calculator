import java.io.Serializable;

public class ResultPacket implements Serializable {
    private double result;
    private long time;

    public ResultPacket(double result, long time) {
        this.result = result;
        this.time = time;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Calculation Time: " + time + " ");
        stringBuilder.append("Result: " + result);
        return stringBuilder.toString();
    }
}