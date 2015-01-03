package shs.sc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.widget.TextView;
import android.widget.Toast;

public class DataReceiver {
	
	public static void setInfoOnTextView(final TextView v, final String sport, final String league, final String date){
		Thread newThread = new Thread(new Runnable(){

			@Override
			public void run() {
				
				final String message = getHTML(/*URL GOES HERE!!!*/"http://73.162.233.8:8080/requestdata.jsp?sport="+sport+"&league="+league+"&date="+date).replaceAll("%", "\n");

				v.post(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						v.setText(message);
						MainActivity.scrollToBottom();
						//this is what causes the ScrollView to scroll automatically to the bottom once GO is pressed
					}
					
				});
				
			}
			
		});
		newThread.start();
	}
	
	private static String getHTML(String url){
		//this thing is probably really hard to read. Don't be too concerned about it.
		
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String html = "";
		InputStream in = null;
		try {
			in = response.getEntity().getContent();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder str = new StringBuilder();
		String line = null;
		try {
			while((line = reader.readLine()) != null)
			{
			    str.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		html = str.toString();
		return html;
	}

}
