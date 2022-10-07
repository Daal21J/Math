package com.example.mathsolver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    static TextView questionText,scoreText,resultText,timeText;
    static Button button0,button1,button2,button3,playAgainButton;
    static String[] Questions ={ "3 x 5" , " 6 x 11 "," 7 x 12 "," 8 x 20 "," 9 x 21 "," 15 % 3 ","17 % 3 "," 144 / 12 "," 0! "," 5! ",""};
    static Integer[] Answers={15,66,84,160,189,0,2,12,1,120,0};
    static int score;
    static int currentQuestion=0;
    static int correct;
    static String time;
    static CountDownTimer cd= new CountDownTimer(30000,1000) {
        @Override
        public void onTick(long l) {
            time=""+l/1000;
            if(l/1000<=9){
                time="0"+l/1000;
            }
            timeText.setText("0:"+time);
        }

        @Override
        public void onFinish() {
            button0.setEnabled(false);
            button1.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            String R="Game over, your score is " + score + " ";
            if(score>6){
                R+="! :D";
            }else{
                R+=":<";
            }
            resultText.setText(R);
            playAgainButton.setVisibility(View.VISIBLE);

        }
    }.start();




    public static Integer[] generateArray(int correctValue,int max,int min){
        correct=correctValue;
        Integer[]table={correctValue,max,max-1,min};
        List<Integer> intList = Arrays.asList(table);
        Collections.shuffle(intList);
        return table;

    }
   public static void reset(){
        currentQuestion=0;
        score=0;
        scoreText.setText("0/10");
        //countdown restart
        playAgainButton.setVisibility(View.INVISIBLE);
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        resultText.setText("");
        setup();
        cd.start();
   }

    public static void setup(){
        if(currentQuestion==10) {
            playAgainButton.setVisibility(View.VISIBLE);
        }else{
            Integer[] tabPossibleValue = null;
            questionText.setText(Questions[currentQuestion]);
            tabPossibleValue = generateArray(Answers[currentQuestion], Answers[currentQuestion] + 3, Answers[currentQuestion] - 1);
            currentQuestion++;
            button0.setText(String.valueOf(tabPossibleValue[0]));
            button1.setText(String.valueOf(tabPossibleValue[1]));
            button2.setText(String.valueOf(tabPossibleValue[2]));
            button3.setText(String.valueOf(tabPossibleValue[3]));
        }
    }

    

       public void clicked(View view) {
        Button clickedButton=(Button) view;
           if(currentQuestion==10) {
               if (clickedButton.getText().toString().equals(Integer.toString(correct))) {
                   score = score + 1;
                   scoreText.setText(score + "/10");
               }
               button0.setEnabled(false);
               button1.setEnabled(false);
               button2.setEnabled(false);
               button3.setEnabled(false);
                   String R="Game over, your score is " + score + " ";
               if(score>6){
                   R+="! :D";
               }else{
                   R+=":<";
               }
               resultText.setText(R);
               cd.cancel();
               playAgainButton.setVisibility(View.VISIBLE);
           }else{
               if (clickedButton.getText().toString().equals(Integer.toString(correct))) {
                   score =score+1;
                   scoreText.setText(score + "/10");
                   resultText.setText("Correct! :D");

               } else {
                   resultText.setText("Incorrect! :<");
               }

               setup();
           }

    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       questionText = (TextView) findViewById(R.id.questionText);
        scoreText = (TextView) findViewById(R.id.scoreText);
        resultText = (TextView) findViewById(R.id.resultText);
        timeText = (TextView) findViewById(R.id.timeText);
        playAgainButton=(Button)findViewById(R.id.playAgainButton);
        button0=(Button)findViewById(R.id.button0);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        resultText.setText("");
        setup();
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });


    }
    }
