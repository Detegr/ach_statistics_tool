package fi.tapiiri.software.HttpDataInterface;

import com.google.gson.annotations.SerializedName;

public class Opponent {

	@SerializedName("opponent_id")
	private int opponent_id;

	@SerializedName("name")
	private String name;

	public int getOpponent_id() {
		return opponent_id;
	}

	public String getName() {
		return name;
	}
	
	
}