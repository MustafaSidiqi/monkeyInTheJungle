package com.example.taras.monkeyinthejungle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.Collections;

public class TwoPairsActivity extends AppCompatActivity implements OnClickListener {

    ImageView img_11, img_12, img_13, img_14, img_21, img_22, img_23, img_24;

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

        img_11 = (ImageView) findViewById(R.id.img_11);
        img_12 = (ImageView) findViewById(R.id.img_12);
        img_13 = (ImageView) findViewById(R.id.img_13);
        img_14 = (ImageView) findViewById(R.id.img_14);
        img_21 = (ImageView) findViewById(R.id.img_21);
        img_22 = (ImageView) findViewById(R.id.img_22);
        img_23 = (ImageView) findViewById(R.id.img_23);
        img_24 = (ImageView) findViewById(R.id.img_24);

        img_11.setTag("1");
        img_12.setTag("2");
        img_13.setTag("3");
        img_14.setTag("4");
        img_21.setTag("5");
        img_22.setTag("6");
        img_23.setTag("7");
        img_24.setTag("8");

        //Load the images
        loadCardImages();

        //Shuffle the cards
        Collections.shuffle(Arrays.asList(cardArray));

        img_11.setOnClickListener(this);
        img_12.setOnClickListener(this);
        img_13.setOnClickListener(this);
        img_14.setOnClickListener(this);
        img_21.setOnClickListener(this);
        img_22.setOnClickListener(this);
        img_23.setOnClickListener(this);
        img_24.setOnClickListener(this);

        

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

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.img_11:
                int theCard = Integer.parseInt((String) view.getTag());
                runCard(img_11, theCard);
                break;
            case R.id.img_12:
                theCard = Integer.parseInt((String) view.getTag());
                runCard(img_12, theCard);
                break;
            case R.id.img_13:
                theCard = Integer.parseInt((String) view.getTag());
                runCard(img_13, theCard);
                break;
            case R.id.img_14:
                theCard = Integer.parseInt((String) view.getTag());
                runCard(img_14, theCard);
                break;
            case R.id.img_21:
                theCard = Integer.parseInt((String) view.getTag());
                runCard(img_21, theCard);
                break;
            case R.id.img_22:
                theCard = Integer.parseInt((String) view.getTag());
                runCard(img_22, theCard);
                break;
            case R.id.img_23:
                theCard = Integer.parseInt((String) view.getTag());
                runCard(img_23, theCard);
                break;
            case R.id.img_24:
                theCard = Integer.parseInt((String) view.getTag());
                runCard(img_24, theCard);
                break;
            default:
                Log.e("SNH", "Unknown Image");
                break;
        }
    }

    private void runCard(ImageView img, int theCard) {
    }
}
