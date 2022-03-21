package com.sornnram9254.sornram9254;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	Button	 	btn_register;
	EditText 	txt_user, txt_pass, txt_email;
	String	 	user, pass, email, link, result;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		register();
	}

	public void register() {
		btn_register = (Button)findViewById(R.id.btnRegister);
		btn_register.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				txt_user = (EditText)findViewById(R.id.register_user);
				txt_pass = (EditText)findViewById(R.id.register_pass);
				txt_email = (EditText)findViewById(R.id.register_email);

				user = txt_user.getText().toString();
				pass = txt_pass.getText().toString();
				email = txt_email.getText().toString();
				
				if( 	txt_user.getText().toString().equals("") |
						txt_pass.getText().toString().equals("") |
						txt_email.getText().toString().equals("") 
					){
	        			MediaPlayer mp = MediaPlayer.create(getBaseContext() ,
	        			R.raw.check);
	        			mp.start();
						Toast.makeText(RegisterActivity.this,
								"All Field required",
								Toast.LENGTH_SHORT).show();
				}else{
					link =  "http://" + getString(R.string.serverIP) + "/index.php" +
							"?username=" + user +
							"&password=" + pass +
							"&email="	 + email;
					new HttpAsyncTask().execute(link);
					
		        	MediaPlayer mp = MediaPlayer.create(getBaseContext() , R.raw.waitregister);
		        	mp.start();
		        	
					pDialog = ProgressDialog.show(RegisterActivity.this,
							"Register" , "Sending data...", true);
				}
			}});
	}

	private ProgressDialog pDialog;
	
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
    		try {
    			URL site = new URL(link);
    			URLConnection cn = site.openConnection();
    			BufferedReader in = new BufferedReader(new InputStreamReader(
    				cn.getInputStream(), "UTF-8"));
    			String inputLine;
    			StringBuilder a = new StringBuilder();
    			while ((inputLine = in.readLine()) != null)
    				a.append(inputLine);
    			in.close();
    			result = a.toString();
    		} catch (Exception e) {
    			Log.d("InputStream", e.getLocalizedMessage());
    		}
    		return result;
        }
        @Override
        protected void onPostExecute(String result) {
        	pDialog.dismiss();
            Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();
            finish();
       }
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_exit) {
		    AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivity.this);
		    alert.setTitle("Exit")
		    .setTitle("Are you sure you want to exit ?")
		    .setIcon(android.R.drawable.ic_dialog_alert)
		    .setPositiveButton("NO",
		    		new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					})
		    .setNegativeButton("OK",
		    		new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							RegisterActivity.this.finish();
						}
					});
		    AlertDialog showAlert = alert.create();
		    showAlert.show();
		}else if(id == R.id.action_about){
			AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivity.this);
			alert.setIcon(android.R.drawable.ic_dialog_info);
			alert.setTitle("Mr. Sornram Kampeera" + "\n" +
							"CED#1 3RA : 5502041620257" + "\n" +
							"http://sornram9254.com");
			alert.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
				}
			}).show();
		}
		return super.onOptionsItemSelected(item);
	}
}
