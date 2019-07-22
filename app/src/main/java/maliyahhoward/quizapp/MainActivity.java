package maliyahhoward.quizapp;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static  final int REQUEST_CODE_CHEAT = 0;

    private TextView mTextView;


    private LinearLayout mTrueFalseContainer;
    private LinearLayout mFillTheBlankConatiner;

    private EditText mEditText;
    private Button mCheckButton;

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private Button mHintButton;
    private Question[] mQuestions;
    private int mIndex;
    private int mScore;

    private Button mCheatButton;
    private boolean mCheated = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mPreviousButton = (ImageButton) findViewById(R.id.previous_button);
        mHintButton = (Button) findViewById(R.id.hint_button);
        mCheckButton = (Button) findViewById(R.id.check_button);
        mCheatButton = (Button) findViewById(R.id.cheat_button);

        mTrueFalseContainer = (LinearLayout) findViewById(R.id.true_false_container);
        mFillTheBlankConatiner = (LinearLayout) findViewById(R.id.fill_the_blank_container);

        mEditText =  (EditText) findViewById(R.id.edit_text);

        mTrueButton.setOnClickListener(this);
        mFalseButton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
        mPreviousButton.setOnClickListener(this);
        mHintButton.setOnClickListener(this);
        mCheckButton.setOnClickListener(this);
        mCheatButton.setOnClickListener(this);

        mTextView = (TextView) findViewById(R.id.text_view);

        //Initialize an array of questions.
        mQuestions = new Question[5];
        mIndex = 0;

        //Initialize each spot in the array.
        mQuestions[0] = new TrueFalseQuestion(R.string.question_1, R.string.question_1_hint, true);
        mQuestions[1] = new TrueFalseQuestion(R.string.question_2, R.string.question_2_hint, false);
        mQuestions[2] = new TrueFalseQuestion(R.string.question_3, R.string.question_3_hint, false);
        String[] q4Answers = getResources().getStringArray(R.array.question_4_answers);
        mQuestions[3] = new FillTheBlankQuestion(R.string.question_4, R.string.question_4_hint, q4Answers);
        mQuestions[4] = new TrueFalseQuestion(R.string.question_5, R.string.question_5_hint, false);

        //Setup the first question.

        setupQuestion();
    }

    private void setupQuestion()
    {
        mTextView.setText(mQuestions[mIndex].getTextResId());
        if (mQuestions[mIndex].isTrueFalseQuestion())
        {
            mTrueFalseContainer.setVisibility(View.VISIBLE);
            mFillTheBlankConatiner.setVisibility(View.GONE);
        }
        else if (mQuestions[mIndex].isFillTheBlankQuestion())
        {
            mTrueFalseContainer.setVisibility(View.GONE);
            mFillTheBlankConatiner.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData)
    {
        if (resultCode != RESULT_OK)
        {
            return;
        }

        if (requestCode == REQUEST_CODE_CHEAT && resultData != null)
        {
            mCheated = CheatActivity.didCheat(resultData);
        }
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.true_button) {
            checkAnswer(true);
        } else if (view.getId() == R.id.false_button) {
            checkAnswer(false);
        } else if (view.getId() == R.id.check_button) {
            checkAnswer(mEditText.getText().toString());
        } else if (view.getId() == R.id.next_button) {
            // Change to the next question...

            // Increment the index by one.
            mIndex++;

            if (mIndex >= mQuestions.length) {
                // quiz over
            } else {
                // Change text in view.
                setupQuestion();
            }
        } else if (view.getId() == R.id.previous_button) {
            // Change to the previous question

            //Increment the index by one
            mIndex--;

            //DO IF STATEMENT HERE;
            // If the mIndex is greater than or equal to mQuestions.length

            // Change text in view
            setupQuestion();
        } else if (view.getId() == R.id.cheat_button) {
            // TODO: launch CheatActivity
            Intent cheatIntent = CheatActivity.newIntent(this, mQuestions[mIndex]);
            startActivityForResult(cheatIntent, REQUEST_CODE_CHEAT);

        } else if (view.getId() == R.id.hint_button) {
            //Give hint for each question
            Toast myToast = Toast.makeText(this, mQuestions[mIndex].getHintTextResId(), Toast.LENGTH_SHORT);
            myToast.show();

        }
    }


    public boolean checkAnswer(boolean userInput)
    {
        if (mCheated)
        {
            Toast.makeText(this,R.string.cheat_shame, Toast.LENGTH_LONG).show();
            return false;
        }

        else if (mQuestions[mIndex].checkAnswer(userInput)) {
            Toast myToast = Toast.makeText(this, "You are correct! ", Toast.LENGTH_SHORT);
            myToast.show();
            return true;
        } else {
            Toast myToast = Toast.makeText(this, "You are incorrect! ", Toast.LENGTH_SHORT);
            myToast.show();
            return false;
        }
    }

    public boolean checkAnswer(String userInput)
    {
        if (mCheated)
        {
            Toast.makeText(this,R.string.cheat_shame, Toast.LENGTH_LONG).show();
            return false;
        }


        if (mQuestions[mIndex].checkAnswer(userInput)) {
            Toast myToast = Toast.makeText(this, "You are correct! ", Toast.LENGTH_SHORT);
            myToast.show();
            return true;
        }
        else
            {
            Toast myToast = Toast.makeText(this, "You are incorrect! ", Toast.LENGTH_SHORT);
            myToast.show();
            return false;
        }
    }
}



