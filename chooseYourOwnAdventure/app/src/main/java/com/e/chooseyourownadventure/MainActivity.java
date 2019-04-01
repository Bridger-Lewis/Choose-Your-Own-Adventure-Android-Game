package com.e.chooseyourownadventure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }

    TextView textView;
    Button button1, button2, button3, button4;
    BufferedReader reader;
    String[] buttonStrings;
    String line, choice1, choice2, choice3, choice4;

    /** Called when app starts. Sets up text window and buttons */
    public void setup () {
        textView = findViewById(R.id.textView);
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("testStory.txt")));
            while ((line = reader.readLine()) != null) {
                if (line.contains("---")) {
                    break;
                }
            }

            /** Set TextView textView text from file */
            nextLine();
            textView.setText(line);

            /** Set all button text from file and hide unneeded buttons */
            for (int i = 0; i < 1; i++) {
                nextLine();
                if (line.contains("[[")) {
                    buttonStrings = line.split("\\|", 2);
                    line = buttonStrings[0].substring(2);
                    button1.setText(line);
                    choice1 = buttonStrings[1].substring(0,buttonStrings[1].length()-2);
                } else {
                    button1.setVisibility(View.INVISIBLE);
                    button2.setVisibility(View.INVISIBLE);
                    button3.setVisibility(View.INVISIBLE);
                    button4.setVisibility(View.INVISIBLE);
                    break;
                }
                nextLine();
                if (line.contains("[[")) {
                    buttonStrings = line.split("\\|", 2);
                    line = buttonStrings[0].substring(2);
                    button2.setText(line);
                    choice2 = buttonStrings[1].substring(0,buttonStrings[1].length()-2);
                }
                else {
                    button2.setVisibility(View.INVISIBLE);
                    button3.setVisibility(View.INVISIBLE);
                    button4.setVisibility(View.INVISIBLE);
                    break;
                }
                nextLine();
                if (line.contains("[[")) {
                    buttonStrings = line.split("\\|", 2);
                    line = buttonStrings[0].substring(2);
                    button3.setText(line);
                    choice3 = buttonStrings[1].substring(0,buttonStrings[1].length()-2);
                }
                else {
                    button3.setVisibility(View.INVISIBLE);
                    button4.setVisibility(View.INVISIBLE);
                    break;
                }
                nextLine();
                if (line.contains("[[")) {
                    buttonStrings = line.split("\\|", 2);
                    line = buttonStrings[0].substring(2);
                    button4.setText(line);
                    choice4 = buttonStrings[1].substring(0,buttonStrings[1].length()-2);
                }
                else {
                    button4.setVisibility(View.INVISIBLE);
                    break;
                }
            }

            if (reader == null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Sets up text window, buttons, and changes layout as needed */
    public void parse (String choice) {
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("testStory.txt")));

            /** Read through file looking for passed string */
            while ((line = reader.readLine()) != null) {
                if (line.contains("title: ") && line.contains(choice)) {
                    nextLine();
                    nextLine();
                    nextLine();
                    nextLine();
                    nextLine();
                    break;
                }
            }

            /** Set TextView textView text from file */
            textView.setText(line);
            nextLine();

            /** Set button text from file and hide unneeded buttons */
            while (line.contains("[[") == false) {
                Log.d("debug", "parse: "+line.substring(0,2));
                textView.append("\n\n" + line);
                nextLine();
            }
            for (int i = 0; i < 1; i++) {
                if (line.contains("[[")) {
                    buttonStrings = line.split("\\|", 2);
                    line = buttonStrings[0].substring(2);
                    button1.setText(line);
                    button1.setVisibility(View.VISIBLE);
                    choice1 = buttonStrings[1].substring(0,buttonStrings[1].length()-2);
                } else {
                    button1.setVisibility(View.INVISIBLE);
                    button2.setVisibility(View.INVISIBLE);
                    button3.setVisibility(View.INVISIBLE);
                    button4.setVisibility(View.INVISIBLE);
                    break;
                }
                nextLine();
                if (line.contains("[[")) {
                    buttonStrings = line.split("\\|", 2);
                    line = buttonStrings[0].substring(2);
                    button2.setText(line);
                    button2.setVisibility(View.VISIBLE);
                    choice2 = buttonStrings[1].substring(0,buttonStrings[1].length()-2);
                }
                else {
                    button2.setVisibility(View.INVISIBLE);
                    button3.setVisibility(View.INVISIBLE);
                    button4.setVisibility(View.INVISIBLE);
                    break;
                }
                nextLine();
                if (line.contains("[[")) {
                    buttonStrings = line.split("\\|", 2);
                    line = buttonStrings[0].substring(2);
                    button3.setText(line);
                    button3.setVisibility(View.VISIBLE);
                    choice3 = buttonStrings[1].substring(0,buttonStrings[1].length()-2);
                }
                else {
                    button3.setVisibility(View.INVISIBLE);
                    button4.setVisibility(View.INVISIBLE);
                    break;
                }
                nextLine();
                if (line.contains("[[")) {
                    buttonStrings = line.split("\\|", 2);
                    line = buttonStrings[0].substring(2);
                    button4.setText(line);
                    button4.setVisibility(View.VISIBLE);
                    choice4 = buttonStrings[1].substring(0,buttonStrings[1].length()-2);
                }
                else {
                    button4.setVisibility(View.INVISIBLE);
                    break;
                }
            }

            if (reader == null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Reads next line of text from file into the String line */
    public void nextLine () {
       try {
           line = reader.readLine();
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

    /** Called when Button button1 is pressed by user */
    public void button1Press (View view) {
        parse(choice1);
    }

    /** Called when Button button2 is pressed by user */
    public void button2Press (View view) {
        parse(choice2);
    }

    /** Called when Button button3 is pressed by user */
    public void button3Press (View view) {
        parse(choice3);
    }

    /** Called when Button button4 is pressed by user */
    public void button4Press (View view) {
        parse(choice4);
    }
}