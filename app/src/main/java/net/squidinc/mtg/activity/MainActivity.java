package net.squidinc.mtg.activity;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gelitenight.waveview.library.WaveView;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import net.squidinc.mtg.model.Player;
import net.squidinc.mtg.R;
import net.squidinc.mtg.util.WaveHelper;
import net.squidinc.mtg.dialog.LifeDialogFragment;
import net.squidinc.mtg.dialog.LifeDialogFragmentListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author AYee
 *         Where most of the MTG happens
 */
public class MainActivity extends AppCompatActivity {
  private static final String TAG = "MainActivity";

  private TextView textLifeP1, /*textMinus5P1, textPlus5P1,*/
      textMinus1P1, textPlus1P1, textLifeP2, /*textMinus5P2, textPlus5P2,*/
      textMinus1P2, textPlus1P2;
  private FloatingActionMenu fabMenu;
  private FloatingActionButton reset, roll, life, mana, poison;

  /* Players */
  private List<Player> players = new ArrayList<>( 2 ); /* 2+ Players to start */

  /* Life */
  private static final Integer DEFAULT_LIFE = 20;
  private Integer startingLife = 20;
  private Integer player1Life, player2Life;

  /* Poisonnnn! */
  private WaveHelper mWaveHelper1, mWaveHelper2;
  private int borderColor = Color.parseColor( "#44ffffff" );
  private int borderWidth = 10;
  private WaveView wave1, wave2;
  private boolean isPoison = false;

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );

    setContentView( R.layout.activity_main );

    textLifeP1 = (TextView) findViewById( R.id.textLifeP1 );
    //    textMinus5P1 = (TextView) findViewById( R.id.textMinus5P1 );
    //    textPlus5P1 = (TextView) findViewById( R.id.textPlus5P1 );
    textMinus1P1 = (TextView) findViewById( R.id.textMinus1P1 );
    textPlus1P1 = (TextView) findViewById( R.id.textPlus1P1 );
    textLifeP2 = (TextView) findViewById( R.id.textLifeP2 );
    //    textMinus5P2 = (TextView) findViewById( R.id.textMinus5P2 );
    //    textPlus5P2 = (TextView) findViewById( R.id.textPlus5P2 );
    textMinus1P2 = (TextView) findViewById( R.id.textMinus1P2 );
    textPlus1P2 = (TextView) findViewById( R.id.textPlus1P2 );

    // Players
    Player one = new Player( "One", DEFAULT_LIFE, 0, 0, 0 );
    Player two = new Player( "Two", DEFAULT_LIFE, 0, 0, 0 );
    players.add( one );
    players.add( two );

    // Life TODO refactor
    player1Life = DEFAULT_LIFE;
    player2Life = DEFAULT_LIFE;

    // Poison
    wave1 = (WaveView) findViewById( R.id.wave1 );
    wave1.setBorder( borderWidth, borderColor );
    wave2 = (WaveView) findViewById( R.id.wave2 );
    wave2.setBorder( borderWidth, borderColor );

    mWaveHelper1 = new WaveHelper( wave1, 4 );
    mWaveHelper2 = new WaveHelper( wave2, 0 );

    wave1.setShapeType( WaveView.ShapeType.SQUARE );
    mWaveHelper1.start();

    // Floating action bar stuff
    fabMenu = (FloatingActionMenu) findViewById( R.id.fab );
    roll = (FloatingActionButton) findViewById( R.id.fabRoll );
    roll.setOnClickListener( new View.OnClickListener() {
      @Override
      public void onClick( View v ) {
        int one = getRandom();
        int two = getRandom();
        Toast.makeText( getApplicationContext(), "Player 1: " + one + " \nPlayer 2: " + two, Toast.LENGTH_LONG ).show();
      }
    } );
    life = (FloatingActionButton) findViewById( R.id.fabLife );
    life.setOnClickListener( new View.OnClickListener() {
      @Override
      public void onClick( View v ) {
        Log.v( TAG, "Limes life tapped" );
        setStartingLife();
      }
    } );
    mana = (FloatingActionButton) findViewById( R.id.fabMana );
    poison = (FloatingActionButton) findViewById( R.id.fabPoison );
  }

  @Override
  public void onResume() {
    super.onResume();
      /* Player 1 life */
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
    // TODO pull life count from bundle, maybe later from persistent store as well
  }

  @Override
  public boolean onCreateOptionsMenu( Menu menu ) {
    super.onCreateOptionsMenu( menu );

    getMenuInflater().inflate( R.menu.main, menu );
    return true;
  }

  @Override
  public boolean onOptionsItemSelected( MenuItem item ) {
    if ( item.getItemId() == R.id.about ) {
      // TODO load sheet for ratings, feedback, maybe use info?
      return true;
    } else if ( item.getItemId() == R.id.reset ) {
      // TODO alter this so that it's handling whatever life total (20, 30, 40) was picked last
      textLifeP1.setText( startingLife.toString() );
      textLifeP2.setText( startingLife.toString() );
      return true;
    }
    return super.onOptionsItemSelected( item );
  }

  /* This will get used eventually, but not today! */
  /*
  @Override
  public boolean onTouchEvent( MotionEvent event ) {
    if ( event.getAction() == MotionEvent.ACTION_DOWN ) {
      toggleActionBar();
    }
    return true;
  }

  private void toggleActionBar() {
    android.app.ActionBar actionBar = getActionBar();

    if ( actionBar != null ) {
      if ( actionBar.isShowing() ) {
        actionBar.hide();
      } else {
        actionBar.show();
      }
    }
  }
  */

  /* Adds or subtracts life in quantities of 1 or 5 */
  private void calculateLife() {

  }

  /**
   * Adds or subtracts poison. This maxes out at 10 but users can add or subtract as they please. Updates the Player
   * record accordingly. For now, poison updates in increments of one counter per call.
   * TODO implement arbitrary increase / decrease (in the case of the rare card that allows a decrease or reset)
   */
  private void calculatePoison( Player player ) {
    player.setPoisonTotal( player.getPoisonTotal() + 1 );

    // Update the total in the Player object
    if ( players.contains( player ) ) {
      int idx = players.indexOf( player );
      players.set( idx, player );

    }
  }

  private int getRandom() {
    // Use d20 to accommodate more players, less rolls
    Random rand = new Random();
    int randNum = rand.nextInt( ( 20 - 1 ) + 1 ) + 1;

    return randNum;
  }

  /* Set the default life for each Player (via the FAB option) */
  private void setStartingLife() {
    LifeDialogFragment lifeDialogFragment = LifeDialogFragment.newInstance( R.string.life_title );
    lifeDialogFragment.setOnLifeDialogFragmentListener( new LifeDialogFragmentListener() {
      @Override
      public void onLifeSelected( int defaultLifeSelected ) {
        textLifeP1.setText( String.valueOf( defaultLifeSelected ) );
        textLifeP2.setText( String.valueOf( defaultLifeSelected ) );
        startingLife = defaultLifeSelected;
      }
    } );
    lifeDialogFragment.show( getSupportFragmentManager(), getResources().getString( R.string.tag_life_dialog ) );
  }

  private Activity getActivity( View v ) {
    Context context = v.getContext();
    while ( context instanceof ContextWrapper ) {
      if ( context instanceof Activity ) {
        return (Activity) context;
      }
      context = ( (ContextWrapper) context ).getBaseContext();
    }
    return null;
  }
}


