package com.example.taras.monkeyinthejungle.game_frames;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.taras.monkeyinthejungle.R;
import com.example.taras.monkeyinthejungle.games.TwoPairs;

import java.util.Arrays;
import java.util.Collections;

public class TwoPairsFragment extends Fragment implements OnClickListener {

    TwoPairs twopairs = new TwoPairs();

    ImageView imgView_11,
            imgView_12,
            imgView_13,
            imgView_14,
            imgView_21,
            imgView_22,
            imgView_23,
            imgView_24;

    //Images
    int img101,
            img102,
            img103,
            img104,
            img201,
            img202,
            img203,
            img204;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.two_pairs_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        twopairs.shuffleArray();

        imgView_11 = (ImageView) getView().findViewById(R.id.img_11);
        imgView_12 = (ImageView) getView().findViewById(R.id.img_12);
        imgView_13 = (ImageView) getView().findViewById(R.id.img_13);
        imgView_14 = (ImageView) getView().findViewById(R.id.img_14);
        imgView_21 = (ImageView) getView().findViewById(R.id.img_21);
        imgView_22 = (ImageView) getView().findViewById(R.id.img_22);
        imgView_23 = (ImageView) getView().findViewById(R.id.img_23);
        imgView_24 = (ImageView) getView().findViewById(R.id.img_24);

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

        switch (twopairs.getCardArrayIndex(theCard)){
            case 101:
                imgView.setImageResource(img101);
                break;
            case 102:
                imgView.setImageResource(img102);
                break;
            case 103:
                imgView.setImageResource(img103);
                break;
            case 104:
                imgView.setImageResource(img104);
                break;
            case 201:
                imgView.setImageResource(img201);
                break;
            case 202:
                imgView.setImageResource(img202);
                break;
            case 203:
                imgView.setImageResource(img203);
                break;
            case 204:
                imgView.setImageResource(img204);
                break;
            default:
                Log.e("SNH", "Unknown Value");
                break;
        }

        //Which image is selected and save it in temp variable 
        if(twopairs.getCardNum() == 1) {
            twopairs.setFirstCard(twopairs.getCardArrayIndex(theCard));
            if(twopairs.getFirstCard() > 200) {
                twopairs.setFirstCard(twopairs.getFirstCard() - 100);
            }
            twopairs.setCardNum(2);
            twopairs.setClickedFirst(theCard);
            imgView.setEnabled(false);
        } else if (twopairs.getCardNum() == 2) {
            twopairs.setSecondCard(twopairs.getCardArrayIndex(theCard));
            if(twopairs.getSecondCard() > 200) {
                twopairs.setSecondCard(twopairs.getSecondCard() - 100);
            }
            twopairs.setCardNum(1);
            twopairs.setClickedSecond(theCard);

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
        if(twopairs.getFirstCard() == twopairs.getSecondCard()) {

            switch (twopairs.getClickedFirst()){
                case 0:
                    imgView_11.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    imgView_12.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    imgView_13.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    imgView_14.setVisibility(View.INVISIBLE);
                    break;
                case 4:
                    imgView_21.setVisibility(View.INVISIBLE);
                    break;
                case 5:
                    imgView_22.setVisibility(View.INVISIBLE);
                    break;
                case 6:
                    imgView_23.setVisibility(View.INVISIBLE);
                    break;
                case 7:
                    imgView_24.setVisibility(View.INVISIBLE);
                    break;
                default:
                    Log.e("SNH", "Unknown Value");
                    break;
            }

            switch (twopairs.getClickedSecond()){
                case 0:
                    imgView_11.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    imgView_12.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    imgView_13.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    imgView_14.setVisibility(View.INVISIBLE);
                    break;
                case 4:
                    imgView_21.setVisibility(View.INVISIBLE);
                    break;
                case 5:
                    imgView_22.setVisibility(View.INVISIBLE);
                    break;
                case 6:
                    imgView_23.setVisibility(View.INVISIBLE);
                    break;
                case 7:
                    imgView_24.setVisibility(View.INVISIBLE);
                    break;
                default:
                    Log.e("SNH", "Unknown Value");
                    break;
            }

            twopairs.setPoints();
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
