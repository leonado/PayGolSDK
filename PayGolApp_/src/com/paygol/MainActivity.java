package com.paygol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import libreria.paygol.*;



@SuppressLint("NewApi")
public class MainActivity extends Activity  implements OnClickListener{


String url="empty";
String parametros[] = null;
String serviceID="1057";
String currency="EUR";
String price="0.35";
String custom="XXXX";
String unique="unique";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Buscamos el botón por su Id  
        View buttontest = findViewById(R.id.button1);  
        buttontest .setOnClickListener(this); 
        
       
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	@Override
	public void onClick(View v) {
				
		
			
		TextView resultado1 = (TextView)findViewById(R.id.textView2);
		TextView resultado3 = (TextView)findViewById(R.id.textView1); 
		
		
		/*Save in unique the IMEI of device*/
		PayGolSDKConfig config = new PayGolSDKConfig();		
		unique=config.getIMEI3((TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE));
		
		
	
		//Libreria nuevo = new Libreria("1057","EUR","0.35","NL","XXXX","en","31699999999");
		Libreria nuevo = new Libreria(serviceID,
									currency,
									price,
									"NL",
									custom,
									"languege",
									unique
									);
		parametros=nuevo.readParameters();
		
				
		Toast.makeText(getBaseContext(), "---------request parameters-------", Toast.LENGTH_SHORT).show();
		Toast.makeText(getBaseContext(), "Service ID = "+nuevo.getServiceId(), Toast.LENGTH_SHORT).show();
    	Toast.makeText(getBaseContext(), "Currency = "  +nuevo.getCurrency(), Toast.LENGTH_SHORT).show(); 
    	Toast.makeText(getBaseContext(), "Price = " 	+nuevo.getPrice(), Toast.LENGTH_SHORT).show();  
    	Toast.makeText(getBaseContext(), "Country = "	+nuevo.getCountry(), Toast.LENGTH_SHORT).show(); 
    	Toast.makeText(getBaseContext(), "Custom= " 	+nuevo.getCustom(), Toast.LENGTH_SHORT).show();  
    	Toast.makeText(getBaseContext(), "Language = " 	+nuevo.getLanguage(), Toast.LENGTH_SHORT).show();
    	Toast.makeText(getBaseContext(), "Unique= " 	+nuevo.getUnique(), Toast.LENGTH_SHORT).show(); 
    	
    	String space= " ";
		Toast.makeText(getBaseContext(), "---------SMS-------", Toast.LENGTH_LONG).show();
		Toast.makeText(getBaseContext(), "number= " 	+parametros[4], Toast.LENGTH_LONG).show(); 
		Toast.makeText(getBaseContext(), "message = " 	+parametros[5]+space+parametros[6], Toast.LENGTH_LONG).show();
		
			
		resultado1.setText(parametros[0]);		
		
		
		
//		Toast.makeText(getBaseContext(), "IMEI= " +getIMEI(), Toast.LENGTH_LONG).show();
//		Toast.makeText(getBaseContext(), "Country= " +getCountry(), Toast.LENGTH_LONG).show();
//		Toast.makeText(getBaseContext(), "Language= " +getDispLang(), Toast.LENGTH_LONG).show();
//		Toast.makeText(getBaseContext(), "Language ISO3= " +getDispLangISO(), Toast.LENGTH_LONG).show();
		
		if(parametros[9]!=null){
			FragmentManager fragmentManager = getFragmentManager();
			 
	     //ConfirmationDialog dialogo = new ConfirmationDialog(parametros,parametros[9]+":"+"0.35"+" "+parametros[2],parametros[7],parametros[8]);
	     ConfirmationDialog dialogo = new ConfirmationDialog(parametros,
												    		 parametros[9]+":"+parametros[1]+" "+parametros[2],
												    		 parametros[7],
												    		 parametros[8]);
	        dialogo.show(fragmentManager, "tagAlerta");
		
		
		}else{
			FragmentManager fragmentManager = getFragmentManager();
			InfoDialog dialogo = new InfoDialog();
		        dialogo.show(fragmentManager, "tagAlerta");
			
		}
        			
		
		
		
	}
	
	
	public String getIMEI(){
		String IMEI=null;
		TelephonyManager telMgr;
        telMgr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
        IMEI = telMgr.getDeviceId();		
		return IMEI;
	}
	public String getDispLang(){
    	String Language=null;
    	Language=Locale.getDefault().getDisplayLanguage();
    	return Language;
    }
	public String getCountry(){ 
    	String Country=null;
    	Country=Locale.getDefault().getCountry();
    	return Country;
    }
	public String getISO3Country(){
    	String Country=null;
    	Country=Locale.getDefault().getISO3Country();
    	return Country;
    }
	public String getISO3Language(){
    	String Language=null;
    	Language=Locale.getDefault().getISO3Language();
    	return Language;
    }
	
	
    
}