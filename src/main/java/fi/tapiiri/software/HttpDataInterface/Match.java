package fi.tapiiri.software.HttpDataInterface;

import com.google.gson.annotations.SerializedName;

public class Match {

	@SerializedName("match_id")
	private int id;
	
	@SerializedName("opponent_id")
	private int opponent_id;
	
	@SerializedName("date")
	private String date;

	public int getId() {
		return id;
	}

	public int getOpponentId() {
		return opponent_id;
	}

	public String getDate() {
		return date;
	}
	
	public String toString() {
		return opponent_id + " " + date;
	}
}
