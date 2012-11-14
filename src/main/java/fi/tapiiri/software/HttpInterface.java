package fi.tapiiri.software;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpInterface
{
	public void Get(String url) throws MalformedURLException
	{
		URL u=new URL(url);
		HttpURLConnection conn=null;
		try
		{
			conn=(HttpURLConnection)u.openConnection();
			InputStream in=new BufferedInputStream(conn.getInputStream());
			in.
		}
		catch(IOException e)
		{
			System.out.print("Could't get data from url:" + e);
		}
		finally {
			if(conn != null) conn.disconnect();
		}
	}
}
