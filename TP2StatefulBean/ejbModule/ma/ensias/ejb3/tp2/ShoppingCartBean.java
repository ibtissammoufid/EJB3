package ma.ensias.ejb3.tp2;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

/**
 * Session Bean implementation class ShoppingCartBean
 */
@Stateful
@LocalBean
public class ShoppingCartBean implements ShoppingCartBeanRemote, ShoppingCartBeanLocal {

    ArrayList<String> cartItems;
	
    public ShoppingCartBean() {
    	cartItems = new ArrayList<>();
    }
    
    public void addBookItem(String book) {
    	cartItems.add(book);
    	}
    	public void removeBookItem(String book) {
    	cartItems.remove(book);
    	}
    	public void setCartItems(ArrayList<String> cartItems) {
    	this.cartItems = cartItems;
    	}
    	public ArrayList<String> getCartItems() {
    	return cartItems;
    	}

}
