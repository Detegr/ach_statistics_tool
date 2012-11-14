package fi.tapiiri.software.HttpDataInterface;

import com.google.gson.annotations.SerializedName;

public class Player
{
	@SerializedName("player_id")
	private int id;

	@SerializedName("last_name")
	private String lastName;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("player_number")
	private int playerNumber;

	@SerializedName("picture_path")
	private String picturePath;

	@SerializedName("description")
	private String description;

	@SerializedName("active")
	private boolean active;

	public int getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public String getDescription() {
		return description;
	}

	public boolean isActive() {
		return active;
	}
}
