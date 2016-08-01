package net.squidinc.mtg;

import android.annotation.SuppressLint;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.Random;

/**@author AYee
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView textLifeP1,textMinus5P1, textPlus5P1, textMinus1P1, textPlus1P1, textLifeP2, textMinus5P2, textPlus5P2, textMinus1P2, textPlus1P2;
    private static final Integer DEFAULT_LIFE = 20, TWO_HEADED_DEFAULT_LIFE = 30;
    private Integer player1Life, player2Life;
    private FloatingActionMenu fabMenu;
    private FloatingActionButton reset, roll, life, mana, poison;

    private boolean isOneOnOne = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        textLifeP1 = (TextView) findViewById(R.id.textLifeP1);
        textMinus5P1 = (TextView) findViewById(R.id.textMinus5P1);
        textPlus5P1 = (TextView) findViewById(R.id.textPlus5P1);
        textMinus1P1 = (TextView) findViewById(R.id.textMinus1P1);
        textPlus1P1 = (TextView) findViewById(R.id.textPlus1P1);
        textLifeP2 = (TextView) findViewById(R.id.textLifeP2);
        textMinus5P2 = (TextView) findViewById(R.id.textMinus5P2);
        textPlus5P2 = (TextView) findViewById(R.id.textPlus5P2);
        textMinus1P2 = (TextView) findViewById(R.id.textMinus1P2);
        textPlus1P2 = (TextView) findViewById(R.id.textPlus1P2);

        // Floating action bar stuff
        fabMenu = (FloatingActionMenu) findViewById( R.id.fab );
        roll = (FloatingActionButton) findViewById( R.id.fabRoll );
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int one = getRandom();
                int two = getRandom();
                Toast.makeText(getApplicationContext(), "Player 1: " + one + " Player 2: " + two, Toast.LENGTH_LONG).show();
            }
        });
        life = (FloatingActionButton) findViewById( R.id.fabLife );
        mana = (FloatingActionButton) findViewById( R.id.fabMana );
        poison = (FloatingActionButton) findViewById( R.id.fabPoison );
    }

    @Override
    public void onResume(){
      super.onResume();
      /* Player 1 life */
        if (isOneOnOne) {
            player1Life = DEFAULT_LIFE;
            player2Life = DEFAULT_LIFE;
        } else {
            player1Life = TWO_HEADED_DEFAULT_LIFE;
            player2Life = TWO_HEADED_DEFAULT_LIFE;
        }
        // Remove 5 life for Player 1 on tap
        textMinus5P1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                player1Life -= 5;
                textLifeP1.setText( player1Life.toString() );
            }
        } );
        // Add 5 life for Player 1 on tap
        textPlus5P1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                player1Life += 5;
                textLifeP1.setText( player1Life.toString() );
            }
        } );
        // Remove 1 life for Player 1 on tap
        textMinus1P1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                player1Life -= 1;
                textLifeP1.setText( player1Life.toString() );
            }
        } );
        // Add 1 life for Player 1 on tap
        textPlus1P1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                player1Life += 1;
                textLifeP1.setText( player1Life.toString() );
            }
        } );

      /* Player 2 life */
        // Remove 5 life for Player 2 on tap
        textMinus5P2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                player2Life -= 5;
                textLifeP2.setText( player2Life.toString() );
            }
        } );
        // Add 5 life for Player 2 on tap
        textPlus5P2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                player2Life += 5;
                textLifeP2.setText( player2Life.toString() );
            }
        } );
        // Remove 1 life for Player 2 on tap
        textMinus1P2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                player2Life -= 1;
                textLifeP2.setText( player2Life.toString() );
            }
        } );
        // Add 1 life for Player 2 on tap
        textPlus1P2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                player2Life += 1;
                textLifeP2.setText( player2Life.toString() );
            }
        } );
        // TODO pull life count from bundle
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu ) {
        super.onCreateOptionsMenu( menu );

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

        @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        if ( item.getItemId() == R.id.about ) {
            // TODO load sheet
            return true;
        }
        else if ( item.getItemId() == R.id.reset ) {
            // TODO alter this so that it's handling whatever life total (20, 30, 40) was picked last
            if (isOneOnOne){
                player1Life = DEFAULT_LIFE;
                player2Life = DEFAULT_LIFE;
            } else {
                player1Life = TWO_HEADED_DEFAULT_LIFE;
                player2Life = TWO_HEADED_DEFAULT_LIFE;
            }
            textLifeP1.setText(player1Life.toString());
            textLifeP2.setText(player2Life.toString());
          return true;
        }
        return super.onOptionsItemSelected( item );
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            toggleActionBar();
        }
        return true;
    }

    private void toggleActionBar() {
        android.app.ActionBar actionBar = getActionBar();

        if(actionBar != null) {
            if(actionBar.isShowing()) {
                actionBar.hide();
            }
            else {
                actionBar.show();
            }
        }
    }

    private int getRandom(){
        // Use d20 to accommodate more players, less rolls
        Random rand = new Random();
        int randNum = rand.nextInt((20 - 1) + 1) + 1;

        return randNum;
    }
}
