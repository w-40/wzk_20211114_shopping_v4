package nuc.ss.shopping.db;

import nuc.ss.shopping.entity.Book;
import nuc.ss.shopping.entity.Category;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookDataSet {
    public static List<Book> books = new ArrayList<Book>();

    /*public void test() {
        String bid = new String("123");
        String name = new String("123");
        String author = new String("456");
        float price = 123;
        int num = 123;
        Category category = new Category("123", "工具", "计算机");
        Book b1 = new Book(bid, name, author, price, num, category);

        String bid1 = new String("123");
        String name1 = new String("123");
        String author1 = new String("456");
        float price1 = 123;
        int num1 = 123;
        Category category1 = new Category("123", "工具", "计算机");
        Book b2 = new Book(bid, name, author, price, num, category);

    public BookDataSet() {
       // books.add(b1);
       // books.add(b2);
    }
    }*/

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public boolean addBook(Book book) {
        books.add(book);
        return true;
    }
}