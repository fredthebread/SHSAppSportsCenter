package shs.sc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
	
	public static Context context;
	
	private LinearLayout main;
	
	private LinearLayout choosers;
	private Spinner sportSpinner;
	private Spinner leagueSpinner;
	private DatePicker datePicker;
	
	private Button goButton;
	
	public static ScrollView scroll;
	private TextView scrollText;
	
	public static Runnable scrollerDowner = new Runnable() {
        @Override
        public void run() {
            scroll.fullScroll(ScrollView.FOCUS_DOWN);
        }
    };
    
    private static ArrayList<String> sports;
    private static HashMap<String, String[]> leagues;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        
        //initialize leagues and sports
    	leagues = new HashMap<String,String[]>();
    	sports = new ArrayList<String>();
    	
    	leagues.put("SELECT SPORT...", new String[] {"SELECT LEAGUE..."}); sports.add("SELECT SPORT...");
    	leagues.put("Football", new String[] {"SELECT LEAGUE..."/*Insert more leagues here as appropriate to sport*/}); sports.add("Football");
    	leagues.put("Field Hockey", new String[] {"SELECT LEAGUE..."/*Insert more leagues here as appropriate to sport*/}); sports.add("Field Hockey");
    	leagues.put("Cross Country", new String[] {"SELECT LEAGUE..."/*Insert more leagues here as appropriate to sport*/}); sports.add("Cross Country");
    	leagues.put("Water Polo", new String[] {"SELECT LEAGUE..."/*Insert more leagues here as appropriate to sport*/}); sports.add("Water Polo");
    	leagues.put("Volleyball", new String[] {"SELECT LEAGUE..."/*Insert more leagues here as appropriate to sport*/}); sports.add("Volleyball");
    	leagues.put("Tennis", new String[] {"SELECT LEAGUE...", "test"/*Insert more leagues here as appropriate to sport*/}); sports.add("Tennis");
    	leagues.put("Soccer", new String[] {"SELECT LEAGUE..."/*Insert more leagues here as appropriate to sport*/}); sports.add("Soccer");
    	leagues.put("Wrestling", new String[] {"SELECT LEAGUE..."/*Insert more leagues here as appropriate to sport*/}); sports.add("Wrestling");
    	leagues.put("Basketball", new String[] {"SELECT LEAGUE..."/*Insert more leagues here as appropriate to sport*/}); sports.add("Basketball");
    	leagues.put("Baseball", new String[] {"SELECT LEAGUE..."/*Insert more leagues here as appropriate to sport*/}); sports.add("Baseball");
    	leagues.put("Track And Field", new String[] {"SELECT LEAGUE..."/*Insert more leagues here as appropriate to sport*/}); sports.add("Track And Field");
    	leagues.put("Golf", new String[] {"SELECT LEAGUE..."/*Insert more leagues here as appropriate to sport*/}); sports.add("Golf");
    	leagues.put("Badminton", new String[] {"SELECT LEAGUE..."/*Insert more leagues here as appropriate to sport*/}); sports.add("Badminton");
    	leagues.put("Dance Team", new String[] {"SELECT LEAGUE..."/*Insert more leagues here as appropriate to sport*/}); sports.add("Dance Team");
    	leagues.put("Cheerleading", new String[] {"SELECT LEAGUE..."/*Insert more leagues here as appropriate to sport*/}); sports.add("Cheerleading");
    	//add sports that aren't present here
    	//			|
    	//			V

        
        main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        GradientDrawable backgroundGradient = new GradientDrawable();
        backgroundGradient.setShape(GradientDrawable.RECTANGLE);
        backgroundGradient.setColors(new int[]{0xffffffff,0xffff3333});
        backgroundGradient.setOrientation(Orientation.TOP_BOTTOM);
        main.setBackground(backgroundGradient);
        
       
    	////////spinners/DatePicker
        choosers = new LinearLayout(this);
        choosers.setOrientation(LinearLayout.HORIZONTAL);
        
        LinearLayout.LayoutParams chooserParams = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT, 1.0f);
        
        LinearLayout spinners = new LinearLayout(this);
        spinners.setOrientation(LinearLayout.VERTICAL);
        
    	sportSpinner = new Spinner(this);
    	sportSpinner.setLayoutParams(chooserParams);
    	ArrayAdapter<String> sportAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sports);
    	sportSpinner.setAdapter(sportAdapter);
    	sportSpinner.setSelection(sportAdapter.getPosition("SELECT SPORT..."));
    	sportSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> s, View v,
					int index, long id) {
				sportSelected(v);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
    		
    	});
    	spinners.addView(sportSpinner);
    	
    	leagueSpinner = new Spinner(this);
    	leagueSpinner.setLayoutParams(chooserParams);
    	ArrayAdapter<String> leagueAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, leagues.get(sportSpinner.getSelectedItem()));
    	leagueSpinner.setAdapter(leagueAdapter);
    	leagueSpinner.setSelection(0);
    	spinners.addView(leagueSpinner);
    	
    	choosers.addView(spinners);
    	
    	datePicker = new DatePicker(this);
    	datePicker.setLayoutParams(chooserParams);
    	datePicker.setCalendarViewShown(false);
    	choosers.addView(datePicker);
    	///////
        
        goButton = new Button(this);
        goButton.setText("GO");
        goButton.setBackgroundColor(0xffff0000);
        goButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goButtonClicked(v);
				//see method just after onCreate()
				
			}
		});
        
        scroll = new ScrollView(this);
        LinearLayout.LayoutParams scrollParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        scrollParams.setMargins(20, 20, 20, 20);
        scroll.setLayoutParams(scrollParams);
     
        scrollText = new TextView(this);
        scrollText.setText("No timeline to display currently.");
        scrollText.setTextColor(0xff000000);
        scrollText.setSingleLine(false);
        scrollText.setMaxLines(100);
        scroll.addView(scrollText);

        main.addView(choosers);
        main.addView(goButton);
        main.addView(scroll);
        
        setContentView(main);
    }
    
    public void goButtonClicked(View v){
    	
    	//get info from 2 Spinners/DatePicker
    	String sport = ((String) sportSpinner.getSelectedItem()).trim().replaceAll("\\s+","_").toLowerCase();
    	String league = ((String) leagueSpinner.getSelectedItem()).trim().replaceAll("\\s+","_").toLowerCase();;
    	
    	String month = ""+(datePicker.getMonth()+1);
    	if(Integer.parseInt(month)<10)month="0"+month;
    	String day = ""+datePicker.getDayOfMonth();
    	if(Integer.parseInt(day)<10)day="0"+day;
    	String yr = ""+datePicker.getYear();
    	String date = month+"-"+day+"-"+yr;
    	
    	// if sportSpinner or leagueSpinner still have "select" then make notification saying make all selections first
    	if(sport.equals("select_sport...")){
    		Toast.makeText(context, "Please select a sport.", Toast.LENGTH_SHORT).show();
    		return;
    	}
    	else if(league.equals("select_league...")){
    		Toast.makeText(context, "Please select a league.", Toast.LENGTH_SHORT).show();
    		return;
    	}
    	
    	DataReceiver.setInfoOnTextView(scrollText, sport, league, date);
    	
    }
    
    private void sportSelected(View v){
    	String key = ((TextView)v).getText().toString();
    	ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, leagues.get(key));
    	leagueSpinner.setAdapter(a);
    	leagueSpinner.setSelection(0);
    }
    
    public static void scrollToBottom(){
    	//this is what causes the ScrollView to scroll automatically to the bottom once GO is pressed
    	scroll.post(scrollerDowner);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
