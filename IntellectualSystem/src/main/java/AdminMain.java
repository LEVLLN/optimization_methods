import entity.asker.Answer;
import entity.asker.ExpertController;
import entity.asker.Question;
import entity.asker.Variant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMain {
    public static void main(String[] args) throws IOException {
        extendQuestion(); //Усовершенствовать вопрос
       // addQuestion(); //Добавить новый вопрос

    }

    static void addAnswer(ArrayList<String> tags) throws IOException {
        ExpertController cr = new ExpertController();
        List<Answer> anwerList = new ArrayList<Answer>();
        Answer a;
        a = cr.createAnswer();
        a.setTags(tags);
        anwerList.add(a);
        cr.writeToFile(anwerList, "tempAnsw.json");

    }

    static void extendQuestion() throws IOException {
        ExpertController creator = new ExpertController();
        List<Question> questions;
        List<String> newTags = new ArrayList<String>();
        List<Variant> newVariants = new ArrayList<Variant>();
        questions = creator.getQuestionsFromFile();

        for (int i = 0; i < questions.size(); i++) {
            System.out.println(i + 1 + ") " + questions.get(i).getQuestionValue());
        }
        System.out.println("Выберите вопрос, который хотите усовершенствовать");
        Scanner scannerI = new Scanner(System.in);
        int numQuestion = scannerI.nextInt() - 1;
        System.out.println("Всего вариантов: " + questions.get(numQuestion).getVariants().size());
        int varCountOld = questions.get(numQuestion).getVariants().size();
        System.out.println("Сколько вариантов добавить?");
        Scanner countQSc = new Scanner(System.in);
        int countQuestion = countQSc.nextInt();
        for (int i = 1; i <= countQuestion; i++) {
            newVariants = questions.get(numQuestion).getVariants();
            newVariants.add(creator.createVariant(i + varCountOld-1));
        }
        questions.get(numQuestion).setVariants(newVariants);
        creator.writeToFile(questions,"questions.json");
        for (Question question : questions) {
            for (Variant var : question.getVariants()) {
                newTags.add(var.getVariantValue());
            }

        }
        creator.writeToFile(newTags,"allVariantList.json");
    }

    static void addQuestion() throws IOException {
        ExpertController creator = new ExpertController();
        Question question;
        question = creator.createQuestion();
        List<Question> questions;
        questions = creator.getQuestionsFromFile();
        questions.add(question);
        creator.writeToFile(questions, "questions.json");
        addAllVariants();
    }

    static void addAllVariants() throws IOException {
        ExpertController conroller = new ExpertController();
        List<Question> questions;
        questions = conroller.getQuestionsFromFile();
        List<String> variantList = new ArrayList<String>();


        for (Question question : questions) {
            for (Variant v : question.getVariants()) {
                variantList.add(v.getVariantValue());
            }
        }
        conroller.writeToFile(variantList, "allVariantList.json");

    }

}
