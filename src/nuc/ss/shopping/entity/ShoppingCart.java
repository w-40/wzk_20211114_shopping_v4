package nuc.ss.shopping.entity;
/**
 * @author：wzk
 * @desc：电商购物平台实体类：购物车
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ShoppingCart extends HashMap<Book, Integer> {
    public static Map<Book,Integer> carts = new HashMap<Book,Integer>();
    private volatile boolean isUpdate = false;

    public boolean isUpdate() {
        return isUpdate;
    }

    public Map<Book, Integer> getCarts() {
        return carts;
    }

    public void setUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public boolean buy(Book book, Integer quantity) throws StockException {

        Integer num = super.get(book);

        if (book.getNum() < quantity) {
            throw new StockException("您买的" + book.getName() + "仅剩" + book.getNum() + ",库存不足！");
        }

        if (num != null) {
            num += quantity;
        } else {
            num = quantity;
        }

        super.put(book, num);

        this.isUpdate = true;

        book.setNum(book.getNum() - quantity);

        return true;
    }

    public void remove(Book book) throws StockException {

        Integer cartBookNum = super.get(book);

        if (cartBookNum == null) {
            throw new StockException("您还没有购买" + book.getName());
        }

        super.remove(book);


        this.isUpdate = true;
        book.setNum(book.getNum() + cartBookNum);
    }

    public String toString() {

        StringBuffer buffer = new StringBuffer("");

        Set<Book> keys = super.keySet();
        Iterator<Book> iterator = keys.iterator();

        while (iterator.hasNext()) {

            Book book = iterator.next();
            buffer.append("您" + "购买了" + book + super.get(book) + "件\r\n");
        }

        return buffer.toString();
    }
}
