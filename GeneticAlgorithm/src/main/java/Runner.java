import entity.Individual;
import entity.PopulationFactory;

import java.util.ArrayList;
import java.util.Collections;

public class Runner {
    static PopulationFactory factory = new PopulationFactory();
    static ArrayList<Individual> parents = new ArrayList<Individual>();
    static ArrayList<Individual> childes = new ArrayList<Individual>();
    static int count = 5;
    static int lastIndividual = count - 1;
    static double newAverageDescendantsDifference;
    static double oldAverageDescendantsDifference;

    public static void main(String[] args) {
        //Генерируем новую популяцию
        ArrayList<Individual> individualNewList = factory.getNewPopulation(count);
        int i= 1;
        for (Individual individual : individualNewList) {
            System.out.println("Новейшая особь "+i+individual);
            System.out.println();
            i+=1;
        }
        System.out.println("____________________________________");


        //Получение сгенерированной популяции
        parents = selectionMethod(individualNewList);
        i= 1;
        for (Individual parent : parents) {
            System.out.println("Родитель "+i+parent);
            System.out.println();
            i+=1;
        }
        System.out.println("____________________________________");
        childes = getNewGeneration(parents);
        //если суммарное(или среднее) отклонение родителей меньше наследников, то реализовать в следующем поколении мутацию.
    }

    static ArrayList<Individual> selectionMethod(ArrayList<Individual> generation){
        Collections.sort(generation, Individual.COMPARE_BY_COUNT);
        System.out.println("Не выжила особь: " + generation.get(lastIndividual) + '\n');
        generation.remove(generation.get(lastIndividual));
        return generation;
    }
    //  Кроссинговер: R G | B
    static Individual getChild(Individual parent1, Individual parent2) {
        Individual child = factory.buildChild(parent1.getR(),parent1.getG(),parent2.getB());
        return child;
    }

    static ArrayList<Individual> getNewGeneration(ArrayList<Individual> parentList){
        ArrayList<Individual> newGeneragion = new ArrayList<Individual>();
        newGeneragion.add(getChild(parentList.get(0), parentList.get(1)));
        newGeneragion.add(getChild(parentList.get(0), parentList.get(2)));
        newGeneragion.add(getChild(parentList.get(0), parentList.get(3)));
        newGeneragion.add(getChild(parentList.get(1), parentList.get(2)));
        newGeneragion.add(getChild(parentList.get(2), parentList.get(3)));
        Collections.sort(newGeneragion, Individual.COMPARE_BY_COUNT);
        System.out.println("____________________________________");
        for (int j = 0; j <newGeneragion.size() ; j++) {
            System.out.println("Потомок" + newGeneragion.get(j));
            System.out.println();
        }
        return newGeneragion;
    }

}
