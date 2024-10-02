package com.example.project3;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText name, idNo, phone, email, password;
    LinearLayout inputLayout, outputLayout;
    Button signUp, ok;
    CheckBox showPassword;
    TextView outputName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = findViewById(R.id.et_name);
        idNo = findViewById(R.id.et_id);
        phone = findViewById(R.id.et_phone);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        signUp = findViewById(R.id.btn_signUp);
        ok = findViewById(R.id.btn_ok);
        showPassword = findViewById(R.id.cb_showPassword);
        inputLayout = (LinearLayout) findViewById(R.id.input);
        outputLayout = (LinearLayout) findViewById(R.id.lo_output);
        outputName = findViewById(R.id.tv_outputName);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = name.getText().toString();
                String ID = idNo.getText().toString();
                String Phone = phone.getText().toString();
                String Email = email.getText().toString();
                String Password = password.getText().toString();

                Pattern namePattern = Pattern.compile("[a-zA-Z]| |\\.");
                Pattern idPattern = Pattern.compile("0182210012101[0-9]{3}");
                Pattern phonePattern = Pattern.compile("01[1-9]\\d{8}");
                Pattern emailPattern = Pattern.compile("(www.)?cse_0182210012101[0-9]{3}@lus.ac.bd");
                Pattern passwordPattern = Pattern.compile("\\S");

                Matcher matchName = namePattern.matcher(Name);
                boolean nameMatched = matchName.matches();
                Matcher matchID = idPattern.matcher(ID);
                boolean idMatched = matchID.matches();
                Matcher matchPhone = phonePattern.matcher(Phone);
                boolean phoneMatched = matchPhone.matches();
                Matcher matchEmail = emailPattern.matcher(Email);
                boolean emailMatched = matchEmail.matches();
                Matcher matchPassword = passwordPattern.matcher(Password);
                boolean passwordMatched = matchPassword.matches();

                if(Name.isEmpty()){
                    name.setError("Enter your name");
                    name.findFocus();
                }
                else if(!nameMatched){
                    Toast.makeText(getApplicationContext(),"Invalid Name",Toast.LENGTH_SHORT).show();
                }

                if(ID.isEmpty()){
                    idNo.setError("Enter your id");
                    idNo.findFocus();
                }
                else if(!idMatched){
                    Toast.makeText(getApplicationContext(),"Invalid ID",Toast.LENGTH_SHORT).show();
                }

                if(Email.isEmpty()){
                    email.setError("Enter your email");
                    email.findFocus();
                }
                else if(!emailMatched){
                    Toast.makeText(getApplicationContext(),"Invalid Email",Toast.LENGTH_SHORT).show();
                }

                if(Phone.isEmpty()){
                    phone.setError("Enter your phone");
                    phone.findFocus();
                }
                else if(!phoneMatched){
                    Toast.makeText(getApplicationContext(),"Invalid Phone Number",Toast.LENGTH_SHORT).show();
                }

                if(Password.isEmpty()){
                    password.setError("Set your password");
                    password.findFocus();
                }
                else if(!passwordMatched){
                    Toast.makeText(getApplicationContext(),"Invalid Password",Toast.LENGTH_SHORT).show();
                }

                if(showPassword.isChecked()){
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                else{
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }

                if(nameMatched && emailMatched && phoneMatched && idMatched && passwordMatched){

                    outputName.setText(Name);
                    inputLayout.setVisibility(View.GONE);
                    outputLayout.setVisibility(View.VISIBLE);
                }


            }



        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputLayout.setVisibility(View.VISIBLE);
                outputLayout.setVisibility(View.GONE);
            }
        });


    }
}