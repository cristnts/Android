package com.example.vania.tirateunafoto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {



	private LocationManager locationManager;
	private Uri photoURI;
	private String mCurrentPhotoPath;
	static final int REQUEST_IMAGE_CAPTURE = 1;
	static final int REQUEST_TAKE_PHOTO = 1;
	private GPS locationListener;

	@Override
	 protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
     locationListener = new GPS(getApplicationContext());
     if(ActivityCompat.checkSelfPermission(this, manifest.permission.ACCES_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED &&
        ActivityCompar.checkSelfPermission(this, Manifest.permission.ACCES_COARSE_LOCATION)
        != PackageManager.PERMISSION_GRANTED){
         return;

    }
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000, 0, locationListener);
    }

    public void btnClick(View v){
        dispatchTakePictureIntent();
    }

    public void btnSend(View v){

	    sendMail(lationListener.getLatitud(), locationListener.getLongitud(), locationListener.getCiudad());

	}

	private void dispatchTakePictureIntent(){

	    Intent takePictureIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
	    if(takePictureIntent.resolveActivity(getPackageManager()) != null){
	    file photofile = null;
	    try{
	        photofile = createImageFile();
	    }   catch (IOException ex){}
	    if (photofile != null) {
	        photoURI = Uri.fromFile(photoFile);
	        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
	        starActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

	    }

	    }

	}

@Override
protected void onActivityResult(int requestCode, int resultCode, intent data){

	    if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){

	        ImageView iv = (ImageView)findViewByid(R.id.imageView);
	        iv.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath));

	    new MediaScannerConnection.MediaScannerConnectionClient(){
	        private mediaScannerConnection msc = null;{
	            msc = new MediaScannerConnection(getApplicationContext(), this);
	            msc.connect();
	        }
        public void onMediaScannerConnected(){
                msc.scanFile(mCurrentPhotoPath, null);
                }
	    public void onScanCompleted(String path, Uri uri){
	            msc.disconnect();
	        }

	    };

	    }

	}

private File createImageFile() throws IOException {

	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    String imageFileName = "foto " +timeStamp;
	    File StorageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
	    File image = File.createTempFile(
	                    imageFileName,
	                    ".jpg",
	                    storageDir
	    );
	    mCurrentPhotoPath = image.getAbsolutePath();
	    return image;
	    }

	    public void sendMail(double latitude, double longitude, String ciudad){

	    String msj = "la foto ha sido capturada en las siguientes coordenadas: \n" +
	    "Latitud: " + latitude + "\n longitud: "+ longitude + "\n" + "correspondientes a la ciudad de: "+ ciudad;
	Intent i = new Intent(Intent.ACTION_SEND);
	i.setType("message/rfc822");
	i.putExtra(Intent.EXTRA_EMAIL, new String[]("mauricio.alex.vasquez@gmail.com"));
	i.putExtra(Intent.EXTRA_SUBJECT, "Enviando una foto adjunta al correo");
	i.putExtra(Intent.EXTRA_TEXT, msj);

	i.setType("application/jpg");
	i.putExtra(Intent.EXTRA_STREAM, photoURI);
	try{
	    startActivity(Intent.createChooser(i, "Send mail..."));

	}catch(android.content.ActivityNotFoundException ex){
	    toast.makeText(MainActivity.this, "There are no email cliente installed", Toast.LENGTH_SHORT.show();

	}

	}

	}

public class Database{

    private SQLiteDatabase db;

    public Database(SQLiteDatabase db){
        this.db=db;
        crearTablas();



    }


    private void crearTablas(){

        db.execSQL("create table id not exist datos" + "(_id integer PRIMARY KEY AUTOINCREMENT, rut varchar, nombre varchar, trabajando integer);");
    }


}





















}
