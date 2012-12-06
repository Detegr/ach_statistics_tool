package fi.tapiiri.software;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.lang.Exception;
import java.net.URISyntaxException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import fi.tapiiri.software.HttpDataInterface.*;

public class MainActivity extends Activity {
	
    private static String TAG = "ach_statistics_tool";

    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after 
     * previously being shut down then this Bundle contains the data it most 
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");

        setContentView(R.layout.main);
	
        Spinner p_spinner, m_spinner, s_spinner;
        p_spinner = (Spinner) findViewById(R.id.player_spinner);
        m_spinner = (Spinner) findViewById(R.id.match_spinner);
        s_spinner = (Spinner) findViewById(R.id.statistics_item_spinner);
        TextView message = (TextView) findViewById(R.id.message);
        
        message.setText("Who do you voodoo, bitch?");
        Button add = (Button) findViewById(R.id.add);
        Button reduce = (Button) findViewById(R.id.reduce);
        add.setOnClickListener(addListener);
        reduce.setOnClickListener(reduceListener);
        
        List<Player> player_list = null;
        List<Match> match_list = null;
        List<StatisticsItem> item_list = null;
        try {
        	player_list = HttpInterface.getData("http://muum.org:8080/players", PlayerResponse.class);
        	match_list = HttpInterface.getData("http://muum.org:8080/matches", MatchResponse.class);
        	item_list = HttpInterface.getData("http://muum.org:8080/items", StatisticsItemResponse.class);
        }
        catch (URISyntaxException e) {
        	message.setText("URISyntaxException happened, bitch!");
        }
        
        ArrayAdapter<Player> players_adapter = new ArrayAdapter<Player>(this, android.R.layout.simple_spinner_item, player_list);
        ArrayAdapter<Match> matches_adapter = new ArrayAdapter<Match>(this, android.R.layout.simple_spinner_item, match_list);
        ArrayAdapter<StatisticsItem> items_adapter = new ArrayAdapter<StatisticsItem>(this, android.R.layout.simple_spinner_item, item_list);
        
        players_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        matches_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        items_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        p_spinner.setAdapter(players_adapter);
        m_spinner.setAdapter(matches_adapter);
        s_spinner.setAdapter(items_adapter);
    }
    
    private OnClickListener addListener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			
			ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
	        Spinner p_spinner, m_spinner, s_spinner;
	        p_spinner = (Spinner) findViewById(R.id.player_spinner);
	        m_spinner = (Spinner) findViewById(R.id.match_spinner);
	        s_spinner = (Spinner) findViewById(R.id.statistics_item_spinner);
	        
	        Player player = (Player) p_spinner.getSelectedItem();
	        Match match = (Match) m_spinner.getSelectedItem();
	        StatisticsItem item = (StatisticsItem) s_spinner.getSelectedItem();
	        params.add(new BasicNameValuePair("playerid", "" + player.getId()));
	        params.add(new BasicNameValuePair("matchid", "" + match.getId()));
	        params.add(new BasicNameValuePair("itemid", "" + item.getId()));
	        
			TextView message = (TextView) findViewById(R.id.message);
			message.setText("Add button pushed");
			
			List<StatisticsEvent> events = null;
			try
			{
				events = HttpInterface.insertEvent("http://muum.org:8080/", params);
			} 
			catch (URISyntaxException e)
			{
				message.append(": Insertion unsuccessful!");
				return;
			}
			catch (Exception e) {
				message.append(": Insertion unsuccessful!");
				return;
			}
			message.append(": Insertion successful!");
			
		}

    };
    
    private OnClickListener reduceListener = new OnClickListener() {
    	
		@Override
		public void onClick(View arg0) {
			
			TextView message = (TextView) findViewById(R.id.message);
			message.setText("Reduce button pushed");
			
			try
			{
				HttpInterface.deleteEvent("http://muum.org:8080/delete/");
			} catch (URISyntaxException e)
			{
				message.append(": Deletion unsuccessful!");
				return;
			} catch (Exception e)
			{
				message.append(": Deletion unsuccessful!");
			}
			message.append(": Deletion unsuccessful!");
			
		}
		
    };

}

