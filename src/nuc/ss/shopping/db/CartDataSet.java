package nuc.ss.shopping.db;

import nuc.ss.shopping.entity.Book;
import nuc.ss.shopping.entity.ShoppingCart;
import nuc.ss.shopping.entity.StockException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CartDataSet {
    public static Map<Book,Integer> carts = new HashMap<Book,Integer>();

    private volatile boolean isUpdate = false;

    public boolean isUpdate(){
        return isUpdate;
    }



    public void setUpdate(boolean isUpdate){
        this.isUpdate = isUpdate;
    }



    public Map<Book, Integer> getCarts() {
        return carts;
    }

    public void setCarts(Map<Book, Integer> carts) {
        CartDataSet.carts = carts;
    }

    public void buy(Book book, Integer quantity) throws StockException {

        Integer num = carts.get(book);

        if (book.getNum() < quantity){
            throw new StockException("您买的" + book.getName() + "仅剩" + book.getNum() + ",库存不足！");
        }

        if (num != null){
            num += quantity;
        }
        else{
            num = quantity;
        }
        carts.put(book, num);
        this.isUpdate = true;
        book.setNum(book.getNum() - quantity);
    }

    public void remove(Book book) throws StockException{

        Integer num = carts.get(book);

        if (num == null){
            throw new StockException("您还没有购买" + book.getName());
        }

        carts.remove(book);

        this.isUpdate = true;
        book.setNum(book.getNum() + num);
    }

    public String toString (){

        StringBuffer buffer = new StringBuffer("");

        Set<Book> keys = carts.keySet();
        Iterator<Book> or = keys.iterator();

        while (or.hasNext()){

            Book book = or.next();
            buffer.append("您" + "购买了" + book + carts.get(book) + "件\r\n");
        }
        return buffer.toString();
    }
}
