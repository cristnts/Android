//
// Created by Vania on 12/04/2018.
//
public class GRP implements LocationListener{

	private String ciudad;
	private Double latitud;
	private double longitud;
	private context context;

	public GPS(Context context){

		this.context = context;
	}

	@Override
	public void onLocationChanged(Location location){
		latitud = location.getLatitude();
		longitud = location.getLongitude();

		Geocoder gcd = new Geocoder(context, Locale.getDefault());
		List<Address> addresses;
		try{
			addresses = gcd.getFromLocation(latitud , longitud, 1);
			if(addresses.size() > 0){
				ciudad = addresses.get(0).getLocality();
			}

		}
		catch (IOException e){
			e.printStackTrace();
		}


		Toast.makeText(context, ciudad, Toast.LENGTH_SHORT).show();
	}

	public String getCiudad(){
	    return ciudad;
	}

    @Override
    public void onProviderDisable(String provider){}

    @Override
    public void onProviderEnable(String provider){}

    @Override
    public void onStatusChanged(String provider, int status, Bundleextras){}

    public Double getLatitud(){
        return latitud;
    }

    public Double getLongitud(){
        return longitud;
    }

}
