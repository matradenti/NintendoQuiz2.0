package com.example.android.nintendoquiz;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.bitmap;
import static android.R.attr.button;
import static android.R.attr.id;
import static android.R.attr.name;
import static android.R.attr.rating;
import static android.R.attr.textScaleX;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static android.os.Build.VERSION_CODES.N;
import static android.text.TextUtils.isEmpty;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.example.android.nintendoquiz.R.drawable.mario;
import static com.example.android.nintendoquiz.R.id.answers;
import static com.example.android.nintendoquiz.R.id.checkA;
import static com.example.android.nintendoquiz.R.id.checkFieldAB;
import static com.example.android.nintendoquiz.R.id.checkFieldCD;
import static com.example.android.nintendoquiz.R.id.confirmButton;
import static com.example.android.nintendoquiz.R.id.donkeyBadge;
import static com.example.android.nintendoquiz.R.id.editTextAnswer;
import static com.example.android.nintendoquiz.R.id.fzeroBadge;
import static com.example.android.nintendoquiz.R.id.image;
import static com.example.android.nintendoquiz.R.id.marioBadge;
import static com.example.android.nintendoquiz.R.id.questionText;
import static com.example.android.nintendoquiz.R.id.radioA;
import static com.example.android.nintendoquiz.R.id.radioB;
import static com.example.android.nintendoquiz.R.id.radioC;
import static com.example.android.nintendoquiz.R.id.radioD;
import static com.example.android.nintendoquiz.R.id.radioField;
import static com.example.android.nintendoquiz.R.id.rules;
import static com.example.android.nintendoquiz.R.id.skipQuestion;
import static com.example.android.nintendoquiz.R.id.smashBrosBadge;
import static com.example.android.nintendoquiz.R.id.stageBackground;
import static com.example.android.nintendoquiz.R.id.userNameText;
import static com.example.android.nintendoquiz.R.string.correctDouble;
import static com.example.android.nintendoquiz.R.string.engage;
import static com.example.android.nintendoquiz.R.string.wrong;

public class MainActivity extends AppCompatActivity {
    /**
     * variable STAGECOUNTER help the procedure to understand in which stage we are inside the game
     * so for each stage i could know which queston and answers show thanks to this variable
     */
    int stageCounter;
    /**
     * variable STATUS give me the information of the user status.. i mean in which status the user is
     * STATUS = 0 mean that the user is in the very beginning and just inserted the name
     * STATUS = 1 mean that the user is in the first menu, he can press start to start the game
     * STATUS = 2 mean that the user is watching the stage introduction, and can only press
     * the button to engage the stage
     * STATUS = 3 mean that the user is reading the question, he can only submit the answer
     * or skip the question
     * STATUS = 4 mean that the user just submitted the correct answer and can choose to go
     * for the double or go to the next question
     * STATUS = 5 mean that the user went for the double, so he is reading the double question and
     * the relatives answer, he can only submit the answer or skip the question
     * STATUS = 6 mean that the user just submitted a correct answer and have to choose to go
     * for tbe double or skip straight to next stage
     * STATUS = 7 mean that the user just chose to go for the next stage without DOUBLE
     * STATUS = 9 mean that the user COMPLETED ALL THE QUIZ!
     */
    int status;

    /**
     * variable SCORE keep the score of the user update
     */
    int score;

    /**
     * skip possibilities is a variable that show the possibility that the user still have to
     * skip a question that doesn't know
     */
    int skipPossibilities;

    boolean correct1 = false;
    boolean correct2 = false;
    boolean correct3 = false;
    boolean correct4 = false;
    boolean correct5 = false;
    boolean correct6 = false;
    boolean correct7 = false;
    boolean correct8 = false;
    boolean correct9 = false;
    boolean correct10 = false;
    boolean correct1D = false;
    boolean correct2D = false;
    boolean correct3D = false;
    boolean correct4D = false;
    boolean correct5D = false;
    boolean correct6D = false;
    boolean correct7D = false;
    boolean correct8DA = false;
    boolean correct8DB = false;
    boolean correct8DC = false;
    boolean correct8DD = false;
    boolean correct9DA = false;
    boolean correct9DB = false;
    boolean correct9DC = false;
    boolean correct9DD = false;
    boolean correct10D = false;
    boolean isGameCompleted = false;
    String textAnswer;
    String userName;
    int counter8D = 0;
    int counter9D = 0;
    MediaPlayer mediaPlayer;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stageCounter = 1;
        EditText userNameText = (EditText) findViewById(R.id.userNameText);
        userNameText.setVisibility(VISIBLE);
        TextView rules = (TextView) findViewById(R.id.rules);
        rules.setText(R.string.intro);
        rules.setVisibility(VISIBLE);
        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        confirmButton.setText(R.string.start);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("MyStageCounter", stageCounter);
        savedInstanceState.putInt("MyStatus", status);
        savedInstanceState.putInt("MyScore", score);
        savedInstanceState.putInt("MySkipPossibilities", skipPossibilities);
        savedInstanceState.putBoolean("MyCorrect1", correct1);
        savedInstanceState.putBoolean("MyCorrect2", correct2);
        savedInstanceState.putBoolean("MyCorrect3", correct3);
        savedInstanceState.putBoolean("MyCorrect4", correct4);
        savedInstanceState.putBoolean("MyCorrect5", correct5);
        savedInstanceState.putBoolean("MyCorrect6", correct6);
        savedInstanceState.putBoolean("MyCorrect7", correct7);
        savedInstanceState.putBoolean("MyCorrect8", correct8);
        savedInstanceState.putBoolean("MyCorrect9", correct9);
        savedInstanceState.putBoolean("MyCorrect10", correct10);
        savedInstanceState.putBoolean("MyCorrect1D", correct1D);
        savedInstanceState.putBoolean("MyCorrect2D", correct2D);
        savedInstanceState.putBoolean("MyCorrect3D", correct3D);
        savedInstanceState.putBoolean("MyCorrect4D", correct4D);
        savedInstanceState.putBoolean("MyCorrect5D", correct5D);
        savedInstanceState.putBoolean("MyCorrect6D", correct6D);
        savedInstanceState.putBoolean("MyCorrect7D", correct7D);
        savedInstanceState.putBoolean("MyCorrect8DA", correct8DA);
        savedInstanceState.putBoolean("MyCorrect8DB", correct8DB);
        savedInstanceState.putBoolean("MyCorrect8DC", correct8DC);
        savedInstanceState.putBoolean("MyCorrect8DD", correct8DD);
        savedInstanceState.putBoolean("MyCorrect9DA", correct9DA);
        savedInstanceState.putBoolean("MyCorrect9DB", correct9DB);
        savedInstanceState.putBoolean("MyCorrect9DC", correct9DC);
        savedInstanceState.putBoolean("MyCorrect9DD", correct9DD);
        savedInstanceState.putBoolean("MyCorrect10D", correct10);
        savedInstanceState.putBoolean("MyIsGameCompleted", isGameCompleted);
        savedInstanceState.putString("MyUserName", userName);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int MyStageCounter = savedInstanceState.getInt("MyStageCounter");
        int MyStatus = savedInstanceState.getInt("MyStatus");
        int MyScore = savedInstanceState.getInt("MyScore");
        int MySkipPossibilities = savedInstanceState.getInt("MySkipPossibilities");
        Boolean MyCorrect1 = savedInstanceState.getBoolean("MyCorrect1");
        Boolean MyCorrect2 = savedInstanceState.getBoolean("MyCorrect2");
        Boolean MyCorrect3 = savedInstanceState.getBoolean("MyCorrect3");
        Boolean MyCorrect4 = savedInstanceState.getBoolean("MyCorrect4");
        Boolean MyCorrect5 = savedInstanceState.getBoolean("MyCorrect5");
        Boolean MyCorrect6 = savedInstanceState.getBoolean("MyCorrect6");
        Boolean MyCorrect7 = savedInstanceState.getBoolean("MyCorrect7");
        Boolean MyCorrect8 = savedInstanceState.getBoolean("MyCorrect8");
        Boolean MyCorrect9 = savedInstanceState.getBoolean("MyCorrect9");
        Boolean MyCorrect10 = savedInstanceState.getBoolean("MyCorrect10");
        Boolean MyCorrect1D = savedInstanceState.getBoolean("MyCorrect1D");
        Boolean MyCorrect2D = savedInstanceState.getBoolean("MyCorrect2D");
        Boolean MyCorrect3D = savedInstanceState.getBoolean("MyCorrect3D");
        Boolean MyCorrect4D = savedInstanceState.getBoolean("MyCorrect4D");
        Boolean MyCorrect5D = savedInstanceState.getBoolean("MyCorrect5D");
        Boolean MyCorrect6D = savedInstanceState.getBoolean("MyCorrect6D");
        Boolean MyCorrect7D = savedInstanceState.getBoolean("MyCorrect7D");
        Boolean MyCorrect8DA = savedInstanceState.getBoolean("MyCorrect8DA");
        Boolean MyCorrect8DB = savedInstanceState.getBoolean("MyCorrect8DB");
        Boolean MyCorrect8DC = savedInstanceState.getBoolean("MyCorrect8DC");
        Boolean MyCorrect8DD = savedInstanceState.getBoolean("MyCorrect8DD");
        Boolean MyCorrect9DA = savedInstanceState.getBoolean("MyCorrect9DA");
        Boolean MyCorrect9DB = savedInstanceState.getBoolean("MyCorrect9DB");
        Boolean MyCorrect9DC = savedInstanceState.getBoolean("MyCorrect9DC");
        Boolean MyCorrect9DD = savedInstanceState.getBoolean("MyCorrect9DD");
        Boolean MyCorrect10D = savedInstanceState.getBoolean("MyCorrect10D");
        Boolean MyIsGameCompleted = savedInstanceState.getBoolean("MyIsGameCompleted");
        String MyUserName = savedInstanceState.getString("MyUsername");
        stageCounter = MyStageCounter;
        status = MyStatus;
        score = MyScore;
        skipPossibilities = MySkipPossibilities;
        correct1 = MyCorrect1;
        correct2 = MyCorrect2;
        correct3 = MyCorrect3;
        correct4 = MyCorrect4;
        correct5 = MyCorrect5;
        correct6 = MyCorrect6;
        correct7 = MyCorrect7;
        correct8 = MyCorrect8;
        correct9 = MyCorrect9;
        correct10 = MyCorrect10;
        correct1D = MyCorrect1D;
        correct2D = MyCorrect2D;
        correct3D = MyCorrect3D;
        correct4D = MyCorrect4D;
        correct5D = MyCorrect5D;
        correct6D = MyCorrect6D;
        correct7D = MyCorrect7D;
        correct8DA = MyCorrect8DA;
        correct8DB = MyCorrect8DB;
        correct8DC = MyCorrect8DC;
        correct8DD = MyCorrect8DD;
        correct9DA = MyCorrect9DA;
        correct9DB = MyCorrect9DB;
        correct9DC = MyCorrect9DC;
        correct9DD = MyCorrect9DD;
        correct10D = MyCorrect10D;
        isGameCompleted = MyIsGameCompleted;
        userName = MyUserName;

    }


    /**
     * the following method is the central part of the game structure
     * the main part of the quiz is around the central button that mean different behviour of the
     * user depending on his status.
     * thanks to this simply test (STATUS == ..) the procedure will operate in a different way
     * depending on the user status.
     */

    public void confirmClick(View view) {

        if (status == 0) {
            ScrollView scrollRules = (ScrollView) findViewById(R.id.scrollRules);
            scrollRules.setVisibility(VISIBLE);
            Button confirmButton = (Button) findViewById(R.id.confirmButton);
            confirmButton.setText(R.string.go);
            status = 1;
            score = 0;
            skipPossibilities = 3;
        } else if (status == 1) {
            /**
             * now I know we are in the start menu, so I can show the first image introduction
             * to the user
             */
            ScrollView scrollRules = (ScrollView) findViewById(R.id.scrollRules);
            scrollRules.setVisibility(GONE);
            EditText userNameText = (EditText) findViewById(R.id.userNameText);
            userName = userNameText.getText().toString();
            if (isEmpty(userName)) {
                Toast.makeText(MainActivity.this, "You have to enter your name!", Toast.LENGTH_LONG).show();
            } else if (userName.equals("Insert your name..")) {
                Toast.makeText(MainActivity.this, "You have to enter your name!", Toast.LENGTH_LONG).show();
            } else displayIntro();

        } else if (status == 2) {
            /**
             * now I know we are in the introduction image menu, so I can show the first question
             * to the user
             */
            engageStage();
        } else if (status == 3) {
            /**
             * now I know we are in the question section, so the user is submitting the answer
             * and the procedure can check it
             */
            submitAnswer();
        } else if (status == 4) {
            /**
             * now I know that the user chose to engage the DOUBLE question of the stage
             */
            displayIntro();
        } else if (status == 5) {
            /**
             * now I know we are in the DOUBLE question section, so the user is submitting the
             * DOUBLE answer and the procedure can check it
             */
            submitAnswer();
        } else if (status == 6) {
            /**
             * now I know that the user just submitted a correct answer and now has to choose
             * to go for the double or simply skip through next stage
             */
            displayDoubleIntro();
        } else if (status == 7) {
            /**
             * now I know that the user chose to go for next stage without DOUBLE.
             */
            if (!isGameCompleted) {
                stageCounter = stageCounter + 1;
                displayIntro();
            } else displayFinal();

        } else if (status == 9) {
            /**
             * now I know that the user completed the game
             */
            displayFinal();
        }
    }

    public void displayFinal() {
        if (mediaPlayer.isPlaying())  {
            stopPlaying();
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.finalsong);
        mediaPlayer.start();
        Button goForDouble = (Button) findViewById(R.id.goForDouble);
        goForDouble.setEnabled(false);
        Button skipQuestion = (Button) findViewById(R.id.skipQuestion);
        skipQuestion.setEnabled(false);
        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        confirmButton.setEnabled(false);
        View answersField = findViewById(R.id.answers);
        answersField.setVisibility(View.INVISIBLE);
        TextView questionText = (TextView) findViewById(R.id.questionText);
        questionText.setVisibility(View.INVISIBLE);
        ImageView stageBackground = (ImageView) findViewById(R.id.stageBackground);
        stageBackground.setImageResource(R.drawable.finalimage);
        Toast.makeText(MainActivity.this, "CONGRAGULATION! \n" + userName + "\nYou completed the quiz with the score of: \n" + score + "\n I hope you had a great time!! \n bye Mattia Radenti", Toast.LENGTH_LONG).show();

    }

    public void skipClick(View view) {
        /**
         * toast test ;)
         */
        Button skipQuestion = (Button) findViewById(R.id.skipQuestion);
        if (skipPossibilities > 0) {
            skipPossibilities = skipPossibilities - 1;

            if (skipPossibilities == 0) {
                skipQuestion.setEnabled(false);
            }
            if (stageCounter != 10) {
                stageCounter = stageCounter + 1;
                displayIntro();
            } else displayFinal();
        } else Toast.makeText(MainActivity.this, wrong, Toast.LENGTH_LONG).show();

    }


    /**
     * the following method will display the intro image and set the control buttons in the correct way for the contest
     */
    public void displayIntro() {

        if (skipPossibilities > 0) {
            Button skipQuestion = (Button) findViewById(R.id.skipQuestion);
            skipQuestion.setEnabled(true);
        }
        Button goForDouble = (Button) findViewById(R.id.goForDouble);
        goForDouble.setEnabled(false);
        View answersField = findViewById(R.id.answers);
        answersField.setVisibility(View.INVISIBLE);
        TextView questionText = (TextView) findViewById(R.id.questionText);
        questionText.setVisibility(View.INVISIBLE);
        ImageView stageBackground = (ImageView) findViewById(R.id.stageBackground);
        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        confirmButton.setText(engage);
        EditText userNameText = (EditText) findViewById(R.id.userNameText);
        userName = userNameText.getText().toString();
        userNameText.setVisibility(View.INVISIBLE);
        status = 2;

        if (stageCounter == 1) {
            stageBackground.setImageResource(R.drawable.mario);
            mediaPlayer = MediaPlayer.create(this, R.raw.mario1);
            mediaPlayer.start();
        } else if (stageCounter == 2) {
            if (mediaPlayer.isPlaying())  {
                stopPlaying();
            }
            stageBackground.setImageResource(R.drawable.donkey);
            mediaPlayer = MediaPlayer.create(this, R.raw.donkey);
            mediaPlayer.start();
        } else if (stageCounter == 3) {
            if (mediaPlayer.isPlaying())  {
                stopPlaying();
            }
            stageBackground.setImageResource(R.drawable.mariokart);
            mediaPlayer = MediaPlayer.create(this, R.raw.mariokart);
            mediaPlayer.start();
        } else if (stageCounter == 4) {
            if (mediaPlayer.isPlaying())  {
                stopPlaying();
            }
            stageBackground.setImageResource(R.drawable.smashbros);
            mediaPlayer = MediaPlayer.create(this, R.raw.smashbros);
            mediaPlayer.start();
        } else if (stageCounter == 5) {
            if (mediaPlayer.isPlaying())  {
                stopPlaying();
            }
            stageBackground.setImageResource(R.drawable.fzero);
            mediaPlayer = MediaPlayer.create(this, R.raw.fzero);
            mediaPlayer.start();
        } else if (stageCounter == 6) {
            if (mediaPlayer.isPlaying())  {
                stopPlaying();
            }
            stageBackground.setImageResource(R.drawable.metroid);
            mediaPlayer = MediaPlayer.create(this, R.raw.metroid1);
            mediaPlayer.start();
        } else if (stageCounter == 7) {
            if (mediaPlayer.isPlaying())  {
                stopPlaying();
            }
            stageBackground.setImageResource(R.drawable.starfox);
            mediaPlayer = MediaPlayer.create(this, R.raw.starfox);
            mediaPlayer.start();
        } else if (stageCounter == 8) {
            if (mediaPlayer.isPlaying())  {
                stopPlaying();
            }
            stageBackground.setImageResource(R.drawable.megaman);
            mediaPlayer = MediaPlayer.create(this, R.raw.megaman1);
            mediaPlayer.start();
        } else if (stageCounter == 9) {
            if (mediaPlayer.isPlaying())  {
                stopPlaying();
            }
            stageBackground.setImageResource(R.drawable.zelda);
            mediaPlayer = MediaPlayer.create(this, R.raw.zelda);
            mediaPlayer.start();
        } else if (stageCounter == 10) {
            if (mediaPlayer.isPlaying())  {
                stopPlaying();
            }
            stageBackground.setImageResource(R.drawable.chrono);
            mediaPlayer = MediaPlayer.create(this, R.raw.chrono);
            mediaPlayer.start();
    }
    }

    public void displayDoubleIntro() {
        if (skipPossibilities > 0) {
            Button skipQuestion = (Button) findViewById(R.id.skipQuestion);
            skipQuestion.setEnabled(true);
        }
        View answersField = findViewById(R.id.answers);
        TextView questionText = (TextView) findViewById(R.id.questionText);
        ImageView stageBackground = (ImageView) findViewById(R.id.stageBackground);
        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        questionText.setVisibility(VISIBLE);
        questionText.setText(R.string.introDouble);
        answersField.setVisibility(View.INVISIBLE);
        confirmButton.setText(R.string.goNextStage);
        Button goForDouble = (Button) findViewById(R.id.goForDouble);
        goForDouble.setEnabled(true);
        status = 7;

        if (stageCounter == 1) {
            stageBackground.setImageResource(R.drawable.mariocorrect);
        } else if (stageCounter == 2) {
            stageBackground.setImageResource(R.drawable.donkeycorrect);
        } else if (stageCounter == 3) {
            stageBackground.setImageResource(R.drawable.mariokartcorrect);
        } else if (stageCounter == 4) {
            stageBackground.setImageResource(R.drawable.smashbroscorrect);
        } else if (stageCounter == 5) {
            stageBackground.setImageResource(R.drawable.fzerocorrect);
        } else if (stageCounter == 6) {
            stageBackground.setImageResource(R.drawable.metroidcorrect);
        } else if (stageCounter == 7) {
            stageBackground.setImageResource(R.drawable.starfoxcorrect);
        } else if (stageCounter == 8) {
            stageBackground.setImageResource(R.drawable.megamancorrect);
        } else if (stageCounter == 9) {
            stageBackground.setImageResource(R.drawable.zeldacorrect);
        } else if (stageCounter == 10) {
            stageBackground.setImageResource(R.drawable.chronocorrect);
        }
    }

    /**
     * the following method will display the background image, the question and the answer possibilities
     */
    public void engageStage() {
        TextView questionText = (TextView) findViewById(R.id.questionText);
        questionText.setVisibility(VISIBLE);
        TextView rules = (TextView) findViewById(R.id.rules);
        rules.setVisibility(GONE);

        if (skipPossibilities > 0) {
            Button skipQuestion = (Button) findViewById(R.id.skipQuestion);
            skipQuestion.setEnabled(true);
        }
        Button goForDouble = (Button) findViewById(R.id.goForDouble);
        goForDouble.setEnabled(false);
        View answersField = findViewById(R.id.answers);
        ImageView stageBackground = (ImageView) findViewById(R.id.stageBackground);
        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        RadioButton radioA = (RadioButton) findViewById(R.id.radioA);
        RadioButton radioB = (RadioButton) findViewById(R.id.radioB);
        RadioButton radioC = (RadioButton) findViewById(R.id.radioC);
        RadioButton radioD = (RadioButton) findViewById(R.id.radioD);
        CheckBox checkA = (CheckBox) findViewById(R.id.checkA);
        CheckBox checkB = (CheckBox) findViewById(R.id.checkB);
        CheckBox checkC = (CheckBox) findViewById(R.id.checkC);
        CheckBox checkD = (CheckBox) findViewById(R.id.checkD);
        EditText editTextAnswer = (EditText) findViewById(R.id.editTextAnswer);
        questionText.setVisibility(VISIBLE);
        answersField.setVisibility(VISIBLE);
        confirmButton.setText(R.string.submit);
        status = 3;

        if (stageCounter == 1) {
            stageBackground.setImageResource(R.drawable.mario65);
            questionText.setText(R.string.question1);
            showRadioButtons();
            hideCheckBoxes();
            hideEditText();
            radioA.setText(R.string.answer1A);
            radioB.setText(R.string.answer1B);
            radioC.setText(R.string.answer1C);
            radioD.setText(R.string.answer1D);
        } else if (stageCounter == 2) {
            stageBackground.setImageResource(R.drawable.donkey65);
            questionText.setText(R.string.question2);
            showRadioButtons();
            hideCheckBoxes();
            hideEditText();
            radioA.setText(R.string.answer2A);
            radioB.setText(R.string.answer2B);
            radioC.setText(R.string.answer2C);
            radioD.setText(R.string.answer2D);
        } else if (stageCounter == 3) {
            stageBackground.setImageResource(R.drawable.mariokart65);
            questionText.setText(R.string.question3);
            showRadioButtons();
            hideCheckBoxes();
            hideEditText();
            radioA.setText(R.string.answer3A);
            radioB.setText(R.string.answer3B);
            radioC.setText(R.string.answer3C);
            radioD.setText(R.string.answer3D);
        } else if (stageCounter == 4) {
            stageBackground.setImageResource(R.drawable.smashbros65);
            questionText.setText(R.string.question4);
            showRadioButtons();
            hideCheckBoxes();
            hideEditText();
            radioA.setText(R.string.answer4A);
            radioB.setText(R.string.answer4B);
            radioC.setText(R.string.answer4C);
            radioD.setText(R.string.answer4D);
        } else if (stageCounter == 5) {
            stageBackground.setImageResource(R.drawable.fzero65);
            questionText.setText(R.string.question5);
            radioA.setText(R.string.answer5A);
            radioB.setText(R.string.answer5B);
            radioC.setText(R.string.answer5C);
            radioD.setText(R.string.answer5D);
            showRadioButtons();
            hideCheckBoxes();
            hideEditText();
        } else if (stageCounter == 6) {
            stageBackground.setImageResource(R.drawable.metroid65);
            questionText.setText(R.string.question6);
            radioA.setText(R.string.answer6A);
            radioB.setText(R.string.answer6B);
            radioC.setText(R.string.answer6C);
            radioD.setText(R.string.answer6D);
            showRadioButtons();
            hideCheckBoxes();
            hideEditText();
        } else if (stageCounter == 7) {
            stageBackground.setImageResource(R.drawable.starfox65);
            questionText.setText(R.string.question7);
            radioA.setText(R.string.answer7A);
            radioB.setText(R.string.answer7B);
            radioC.setText(R.string.answer7C);
            radioD.setText(R.string.answer7D);
            showRadioButtons();
            hideCheckBoxes();
            hideEditText();
        } else if (stageCounter == 8) {
            stageBackground.setImageResource(R.drawable.megaman65);
            questionText.setText(R.string.question8);
            radioA.setText(R.string.answer8A);
            radioB.setText(R.string.answer8B);
            radioC.setText(R.string.answer8C);
            radioD.setText(R.string.answer8D);
            showRadioButtons();
            hideCheckBoxes();
            hideEditText();
        } else if (stageCounter == 9) {
            stageBackground.setImageResource(R.drawable.zelda65);
            questionText.setText(R.string.question9);
            showEditText();
            hideRadioButtons();
            hideCheckBoxes();
        } else if (stageCounter == 10) {
            stageBackground.setImageResource(R.drawable.chrono65);
            questionText.setText(R.string.question10);
            showRadioButtons();
            hideCheckBoxes();
            hideEditText();
            radioA.setText(R.string.answer10A);
            radioB.setText(R.string.answer10B);
            radioC.setText(R.string.answer10C);
            radioD.setText(R.string.answer10D);
        }

    }

    /**
     * the following method will display the background image, the question and the answer possibilities
     */
    public void engageDoubleStage() {
        /**
         * try to set the finding procedures in a secific method
         */
        if (skipPossibilities > 0) {
            Button skipQuestion = (Button) findViewById(R.id.skipQuestion);
            skipQuestion.setEnabled(true);
        }
        Button goForDouble = (Button) findViewById(R.id.goForDouble);
        goForDouble.setEnabled(false);
        View answersField = findViewById(R.id.answers);
        TextView questionText = (TextView) findViewById(R.id.questionText);
        ImageView stageBackground = (ImageView) findViewById(R.id.stageBackground);
        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        RadioButton radioA = (RadioButton) findViewById(R.id.radioA);
        RadioButton radioB = (RadioButton) findViewById(R.id.radioB);
        RadioButton radioC = (RadioButton) findViewById(R.id.radioC);
        RadioButton radioD = (RadioButton) findViewById(R.id.radioD);
        CheckBox checkA = (CheckBox) findViewById(R.id.checkA);
        CheckBox checkB = (CheckBox) findViewById(R.id.checkB);
        CheckBox checkC = (CheckBox) findViewById(R.id.checkC);
        CheckBox checkD = (CheckBox) findViewById(R.id.checkD);
        questionText.setVisibility(VISIBLE);
        answersField.setVisibility(VISIBLE);
        confirmButton.setText(R.string.submit);
        status = 5;


        if (stageCounter == 1) {
            if (mediaPlayer.isPlaying())  {
                stopPlaying();
            }
            mediaPlayer = MediaPlayer.create(this, R.raw.mario2);
            mediaPlayer.start();
            stageBackground.setImageResource(R.drawable.mario65);
            questionText.setText(R.string.double1);
            showRadioButtons();
            hideCheckBoxes();
            hideEditText();
            radioA.setText(R.string.answerDouble1A);
            radioB.setText(R.string.answerDouble1B);
            radioC.setText(R.string.answerDouble1C);
            radioD.setText(R.string.answerDouble1D);
        } else if (stageCounter == 2) {
            if (mediaPlayer.isPlaying())  {
                stopPlaying();
            }
            mediaPlayer = MediaPlayer.create(this, R.raw.donkey);
            mediaPlayer.start();
            stageBackground.setImageResource(R.drawable.donkey65);
            questionText.setText(R.string.double2);
            showRadioButtons();
            hideCheckBoxes();
            hideEditText();
            radioA.setText(R.string.answerDouble2A);
            radioB.setText(R.string.answerDouble2B);
            radioC.setText(R.string.answerDouble2C);
            radioD.setText(R.string.answerDouble2D);
        } else if (stageCounter == 3) {
            if (mediaPlayer.isPlaying())  {
                stopPlaying();
            }
            mediaPlayer = MediaPlayer.create(this, R.raw.mariokart2);
            mediaPlayer.start();
            stageBackground.setImageResource(R.drawable.mariokart65);
            questionText.setText(R.string.double3);
            showRadioButtons();
            hideCheckBoxes();
            hideEditText();
            radioA.setText(R.string.answerDouble3A);
            radioB.setText(R.string.answerDouble3B);
            radioC.setText(R.string.answerDouble3C);
            radioD.setText(R.string.answerDouble3D);
        } else if (stageCounter == 4) {
            if (mediaPlayer.isPlaying())  {
                stopPlaying();
            }
            mediaPlayer = MediaPlayer.create(this, R.raw.smashbros);
            mediaPlayer.start();
            stageBackground.setImageResource(R.drawable.smashbros65);
            questionText.setText(R.string.double4);
            showRadioButtons();
            hideCheckBoxes();
            hideEditText();
            radioA.setText(R.string.answerDouble4A);
            radioB.setText(R.string.answerDouble4B);
            radioC.setText(R.string.answerDouble4C);
            radioD.setText(R.string.answerDouble4D);
        } else if (stageCounter == 5) {
            if (mediaPlayer.isPlaying())  {
                stopPlaying();
            }
            mediaPlayer = MediaPlayer.create(this, R.raw.fzero2);
            mediaPlayer.start();
            stageBackground.setImageResource(R.drawable.fzero65);
            questionText.setText(R.string.double5);
            radioA.setText(R.string.answerDouble5A);
            radioB.setText(R.string.answerDouble5B);
            radioC.setText(R.string.answerDouble5C);
            radioD.setText(R.string.answerDouble5D);
            showRadioButtons();
            hideCheckBoxes();
            hideEditText();
        } else if (stageCounter == 6) {
            if (mediaPlayer.isPlaying())  {
                stopPlaying();
            }
            mediaPlayer = MediaPlayer.create(this, R.raw.metroid2);
            mediaPlayer.start();
            stageBackground.setImageResource(R.drawable.metroid65);
            questionText.setText(R.string.double6);
            radioA.setText(R.string.answerDouble6A);
            radioB.setText(R.string.answerDouble6B);
            radioC.setText(R.string.answerDouble6C);
            radioD.setText(R.string.answerDouble6D);
            showRadioButtons();
            hideCheckBoxes();
            hideEditText();
        } else if (stageCounter == 7) {
            if (mediaPlayer.isPlaying())  {
                stopPlaying();
            }
            mediaPlayer = MediaPlayer.create(this, R.raw.starfox2);
            mediaPlayer.start();
            stageBackground.setImageResource(R.drawable.starfox65);
            questionText.setText(R.string.double7);
            radioA.setText(R.string.answerDouble7A);
            radioB.setText(R.string.answerDouble7B);
            radioC.setText(R.string.answerDouble7C);
            radioD.setText(R.string.answerDouble7D);
            showRadioButtons();
            hideCheckBoxes();
            hideEditText();
        } else if (stageCounter == 8) {
            if (mediaPlayer.isPlaying())  {
                stopPlaying();
            }
            mediaPlayer = MediaPlayer.create(this, R.raw.megaman2);
            mediaPlayer.start();
            stageBackground.setImageResource(R.drawable.megaman65);
            questionText.setText(R.string.double8);
            showCheckBox();
            hideRadioButtons();
            hideEditText();
            checkA.setText(R.string.answerDouble8A);
            checkB.setText(R.string.answerDouble8B);
            checkC.setText(R.string.answerDouble8C);
            checkD.setText(R.string.answerDouble8D);
        } else if (stageCounter == 9) {
            if (mediaPlayer.isPlaying())  {
                stopPlaying();
            }
            mediaPlayer = MediaPlayer.create(this, R.raw.zelda2);
            mediaPlayer.start();
            stageBackground.setImageResource(R.drawable.zelda65);
            questionText.setText(R.string.double9);
            hideEditText();
            showCheckBox();
            hideRadioButtons();
            checkA.setText(R.string.answerDouble9A);
            checkB.setText(R.string.answerDouble9B);
            checkC.setText(R.string.answerDouble9C);
            checkD.setText(R.string.answerDouble9D);
        } else if (stageCounter == 10) {
            if (mediaPlayer.isPlaying())  {
                stopPlaying();
            }
            mediaPlayer = MediaPlayer.create(this, R.raw.chrono);
            mediaPlayer.start();
            stageBackground.setImageResource(R.drawable.chrono65);
            questionText.setText(R.string.double10);
            showRadioButtons();
            hideCheckBoxes();
            hideEditText();
            radioA.setText(R.string.answerDouble10A);
            radioB.setText(R.string.answerDouble10B);
            radioC.setText(R.string.answerDouble10C);
            radioD.setText(R.string.answerDouble10D);
        }
    }

    /**
     * the following method will check the stage, and for each stage will recall the correct method
     */

    public void submitAnswer() {
        if (status == 3) {
            if (stageCounter == 1) {
                checkAnswer1();
            } else if (stageCounter == 2) {
                checkAnswer2();
            } else if (stageCounter == 3) {
                checkAnswer3();
            } else if (stageCounter == 4) {
                checkAnswer4();
            } else if (stageCounter == 5) {
                checkAnswer5();
            } else if (stageCounter == 6) {
                checkAnswer6();
            } else if (stageCounter == 7) {
                checkAnswer7();
            } else if (stageCounter == 8) {
                checkAnswer8();
            } else if (stageCounter == 9) {
                checkAnswer9();
            } else if (stageCounter == 10) {
                checkAnswer10();
            }
        }
        if (status == 5) {
            if (stageCounter == 1) {
                checkAnswer1D();
            } else if (stageCounter == 2) {
                checkAnswer2D();
            } else if (stageCounter == 3) {
                checkAnswer3D();
            } else if (stageCounter == 4) {
                checkAnswer4D();
            } else if (stageCounter == 5) {
                checkAnswer5D();
            } else if (stageCounter == 6) {
                checkAnswer6D();
            } else if (stageCounter == 7) {
                checkAnswer7D();
            } else if (stageCounter == 8) {
                checkAnswer8D();
            } else if (stageCounter == 9) {
                checkAnswer9D();
            } else if (stageCounter == 10) {
                checkAnswer10D();
            }
        }
    }


    public void checkAnswer1() {
        RadioButton radioA = (RadioButton) findViewById(R.id.radioA);
        RadioButton radioB = (RadioButton) findViewById(R.id.radioB);
        RadioButton radioC = (RadioButton) findViewById(R.id.radioC);
        RadioButton radioD = (RadioButton) findViewById(R.id.radioD);
        boolean correct1 = radioB.isChecked();
        if (correct1) {
            score = score + 1;
            radioA.setChecked(false);
            radioB.setChecked(false);
            radioC.setChecked(false);
            radioD.setChecked(false);
            displayDoubleIntro();
            View marioBadge = findViewById(R.id.marioBadge);
            marioBadge.setVisibility(VISIBLE);
        } else wrongAnswer();
    }

    public void checkAnswer2() {
        RadioButton radioA = (RadioButton) findViewById(R.id.radioA);
        RadioButton radioB = (RadioButton) findViewById(R.id.radioB);
        RadioButton radioC = (RadioButton) findViewById(R.id.radioC);
        RadioButton radioD = (RadioButton) findViewById(R.id.radioD);
        boolean correct2 = radioA.isChecked();
        if (correct2) {
            score = score + 1;
            displayDoubleIntro();
            radioA.setChecked(false);
            radioB.setChecked(false);
            radioC.setChecked(false);
            radioD.setChecked(false);
            View donkeyBadge = findViewById(R.id.donkeyBadge);
            donkeyBadge.setVisibility(VISIBLE);
        } else wrongAnswer();
    }

    public void checkAnswer3() {
        RadioButton radioA = (RadioButton) findViewById(R.id.radioA);
        RadioButton radioB = (RadioButton) findViewById(R.id.radioB);
        RadioButton radioC = (RadioButton) findViewById(R.id.radioC);
        RadioButton radioD = (RadioButton) findViewById(R.id.radioD);
        boolean correct3 = radioC.isChecked();
        if (correct3) {
            score = score + 1;
            displayDoubleIntro();
            radioA.setChecked(false);
            radioB.setChecked(false);
            radioC.setChecked(false);
            radioD.setChecked(false);
            View marioKartBadge = findViewById(R.id.marioKartBadge);
            marioKartBadge.setVisibility(VISIBLE);
        } else wrongAnswer();
    }

    public void checkAnswer4() {
        RadioButton radioA = (RadioButton) findViewById(R.id.radioA);
        RadioButton radioB = (RadioButton) findViewById(R.id.radioB);
        RadioButton radioC = (RadioButton) findViewById(R.id.radioC);
        RadioButton radioD = (RadioButton) findViewById(R.id.radioD);
        boolean correct4 = radioC.isChecked();
        if (correct4) {
            score = score + 1;
            displayDoubleIntro();
            radioA.setChecked(false);
            radioB.setChecked(false);
            radioC.setChecked(false);
            radioD.setChecked(false);
            View smashbrosBadge = findViewById(smashBrosBadge);
            smashbrosBadge.setVisibility(VISIBLE);
        } else wrongAnswer();
    }

    public void checkAnswer5() {
        RadioButton radioA = (RadioButton) findViewById(R.id.radioA);
        RadioButton radioB = (RadioButton) findViewById(R.id.radioB);
        RadioButton radioC = (RadioButton) findViewById(R.id.radioC);
        RadioButton radioD = (RadioButton) findViewById(R.id.radioD);
        boolean correct5 = radioC.isChecked();
        if (correct5) {
            score = score + 1;
            radioA.setChecked(false);
            radioB.setChecked(false);
            radioC.setChecked(false);
            radioD.setChecked(false);
            displayDoubleIntro();
            View fzeroBadge = findViewById(R.id.fzeroBadge);
            fzeroBadge.setVisibility(VISIBLE);
        } else wrongAnswer();
    }

    public void checkAnswer6() {
        RadioButton radioA = (RadioButton) findViewById(R.id.radioA);
        RadioButton radioB = (RadioButton) findViewById(R.id.radioB);
        RadioButton radioC = (RadioButton) findViewById(R.id.radioC);
        RadioButton radioD = (RadioButton) findViewById(R.id.radioD);
        boolean correct6 = radioC.isChecked();
        if (correct6) {
            score = score + 1;
            radioA.setChecked(false);
            radioB.setChecked(false);
            radioC.setChecked(false);
            radioD.setChecked(false);
            displayDoubleIntro();
            View metroidBadge = findViewById(R.id.metroidBadge);
            metroidBadge.setVisibility(VISIBLE);
        } else wrongAnswer();
    }

    public void checkAnswer7() {
        RadioButton radioA = (RadioButton) findViewById(R.id.radioA);
        RadioButton radioB = (RadioButton) findViewById(R.id.radioB);
        RadioButton radioC = (RadioButton) findViewById(R.id.radioC);
        RadioButton radioD = (RadioButton) findViewById(R.id.radioD);
        boolean correct7 = radioD.isChecked();
        if (correct7) {
            score = score + 1;
            radioA.setChecked(false);
            radioB.setChecked(false);
            radioC.setChecked(false);
            radioD.setChecked(false);
            displayDoubleIntro();
            View starfoxBadge = findViewById(R.id.starfoxBadge);
            starfoxBadge.setVisibility(VISIBLE);
        } else wrongAnswer();
    }

    public void checkAnswer8() {
        RadioButton radioA = (RadioButton) findViewById(R.id.radioA);
        RadioButton radioB = (RadioButton) findViewById(R.id.radioB);
        RadioButton radioC = (RadioButton) findViewById(R.id.radioC);
        RadioButton radioD = (RadioButton) findViewById(R.id.radioD);
        boolean correct8 = radioC.isChecked();
        if (correct8) {
            score = score + 1;
            radioA.setChecked(false);
            radioB.setChecked(false);
            radioC.setChecked(false);
            radioD.setChecked(false);
            displayDoubleIntro();
            View megamanBadge = findViewById(R.id.megamanBadge);
            megamanBadge.setVisibility(VISIBLE);
        } else wrongAnswer();
    }

    public void checkAnswer9() {
        EditText editTextAnswer = (EditText) findViewById(R.id.editTextAnswer);
        String textAnswer = editTextAnswer.getText().toString();
        String correctAnswer = getString(R.string.answer9);
        if (textAnswer.equals(correctAnswer)) {
            score = score + 1;
            displayDoubleIntro();
            View zeldaBadge = findViewById(R.id.zeldaBadge);
            zeldaBadge.setVisibility(VISIBLE);
        } else wrongAnswer();


    }

    public void checkAnswer10() {
        RadioButton radioA = (RadioButton) findViewById(R.id.radioA);
        RadioButton radioB = (RadioButton) findViewById(R.id.radioB);
        RadioButton radioC = (RadioButton) findViewById(R.id.radioC);
        RadioButton radioD = (RadioButton) findViewById(R.id.radioD);
        boolean correct10 = radioA.isChecked();
        if (correct10) {
            score = score + 1;
            radioA.setChecked(false);
            radioB.setChecked(false);
            radioC.setChecked(false);
            radioD.setChecked(false);
            displayDoubleIntro();
            View chronoBadge = findViewById(R.id.chronoBadge);
            chronoBadge.setVisibility(VISIBLE);
            status = 9;
            isGameCompleted = true;
        } else wrongAnswer();
    }

    public void doubleCompleted() {
        Button skipQuestion = (Button) findViewById(R.id.skipQuestion);
        skipQuestion.setEnabled(false);
        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        confirmButton.setText(R.string.goNextStage);
        View answersField = findViewById(R.id.answers);
        answersField.setVisibility(View.INVISIBLE);
        TextView questionText = (TextView) findViewById(R.id.questionText);
        questionText.setVisibility(View.VISIBLE);
        questionText.setText(correctDouble);
        ImageView stageBackground = (ImageView) findViewById(R.id.stageBackground);
        status = 7;
        if (stageCounter == 1) {
            stageBackground.setImageResource(R.drawable.mariocorrectdouble);
            ImageView marioBadge = (ImageView) findViewById(R.id.marioBadge);
            marioBadge.setImageResource(R.drawable.mario_icondouble);
        } else if (stageCounter == 2) {
            stageBackground.setImageResource(R.drawable.donkeycorrectdouble);
            ImageView donkeyBadge = (ImageView) findViewById(R.id.donkeyBadge);
            donkeyBadge.setImageResource(R.drawable.barrel_icondouble);
        } else if (stageCounter == 3) {
            stageBackground.setImageResource(R.drawable.mariokartcorrectdouble);
            ImageView marioKartBadge = (ImageView) findViewById(R.id.marioKartBadge);
            marioKartBadge.setImageResource(R.drawable.mariokart_icondouble);
        } else if (stageCounter == 4) {
            stageBackground.setImageResource(R.drawable.smashbroscorrectdouble);
            ImageView smashbrosBadge = (ImageView) findViewById(R.id.smashBrosBadge);
            smashbrosBadge.setImageResource(R.drawable.smashbros_icondouble);
        } else if (stageCounter == 5) {
            stageBackground.setImageResource(R.drawable.fzerocorrectdouble);
            ImageView fzeroBadge = (ImageView) findViewById(R.id.fzeroBadge);
            fzeroBadge.setImageResource(R.drawable.fzero_icondouble);
        } else if (stageCounter == 6) {
            stageBackground.setImageResource(R.drawable.metroidcorrectdouble);
            ImageView metroidBadge = (ImageView) findViewById(R.id.metroidBadge);
            metroidBadge.setImageResource(R.drawable.metroid_icondouble);
        } else if (stageCounter == 7) {
            stageBackground.setImageResource(R.drawable.starfoxcorrectdouble);
            ImageView starfoxBadge = (ImageView) findViewById(R.id.starfoxBadge);
            starfoxBadge.setImageResource(R.drawable.starfox_icondouble);
        } else if (stageCounter == 8) {
            stageBackground.setImageResource(R.drawable.megamancorrectdouble);
            ImageView megamanBadge = (ImageView) findViewById(R.id.megamanBadge);
            megamanBadge.setImageResource(R.drawable.megaman_icondouble);
        } else if (stageCounter == 9) {
            stageBackground.setImageResource(R.drawable.zeldacorrectdouble);
            ImageView zeldaBadge = (ImageView) findViewById(R.id.zeldaBadge);
            zeldaBadge.setImageResource(R.drawable.zelda_icondouble);
        } else if (stageCounter == 10) {
            stageBackground.setImageResource(R.drawable.chronocorrectdouble);
            ImageView chronoBadge = (ImageView) findViewById(R.id.chronoBadge);
            chronoBadge.setImageResource(R.drawable.chrono_icondouble);
            status = 9;
            isGameCompleted = true;
        }
    }


    public void checkAnswer1D() {
        RadioButton radioA = (RadioButton) findViewById(R.id.radioA);
        RadioButton radioB = (RadioButton) findViewById(R.id.radioB);
        RadioButton radioC = (RadioButton) findViewById(R.id.radioC);
        RadioButton radioD = (RadioButton) findViewById(R.id.radioD);
        boolean correct1D = radioC.isChecked();
        if (correct1D) {
            score = score + 2;
            radioA.setChecked(false);
            radioB.setChecked(false);
            radioC.setChecked(false);
            radioD.setChecked(false);
            doubleCompleted();
        } else wrongDoubleAnswer();
    }

    public void checkAnswer2D() {
        RadioButton radioA = (RadioButton) findViewById(R.id.radioA);
        RadioButton radioB = (RadioButton) findViewById(R.id.radioB);
        RadioButton radioC = (RadioButton) findViewById(R.id.radioC);
        RadioButton radioD = (RadioButton) findViewById(R.id.radioD);
        boolean correct2D = radioB.isChecked();
        if (correct2D) {
            score = score + 2;
            radioA.setChecked(false);
            radioB.setChecked(false);
            radioC.setChecked(false);
            radioD.setChecked(false);
            doubleCompleted();
        } else wrongDoubleAnswer();
    }

    public void checkAnswer3D() {
        RadioButton radioA = (RadioButton) findViewById(R.id.radioA);
        RadioButton radioB = (RadioButton) findViewById(R.id.radioB);
        RadioButton radioC = (RadioButton) findViewById(R.id.radioC);
        RadioButton radioD = (RadioButton) findViewById(R.id.radioD);
        boolean correct3D = radioB.isChecked();
        if (correct3D) {
            score = score + 2;
            radioA.setChecked(false);
            radioB.setChecked(false);
            radioC.setChecked(false);
            radioD.setChecked(false);
            doubleCompleted();
        } else wrongDoubleAnswer();
    }

    public void checkAnswer4D() {
        RadioButton radioA = (RadioButton) findViewById(R.id.radioA);
        RadioButton radioB = (RadioButton) findViewById(R.id.radioB);
        RadioButton radioC = (RadioButton) findViewById(R.id.radioC);
        RadioButton radioD = (RadioButton) findViewById(R.id.radioD);
        boolean correct4D = radioC.isChecked();
        if (correct4D) {
            score = score + 2;
            radioA.setChecked(false);
            radioB.setChecked(false);
            radioC.setChecked(false);
            radioD.setChecked(false);
            doubleCompleted();
        } else wrongDoubleAnswer();
    }

    public void checkAnswer5D() {
        RadioButton radioA = (RadioButton) findViewById(R.id.radioA);
        RadioButton radioB = (RadioButton) findViewById(R.id.radioB);
        RadioButton radioC = (RadioButton) findViewById(R.id.radioC);
        RadioButton radioD = (RadioButton) findViewById(R.id.radioD);
        boolean correct5D = radioA.isChecked();
        if (correct5D) {
            score = score + 2;
            radioA.setChecked(false);
            radioB.setChecked(false);
            radioC.setChecked(false);
            radioD.setChecked(false);
            doubleCompleted();
        } else wrongDoubleAnswer();
    }

    public void checkAnswer6D() {
        RadioButton radioA = (RadioButton) findViewById(R.id.radioA);
        RadioButton radioB = (RadioButton) findViewById(R.id.radioB);
        RadioButton radioC = (RadioButton) findViewById(R.id.radioC);
        RadioButton radioD = (RadioButton) findViewById(R.id.radioD);
        boolean correct6D = radioA.isChecked();
        if (correct6D) {
            score = score + 2;
            radioA.setChecked(false);
            radioB.setChecked(false);
            radioC.setChecked(false);
            radioD.setChecked(false);
            doubleCompleted();
        } else wrongDoubleAnswer();
    }

    public void checkAnswer7D() {
        RadioButton radioA = (RadioButton) findViewById(R.id.radioA);
        RadioButton radioB = (RadioButton) findViewById(R.id.radioB);
        RadioButton radioC = (RadioButton) findViewById(R.id.radioC);
        RadioButton radioD = (RadioButton) findViewById(R.id.radioD);
        boolean correct7D = radioC.isChecked();
        if (correct7D) {
            score = score + 2;
            radioA.setChecked(false);
            radioB.setChecked(false);
            radioC.setChecked(false);
            radioD.setChecked(false);
            doubleCompleted();
        } else wrongDoubleAnswer();
    }

    public void checkAnswer8D() {
        CheckBox checkA = (CheckBox) findViewById(R.id.checkA);
        CheckBox checkB = (CheckBox) findViewById(R.id.checkB);
        CheckBox checkC = (CheckBox) findViewById(R.id.checkC);
        CheckBox checkD = (CheckBox) findViewById(R.id.checkD);
        boolean correct8DA = checkA.isChecked();
        boolean correct8DB = checkA.isChecked();
        boolean correct8DC = checkA.isChecked();
        boolean correct8DD = checkD.isChecked();
        if (correct8DA) {
            counter8D = counter8D + 1;
        }
        if (correct8DB) {
            counter8D = counter8D + 1;
        }
        if (correct8DC) {
            counter8D = counter8D + 1;
        }
        if (counter8D == 3) {
            score = score + 2;
            doubleCompleted();
        } else wrongDoubleAnswer();
        checkA.setChecked(false);
        checkB.setChecked(false);
        checkC.setChecked(false);
        checkD.setChecked(false);
    }

    public void checkAnswer9D() {
        CheckBox checkA = (CheckBox) findViewById(R.id.checkA);
        CheckBox checkB = (CheckBox) findViewById(R.id.checkB);
        CheckBox checkC = (CheckBox) findViewById(R.id.checkC);
        CheckBox checkD = (CheckBox) findViewById(R.id.checkD);
        boolean correct9DA = checkA.isChecked();
        boolean correct9DB = checkA.isChecked();
        boolean correct9DC = checkA.isChecked();
        boolean correct9DD = checkD.isChecked();
        if (correct9DA) {
            counter9D = counter9D + 1;
        }
        if (correct9DB) {
            counter9D = counter9D + 1;
        }
        if (correct9DC) {
            counter9D = counter9D + 1;
        }
        if (counter9D == 3) {
            score = score + 2;
            doubleCompleted();
        } else wrongDoubleAnswer();
        checkA.setChecked(false);
        checkB.setChecked(false);
        checkC.setChecked(false);
        checkD.setChecked(false);
    }

    public void checkAnswer10D() {
        RadioButton radioA = (RadioButton) findViewById(R.id.radioA);
        RadioButton radioB = (RadioButton) findViewById(R.id.radioB);
        RadioButton radioC = (RadioButton) findViewById(R.id.radioC);
        RadioButton radioD = (RadioButton) findViewById(R.id.radioD);
        boolean correct10D = radioC.isChecked();
        if (correct10D) {
            score = score + 2;
            radioA.setChecked(false);
            radioB.setChecked(false);
            radioC.setChecked(false);
            radioD.setChecked(false);
            doubleCompleted();
            isGameCompleted = true;
        } else wrongDoubleAnswer();
    }

    public void wrongAnswer() {
        if (mediaPlayer.isPlaying())  {
            stopPlaying();
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.wrong);
        mediaPlayer.start();
        if (skipPossibilities > 0) {
            Button skipQuestion = (Button) findViewById(R.id.skipQuestion);
            skipQuestion.setEnabled(true);
        }
        View answersField = findViewById(R.id.answers);
        TextView questionText = (TextView) findViewById(R.id.questionText);
        ImageView stageBackground = (ImageView) findViewById(R.id.stageBackground);
        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        Button skipQuestion = (Button) findViewById(R.id.skipQuestion);
        skipQuestion.setEnabled(false);
        questionText.setVisibility(VISIBLE);
        questionText.setText(R.string.wrongAnswer);
        answersField.setVisibility(View.INVISIBLE);
        confirmButton.setText(R.string.goNextStage);
        Button goForDouble = (Button) findViewById(R.id.goForDouble);
        goForDouble.setEnabled(false);
        status = 7;
        stageBackground.setImageResource(R.drawable.wronganswer);
        Toast.makeText(MainActivity.this, " WRONG ANSWER! ", Toast.LENGTH_LONG).show();
        if (stageCounter == 10) {
            status = 9;
        }
        if (score != 0) {
            score = score - 1;
        } else gameOver();
    }


    public void wrongDoubleAnswer() {
        if (skipPossibilities > 0) {
            Button skipQuestion = (Button) findViewById(R.id.skipQuestion);
            skipQuestion.setEnabled(true);
        }
        View answersField = findViewById(R.id.answers);
        TextView questionText = (TextView) findViewById(R.id.questionText);
        ImageView stageBackground = (ImageView) findViewById(R.id.stageBackground);
        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        Button skipQuestion = (Button) findViewById(R.id.skipQuestion);
        skipQuestion.setEnabled(false);
        questionText.setVisibility(VISIBLE);
        questionText.setText(R.string.wrongDoubleAnswer);
        answersField.setVisibility(View.INVISIBLE);
        confirmButton.setText(R.string.goNextStage);
        Button goForDouble = (Button) findViewById(R.id.goForDouble);
        goForDouble.setEnabled(false);
        status = 7;
        stageBackground.setImageResource(R.drawable.wronganswer);
        Toast.makeText(MainActivity.this, " WRONG DOUBLE ANSWER! \n you lost DOUBLE points!! ", Toast.LENGTH_LONG).show();
        if (stageCounter == 10) {
            isGameCompleted = true;
        }
        if (score > 1) {
            score = score - 2;
        } else gameOver();
    }

    public void gameOver() {
        if (mediaPlayer.isPlaying())  {
            stopPlaying();
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.wrong);
        mediaPlayer.start();
        Button skipQuestion = (Button) findViewById(R.id.skipQuestion);
        skipQuestion.setEnabled(false);
        View answersField = findViewById(R.id.answers);
        TextView questionText = (TextView) findViewById(R.id.questionText);
        ImageView stageBackground = (ImageView) findViewById(R.id.stageBackground);
        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        questionText.setVisibility(View.INVISIBLE);
        answersField.setVisibility(View.INVISIBLE);
        confirmButton.setText("GAME OVER");
        confirmButton.setEnabled(false);
        Button goForDouble = (Button) findViewById(R.id.goForDouble);
        goForDouble.setEnabled(false);
        Toast.makeText(MainActivity.this, " GAME OVER ", Toast.LENGTH_LONG).show();
        stageBackground.setImageResource(R.drawable.gameover);
    }

    public void showRadioButtons() {
        View radioField = findViewById(R.id.radioField);
        radioField.setVisibility(VISIBLE);
        RadioButton radioA = (RadioButton) findViewById(R.id.radioA);
        RadioButton radioB = (RadioButton) findViewById(R.id.radioB);
        RadioButton radioC = (RadioButton) findViewById(R.id.radioC);
        RadioButton radioD = (RadioButton) findViewById(R.id.radioD);
        radioA.setChecked(false);
        radioB.setChecked(false);
        radioC.setChecked(false);
        radioD.setChecked(false);
    }

    public void showCheckBox() {
        View checkFieldAB = findViewById(R.id.checkFieldAB);
        checkFieldAB.setVisibility(VISIBLE);
        View checkFieldCD = findViewById(R.id.checkFieldCD);
        checkFieldCD.setVisibility(VISIBLE);
        CheckBox checkA = (CheckBox) findViewById(R.id.checkA);
        CheckBox checkB = (CheckBox) findViewById(R.id.checkB);
        CheckBox checkC = (CheckBox) findViewById(R.id.checkC);
        CheckBox checkD = (CheckBox) findViewById(R.id.checkD);
        checkA.setChecked(false);
        checkB.setChecked(false);
        checkC.setChecked(false);
        checkC.setChecked(false);
    }

    public void hideCheckBoxes() {
        View checkFieldAB = findViewById(R.id.checkFieldAB);
        checkFieldAB.setVisibility(GONE);
        View checkFieldCD = findViewById(R.id.checkFieldCD);
        checkFieldCD.setVisibility(GONE);
    }

    public void hideRadioButtons() {
        View radioField = findViewById(R.id.radioField);
        radioField.setVisibility(GONE);
    }

    public void hideEditText() {
        EditText editTextAnswer = (EditText) findViewById(R.id.editTextAnswer);
        editTextAnswer.setVisibility(GONE);
    }

    public void showEditText() {
        EditText editTextAnswer = (EditText) findViewById(R.id.editTextAnswer);
        editTextAnswer.setVisibility(VISIBLE);
    }

    public void showStatus(View view) {
        Toast.makeText(MainActivity.this, "Player Name: \n" + userName + "\nSkip Possibilities: \n" + skipPossibilities + "\nScore: " + score, Toast.LENGTH_LONG).show();
    }

    public void pictureIntent(View view){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageButton thumbnail = (ImageButton) findViewById(R.id.thumbnail);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            thumbnail.setImageBitmap(imageBitmap);
        }
    }

    public void goForDoubleClick(View view) {
        status = 4;
        engageDoubleStage();
        Button goForDouble = (Button) findViewById(R.id.goForDouble);
        goForDouble.setEnabled(false);
    }

    public void stopPlaying() {
        mediaPlayer.reset();
        mediaPlayer.release();
    }
}
