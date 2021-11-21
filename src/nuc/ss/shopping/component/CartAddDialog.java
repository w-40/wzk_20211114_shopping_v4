package nuc.ss.shopping.component;

import nuc.ss.shopping.db.BookDataSet;
import nuc.ss.shopping.entity.Book;
import nuc.ss.shopping.entity.Category;
import nuc.ss.shopping.entity.ShoppingCart;
import nuc.ss.shopping.entity.StockException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CartAddDialog extends JDialog {
    public CartAddDialog(Frame jf, String title, boolean modal) {
        super(jf, title, modal);
        this.setSize(453, 161);
        this.setLocation(635, 330);
        Box vBox = Box.createVerticalBox();

        //组装书名输入框
        Box bookNameBox = Box.createHorizontalBox();
        JLabel bookNameLabel = new JLabel("请输入你要添加的书名：     ");
        JTextField bookNameField = new JTextField(15);
        bookNameBox.add(bookNameLabel);
        bookNameBox.add(Box.createHorizontalStrut(20));
        bookNameBox.add(bookNameField);

        //组装数量输入框
        Box numBox = Box.createHorizontalBox();
        JLabel numLabel = new JLabel("请输入你要添加的书的数量：");
        JTextField numField = new JTextField(15);
        numBox.add(numLabel);
        numBox.add(Box.createHorizontalStrut(10));
        numBox.add(numField);

        //组装按钮
        Box btnBox = Box.createHorizontalBox();
        JButton addBtn = new JButton("确认添加");
        btnBox.add(addBtn);

        vBox.add(Box.createVerticalStrut(10));
        vBox.add(bookNameBox);
        vBox.add(Box.createVerticalStrut(10));
        vBox.add(numBox);
        vBox.add(Box.createVerticalStrut(10));
        vBox.add(btnBox);

        // 为了左右有间距，在vBox外层封装一个水平的Box，添加间隔
        Box hBox = Box.createHorizontalBox();
        hBox.add(Box.createHorizontalStrut(20));
        hBox.add(vBox);
        hBox.add(Box.createHorizontalStrut(20));

        this.add(hBox);

        ShoppingCart shoppingCart = new ShoppingCart();

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookDataSet bds = new BookDataSet();
                String name = bookNameField.getText().trim();
                String bid = "";
                String author = "";
                float price = 0;
                int num = 0;
                Category category = null;

                int cartAddNum = Integer.parseInt(numField.getText());
                List<Book> books = bds.getBooks();
                for (Book book : books) {
                    if (name.equals(book.getName())){
                        bid = book.getId();
                        author = book.getAuthor();
                        price = book.getPrice();
                        num = book.getNum();
                        category = book.getCategory();
                    }
                }
                Book book = new Book(bid,name,author,price,num,category);

                boolean flag = false;
                try {
                    flag = shoppingCart.buy(book,cartAddNum);
                } catch (StockException stockException) {
                    JOptionPane.showMessageDialog(jf,stockException);
                }
                if (flag == true){
                    JOptionPane.showMessageDialog(jf,"添加成功");
                    dispose();
                }else {
                    JOptionPane.showMessageDialog(jf,"添加失败");
                }
            }
        });
    }
}
