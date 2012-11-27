package fi.tapiiri.software.HttpDataInterface;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

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

	public static boolean insertEvent(String url, List<NameValuePair> params) throws URISyntaxException
	{
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		try
		{
			post.setEntity(new UrlEncodedFormEntity(params));
		}
		catch(IOException e)
		{
			System.out.println(e);
			return false;
		}
		HttpResponse r=null;
		try
		{
			r=client.execute(post);
		}
		catch(HttpException e)
		{
			System.out.println(e);
			return false;
		}
		catch(IOException e)
		{
			System.out.println(e);
			return false;
		}
		System.out.println(r.getEntity());
		return true;
	}
}
