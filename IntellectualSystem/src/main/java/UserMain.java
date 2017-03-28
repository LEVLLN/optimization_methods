import entity.asker.Answer;
import entity.asker.ExpertController;
import entity.asker.Question;
import entity.asker.Variant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserMain {
    public static void main(String[] args) throws IOException {

        ExpertController creator = new ExpertController();
        List<Question> questions;
        List<String> tags = new ArrayList<String>();
        List<Answer> answerList;
        Answer finalAnswer = new Answer();
        questions = creator.getQuestionsFromFile();
        answerList = creator.getAnswersFromFile();
        int count = 0;
        int maxCount = 0;
        float currentCoef = 0.0f;
        float bestCoef = 0.0f;
        for (Question question : questions) {
            System.out.println(question.getQuestionValue());
            for (Variant variant : question.getVariants()) {
                System.out.println(variant.getId() + ") " + variant.getVariantValue());
            }
            System.out.println("Ваш ответ");
            Scanner idScanner = new Scanner(System.in);
            int varId = idScanner.nextInt();
            String tag = question.getVariants().get(varId - 1).getVariantValue();
            tags.add(tag);

        }
        System.out.println("Вы ищите работу по таким признакам: ");
        for (String tag : tags) {
            System.out.println(tag);
        }
        System.out.println("_________________________________________");
        System.out.println("Экспертная система подбирает варианты");
        for (Answer answer : answerList) {
            count = 0;
            maxCount = answer.getTags().size();
            for (String answerTag : answer.getTags()) {
                for (String tag : tags) {
                    if (tag.equals(answerTag)) {
                        count += 1;
                    }
                }
            }
            currentCoef = (float) count / maxCount;

            if (currentCoef > bestCoef) {
                bestCoef = currentCoef;
                finalAnswer = answer;
            }

            System.out.println("Мы получили совпадений " + count + " из " + maxCount + " на фирме " + answer.getCompanyName() + " с коэфициентом: " + currentCoef);
        }
        System.out.println("\n>>>>>Наиболее благоприятный выбор для Вас: " + finalAnswer.getCompanyName() + " профессия: " + finalAnswer.getProfession() + '\n');

        System.out.println("Если Вы не нашли подходящую вакансию, Вы можете помочь нам расширить систему, добавив свой вариант");
        System.out.println("1) Добавить свой вариант");
        System.out.println("2) Меня всё устраивает");
        Scanner extendSc = new Scanner(System.in);
        int extendInt = extendSc.nextInt();
        switch (extendInt) {
            case 1: {
                Answer newAnsw;
                newAnsw = creator.createAnswer();
                newAnsw.setTags(tags);
                answerList.add(newAnsw);
                creator.writeToFile(answerList, "answer.json");
                System.out.println("Спасибо за помощь нашей системе!");
            }
            case 2: {
                System.out.println("Благодарим за участие");
                break;
            }
        }

    }
}
