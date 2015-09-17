package com.bignerdranch.android.geoquiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {

    private static final String TAG = "CheatActivity";
    public static final String EXTRA_ANSWER_IS_TRUE = "tfquiz.ANSWER_IS_TRUE";
    public static final String EXTRA_ANSWER_SHOWN = "tfquiz.ANSWER_SHOWN";

    boolean mAnswerIsTrue;

    TextView mAnswerTextView;
    Button mShowAnswer;


    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_cheat);

        if (savedInstanceState == null) {
            // first startup, so the answer must not
            // be shown yet
            setAnswerShownResult(false);
        }

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);

        mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Log.d(TAG, "Show animation, Android Version is: " + Build.VERSION.SDK_INT);

                    int cx = mShowAnswer.getWidth() / 2;
                    int cy = mShowAnswer.getHeight() / 2;
                    float radius = mShowAnswer.getWidth();
                    Animator anim = ViewAnimationUtils.createCircularReveal(mShowAnswer, cx, cy, radius, 0);

                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            mAnswerTextView.setVisibility(View.VISIBLE);
                            mShowAnswer.setVisibility(View.INVISIBLE);
                        }
                    });
                    anim.start();
                } else {
                    Log.d(TAG, "Don't show animation, Android Version is: " + Build.VERSION.SDK_INT);
                    mAnswerTextView.setVisibility(View.VISIBLE);
                    mShowAnswer.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

}
