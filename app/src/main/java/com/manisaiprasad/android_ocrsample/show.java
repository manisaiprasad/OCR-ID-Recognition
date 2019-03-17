package com.manisaiprasad.android_ocrsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.komamitsu.android_ocrsample.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class show extends AppCompatActivity {
   private TextView showText;

   private String panNumber;
   private String panName;
   private String panFName;
   private String panDOB;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        showText = (TextView)findViewById(R.id.showText);

        String sessionId= getIntent().getStringExtra("EXTRA_SESSION_ID");
//        showText.setText(sessionId);
        int state=0;

        String lines[] = sessionId.split("(\r\n|\r|\n)", -1);
        for (int i=0;i<lines.length;i++){

            String Temp= lines[i];

            Pattern patternPanNumber = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");

            Pattern patternPanDOB = Pattern.compile("^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$");

            Pattern patternPanName = Pattern.compile("([^#]*Name)");

//            Pattern patternPanFName = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");

            Matcher matcherPanNumber = patternPanNumber .matcher(Temp);

            Matcher matcherPanDOB = patternPanDOB .matcher(Temp);

            Matcher matcherPanName = patternPanName .matcher(Temp);

            if (matcherPanNumber .matches()) {
                Toast.makeText(getApplicationContext(), Temp+" is you PAN Number",
                        Toast.LENGTH_LONG).show();
                panNumber=Temp;


                String temp = (String) showText.getText();

                showText.setText(temp+"\nPAN Number : "+panNumber);

            }

            if (matcherPanDOB .matches()) {
                Toast.makeText(getApplicationContext(), Temp+" is you DOB ",
                        Toast.LENGTH_LONG).show();
                panDOB=Temp;


                String temp = (String) showText.getText();

                showText.setText(temp+"\nPAN DOB : "+panDOB);

            }

            if (matcherPanName .matches()) {
                if (state==1){

                    panFName=lines[i+1];


                    String temp1 = (String) showText.getText();

                    showText.setText(temp1+"\n Father Name : "+panFName);

                }else {
                    Toast.makeText(getApplicationContext(), Temp+" is you DOB ",
                            Toast.LENGTH_LONG).show();
                    panName=lines[i+1];


                    String temp = (String) showText.getText();

                    showText.setText(temp+"\n Name : "+panName);


                    state=1;
                }


            }






        }
//        for (int i=0;i<lines.length;i++){
//
//            String Temp= lines[i];
//
//            Pattern pattern = Pattern.compile("^(0[1-9]|[1-9]|[12][0-9]|3[01])-(0[1-9]|[1-9]|1[012])-(19|20)\\d\\d$");
//
//            Matcher matcher = pattern .matcher(Temp);
//
//            if (matcher .matches()) {
//                Toast.makeText(getApplicationContext(), Temp+" is you  DOB",
//                        Toast.LENGTH_LONG).show();
//                panDOB=Temp;
//
//                String temp = (String) showText.getText();
//
//                showText.setText(temp+"\n"+"PAN DOB"+panDOB);
//
//
//
//            }
//
//
//
//
//
//        }


    }
}
