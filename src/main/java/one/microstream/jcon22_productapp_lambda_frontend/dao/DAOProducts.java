
package one.microstream.jcon22_productapp_lambda_frontend.dao;

import java.util.List;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import one.microstream.jcon22_productapp_lambda_frontend.domain.Product;


public class DAOProducts
{
	public static List<Product> listProducts()
	{
		final Client client = ClientBuilder.newClient();
		
		final Response response = client
			.target("https://wuoa6v5muc.execute-api.eu-central-1.amazonaws.com/beta/products")
			.request(MediaType.APPLICATION_JSON)
			.get(Response.class);

		response.bufferEntity();

		final List<Product> result = response.readEntity(new GenericType<List<Product>>()
		{});
		
		client.close();

		return result;
	}
	
	public static Product updateProduct(final Product product)
	{
		final Client client = ClientBuilder.newClient();
		
		final Response response =
			client.target("https://wuoa6v5muc.execute-api.eu-central-1.amazonaws.com/beta/products/update")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(product, MediaType.APPLICATION_JSON));
		
		final Product result = response.readEntity(Product.class);
		
		client.close();
		
		return result;
	}
	
	public static Product insertProduct(final Product product)
	{
		final Client client = ClientBuilder.newClient();
		
		final Response response =
			client.target("https://wuoa6v5muc.execute-api.eu-central-1.amazonaws.com/beta/products/insert")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(product, MediaType.APPLICATION_JSON));
		
		final Product result = response.readEntity(Product.class);

		client.close();

		return result;
	}
	
	public static void deleteProduct(final Product product)
	{
		final Client client = ClientBuilder.newClient();
		
		final Response response = client
			.target("https://wuoa6v5muc.execute-api.eu-central-1.amazonaws.com/beta/products/delete?uuid="
				+ product.getUuid())
			.request(MediaType.APPLICATION_JSON)
			.delete(Response.class);
		
		client.close();
	}
}
