package entity;

import java.util.ArrayList;
import java.util.Random;

public class PopulationFactory {
    //Создание самой первой особи
    public Individual createNewIndividual() {
        Individual individual;
        Random rand = new Random();
        individual = new Individual();
        individual.setR(rand.nextInt(255)+1);
        individual.setG(rand.nextInt(255)+1);
        individual.setB(rand.nextInt(255)+1);

        final int violetR = 96;
        final int violetG = 96;
        final int violetB = 159;

        double differenceB = (Math.abs(violetB - individual.getB()));
        double differenceR = (Math.abs(violetR - individual.getR()));
        double differenceG = (Math.abs(violetG - individual.getG()));

        double coefficientR = getReproporcialCoef(differenceR) / (getReproporcialCoef(differenceR) + getReproporcialCoef(differenceB) + getReproporcialCoef(differenceG));
        double coefficientG = getReproporcialCoef(differenceG) / (getReproporcialCoef(differenceR) + getReproporcialCoef(differenceB) + getReproporcialCoef(differenceG));
        double coefficientB = getReproporcialCoef(differenceB) / (getReproporcialCoef(differenceR) + getReproporcialCoef(differenceB) + getReproporcialCoef(differenceG));

        individual.setDifferenceR(differenceR);
        individual.setDifferenceG(differenceG);
        individual.setDifferenceB(differenceB);
        individual.setCoefficientR(coefficientR);
        individual.setCoefficientG(coefficientG);
        individual.setCoefficientB(coefficientB);
        return individual;
    }
    public Individual buildChild(int R,int G,int B) {
        Individual individual;
        individual = new Individual();
        individual.setR(R);
        individual.setG(G);
        individual.setB(B);

        final int violetR = 96;
        final int violetG = 96;
        final int violetB = 159;

        double differenceB = (Math.abs(violetB - individual.getB()));
        double differenceR = (Math.abs(violetR - individual.getR()));
        double differenceG = (Math.abs(violetG - individual.getG()));

        double coefficientR = getReproporcialCoef(differenceR) / (getReproporcialCoef(differenceR) + getReproporcialCoef(differenceB) + getReproporcialCoef(differenceG));
        double coefficientG = getReproporcialCoef(differenceG) / (getReproporcialCoef(differenceR) + getReproporcialCoef(differenceB) + getReproporcialCoef(differenceG));
        double coefficientB = getReproporcialCoef(differenceB) / (getReproporcialCoef(differenceR) + getReproporcialCoef(differenceB) + getReproporcialCoef(differenceG));

        individual.setDifferenceR(differenceR);
        individual.setDifferenceG(differenceG);
        individual.setDifferenceB(differenceB);
        individual.setCoefficientR(coefficientR);
        individual.setCoefficientG(coefficientG);
        individual.setCoefficientB(coefficientB);
        return individual;
    }
    //Получить обратный коэффициент цвета
    static double getReproporcialCoef(double coef) {
        if (coef == 0.0) {
            return 1.;
        } else {
            return 1. / coef;
        }
    }

    //Создание самой первой популяции
    public ArrayList<Individual> getNewPopulation(int count) {
        ArrayList<Individual> population = new ArrayList<Individual>();
        for (int i = 0; i < count; i++) {
            population.add(createNewIndividual());
        }

        return population;
    }
}
