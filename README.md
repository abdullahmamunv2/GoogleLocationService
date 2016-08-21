This is basically an api for google location service.
Using this api u can easily get gps location.
This api is implemented for android.

if u wanted to use this api in your android project then:
	
	1. Your Activity must be implemented the LocationListener interface.
	2.Then craeate an instance of GoogleLocationService
	3.Then instantiate Google Map with necessary configuration.

Now update the following function as u wish:
 	1.public void onLocationChanged(Location location)
	2.public void onStatusChanged(String provider, int status, Bundle extras)
	3.public void onProviderEnabled(String provider)
	4.public void onProviderDisabled(String provider) 


******** please put yout google api key in androidmanifest.xml file before testing **********
	
