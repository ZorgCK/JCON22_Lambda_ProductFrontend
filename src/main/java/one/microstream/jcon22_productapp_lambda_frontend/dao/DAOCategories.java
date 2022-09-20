
package one.microstream.jcon22_productapp_lambda_frontend.dao;

import java.util.List;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import one.microstream.jcon22_productapp_lambda_frontend.domain.Category;


public class DAOCategories
{
	public static List<Category> listCategories()
	{
		final Client client = ClientBuilder.newClient();

		final Response response = client
			.target("https://f0upsooznf.execute-api.eu-central-1.amazonaws.com/beta/categories")
			.request(MediaType.APPLICATION_JSON)
			.get(Response.class);
		response.bufferEntity();
		
		final List<Category> result = response.readEntity(new GenericType<List<Category>>()
		{});

		client.close();

		return result;
	}
}
