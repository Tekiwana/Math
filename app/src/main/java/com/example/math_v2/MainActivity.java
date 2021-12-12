package com.example.math_v2;

import static com.example.math_v2.R.id.btn_add;
import static com.example.math_v2.R.id.checkbox;
import static com.example.math_v2.R.id.fraction_box;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {
    String operation = "";
    Boolean fraction = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_add = findViewById(R.id.btn_add);
        Button btn_subs = findViewById(R.id.btn_subs);
        Button btn_multiply = findViewById(R.id.btn_multiply);
        Button btn_divide = findViewById(R.id.btn_divide);
        CheckBox fraction_box1 = findViewById(R.id.fraction_box);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operation = "+";
                start_main();
            }
        });


        btn_subs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operation = "-";
                start_main();
            }
        });


        btn_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operation = "x";
                start_main();
            }
        });


        btn_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operation = "/";
                start_main();

            }
        });

        fraction_box1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fraction_box1.isChecked() == false){
                    fraction = true;
                }
                else {
                    fraction = false;
                }
            }
        });


    }

    public void start_main(){
        Intent i = new Intent(this, Main.class);
        i.putExtra("operation", operation);
        i.putExtra("fraction", fraction);

        startActivity(i);
    }
}