package fi.tapiiri.software;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import java.util.List;
import java.lang.Exception;
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
	
	Spinner p_spinner = (Spinner) findViewById(R.id.player_spinner);
	List<Player> player_list = null;
	try {
	player_list = HttpInterface.getPlayers("http://muum.org:8080");
	}
	catch (Exception e) {
	}
	//System.out.print(player_list.toString());
	ArrayAdapter<Player> players_adapter = new ArrayAdapter<Player>(this, android.R.layout.simple_spinner_item, player_list);

	players_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	p_spinner.setAdapter(players_adapter);
	

    }

}

