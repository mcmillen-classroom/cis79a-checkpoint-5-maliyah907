package maliyahhoward.quizapp;

import android.content.Context;

public class Question {
    private int mTextResId;
    private int mHintTextResId;

    public Question(int textResId, int hintTextResId) {
        mTextResId = textResId;
        mHintTextResId = hintTextResId;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }
    public String getText(Context ctx)
    {
        return ctx.getString(mTextResId);
    }

    //stub
    public String getAnswerText(Context ctx)
    {
        return "";
    }


    public int getHintTextResId() {
        return mHintTextResId;
    }

    // stub method - intentionally does nothing
    // only applies to true false question
    public boolean checkAnswer(boolean boolResponse) {
        return false;
    }

    // stub method
    // only applies to fill the blank question
    public boolean checkAnswer(String userAnswer) {
        return false;
    }

    //stub
    public boolean isTrueFalseQuestion() {
        return false;
    }

    //stub
    public boolean isFillTheBlankQuestion() {
        return false;
    }

    //stub
    public boolean isMultipleChoiceQuestion()
    {
        return false;
    }

}
