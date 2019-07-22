package maliyahhoward.quizapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class CheatActivity extends AppCompatActivity implements View.OnClickListener{


    public static final String EXTRA_QUESTION_TEXT = "question_text";
    private TextView mQuestionTextView;
    private TextView mAnswerTextView;
    private Button mShowAnswerButton;

    private String mAnswerText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);

        mShowAnswerButton.setOnClickListener(this);

        Intent launchIntent = getIntent();
        String questionText = launchIntent.getStringExtra(EXTRA_QUESTION_TEXT);
        mQuestionTextView.setText(questionText);
        mAnswerText = launchIntent.getStringExtra("answer_text");
    }

    @Override
    public void onClick(View view)
    {
        mAnswerTextView.setText(mAnswerText);

        Intent resIntent = new Intent();
        resIntent.putExtra("did_cheat",true);
        setResult(RESULT_OK,resIntent);
    }
    public static Intent newIntent(Context ctx, Question question)
    {
        Intent ret = new Intent(ctx, CheatActivity.class);
        ret.putExtra(EXTRA_QUESTION_TEXT, question.getText(ctx));
        ret.putExtra("answer_text", question.getAnswerText(ctx));

        return ret;
    }

    public  static boolean didCheat(Intent resultData)
    {
        return resultData.getBooleanExtra("did_cheat",false);
    }
}
