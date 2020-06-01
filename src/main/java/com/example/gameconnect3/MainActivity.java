package com.example.gameconnect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 0: yellow 1:red 2: empty
    int[] gamestatus = {2,2,2,2,2,2,2,2,2};
    int [][] winningPositions ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7,},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer =0;
    boolean gameactive = true;
    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gamestatus[tappedCounter]==2 && gameactive) {
            gamestatus[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yelloe);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(200);
            for (int[] winningPositions : winningPositions) {
                if (gamestatus[winningPositions[0]] == gamestatus[winningPositions[1]] && gamestatus[winningPositions[1]] == gamestatus[winningPositions[2]] && gamestatus[winningPositions[0]] != 2) {
                   gameactive=false;
                    if (activePlayer == 1) {
                        Toast.makeText(this, "Yellow has won", Toast.LENGTH_SHORT).show();
                        Button playAgainButton =(Button)findViewById(R.id.playagainbutton);
                        TextView winnerTextView = (TextView)findViewById(R.id.winnerTextView);
                        winnerTextView.setText("Yellow has won");
                        playAgainButton.setVisibility(View.VISIBLE);
                        winnerTextView.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(this, "Red has won", Toast.LENGTH_SHORT).show();
                        Button playagainbutton =(Button)findViewById(R.id.playagainbutton);
                        TextView winnertextview = (TextView)findViewById(R.id.winnerTextView);
                        winnertextview.setText("Red has won");
                        playagainbutton.setVisibility(View.VISIBLE);
                        winnertextview.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }
    public void playAgain(View view)
    {
        Button playAgainButton =(Button)findViewById(R.id.playagainbutton);
        TextView winnerTextView = (TextView)findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridlayout =(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0; i<gridlayout.getChildCount(); i++) {
        ImageView counter = (ImageView) gridlayout.getChildAt(i);
        counter.setImageDrawable(null);

    }
        for(int i =0;i<gamestatus.length;i++) {
            gamestatus[i] = 2;
        }
         activePlayer =0;
         gameactive = true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button playagainbutton =(Button)findViewById(R.id.playagainbutton);
        TextView winnertextview = (TextView)findViewById(R.id.winnerTextView);

        playagainbutton.setVisibility(View.INVISIBLE);
        winnertextview.setVisibility(View.INVISIBLE);
    }
}
