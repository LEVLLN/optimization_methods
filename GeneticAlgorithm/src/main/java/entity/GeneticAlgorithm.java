package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GeneticAlgorithm {
    private PopulationFactory factory = new PopulationFactory();
    private ArrayList<Individual> parents = new ArrayList<Individual>();
    private ArrayList<Individual> childes = new ArrayList<Individual>();
    private int count = 5;
    private int lastIndividual = count - 1;
    private double averagePGeneration = 0;
    private double averageCGeneration = 0;
    private ArrayList<Individual> violetIndivides = new ArrayList<Individual>();
    private int mutationCount = 0;
    public ArrayList<Individual> firstGeneration() {
        //Генерируем новую популяцию
        return factory.getNewPopulation(count);
    }

    // Отбираем годных для продолжения рода кандидатов, отсеивая негодных. Будущие родители
    public ArrayList<Individual> selectionMethod(ArrayList<Individual> generation) {
        averagePGeneration = 0;
        Collections.sort(generation, Individual.COMPARE_BY_COUNT);
        //Получить среднее число разницы всей популяции
        for (Individual g : generation) {
            averagePGeneration += g.getSummOfDifference();
        }
        averagePGeneration = averagePGeneration / generation.size();
        System.out.println("Не выжила особь: " + generation.get(lastIndividual) + '\n');
        generation.remove(generation.get(lastIndividual));
        return generation;
    }

    //  Кроссинговер: R G | B
    public Individual getChild(Individual parent1, Individual parent2) {
        return factory.buildChild(parent1.getR(), parent1.getG(), parent2.getB());
    }

    // Получение потомка с мутацией
    public Individual getChildWithMutation(Individual parent1, Individual parent2) {
        Random rndChoise = new Random();
        Random rndRGB = new Random();
        Individual child = new Individual();
        int randomInt = rndChoise.nextInt(3)+1;
        if (randomInt==1) {
            child = factory.buildChild(rndRGB.nextInt(255)+1, parent1.getG(), parent2.getB());
            return child;
        }
        if (randomInt==2) {
            child = factory.buildChild(parent1.getR(), rndRGB.nextInt(255)+1, parent2.getB());
            return child;
        }
        if (randomInt==3) {
            child = factory.buildChild(parent1.getR(), parent1.getG(), rndRGB.nextInt(255)+1);
            return child;
        }
        return child;

    }



    //Получение новых потомков
    public ArrayList<Individual> getNewGeneration(ArrayList<Individual> parentList, boolean needMutation) {
        averageCGeneration = 0;
        ArrayList<Individual> newGeneration = new ArrayList<Individual>();
        if (!needMutation) {
            newGeneration.add(getChild(parentList.get(0), parentList.get(1)));
            newGeneration.add(getChild(parentList.get(2), parentList.get(0)));
            newGeneration.add(getChild(parentList.get(1), parentList.get(3)));
            newGeneration.add(getChild(parentList.get(1), parentList.get(2)));
            newGeneration.add(getChild(parentList.get(2), parentList.get(3)));
            Collections.sort(newGeneration, Individual.COMPARE_BY_COUNT);
            for (Individual g : newGeneration) {
                averageCGeneration += g.getSummOfDifference();
            }
            averageCGeneration = averageCGeneration / newGeneration.size();
            return newGeneration;

        } else {
            mutationCount = mutationCount+1;
            System.out.println("C мутацией");
            newGeneration.add(getChildWithMutation(parentList.get(0), parentList.get(1)));
            newGeneration.add(getChildWithMutation(parentList.get(2), parentList.get(0)));
            newGeneration.add(getChildWithMutation(parentList.get(1), parentList.get(3)));
            newGeneration.add(getChildWithMutation(parentList.get(1), parentList.get(2)));
            newGeneration.add(getChildWithMutation(parentList.get(2), parentList.get(3)));
            Collections.sort(newGeneration, Individual.COMPARE_BY_COUNT);
            for (Individual g : newGeneration) {
                averageCGeneration += g.getSummOfDifference();
            }
            averageCGeneration = averageCGeneration / newGeneration.size();
            return newGeneration;
        }
    }

    public static void main(String[] args) {
        int mainCounter = 0;
        int cicleCounter = 4;
        int planCicleCount = cicleCounter;
        boolean mutable = false;
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        ArrayList<Individual> firstGeneration = geneticAlgorithm.firstGeneration();
        int i = 1;
        for (Individual firstIndivid : firstGeneration) {
            System.out.println("Новейшая особь " + i + firstIndivid + '\n');
            i++;
        }
        while (mainCounter < cicleCounter) {
            int violetCount = 0;
            System.out.println("_____________________________________________________");
            geneticAlgorithm.parents = geneticAlgorithm.selectionMethod(firstGeneration);
            System.out.println("_____________________________________________________");
            i = 1;
            for (Individual parent : geneticAlgorithm.parents) {
                System.out.println("Родитель " + i + parent + '\n');
                i++;
            }
            System.out.println("Среднее отклонение текущего поколения: " + geneticAlgorithm.averagePGeneration);
            System.out.println("_____________________________________________________");
            geneticAlgorithm.childes = geneticAlgorithm.getNewGeneration(geneticAlgorithm.parents, mutable);
            i = 1;
            for (Individual child : geneticAlgorithm.childes) {
                violetCount = 0;
                System.out.println("Потомок " + i + child + '\n');
                i++;
                if (child.getDifferenceR()<=30&&child.getDifferenceG()<=30&&child.getDifferenceB()<=30){
                    geneticAlgorithm.violetIndivides.add(child);
                    violetCount+=1;
                }
            }
            System.out.println("Среднее отклонение следующего поколения: " + geneticAlgorithm.averageCGeneration);
            System.out.println("---------------------------------------------------------------------------------");
            if (geneticAlgorithm.averageCGeneration <  geneticAlgorithm.averagePGeneration) {
                System.out.println("Новое поколение лучше предыдущего");

                mutable=false;
            } else if(geneticAlgorithm.averageCGeneration >=  geneticAlgorithm.averagePGeneration) {
                System.out.println("Новое поколение хуже предыдущего");

                mutable=true;
            }
            Random random = new Random();
            if (violetCount==0){
                cicleCounter++;
            }
            firstGeneration = geneticAlgorithm.childes;
            mainCounter++;
        }
        System.out.println("=================================ВЫВОД ФИОЛЕТОВЫХ ОСОБЕЙ========================================================");
        for (Individual violetIndivide : geneticAlgorithm.violetIndivides) {
            System.out.println("Фиолетовая особь: "+violetIndivide);
            System.out.println("");
        }
        System.out.println("===========================СТАТИСТИКА==============================================================");
        System.out.println();
        System.out.println("Планируемое количество циклов генетического алгоритма: "+ planCicleCount);
        System.out.println("Фактическое количество циклов генетического алгоритма: "+mainCounter);
        int populationCount = mainCounter+1;
        System.out.println("Количество популяций, созданных генетическим алгоритмом: "+populationCount);
        int individualCount = populationCount*5;
        System.out.println("Количество особей, созданных генетическим алгоритмом: "+individualCount);
        int violetPerPopulation = (geneticAlgorithm.violetIndivides.size());
        System.out.println("Общее количество полученных фиолетовых особей : "+ violetPerPopulation);
        System.out.println("Количество мутаций: "+geneticAlgorithm.mutationCount);
    }
}

