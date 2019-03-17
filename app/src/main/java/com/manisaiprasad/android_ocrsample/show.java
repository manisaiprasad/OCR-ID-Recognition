package com.manisaiprasad.android_ocrsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.komamitsu.android_ocrsample.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class show extends AppCompatActivity {

    private TextView idNum;
    private TextView name;
//    private TextView pname;
    private TextView fName;
    private TextView dob;
    private TextView all;
    private TextView cardType;



   private String panNumber;
   private String panName;
   private String panFName;
   private String panDOB;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        idNum = (TextView)findViewById(R.id.idnum);
        name = (TextView) findViewById(R.id.name);
//        pname = (TextView) findViewById(R.id.pname);
        fName = (TextView) findViewById(R.id.fname);
        dob = (TextView) findViewById(R.id.dob);
        all = (TextView) findViewById(R.id.all);
        cardType = (TextView) findViewById(R.id.cardtype);


        String sessionId= getIntent().getStringExtra("EXTRA_SESSION_ID");
//        showText.setText(sessionId);
        int state=0;
        int panId = 0;

//        all.setText(sessionId);

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
//                Toast.makeText(getApplicationContext(), Temp+" is you PAN Number",
//                        Toast.LENGTH_LONG).show();
                panNumber=Temp;

                panId=1;
                idNum.setText("PAN ID : "+panNumber);
                cardType.setText("PAN Card");
            }

            if (matcherPanDOB .matches()) {
//                Toast.makeText(getApplicationContext(), Temp+" is you DOB ",
//                        Toast.LENGTH_LONG).show();
                panDOB=Temp;



                dob.setText("Date of Birth : "+panDOB);
            }

            if (matcherPanName .matches()) {
                if (state==1){

                    panFName=lines[i+1];



                    fName.setText("Father's name: "+panFName);

                }else {
//                    Toast.makeText(getApplicationContext(), Temp+" is you DOB ",
//                            Toast.LENGTH_LONG).show();
                    panName=lines[i+1];



                    name.setText(panName);


                    state=1;
                }
            }

        }
        if (panId != 1){

            for (int i=0;i<lines.length;i++){

                String Temp= lines[i];

                Pattern patternDLNumber = Pattern.compile("[A-Z]{2}[0-9]{14}");

                Pattern patternDLI = Pattern.compile("([^#]*On:)");

//                Pattern patternDLName = Pattern.compile("([^#]*Name)");

                Pattern patternDLFName = Pattern.compile("(S/O[^#])");

                Matcher matcherDLNumber = patternDLNumber .matcher(Temp);

                Matcher matcherDLI = patternDLI .matcher(Temp);

                Matcher matcherDLFName = patternDLFName .matcher(Temp);

                if (matcherDLNumber .matches()) {
//                Toast.makeText(getApplicationContext(), Temp+" is you PAN Number",
//                        Toast.LENGTH_LONG).show();
                    panNumber=Temp;

                    idNum.setText("DL ID : "+panNumber);
                    cardType.setText("Driving Licence");

                    name.setText(lines[i+1]);
                    fName.setText("Father's Name : "+lines[i+2].substring(3,lines[i+2].length()));

                    dob.setText(lines[i+3]);





                }

                if (matcherDLI .matches()) {
//                Toast.makeText(getApplicationContext(), Temp+" is you DOB ",
//                        Toast.LENGTH_LONG).show();
                    panDOB=Temp;

                    String st = (String) dob.getText();
                    dob.setText(st+"\nDate of Issued : "+panDOB);

                }

//                if (matcherDLFName .matches()) {
//                    if (state==1){
//
//                        panFName=lines[i+1];
//
//
//
//                        fName.setText("Father's name: "+panFName);
//
//                    }else {
////                    Toast.makeText(getApplicationContext(), Temp+" is you DOB ",
////                            Toast.LENGTH_LONG).show();
//                        panName=lines[i+1];
//
//
//
//                        name.setText(panName);
//
//
//                        state=1;
//                    }


                }



            }else{
            all.setText(sessionId);
        }

        }
//


    }

