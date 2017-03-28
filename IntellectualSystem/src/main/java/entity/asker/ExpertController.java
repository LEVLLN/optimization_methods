package entity.asker;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpertController {

    public Variant createVariant(int id) {
        Variant variant = new Variant();
        System.out.println("Добавьте вариант");
        Scanner scanner = new Scanner(System.in);
        String variantStr = scanner.nextLine();
        variant.setVariantValue(variantStr);
        variant.setId(id + 1);
        return variant;
    }

    public Answer createAnswer() {

        Answer answer = new Answer();
        System.out.println("Введите название компании");
        Scanner companySc = new Scanner(System.in);
        String companyStr = companySc.nextLine();
        System.out.println("Введите название профессии");
        Scanner professionSc = new Scanner(System.in);
        String professionStr = professionSc.nextLine();
        answer.setCompanyName(companyStr);
        answer.setProfession(professionStr);
        List<String> stringList = new ArrayList<String>();
        answer.setTags(stringList);
        return answer;
    }

    public Question createQuestion() {

        List<Variant> variants = new ArrayList<Variant>();
        System.out.println("Как будет звучать вопрос?");
        Scanner questionScanner = new Scanner(System.in);
        String questionStr = questionScanner.nextLine();
        System.out.println("Сколько вариантов ответа?");
        Scanner countVariants = new Scanner(System.in);
        int variantNum = countVariants.nextInt();
        for (int i = 0; i < variantNum; i++) {
            variants.add(createVariant(i));
        }
        Question question = new Question();
        question.setQuestionValue(questionStr);
        question.setVariants(variants);
        return question;
    }

    public void writeToFile(Object object, String filePath) throws IOException {

        Gson gson = new Gson();
        String jsonString = gson.toJson(object);
        File myFile = new File(filePath);
        FileOutputStream fOut = new FileOutputStream(myFile);
        OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
        myOutWriter.append(jsonString);
        myOutWriter.close();
        fOut.close();
    }

    public ArrayList<Question> getQuestionsFromFile() throws IOException {

        Gson gson = new Gson();
        ArrayList<Question> questionArray = gson.fromJson(new FileReader("questions.json"), new TypeToken<ArrayList<Question>>() {
        }.getType());
        return questionArray;
    }

    public ArrayList<Answer> getAnswersFromFile() throws FileNotFoundException {

        Gson gson = new Gson();
        ArrayList<Answer> answerArrayList = gson.fromJson(new FileReader("answer.json"), new TypeToken<ArrayList<Answer>>() {
        }.getType());
        return answerArrayList;
    }
    public ArrayList<String> getVariantListFile() throws FileNotFoundException {
        Gson gson = new Gson();
        ArrayList<String> stringArrayList = gson.fromJson(new FileReader("allVariantList.json"), new TypeToken<ArrayList<String>>() {
        }.getType());
        return stringArrayList;
    }
}
