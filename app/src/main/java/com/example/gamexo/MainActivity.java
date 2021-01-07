package com.example.gamexo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // red:1 , yellow:0 , 2: empty

    int [] eachImageState = {2,2,2,2,2,2,2,2,2};                                                    // each image if equals to 2 then it's empty according to the eachImageState[imageId]
    int [][] aimeToWin = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int bol = 1;
    boolean wins = true;
    public void insertNow(View view){
        ImageView imageView = (ImageView) view;                                                     // to make this specific imageView equals this specific view on the gradle
        int imageID = Integer.parseInt(imageView.getTag().toString());                              // to determine the id of each imageView

        if(eachImageState[imageID] ==2 && wins) {
            if (bol == 1) {
                imageView.setImageResource(R.drawable.red);
                eachImageState[imageID] = bol;                                                      // to make each image[id] equal the color of the image
                bol = 0;
            } else {
                imageView.setImageResource(R.drawable.yellow);
                eachImageState[imageID] = bol;                                                      // to make each image[id] equal the color of the image
                bol = 1;
            }
            imageView.setTranslationY(-1500);
            imageView.animate().translationYBy(1500).setDuration(300);

        TextView textView2 = (TextView)findViewById(R.id.winnerPlayer);
        Button btn = (Button)findViewById(R.id.button);
        for(int []winningPosition : aimeToWin)                                                      // each winningPosition equals the first index of each entry array of the main one
        {
            if(eachImageState[winningPosition[0]] == eachImageState[winningPosition[1]] &&
                    eachImageState[winningPosition[1]] == eachImageState[winningPosition[2]] && eachImageState[winningPosition[0]]!=2)
            {
                // someone has won
                textView2.setVisibility(View.VISIBLE);
                wins =false;
                String winner = "";
                if(bol == 0){
                    winner = "Red";
                    textView2.setTextColor(Color.RED);
                }else if(bol == 1){
                    winner = "Yellow";
                    textView2.setTextColor(Color.YELLOW);
                }
                textView2.setText(winner + " has won ");
                btn.setVisibility(View.VISIBLE);
            }
        }
        }else if (!wins) {
            Toast.makeText(this , "The game has ended, try another one :)"  ,Toast.LENGTH_SHORT).show();
        }
    }                                                           // inserting the new images for playing the game


    public void retry(View view){
        Button btn = (Button)findViewById(R.id.button);
        TextView textView2 = (TextView)findViewById(R.id.winnerPlayer);
        textView2.setVisibility(View.INVISIBLE);
        btn.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {                                    // looping through all the gridLayouts items
            ImageView count = (ImageView) gridLayout.getChildAt(i);
            count.setImageDrawable(null);                                                     // removing all the images resources
        }

        for(int i =0 ; i<eachImageState.length ; i++){
            eachImageState[i] = 2;
        }                                                                                         // each image if equals to 2 then it's empty according to the eachImageState[imageId]
         bol =1;
         wins = true;
    }                                                               // onclick method to restart the game form beginning and start a new game


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}