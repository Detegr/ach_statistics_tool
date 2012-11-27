package fi.tapiiri.software.HttpDataInterface;

import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;

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
			players=HttpInterface.getPlayers("http://muum.org:8080/players");
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
}
