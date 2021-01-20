package ma.ensias.ejb3.tp2.client;
import java.util.ArrayList;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ma.ensias.ejb3.tp2.ShoppingCartBeanRemote;

public class ShoppingCartClient {
	
	public ShoppingCartClient() {
	}

	public static void main(String[] args) throws InterruptedException {
		ShoppingCartClient shoppingCartTest = new ShoppingCartClient();
		shoppingCartTest.doTest();
	}

	InitialContext getInitialContext() throws javax.naming.NamingException {
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,
		"org.wildfly.naming.client.WildFlyInitialContextFactory");
		
		p.put(Context.URL_PKG_PREFIXES,
		"jboss.naming:org.jnp.interfaces");
		p.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		return new InitialContext(p);
	}
	
	void doTest() throws InterruptedException {
		try {
			InitialContext ic = getInitialContext();
			System.out.println("ShoppingCart Lookup");
			ShoppingCartBeanRemote shoppingCart = (ShoppingCartBeanRemote) ic.lookup("TP2StatefulBean/ShoppingCartBean!ma.ensias.ejb3.tp2.ShoppingCartBeanRemote");
			//Thread.sleep(5000);
			System.out.println("Adding Book Items...");
			shoppingCart.addBookItem("Java coding rules");
			//Thread.sleep(5000);
			shoppingCart.addBookItem("EJB3 developers guide");
			System.out.println("Printing Cart Items:");
			//Thread.sleep(10000);
			ArrayList<String> cartItems = shoppingCart.getCartItems();
			for (String book : (ArrayList<String>) cartItems) {
				System.out.println(book);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
