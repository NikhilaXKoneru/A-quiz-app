package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.util.AndroidRuntimeException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.quizapp.databinding.ActivityMainBinding;
import com.example.quizapp.model.Question;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int currentQuestionIndex =0;

    private Question[] questionBank = new Question[]{
            new Question(R.string.question_amendments, false), //correct: 27
            new Question(R.string.question_constitution, true),
            new Question(R.string.question_declaration, true),
            new Question(R.string.question_independence_rights, true),
            new Question(R.string.question_religion, true),
            new Question(R.string.question_government, false),
            new Question(R.string.question_government_feds, false),
            new Question(R.string.question_government_senators, true),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.questionTextView.setText(questionBank[currentQuestionIndex].getAnswerId());

        binding.TrueButton.setOnClickListener(view -> {checkAnswer(true);});
        binding.falseButton.setOnClickListener(view -> {checkAnswer(false);});

        binding.nextButton.setOnClickListener(view -> {
            currentQuestionIndex = (currentQuestionIndex + 1)% questionBank.length;
            updateQuestion();
        });
        binding.PrevButton.setOnClickListener(view -> {
            if(currentQuestionIndex>0){
                currentQuestionIndex = (currentQuestionIndex - 1)% questionBank.length;
            updateQuestion();
            }
        });
    }

    private void checkAnswer(boolean userChoseCorrect){
        boolean answerIsCorrect = questionBank[currentQuestionIndex].isAnswerTrue();
        int messageId;
        if(answerIsCorrect == userChoseCorrect){
            messageId = R.string.correct_answer;
        }
        else {
            messageId = R.string.wrong_answer;
        }
        Toast.makeText(this,messageId,Toast.LENGTH_SHORT).show();
    }

    private void updateQuestion() {
        binding.questionTextView.setText(questionBank[currentQuestionIndex].getAnswerId());
    }
}