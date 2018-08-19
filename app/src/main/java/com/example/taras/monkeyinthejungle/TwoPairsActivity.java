package com.example.taras.monkeyinthejungle;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.Collections;

public class TwoPairsActivity extends AppCompatActivity implements OnClickListener {

    ImageView imgView_11, imgView_12, imgView_13, imgView_14, imgView_21, imgView_22, imgView_23, imgView_24;

    //Array for images
    Integer[] cardArray = {101, 102, 103, 104, 201, 202, 203, 204};

    //Images
    int img101, img102, img103, img104, img201, img202, img203, img204;

    int firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNum = 1;
    int points;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_pairs);

        imgView_11 = (ImageView) findViewById(R.id.img_11);
        imgView_12 = (ImageView) findViewById(R.id.img_12);
        imgView_13 = (ImageView) findViewById(R.id.img_13);
        imgView_14 = (ImageView) findViewById(R.id.img_14);
        imgView_21 = (ImageView) findViewById(R.id.img_21);
        imgView_22 = (ImageView) findViewById(R.id.img_22);
        imgView_23 = (ImageView) findViewById(R.id.img_23);
        imgView_24 = (ImageView) findViewById(R.id.img_24);

        imgView_11.setTag("0");
        imgView_12.setTag("1");
        imgView_13.setTag("2");
        imgView_14.setTag("3");
        imgView_21.setTag("4");
        imgView_22.setTag("5");
        imgView_23.setTag("6");
        imgView_24.setTag("7");

        //Load the images
        loadCardImages();

        //Shuffle the cards
        Collections.shuffle(Arrays.asList(cardArray));

        imgView_11.setOnClickListener(this);
        imgView_12.setOnClickListener(this);
        imgView_13.setOnClickListener(this);
        imgView_14.setOnClickListener(this);
        imgView_21.setOnClickListener(this);
        imgView_22.setOnClickListener(this);
        imgView_23.setOnClickListener(this);
        imgView_24.setOnClickListener(this);

    }

    private void loadCardImages() {
        img101 = R.drawable.img_11;
        img102 = R.drawable.img_12;
        img103 = R.drawable.img_13;
        img104 = R.drawable.img_14;
        img201 = R.drawable.img_21;
        img202 = R.drawable.img_22;
        img203 = R.drawable.img_23;
        img204 = R.drawable.img_24;
    }

    private void runCard(ImageView imgView, int theCard) {
        System.out.println("theCard " + theCard);
        if(cardArray[theCard] == 101) {
            imgView.setImageResource(img101);
        } else if(cardArray[theCard] == 101) {
            imgView.setImageResource(img101);
        } else if(cardArray[theCard] == 102) {
            imgView.setImageResource(img102);
        } else if(cardArray[theCard] == 103) {
            imgView.setImageResource(img103);
        } else if(cardArray[theCard] == 104) {
            imgView.setImageResource(img104);
        } else if(cardArray[theCard] == 201) {
            imgView.setImageResource(img201);
        } else if(cardArray[theCard] == 202) {
            imgView.setImageResource(img202);
        } else if(cardArray[theCard] == 203) {
            imgView.setImageResource(img203);
        } else if(cardArray[theCard] == 204) {
            imgView.setImageResource(img204);
        }

        //Which image is selected and save it in temp variable 
        if(cardNum == 1) {
            firstCard = cardArray[theCard];
            if(firstCard > 200) {
                firstCard = firstCard - 100;
            }
            cardNum = 2;
            clickedFirst = theCard;

            imgView.setEnabled(false);
        } else if (cardNum == 2) {
            secondCard = cardArray[theCard];
            if(secondCard > 200) {
                secondCard = secondCard - 100;
            }
            cardNum = 1;
            clickedSecond = theCard;

            imgView_11.setEnabled(false);
            imgView_12.setEnabled(false);
            imgView_13.setEnabled(false);
            imgView_14.setEnabled(false);
            imgView_21.setEnabled(false);
            imgView_22.setEnabled(false);
            imgView_23.setEnabled(false);
            imgView_24.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    calculate();
                }
            }, 1000);
        }
    }

    private void calculate() {
        // If images are eqaul remove them and point
        if(firstCard == secondCard) {
            if(clickedFirst == 0) {
                imgView_11.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 1) {
                imgView_12.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 2) {
                imgView_13.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 3) {
                imgView_14.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 4) {
                imgView_21.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 5) {
                imgView_22.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 6) {
                imgView_23.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 7) {
                imgView_24.setVisibility(View.INVISIBLE);
            }

            if(clickedSecond == 0) {
                imgView_11.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 1) {
                imgView_12.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 2) {
                imgView_13.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 3) {
                imgView_14.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 4) {
                imgView_21.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 5) {
                imgView_22.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 6) {
                imgView_23.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 7) {
                imgView_24.setVisibility(View.INVISIBLE);
            }
            points++;

        } else {
            imgView_11.setImageResource(R.drawable.img_back);
            imgView_12.setImageResource(R.drawable.img_back);
            imgView_13.setImageResource(R.drawable.img_back);
            imgView_14.setImageResource(R.drawable.img_back);
            imgView_21.setImageResource(R.drawable.img_back);
            imgView_22.setImageResource(R.drawable.img_back);
            imgView_23.setImageResource(R.drawable.img_back);
            imgView_24.setImageResource(R.drawable.img_back);
        }

        imgView_11.setEnabled(true);
        imgView_12.setEnabled(true);
        imgView_13.setEnabled(true);
        imgView_14.setEnabled(true);
        imgView_21.setEnabled(true);
        imgView_22.setEnabled(true);
        imgView_23.setEnabled(true);
        imgView_24.setEnabled(true);

    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.img_11:
                int theCard = Integer.parseInt((String) view.getTag());
                runCard(imgView_11, theCard);
                break;
            case R.id.img_12:
                theCard = Integer.parseInt((String) view.getTag());
                runCard(imgView_12, theCard);
                break;
            case R.id.img_13:
                theCard = Integer.parseInt((String) view.getTag());
                runCard(imgView_13, theCard);
                break;
            case R.id.img_14:
                theCard = Integer.parseInt((String) view.getTag());
                runCard(imgView_14, theCard);
                break;
            case R.id.img_21:
                theCard = Integer.parseInt((String) view.getTag());
                runCard(imgView_21, theCard);
                break;
            case R.id.img_22:
                theCard = Integer.parseInt((String) view.getTag());
                runCard(imgView_22, theCard);
                break;
            case R.id.img_23:
                theCard = Integer.parseInt((String) view.getTag());
                runCard(imgView_23, theCard);
                break;
            case R.id.img_24:
                theCard = Integer.parseInt((String) view.getTag());
                runCard(imgView_24, theCard);
                break;
            default:
                Log.e("SNH", "Unknown Image");
                break;
        }
    }
}
