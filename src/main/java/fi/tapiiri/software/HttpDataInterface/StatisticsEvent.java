package fi.tapiiri.software.HttpDataInterface;

import com.google.gson.annotations.SerializedName;

public class StatisticsEvent
{
	@SerializedName("statistics_event_id")
	private int id;

	@SerializedName("player_id")
	private int playerId;

	@SerializedName("match_id")
	private int matchId;

	@SerializedName("item_id")
	private int itemId;

	public int getId() {
		return id;
	}

	public int getPlayerId() {
		return playerId;
	}

	public int getMatchId() {
		return matchId;
	}

	public int getItemId() {
		return itemId;
	}

}
