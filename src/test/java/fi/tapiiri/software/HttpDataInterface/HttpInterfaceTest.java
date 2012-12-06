package fi.tapiiri.software.HttpDataInterface;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import junit.framework.Assert;

public class HttpInterfaceTest
{
	@Test
	public void testGetPlayers()
	{
		List<Player> players=null;
		try
		{
			players=HttpInterface.getData("http://muum.org:8080/players", PlayerResponse.class);
		} catch(URISyntaxException e)
		{
			Assert.assertTrue(false);
		}
		Iterator<Player> it=players.listIterator();
		while(it.hasNext())
		{
			Player p=it.next();
			System.out.println(p.getFirstName() + " " + p.getLastName());
		}
		Assert.assertNotSame(players.size(), 0);
	}

	@Test
	public void testInsertEvent()
	{
		ArrayList<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("playerid", "1"));
		params.add(new BasicNameValuePair("matchid", "9"));
		params.add(new BasicNameValuePair("itemid", "4"));

		List<StatisticsEvent> events=null;
		try
		{
			events=HttpInterface.insertEvent("http://muum.org:8080/", params);
		}
		catch(Exception e)
		{
			Assert.assertTrue(false);
		}

		Iterator<StatisticsEvent> it=events.listIterator();
		while(it.hasNext())
		{
			StatisticsEvent e=it.next();
			System.out.println("Inserted Id:" + e.getId() + " PlayerId: " + e.getPlayerId() + " MatchId: " + e.getMatchId() + " ItemId: " + e.getItemId());
		}
		
		Assert.assertNotSame(events.size(), 0);
	}

	@Test
	public void testDeleteEvent()
	{
		boolean ok=false;
		try
		{
			ok=HttpInterface.deleteEvent("http://muum.org:8080/delete/1/9/4");
		}
		catch(Exception e) {}
		Assert.assertTrue(ok);
		if(ok)
		{
			System.out.println("Deleted PlayerId: 1 MatchId: 9 ItemId: 4");
		}
	}

}
