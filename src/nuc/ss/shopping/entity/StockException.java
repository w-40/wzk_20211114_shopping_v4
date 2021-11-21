package nuc.ss.shopping.entity;

public class StockException extends Exception{
    public StockException(){
        super();
    }

    public StockException(String message){
        super(message);
    }
}
