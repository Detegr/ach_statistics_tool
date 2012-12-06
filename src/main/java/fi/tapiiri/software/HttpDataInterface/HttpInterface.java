package fi.tapiiri.software.HttpDataInterface;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;

public class HttpInterface
{
	public static <T> List<T> getData(String url, Class<? extends Response<T>> cls) throws URISyntaxException
	{
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		HttpResponse r=null;
		try
		{
			r=client.execute(get);
		}
		catch(HttpException e)
		{
			System.out.println(e);
			return null;
		}
		catch(IOException e)
		{
			System.out.println(e);
			return null;
		}
		StringBuilder builder=new StringBuilder();
		try
		{
			InputStream content=new BufferedInputStream(r.getEntity().getContent());
			BufferedReader reader=new BufferedReader(new InputStreamReader(content));
			String line=null;
			while((line = reader.readLine()) != null)
			{
				builder.append(line);
			}
		}
		catch(IOException e)
		{
			System.out.println(e);
			return null;
		}
		Gson gson=new Gson();
		Response<T> mainresponse=gson.fromJson(builder.toString(), cls);

		return mainresponse.getResponse();
	}

	public static List<StatisticsEvent> insertEvent(String url, List<NameValuePair> params) throws URISyntaxException
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
			return null;
		}
		HttpResponse r=null;
		try
		{
			r=client.execute(post);
		}
		catch(HttpException e)
		{
			System.out.println(e);
			return null;
		}
		catch(IOException e)
		{
			System.out.println(e);
			return null;
		}
		StringBuilder builder = new StringBuilder();
		try
		{
			InputStream content=new BufferedInputStream(r.getEntity().getContent());
			BufferedReader reader=new BufferedReader(new InputStreamReader(content));
			String line=null;
			while((line = reader.readLine()) != null)
			{
				builder.append(line);
			}
		}
		catch(IOException e)
		{
			System.out.println(e);
			return null;
		}
		Gson gson=new Gson();
		StatisticsEventResponse response = gson.fromJson(builder.toString(), StatisticsEventResponse.class);
		return response.response;
	}

	public static boolean deleteEvent(String url) throws URISyntaxException
	{
		HttpClient client = new DefaultHttpClient();
		HttpDelete delete = new HttpDelete(url);
		HttpResponse r=null;
		try
		{
			r=client.execute(delete);
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
		StringBuilder builder = new StringBuilder();
		try
		{
			InputStream content=new BufferedInputStream(r.getEntity().getContent());
			BufferedReader reader=new BufferedReader(new InputStreamReader(content));
			String line=null;
			while((line = reader.readLine()) != null)
			{
				builder.append(line);
			}
		}
		catch(IOException e)
		{
			System.out.println(e);
			return false;
		}
		Gson gson=new Gson();
		BooleanResponse response = gson.fromJson(builder.toString(), BooleanResponse.class);
		return response.response;
	}
}
