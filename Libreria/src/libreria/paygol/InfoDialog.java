package libreria.paygol;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

@SuppressLint("NewApi")
public class InfoDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
 
    	AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
 
        builder.setMessage("Error al obtener parametros para crear SMS")
               .setTitle("Informaci�n")
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       dialog.cancel();
                   }
               });
 
        return builder.create();
    }
}