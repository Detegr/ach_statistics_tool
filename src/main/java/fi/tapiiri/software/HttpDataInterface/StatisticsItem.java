package fi.tapiiri.software.HttpDataInterface;

import com.google.gson.annotations.SerializedName;

public class StatisticsItem {

	@SerializedName("item_id")
	private int id;
	
	@SerializedName("name")
	private String name;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
	
}
