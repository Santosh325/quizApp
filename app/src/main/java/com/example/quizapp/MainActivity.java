package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button trueButton;
    Button falseButton;
    TextView quizQuestion;
    ImageButton nextButton;
    private int currentIndexQuestion = 0;
    Question[] questionBank = new Question[] {

            new Question(R.string.question_declaration,true),
            new Question(R.string.question_nepal,false),
            new Question(R.string.question_messi,true),
            new Question(R.string.question_ronaldo,false),
            new Question(R.string.question_android,false),
            new Question(R.string.question_flutter,true)

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        nextButton = findViewById(R.id.next_button);
        trueButton = findViewById(R.id.true_text);
        falseButton = findViewById(R.id.false_text);
        quizQuestion = findViewById(R.id.quiz);

        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.true_text:
                checkAnswer(true);
                break;
            case R.id.false_text:
                checkAnswer(false);
                break;
            case R.id.next_button:
                currentIndexQuestion = (currentIndexQuestion + 1) % questionBank.length;
                updateQuestion();
        }
    }

    private void updateQuestion() {
        Log.d("Current","Onlclick" + currentIndexQuestion);
        quizQuestion.setText(questionBank[currentIndexQuestion].getAnswerResId());
    }

    private void checkAnswer(boolean userChooseAnser) {
        boolean answerIsTrue = questionBank[currentIndexQuestion].isAnswerTrue();
        int toastMessageId;

        if(answerIsTrue == userChooseAnser) {
            toastMessageId = R.string.true_answer;
        } else {
            toastMessageId = R.string.wrong_answer;
        }

        Toast.makeText(MainActivity.this,toastMessageId,Toast.LENGTH_SHORT).show();
    }
}
