package fi.tapiiri.software.HttpDataInterface;

import java.util.List;

abstract class Response<T>
{
	private List<T> response;
	public List<T> getResponse()
	{
		return response;
	}
}
