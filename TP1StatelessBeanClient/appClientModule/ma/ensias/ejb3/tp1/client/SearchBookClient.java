package ma.ensias.ejb3.tp1.client;

import java.util.List;
import java.util.Properties;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ma.ensias.ejb3.tp1.SearchBookFacadeRemote;

public class SearchBookClient {
	
	public SearchBookClient() {
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("hello");
		
		SearchBookClient searchFacadeTest = new SearchBookClient();
		searchFacadeTest.doTest();
	}

	
	InitialContext getInitialContext() throws javax.naming.NamingException {
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,"org.wildfly.naming.client.WildFlyInitialContextFactory");
		p.put(Context.URL_PKG_PREFIXES,"jboss.naming:org.jnp.interfaces");
		p.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		
		
		return new InitialContext(p);
		}
	
	void doTest() {
		try {
		InitialContext ic = getInitialContext();
		System.out.println("SearchFacade Lookup");
		SearchBookFacadeRemote searchFacade = (SearchBookFacadeRemote)ic.lookup("ejb:/TP1StatelessBean/SearchBookFacade!ma.ensias.ejb3.tp1.SearchBookFacadeRemote");
		System.out.println("Searching books");
		
		List<String> bookList = searchFacade.bookSearch("java");
		System.out.println("Printing books list");
		for (String book : (List<String>) bookList) {
		System.out.println(" -- " + book);
		}
		} catch (NamingException e) {
		e.printStackTrace();
		}
		}
	
	 @AroundInvoke
	    public Object timerLog(InvocationContext ctx) throws Exception {
	    	String beanClassName = ctx.getClass().getName();
	    	String businessMethodName = ctx.getMethod().getName();
	    	String target = beanClassName + "." + businessMethodName;
	    	long startTime = System.currentTimeMillis();
	    	System.out.println("Invoking " + target);
	    	try {
	    	return ctx.proceed();
	    	} finally {
	    	System.out.println("Exiting " + target);
	    	long totalTime = System.currentTimeMillis() - startTime;
	    	System.out.println("Business method {" +
	    	businessMethodName + "} in " + beanClassName + " takes " + totalTime +
	    	"ms to execute");
	    	}
	    	
	    }
}
