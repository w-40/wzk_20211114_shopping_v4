package nuc.ss.shopping.entity;
/**
 * @author：wzk
 * @desc：电商购物平台异常类：库存不足
 */
public class StockException extends Exception{
    public StockException(){
        super();
    }

    public StockException(String message){
        super(message);
    }
}
