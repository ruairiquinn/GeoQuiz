package com.bignerdranch.android.geoquiz;

/**
 * Created by ruairiquinn on 16/09/2015.
 */
public class Question {

    private int mTextResId;
    private Boolean mAnswerTrue;

    public Question(int mTextResId, Boolean mAnswerTrue) {
        this.mTextResId = mTextResId;
        this.mAnswerTrue = mAnswerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public Boolean getAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(Boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
