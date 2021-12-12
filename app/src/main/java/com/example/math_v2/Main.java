package com.example.math_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Main extends AppCompatActivity {
    int correct_answer;
    Random random = new Random();
    String question_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i = getIntent();
        String operation = i.getStringExtra("operation");
        //Boolean fraction = i.getBooleanExtra("fraction");


// Identify the elements on the Activity
        TextView correct_answer_counter = findViewById(R.id.correct_answer_counter_textview);
        TextView wrong_answer_counter = findViewById(R.id.wrong_answer_counter_textview);
        TextView question_display = findViewById(R.id.question_display);
        EditText answer_input = findViewById(R.id.answer_input_edittext);
        Button btn_submit = findViewById(R.id.btn_submit);
        ImageView mail_icon = findViewById(R.id.icon_mail);
        mail_icon.setEnabled(false);


// On click of the Submit button it will check if the answer is correct or not.
// If correct add 1 to the correct counter. If wrong add 1 to the wrong counter.
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (question_display.getText().toString().equals("")){
                    btn_submit.setText("Submit");
                    answer_input.setEnabled(true);

                }else if (answer_input.getText().toString().equals(String.valueOf(correct_answer))){
                    int counter = Integer.parseInt(correct_answer_counter.getText().toString());
                    counter ++;
                    correct_answer_counter.setText(String.valueOf(counter));
                }else{
                    int counter = Integer.parseInt(wrong_answer_counter.getText().toString());
                    counter ++;
                    wrong_answer_counter.setText(String.valueOf(counter));
                }


//Reset the Answer TextField, then call the generate_question and check_answer functions.
                answer_input.setText("");
                question_display.setText(generate_question(operation));
                correct_answer = check_answer(question_string, operation);

                if (Integer.parseInt(correct_answer_counter.getText().toString()) >= 10){
                    mail_icon.setBackgroundColor(Color.rgb(75, 175, 80));  //75.175.80
                    mail_icon.setClickable(true);
                    mail_icon.setEnabled(true);
                }
            }
        });


/* OnClick Listener for email button.
        If clicked. email will be sent out with Correct and wrong answers.
 */
        mail_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String to = "sallaikrisztian@gmail.com";
                String to2 = "annaszerbijenko@gmail.com";
                String subject = "Ancsi matek.";
                String message = "Correct : " + correct_answer_counter.getText().toString() + "\nWrong : " + wrong_answer_counter.getText().toString();
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{to,to2});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });
    }


/* Generate the question.
        Params : String (operation sign : +,-,*,/)
        Return : String num1 + operation sign + num2
*/
    public String generate_question(String operation){
        int num1 = random.nextInt(100);
        int num2 = random.nextInt(100);
// ADD
        if (operation.equals("+")){
            question_string = String.valueOf(num1)+" "+operation+" "+String.valueOf(num2);
            return question_string;
// SUBTRACT. Answer cannot be < 0.
        }else if (operation.equals("-")){
            while (num1-num2 < 0){
                num1 = random.nextInt(100);
                num2 = random.nextInt(100);
            }
            question_string = String.valueOf(num1)+" "+operation+" "+String.valueOf(num2);
            return question_string;
//DIVIDE. Answer can be INT only.
        }else if (operation.equals("/")){
            while (num1%num2 != 0){
                num1 = random.nextInt(100);
                num2 = random.nextInt(99);
                num2 += 1;
            }
            question_string = String.valueOf(num1)+" "+operation+" "+String.valueOf(num2);
            return question_string;
//MULTIPLY. Up to 20.
        }else{
            while (num1 >= 21 | num2 >= 21){
                num1 = random.nextInt(21);
                num2 = random.nextInt(21);
            }
            question_string = String.valueOf(num1)+" "+operation+" "+String.valueOf(num2);
            return question_string;
        }
    }


/* Define the correct answer.
        Params : String question_string - This string returns from generate_answer function.
                 String operation - This string passed from MainActivity1
        Returns : Integer correct answer
 */
    public int check_answer(String question_string, String operation){
        String[] answer = question_string.split(" ");
        int num1 = Integer.parseInt(answer[0]);
        int num2 = Integer.parseInt(answer[2]);

        if (operation.equals("+")){
            return num1+num2;
        }else if (operation.equals("-")){
            return num1-num2;
        }else if (operation.equals("x")){
            return num1*num2;
        }else{
            return num1/num2;
        }
    }
}
