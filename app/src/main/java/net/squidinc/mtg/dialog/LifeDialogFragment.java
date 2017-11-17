package net.squidinc.mtg.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import net.squidinc.mtg.R;

/**
 * @author AYee
 *         Choose starting life
 *         Date: 2017-11-13
 */

public class LifeDialogFragment extends DialogFragment {
  static LifeDialogFragmentListener sListener;
  static int startingLife = 0;

  @Override
  public Dialog onCreateDialog( Bundle savedInstanceState ) {
    AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
    final String[] life = getResources().getStringArray( R.array.life );
    builder.setTitle( R.string.life_title ).setItems( R.array.life, new DialogInterface.OnClickListener() {
      public void onClick( DialogInterface dialog, int which ) {
        sListener.onLifeSelected( Integer.parseInt( life[which] ) );
        dialog.dismiss();
      }
    } ).setNegativeButton( R.string.button_no, new DialogInterface.OnClickListener() {
      @Override
      public void onClick( DialogInterface dialog, int id ) {
        dialog.dismiss();
      }
    } );

    return builder.create();
  }

  public static LifeDialogFragment newInstance( int titleResource ) {
    LifeDialogFragment ldf = new LifeDialogFragment();

    Bundle args = new Bundle();
    args.putInt( "title", titleResource );
    ldf.setArguments( args );

    return ldf;
  }

  public void setOnLifeDialogFragmentListener( LifeDialogFragmentListener listener ) {
    sListener = listener;
  }
}
