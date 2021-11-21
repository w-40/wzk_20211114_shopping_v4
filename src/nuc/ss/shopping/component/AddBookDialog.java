package nuc.ss.shopping.component;
/**
 * @author：wzk
 * @desc：电商购物平台-添加图书对话框
 */
import nuc.ss.shopping.db.BookDataSet;
import nuc.ss.shopping.entity.Book;
import nuc.ss.shopping.entity.Category;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static nuc.ss.shopping.frame.AddBookFrame.*;

public class AddBookDialog extends JDialog {

    public AddBookDialog(Frame jf, String title, boolean modal) {
        super(jf, title, modal);
        this.setSize(665, 370);
        this.setLocation(635, 330);


        Box vBox = Box.createVerticalBox();

        //组装图书编号
        Box bidBox = Box.createHorizontalBox();
        JLabel bidLabel = new JLabel("图书编号：");
        JTextField bidField = new JTextField(15);

        bidBox.add(bidLabel);
        bidBox.add(Box.createHorizontalStrut(20));
        bidBox.add(bidField);

        //组装图书名称
        Box nameBox = Box.createHorizontalBox();
        JLabel nameLabel = new JLabel("图书名称：");
        JTextField nameField = new JTextField(15);

        nameBox.add(nameLabel);
        nameBox.add(Box.createHorizontalStrut(20));
        nameBox.add(nameField);

        //组装图书作者
        Box authorBox = Box.createHorizontalBox();
        JLabel authorLabel = new JLabel("图书作者：");
        JTextField authorField = new JTextField(15);

        authorBox.add(authorLabel);
        authorBox.add(Box.createHorizontalStrut(20));
        authorBox.add(authorField);

        //组装图书价格
        Box priceBox = Box.createHorizontalBox();
        JLabel priceLabel = new JLabel("图书价格：");
        JTextField priceField = new JTextField(15);

        priceBox.add(priceLabel);
        priceBox.add(Box.createHorizontalStrut(20));
        priceBox.add(priceField);

        //组装图书库存
        Box numBox = Box.createHorizontalBox();
        JLabel numLabel = new JLabel("图书库存：");
        JTextField numField = new JTextField(15);

        numBox.add(numLabel);
        numBox.add(Box.createHorizontalStrut(20));
        numBox.add(numField);

        //组装图书一级类目
        Box firstCategoryBox = Box.createHorizontalBox();
        JLabel firstCategoryLabel = new JLabel("图书一级类目：");
        JComboBox<String> firstCategory = new JComboBox<String>();
        firstCategory.addItem("工具类");
        firstCategory.addItem("小说类");
        firstCategory.setPreferredSize(new Dimension(210, 20));

        firstCategoryBox.add(firstCategoryLabel);
        firstCategoryBox.add(Box.createHorizontalStrut(20));
        firstCategoryBox.add(firstCategory);

        //组装图书二级类目
        Box secondCategoryBox = Box.createHorizontalBox();
        JLabel secondCategoryLabel = new JLabel("图书二级类目：");

        JComboBox<String> secondCategory = new JComboBox<String>();

        //工具类
        secondCategory.addItem("数学类");
        secondCategory.addItem("计算机类");
        secondCategory.addItem("土木类");

        //小说类
        secondCategory.addItem("科幻类");
        secondCategory.addItem("言情类");
        secondCategory.addItem("名著类");

        secondCategoryBox.add(secondCategoryLabel);
        secondCategoryBox.add(Box.createHorizontalStrut(20));

        secondCategoryBox.add(secondCategory);


        //组装按钮
        Box btnBox = Box.createHorizontalBox();
        JButton addBtn = new JButton("添加");


        btnBox.add(addBtn);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(bidBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(nameBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(authorBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(priceBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(numBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(firstCategoryBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(secondCategoryBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(btnBox);

        //为了左右有间距，在vBox外层封装一个水平的Box，添加间隔
        Box hBox = Box.createHorizontalBox();
        hBox.add(Box.createHorizontalStrut(20));
        hBox.add(vBox);
        hBox.add(Box.createHorizontalStrut(20));

        this.add(hBox);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookDataSet bds = new BookDataSet();

                //获取用户录入
                String bid = bidField.getText().trim();
                Category category = new Category(bid, "", "");
                String name = nameField.getText().trim();
                String author = authorField.getText().trim();
                float price = Float.parseFloat(priceField.getText());
                int num = Integer.parseInt(numField.getText());
                category.setFirstLevel(firstCategory.getSelectedItem().toString());
                category.setSecondLevel(secondCategory.getSelectedItem().toString());
                Book book = new Book(bid,name,author,price,num,category);

                boolean flag = bds.addBook(book);
                List<Book> books = bds.getBooks();
                /*for (Book book1 : books) {
                    System.out.println(book1);
                }*/

                Object[] colName = {"书籍编号", "书籍名称", "书籍作者","价格","库存", "书籍分类"};

                Object[][] newB = new Object[books.size()][6];

                for (int i = 0; i < books.size(); i++) {
                    System.out.println(books.get(i));
                }

                for (int i = 0; i < books.size(); i++) {
                    newB[i][0] = books.get(i).getId();
                    newB[i][1] = books.get(i).getName();
                    newB[i][2] = books.get(i).getAuthor();
                    newB[i][3] = books.get(i).getPrice();
                    newB[i][4] = books.get(i).getNum();
                    newB[i][5] = books.get(i).getCategory();
                }
                bookModel.setDataVector(newB, colName);
               // System.out.println("-------------------------------------------------");
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
