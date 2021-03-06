package a.Lifecycle_Restaurant_Ordering;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Map;

import a.getter_setter.Cart;
import a.getter_setter.Dishes;


/**
 * Created by TUSHAR on 09-04-18.
 */

public class Cart_Items {
  //  Map<String,String> restaurants;
   // Map<String,Dishes> dishes;
    static ArrayList<Cart> cartItems;
    static int totalQuantity;
    static Cart_Items cart_items;
    Context context;
    static int totalDishes;
    Cart_Items(Context context)
    {
        this.context = context;

    }
    public static Cart_Items getInstance(Context context)
    {
        if(cart_items == null) {
            cart_items = new Cart_Items(context);
            totalQuantity = 0;
            totalDishes = 0;
            cartItems = new ArrayList<>(50);
        }
        return cart_items;
    }
    public void addDish(Dishes dishes,String Rname,String RKey)
    {
        Log.d("HAR","Dish added: "+dishes.getDishName());
        int id = searchDish(dishes.getDishKey());
        if(id!=-1)
        {
            cartItems.get(id).setQuantity(dishes.getQuantity());
        }
        else {

            Cart NewCart = new Cart();
            NewCart.setDishKey(dishes.getDishKey());
            NewCart.setDishName(dishes.getDishName());
            NewCart.setResKey(RKey);
            NewCart.setDishPrice(dishes.getDishPrice());
            NewCart.setQuantity(dishes.getQuantity());
            NewCart.setResName(Rname);
            cartItems.add(NewCart);
            totalDishes++;
        }
        totalQuantity++;
    }
    public void removeDish(String Key)
    {
        int id = searchDish(Key);
        Log.d("HAR","Key: "+Key);
        int quantity = cartItems.get(id).getQuantity();
        if(quantity == 1)
        {
            cartItems.remove(id);
            totalDishes--;
        }
        else
            cartItems.get(id).setQuantity(quantity-1);
        totalQuantity--;
    }
    private int searchDish(String key)
    {
        Log.d("HAR","CART ITEMS");
        for(int i=0;i<totalDishes;i++)
        {
            //Log.d("HAR","Key: "+cartItems.get(i).getDishKey()+"  Name: "+cartItems.get(i).getDishName());
            if(cartItems.get(i).getDishKey() == key)
            {
                return i;
            }
        }
        return -1;
    }
    public int getTotalQuantity()
    {
        return totalQuantity;
    }
    public ArrayList<Cart> getCartItems()
    {
        return cartItems;
    }
    public int getTotalDishes()
    {
        return totalDishes;
    }

}
