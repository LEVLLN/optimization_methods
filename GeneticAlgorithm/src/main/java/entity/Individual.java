package entity;

import java.util.Comparator;

public class Individual {
    private int R;
    private int G;
    private int B;
    private double differenceB;
    private double differenceR;
    private double differenceG;
    private double coefficientR;
    private double coefficientG;
    private double coefficientB;

    public int getR() {
        return R;
    }

    public void setR(int r) {
        R = r;
    }

    public int getG() {
        return G;
    }

    public void setG(int g) {
        G = g;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }

    public double getDifferenceB() {
        return differenceB;
    }

    public void setDifferenceB(double differenceB) {
        this.differenceB = differenceB;
    }

    public double getDifferenceR() {
        return differenceR;
    }

    public void setDifferenceR(double differenceR) {
        this.differenceR = differenceR;
    }

    public double getDifferenceG() {
        return differenceG;
    }

    public void setDifferenceG(double differenceG) {
        this.differenceG = differenceG;
    }

    public double getCoefficientR() {
        return coefficientR;
    }

    public void setCoefficientR(double coefficientR) {
        this.coefficientR = coefficientR;
    }

    public double getCoefficientG() {
        return coefficientG;
    }

    public void setCoefficientG(double coefficientG) {
        this.coefficientG = coefficientG;
    }

    public double getCoefficientB() {
        return coefficientB;
    }

    public void setCoefficientB(double coefficientB) {
        this.coefficientB = coefficientB;
    }

    @Override
    public String toString() {
        return  "{"+"R=" + R +
                ", G=" + G +
                ", B=" + B+"}"+'\n'+
                "Отклонение генов{"
                + "R= " + getDifferenceR() + ", "
                + "G= " + getDifferenceG() + ", "
                + "B= " + getDifferenceB() + "}"+'\n'+
                "Сумма отклонений: "+getSummOfDifference();

    }

    public double getSummOfDifference() {
        return R + B + G;
    }
    public static final Comparator<Individual> COMPARE_BY_COUNT = new Comparator<Individual>() {
        @Override
        public int compare(Individual o1, Individual o2) {
            int result = Double.compare(o1.getSummOfDifference(),o2.getSummOfDifference());
            return  result;
        }

    };
}
