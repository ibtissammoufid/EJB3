package ma.ensias.ejb3.tp1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

@Stateless
@LocalBean
public class SearchBookFacade implements SearchBookFacadeRemote, SearchBookFacadeLocal {

    public SearchBookFacade() {
        // TODO Auto-generated constructor stub
    }
    
    public List<String> bookSearch(String bookType) {
    	List<String> bookList = new ArrayList<String>();
    	if (bookType.equals("java")) {
    	bookList.add("Java for dummies");
    	bookList.add("Beginnig Java 6");
    	} else if (bookType.equals("C++")) {
    	bookList.add("C++ for dummies");
    	}
    	return bookList;
    	}
    
    HashMap<String, String> countryBookMap = new HashMap<String, String>();
    @PostConstruct
    public void initializeCountryBookList() {
    countryBookMap.put("Australia", "Welcome to Australia");
    countryBookMap.put("Australia", "Australia History");
    countryBookMap.put("Morocco", "Welcome to Morocco");
    countryBookMap.put("Morocco", "Morocco History");
    }
    
    @PreDestroy
    public void destroyBookList() {
    countryBookMap.clear();
    }
    
    public List<String> SearchBookByCountry(String country){
    	List<String> bookList = new ArrayList<String>();
    	if (country.equals("Morocco")) {
    	bookList.add("Java's book");
    	bookList.add(" la boite � merveilles");
    	} else if (country.equals("Autre")) {
    	bookList.add("Book autre");
    	}
    	return bookList;
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
