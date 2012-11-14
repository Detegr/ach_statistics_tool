package fi.tapiiri.software.HttpDataInterface;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;

public class HttpInterface
{
	public static List<Player> getPlayers(String url) throws MalformedURLException
	{
		URL u=new URL(url);
		HttpURLConnection conn=null;
		StringBuilder builder=new StringBuilder();
		try
		{
			conn=(HttpURLConnection)u.openConnection();
			InputStream content=new BufferedInputStream(conn.getInputStream());
			BufferedReader reader=new BufferedReader(new InputStreamReader(content));
			String line=null;
			while((line = reader.readLine()) != null)
			{
				builder.append(line);
			}
		}
		catch(IOException e)
		{
			System.out.println("Could't get data from url:" + e);
		}
		finally {
			if(conn != null) conn.disconnect();
		}

		Gson gson=new Gson();
		PlayerResponse players=gson.fromJson(builder.toString(), PlayerResponse.class);

		return players.response;
	}
}
