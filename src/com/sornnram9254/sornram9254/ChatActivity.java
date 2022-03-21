package com.sornnram9254.sornram9254;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.*;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
@SuppressLint("SimpleDateFormat")

public class ChatActivity extends Activity {
	String link, result, user, msg;
	TextView txtShowMsg;
	EditText txtInput;
	Button btnSend;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);

		btnSend = (Button)findViewById(R.id.btn_send);
		btnSend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	    		user = getIntent().getStringExtra("user");
	    		txtInput = (EditText)findViewById(R.id.txt_input);
			    txtShowMsg = (TextView)findViewById(R.id.txtview_showMsg);
				msg = txtInput.getText().toString();
				if(txtInput.getText().toString().equals("")){
        			MediaPlayer mp = MediaPlayer.create(getBaseContext() ,
        			R.raw.check);
        			mp.start();
					Toast.makeText(ChatActivity.this,
							"All Field required",
							Toast.LENGTH_SHORT).show();
				}else{
				    txtShowMsg.append(Html.fromHtml("<font color=red>" + user + "</font>" + 
				            "<b> : </b>" +
				            "<font color=blue>" + msg + "</font><br />"));
				    txtShowMsg.append(bot(msg) + "\n");
				    txtInput.setText("");
				}
			}
		});
	}
	
    public String bot(String msgInput){

        Pattern pHello = Pattern.compile("(��Ѵ��|���ʴ�|�դ�Ѫ|���ʴդ�Ѫ|���ʴդ�Ѻ|�դѺ|�դ��|�դ��|�ը��|�ը��)");
        Matcher mHello = pHello.matcher(msgInput);

        Pattern pTime = Pattern.compile("(����|������|����������|time|Time)");
        Matcher mTime = pTime.matcher(msgInput);
        
        //ref : http://www.memo8.com/�ѧ���蹵Ѵ����Һ����/
        //ref : http://www.goragod.com/knowledge/�ѧ���蹵Ѵ����Һ%20PHP.html
        Pattern pWTF = Pattern.compile(
        		//"(f u c k|f.u.c.k|�֧|�� �|� � �|� ֧|���|��.�|��_�|��-�|��+�|��|� � �|�- � -�|�.�.�|�� �� ��|��-��-��|���|�����|����-�|���***|�����|�ҵ����|�Ҵ���|� � � � � �|�.�.�.�.�.�|� � �� � � �|�.�.��.�.�.�|�Ѵ���|�Ѵ|��|�ѹ�ҹ|�鹵չ|ᵴ|ashole|a s h o l e|a.s.h.o.l.e|bitch|b i t c h|b.i.t.c.h|shit|s h i t|s.h.i.t|fuck|dick|d i c k|d.i.c.k|���|�����|��������|���|���|����)");
        		"(���|���¹|�ʴ|���͡|�����|�����|�ҵ����|�Ҵ���|�Ѵ���|�Ѵ|��|�ѹ�ҹ|�鹵չ|ᵴ|ashole|bitch|shit|fuck|dick|���|�����|��������|���|���|����)");
        Matcher mWTF = pWTF.matcher(msgInput);

        Pattern pEat = Pattern.compile("(��Ǩا|���|��Ǩѧ|��Ǣ���|�����)");
        Matcher mEat = pEat.matcher(msgInput);

        Pattern pLOL = Pattern.compile("(���|55|555|����|��|����|������|����)");
        Matcher mLOL = pLOL.matcher(msgInput);

        Pattern pWhois = Pattern.compile("whois (.*)");
        Matcher mWhois = pWhois.matcher(msgInput);
        
        Pattern pCalc = Pattern.compile("(�Դ�Ţ|calculator|calc) (.*)(\\+|-|\\*|\\/)(.*)");
        Matcher mCalc = pCalc.matcher(msgInput);
        
        
        if (mHello.find()) {
        	msg = "\n" +
        			"\t ���ʴը�� " + user +
        			"\n";
        	MediaPlayer mp = MediaPlayer.create(getBaseContext() , R.raw.hello);
        	mp.start();
        }else if(mTime.find()){
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            msg = "\n" + dateFormat.format(date) + "\n";
        }
        else if(mWTF.find()){
        	msg = "\n" +
        			"\t WTF? olo" +
        			"\n";
        	MediaPlayer mp = MediaPlayer.create(getBaseContext() , R.raw.wtf);
        	mp.start();
        }
        else if(mEat.find()){
        	msg = "\n" +
        			"\t �������͹�ѹ ������áԹ�ѹ >__<" +
        			"\n";
        	MediaPlayer mp = MediaPlayer.create(getBaseContext() , R.raw.eat);
        	mp.start();
        }
        else if(mLOL.find()){
        	msg = "\n" +
        			"\t �� ������ -___-" +
        			"\n";
        	MediaPlayer mp = MediaPlayer.create(getBaseContext() , R.raw.lol);
        	mp.start();
        }
        else if(mWhois.find()){
        	
        	msg = "\n" +
        			"\t " + mWhois.group(1) +
        			"\n";
        }
        else if(mCalc.find()){
        	int num1 = Integer.parseInt(mCalc.group(2));
        	String op = mCalc.group(3);
        	int num2 = Integer.parseInt(mCalc.group(4));
        	
        	msg = "\n" +
        			"\t " + Integer.toString(calc(num1, op, num2)) +
        			"\n";
        	//MediaPlayer mp = MediaPlayer.create(getBaseContext() , R.raw.err);
        	//mp.start();
        }
        /*Pattern pReip = Pattern.compile("(ip|myip|reip)");
        Matcher mReip = pReip.matcher(msgInput);*/
        /*else if(mReip.find()){
    		try {
    		    URL site = new URL("http://reip.stephack.com");
    		    BufferedReader in = new BufferedReader(
    		    new InputStreamReader(site.openStream()));
    		    
    		    String inputLine;
    		    while ((inputLine = in.readLine()) != null)
    		    	msg = "555";
    		    in.close();
    		} catch (Exception e) { }
        }*/
        
        else{
        	msg = "\n" +
        			"\t ������, ���������㨤���觢ͧ�س" +
        			"\n";
        	MediaPlayer mp = MediaPlayer.create(getBaseContext() , R.raw.err);
        	mp.start();
        }
        return msg;
    }
    public int calc(int num1, String op, int num2){
    	int result = 0;
    	switch (op)
    	{
	    	case "+":
	    		return num1+num2;
	    	case "-":
	    		return num1-num2;
	    	case "*":
	    		return num1*num2;
	    	case "/":
	    		return num1/num2;
    	}
		return result;
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
		    AlertDialog.Builder alert = new AlertDialog.Builder(ChatActivity.this);
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
							ChatActivity.this.finish();
						}
					});
		    AlertDialog showAlert = alert.create();
		    showAlert.show();
		}else if(id == R.id.action_about){
			AlertDialog.Builder alert = new AlertDialog.Builder(ChatActivity.this);
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
