package nuc.ss.shopping.entity;
/**
 * @author：wzk
 * @desc：电商购物平台实体类：购物车
 */

import nuc.ss.shopping.db.BookDataSet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ShoppingCart {
    public static Map<Book,Integer> carts = new HashMap<Book,Integer>();
    private volatile boolean isUpdate = false;
    private BookDataSet bds = new BookDataSet();
    public boolean isUpdate() {
        return isUpdate;
    }

    public Map<Book, Integer> getCarts() {
        return carts;
    }

    public void setCarts(Map<Book, Integer> carts) {
        ShoppingCart.carts = carts;
    }

    public void setUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public boolean buy(Book book, Integer quantity) throws StockException {

        Integer num = carts.get(book);

        if (book.getNum() < quantity) {
            throw new StockException("您买的" + book.getName() + "仅剩" + book.getNum() + ",库存不足！");
        }

        if (num != null) {
            num += quantity;
        } else {
            num = quantity;
        }

        carts.put(book, num);

        this.isUpdate = true;

        book.setNum(book.getNum() - quantity);

        return true;
    }

    public boolean remove(Book book,Integer removeNum) throws StockException {

        Integer cartBookNum = carts.get(book);

        if (cartBookNum == null) {
            throw new StockException("您还没有购买" + book.getName());
        }

//        int i = 0;
//        for (Map.Entry<Book, Integer> entry : carts.entrySet()) {
//            if (cartBookNum > entry.getValue()){
//                throw new StockException("您购买的" + book.getName() + "不足" + cartBookNum + );
//            }
//        }
        if (cartBookNum < removeNum) {
            throw new StockException("您购买的" + book.getName() + "不足" + removeNum + "本");
        }



        carts.remove(book);

        this.isUpdate = true;
        book.setNum(book.getNum() + cartBookNum);
        return true;
    }

    public String toString() {

        StringBuffer buffer = new StringBuffer("");

        Set<Book> keys = carts.keySet();
        Iterator<Book> iterator = keys.iterator();

        while (iterator.hasNext()) {

            Book book = iterator.next();
            buffer.append("您" + "购买了" + book + carts.get(book) + "件\r\n");
        }

        return buffer.toString();
    }

    public boolean clear() {
        carts.clear();
        return true;
    }
}
