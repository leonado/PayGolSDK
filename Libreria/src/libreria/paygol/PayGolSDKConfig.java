package libreria.paygol;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;

public class PayGolSDKConfig extends Activity{
	Context mContext;
	
	/**
	 * PayGolSDKConfig Constructor
	 */
	public PayGolSDKConfig (){}
	/**
	 * PayGolSDKConfig Constructor
	 * @param   mContext
     *            The mContext obtained from PayGolSDk.
	 */
	public PayGolSDKConfig(Context mContext){
	       this.mContext = mContext;
	  }
		
	public String getIMEI(){
		String IMEI=null;
		TelephonyManager telMgr;
        telMgr = (TelephonyManager)getSystemService(mContext.TELEPHONY_SERVICE); 
        IMEI = telMgr.getDeviceId();		
		return IMEI;
	}
	public String getIMEI3(TelephonyManager telMgr){
		String IMEI=null;
        IMEI = telMgr.getDeviceId();		
		return IMEI;
	}
	public String getIMEI2(Context context){
		String IMEI=null;
		TelephonyManager telMgr;
        telMgr = (TelephonyManager)getSystemService(context.TELEPHONY_SERVICE); 
        IMEI = telMgr.getDeviceId();		
		return IMEI;
	}
	public String getIMEI4(String context){
		String IMEI=null;
		TelephonyManager telMgr;
        telMgr = (TelephonyManager)getSystemService(context); 
        IMEI = telMgr.getDeviceId();		
		return IMEI;
	}
	
	public String getISOLanguage(){
		String[] languages = Locale.getISOLanguages();
		Map<String, Locale> LanguageMap = new HashMap<String, Locale>(languages.length);
		for (String language : languages) {
		    Locale locale = new Locale(language);
		    LanguageMap.put(locale.getISO3Language(), locale);
		}
    	return LanguageMap.get(getISO3Language()).toString();
    }
	
	public String getISOCountry(){
		String[] countries = Locale.getISOCountries();
		Map<String, Locale> CountryMap = new HashMap<String, Locale>(countries.length);
		for (String country : countries) {			
		    Locale locale2 = new Locale("",country);
		    CountryMap.put(locale2.getISO3Country(), locale2);
		}
    	return CountryMap.get(getISO3Country()).toString();
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
