package nuc.ss.shopping.entity;
/**
 * @author：wzk
 * @desc：电商购物平台实体类：书籍
 */
public class Book {
    private String bid;
    private String name;
    private String author;
    private float price;
    private int num;
    private Category category;

    public Book() {
    }

    public Book(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public Book(String id, String name, String author, float price, int num, Category category) {
        this.bid = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.num = num;
        this.category = category;
    }

    public String getId() {
        return bid;
    }

    public void setId(String id) {
        this.bid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bid=" + bid +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", category=" + category +
                '}';
    }
}
