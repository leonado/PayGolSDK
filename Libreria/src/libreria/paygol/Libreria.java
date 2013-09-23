package libreria.paygol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.jar.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.telephony.SmsManager;

public class Libreria extends Activity {
	PayGolSDKConfig configIMEI;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 configIMEI = new PayGolSDKConfig(this);
		 
		//Buscamos el botón por su Id  
		//  View buttontest = findViewById(R.id.accept);  
		//   buttontest.setOnClickListener(this);  
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private String service_id 	= "empty";
    private String price 		= "empty";
    private String currency 	= "empty";
    private String country 		= "empty";
    private String language 	= "empty";
    private String custom 		= "empty";
    private String unique 		= "empty";
    static final String BASE_HTTP_PAYMENT = "http://www.paygol.com/ws/mobile/request";
    
    public Libreria(){}

    /**
     * PayGolSDKProduct constructor.
     *
     * @param   service_id
     *            The serveice_id obtained from PayGol.
     * @param   price
     *            The price of the payment.
     * @param   currency
     *            The currency of the payment.
     */
    public Libreria(String service_id, 
                            String price, 
                            String currency) {
        this.service_id = service_id;
        this.price = price;
        this.currency = currency;
        
    }
    /**
     * PayGolSDKProduct constructor.
     *
     * @param   service_id
     *            The serveice_id obtained from PayGol.
     * @param   price
     *            The price of the payment.
     * @param   currency
     *            The currency of the payment.
     * @param   language
     *            The language of the payer.
     * @param   custom
     *            The custom of the payer.
     */
    public Libreria(String service_id,
		            String price, 
		            String currency,
		            String language,
		            String custom) {
    	
		this.service_id = service_id;
		this.price = price;
		this.currency = currency;
		this.language=language;
		this.custom=custom;
}
    /**
     * PayGolSDKProduct constructor.
     *
     * @param   service_id
     *            The serveice_id obtained from PayGol.
     * @param   price
     *            The price of the payment.
     * @param   currency
     *            The currency of the payment.
     * @param   language
     *            The language of the payer.
     * @param   custom
     *            The custom of the payer.
     */
    public Libreria(String service_id,
		            String currency, 
		            String price,
		            String country,
		            String custom,
		            String language,
		            String unique) {
    	
		this.service_id = service_id;
		this.price = price;
		this.currency = currency;
		this.language=language;
		this.custom=custom;
		this.unique=unique;
		this.country=country;
}
    

    /**
     * Getter function for 'service_id'. 
     *
     * @returns  Value of 'service_id'.
     */
    public String getServiceId() {
        return this.service_id;
    }

    /**
     * Getter function for 'price'. 
     *
     * @returns  Value of 'price'.
     */
    public String getPrice() {
        return this.price;
    }

    /**
     * Getter function for 'currency'. 
     *
     * @returns  Value of 'currency'.
     */
    public String getCurrency() {
        return this.currency;
    }
    /**
     * Getter function for 'country'. 
     *
     * @returns  Value of 'country'.
     */
    public String getCountry() {
        return this.country;
    }
    /**
     * Getter function for 'custom'. 
     *
     * @returns  Value of 'custom'.
     */
    public String getCustom() {
        return this.custom;
    }
    /**
     * Getter function for 'language'. 
     *
     * @returns  Value of 'language'.
     */
    public String getLanguage() {
        return this.language;
    }
    /**
     * Getter function for 'unique'. 
     *
     * @returns  Value of 'unique'.
     */
    public String getUnique() {
        return this.unique;
    } 
    
    /**
     * Setter function for 'ServiceId'
     * 
     * @param ServiceId
     */
    public void setServiceId(String ServiceId)
    {
            this.service_id = ServiceId;
    }
    /**
     * Setter function for 'Price'
     * 
     * @param Price
     */
    public void setPrice(String Price)
    {
            this.price = Price;
    }
    
    /**
     * Setter function for 'Currency'
     * 
     * @param Currency
     */
    public void setCurrency(String Currency)
    {
            this.currency = Currency;
    }
    /**
     * Setter function for 'Country'
     * 
     * @param Country
     */
    public void setCountry(String Country)
    {
            this.country = Country;
    }
    /**
     * Setter function for 'Custom'
     * 
     * @param Custom
     */
    public void setCustom(String Custom)
    {
            this.custom = Custom;
    }
    /**
     * Setter function for 'Language'
     * 
     * @param Language
     */
    public void setLanguage(String Language)
    {
            this.language = Language;
    }
    /**
     * Setter function for 'Unique'
     * 
     * @param Unique
     */
    public void setUnique(String Unique)
    {
            this.unique = Unique;
    }

    /**
     * Payment Function
     * 
     * This function create the message for make the payment
     * 
     * @param parameters
     * 
     * parameters[4] = Number Phone, For example: 3555
     * parameters[5] = Text Message, For example: GOL
     * parameters[6] = Number in Message, For example: 8455884
     * 
     */
    public void  payment(String[] parameters){
    	String space=" ";    	
    	
    	//sendSMS(parameters[4],"FAN");  
    	sendSMS(parameters[4],parameters[5]+space+parameters[6]);    	
    	
    }
    
    /**
     * SendSMS Function
     * 
     * @param numberphone
     * 			The number phone for to send SMS
     * @param msg
     * 			The message for to send SMS
     * 
     * SendSMS Function send SMS to number indicated
     */
    public void sendSMS(String numberphone, String msg){
    	SmsManager sms = SmsManager.getDefault();
    	sms.sendTextMessage(numberphone, null, msg, null, null);
    	
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
	public String getDispLangISO(){
    	String Language=null;
    	Language=Locale.getDefault().getISO3Language();
    	return Language;
    }
    
    /**
     * @return
     */
    public String[] readParameters(){    	
    	
    	
    	String url = null;
    	String params[] = null;
    	//+++++++
    	PayGolSDKConfig config = new PayGolSDKConfig();
    	language=config.getISOLanguage();
    	//StringTokenizer CountryAux = new StringTokenizer(config.getISOCountry(),"_");
    	//country=CountryAux.nextToken();
    	//unique= configIMEI.getIMEI();
    	
    	//hilo
    	PostTask posttask;
    	posttask = new PostTask();
		posttask.execute(service_id,currency,price,country,custom,language,unique);
		try {
			url=posttask.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//descompone la url y guarda en un array
		params=SplitString(url);
		params[0]=url;
				
		return params;
		
	}
    /**
     * encodeURL Function
     * 
     * @param params
     * 	The parameters for create URL  
     * @return
     */
    String encodeUrl(Bundle params) {
        if (params == null) return "";
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String key : params.keySet()) {
            if (first) {
                sb.append("?");
                first = false;
            }
            else {
                sb.append("&");
            }
            sb.append(key + "=" + params.getString(key));
        }
        return sb.toString();
    }
	
	/**
	 * SplitString Function
	 * 
	 * @param url
	 * 			The response URL from PayGol Server 
	 * @return
	 * 			SplitString Function return the array with the parameters separated 
	 * 
	 * SplitString Function split using the token ";", then save each parameter in a array  
	 */
	String[] SplitString(String url){		
			
			StringTokenizer tokens = new StringTokenizer(url,";");
			//String parametros[]=null;
			String parametros[] = new String[12];
			int i=1;
			//Mientras encuentre más tokens, es decir separaciones, que imprima cada token con nextToken():
			while(tokens.hasMoreTokens()){
				
				parametros[i]=tokens.nextToken();
				i=i+1;
				
			//System.out.println(tokens.nextToken());
			}
		
		return parametros;
		}



public class PostTask extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... params) {
        boolean result = false;
        
        Bundle parametres = new Bundle();
		String serviceid = "1057";
		String currency2 = "EUR";
		String price2 = "0.35";
		String country = "NL";
		String custom = "XXXX";
		String language = "nl";
		String unique = "21699999999";
		
		//unique=params[0];
		
		
		
		parametres.putString("serviceid", params[0]);
		parametres.putString("currency",params[1]);
		parametres.putString("price",params[2]);
		parametres.putString("country",params[3]);
		parametres.putString("custom",params[4]);
		parametres.putString("language",params[5]);
		parametres.putString("unique",params[6]);
		
		/*parametres.putString("serviceid", serviceid);
		parametres.putString("currency",currency2);
		parametres.putString("price",price2);
		parametres.putString("country",country);
		parametres.putString("custom",custom);
		parametres.putString("language",language);
		parametres.putString("unique",unique);*/
		

        String page = null;
		
		try {
			BufferedReader in =null;
			//String data =null;
			 	String encodedParams = encodeUrl(parametres);
				HttpClient client = new DefaultHttpClient();
				HttpGet request = new HttpGet();				
				request.setURI(new URI(BASE_HTTP_PAYMENT+ encodedParams));				
				HttpResponse response = client.execute(request); 
		        in = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
		        
		        StringBuffer sb = new StringBuffer("");
		        String line = "";
		        String NL = System.getProperty("line.separator");
		        while ((line = in.readLine()) != null) {
		            sb.append(line + NL);
		        }
		        in.close();
		        page = sb.toString();				        
				
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("[GET REQUEST]", "request error");
		}

        publishProgress("progress");
        return page;
    }

    protected void onProgressUpdate(String... progress) {
        StringBuilder str = new StringBuilder();
            for (int i = 1; i < progress.length; i++) {
                str.append(progress[i] + " ");
            }

    }

}
public String readParameters2(){    	
	
	
	String url = null;
	String params[] = null;
	
	//hilo
	PostTask posttask;
	posttask = new PostTask();
	posttask.execute();
	try {
		url=posttask.get();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//descompone la url y guarda en un array
	params=SplitString(url);
			
	return url;
	
}
////-----------extras
/*
 * 
 try {
					BufferedReader in =null;
										    
						HttpClient client = new DefaultHttpClient();
						HttpGet request = new HttpGet();				
						request.setURI(new URI(BASE_HTTP_PAYMENT));				
						HttpResponse response = client.execute(request); 
				        in = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
				        
				        StringBuffer sb = new StringBuffer("");
				        String line = "";
				        String NL = System.getProperty("line.separator");
				        while ((line = in.readLine()) != null) {
				            sb.append(line + NL);
				        }
				        in.close();
				        url = sb.toString();
				        
						//resultado1.setText(page);
						
						StringTokenizer tokens = new StringTokenizer(page,";");
						//Mientras encuentre más tokens, es decir separaciones, que imprima cada token con nextToken():
						while(tokens.hasMoreTokens()){
						System.out.println(tokens.nextToken());
						}

						System.out.println("\n\n");
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Log.e("[GET REQUEST]", "request error");
				}				*/

}
