package com.sornnram9254.sornram9254;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class MainActivity extends Activity {

	Button		btn_login, btn_register;
	EditText	txt_user, txt_pass, txt_channel;
	String	 	user, pass, link, result;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		login();
		register();
	}

	public void register() {
		btn_register = (Button)findViewById(R.id.btnRegister);
		btn_register.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent goRegister = new Intent(getApplicationContext(),
						RegisterActivity.class);
				startActivity(goRegister);
			}});
	}
	
	public void login() {
		btn_login = (Button)findViewById(R.id.btnLogin);
		btn_login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				txt_user = (EditText)findViewById(R.id.login_user);
				txt_pass = (EditText)findViewById(R.id.login_pass);

				user = txt_user.getText().toString();
				pass = txt_pass.getText().toString();
				
				if( 	txt_user.getText().toString().equals("") |
						txt_pass.getText().toString().equals("")
					){
	        			MediaPlayer mp = MediaPlayer.create(getBaseContext() ,
	        			R.raw.check);
	        			mp.start();
						Toast.makeText(MainActivity.this,
								"All Field required",
								Toast.LENGTH_SHORT).show();
				}else{
					link =  "http://" + getString(R.string.serverIP) + "/login.php" +
							"?username=" + user +
							"&password=" + pass;
					new HttpAsyncTask().execute(link);
					
		        	//MediaPlayer mp = MediaPlayer.create(getBaseContext() , R.raw.waitlogin);
		        	//mp.start();
					
					pDialog = ProgressDialog.show(MainActivity.this,
							"login" , "Sending data...", true);
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
            if(result.equals("{\"code\":1}")){
                finish();
    			Intent goChat = new Intent(getApplicationContext(),
    					ChatActivity.class);
    			goChat.putExtra("user", user);
    			startActivity(goChat);
            }else{
    			MediaPlayer mp = MediaPlayer.create(getBaseContext() ,
    			R.raw.userpass);
    			mp.start();
            	Toast.makeText(getBaseContext(),
            			"Error", Toast.LENGTH_LONG).show();
            }
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
		    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
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
							MainActivity.this.finish();
						}
					});
		    AlertDialog showAlert = alert.create();
		    showAlert.show();
		}else if(id == R.id.action_about){
			AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
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

	private static long back_pressed;
	@Override
	public void onBackPressed() // ref: http://androidsnippets.com/double-back-press-to-exit
	{
		if (back_pressed + 2000 > System.currentTimeMillis()) super.onBackPressed();
		else Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
		back_pressed = System.currentTimeMillis();
	}
}
