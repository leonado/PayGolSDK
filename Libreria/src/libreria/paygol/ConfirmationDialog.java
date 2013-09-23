package libreria.paygol;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint({ "NewApi", "ValidFragment" })
public class ConfirmationDialog extends DialogFragment {
	String Message;
	String Accept;
	String Cancel;
	String Params[];
	Boolean Payment=false;
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
 
    	AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
 
        builder.setMessage(Message)
        .setTitle("Paygol")
        .setPositiveButton(Accept, new DialogInterface.OnClickListener()  {
               public void onClick(DialogInterface dialog, int id) {
                    Log.i("Dialog", "Confirmation Accepted.");
                    	if(Payment)accept(Params);
                    	dialog.cancel();
                   }
			
               })
        .setNegativeButton(Cancel, new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
                        Log.i("Dialog", "Confirmation Canceled.");
                        dialog.cancel();
                   }
               });
 
        return builder.create();
    }
    
    
    /**
     * ConfirmationDialog Constructor
     */
    public ConfirmationDialog(){}
    /**
     * ConfirmationDialog Constructor
     * 
     * @param message
     * 			The message in Dialog Interface
     * @param accept
     * 			The accept text in Dialog Interface
     * @param cancel
     * 			The cancel text in Dialog Interface
     */
    public ConfirmationDialog(String message, String accept, String cancel){
    	this.Message=message;
    	this.Accept=accept;
    	this.Cancel=cancel;
    	
    }
    /**
     * ConfirmationDialog Constructor
     * 
     * @param params
     * 			The parameters for to send SMS
     * @param message
     * 			The message in Dialog Interface
     * @param accept
     * 			The accept text in Dialog Interface
     * @param cancel
     * 			The cancel text in Dialog Interface    * 
     * 
     * 
     */
    public ConfirmationDialog(String[] params,String message, String accept, String cancel){
    	this.Params=params;
    	this.Message=message;
    	this.Accept=accept;
    	this.Cancel=cancel;
    	this.Payment=true;
    	
    }
    
    /**
     * Accept Function
     * 
     * @param params
     * 			The parameters for to send SMS
     * 
     *  This Function send the parameters to payment function in PayGolSDK
     */
    public void accept(String[] params) {
		// TODO Auto-generated method stub
    	Libreria accept = new Libreria();
    	if(params!=null){
    		accept.payment(params);
    		Log.i("Accept", "payment satisfactory.");
    		}else{
    			Log.i("Error", "error in parameters ");
    		}   	
    	
		
	}
}