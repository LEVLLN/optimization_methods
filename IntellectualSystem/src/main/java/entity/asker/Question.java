package entity.asker;

import java.util.ArrayList;
import java.util.List;

public class Question {
    String questionValue;
    List<Variant> variants = new ArrayList<Variant>();

    public String getQuestionValue() {
        return questionValue;
    }

    public void setQuestionValue(String questionValue) {
        this.questionValue = questionValue;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }
}
