package nuc.ss.shopping.frame;

import nuc.ss.shopping.db.BookDataSet;
import nuc.ss.shopping.entity.Book;
import org.junit.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static nuc.ss.shopping.frame.AddBookFrame.bookModel;

public class InquireFrame {
    private JFrame jf = new JFrame("电商购物平台_商品查询页面(普通用户)");

    public void init() {
        JPanel jp = new JPanel();
        jf.setLocation(800, 300);
        jf.setSize(800, 650);
        JLabel l_hello = new JLabel("你好,王志凯", JLabel.LEFT);
        l_hello.setFont(new Font("你好,", Font.BOLD, 20));
        JLabel l_from = new JLabel("来自于:(山西省)10.163.192.4", JLabel.RIGHT);
        l_from.setFont(new Font("来自于:", Font.BOLD, 20));

        Box topBox = Box.createHorizontalBox();
        topBox.add(l_hello);
        topBox.add(Box.createHorizontalStrut(360));
        topBox.add(l_from);
        topBox.add(Box.createHorizontalStrut(50));

        //设置分割线
        JSeparator sep = new JSeparator(SwingConstants.CENTER);
        sep.setPreferredSize(new Dimension(1200, 10));

        jp.add(Box.createVerticalStrut(40));

        Box centerBox = Box.createHorizontalBox();
        JLabel bookName = new JLabel("书籍名:");
        bookName.setFont(new Font("书籍名:", Font.BOLD, 20));
        JTextField bookField = new JTextField(15);
        JLabel l_type = new JLabel("分类:");
        l_type.setFont(new Font("分类", Font.BOLD, 20));
        JComboBox<String> type = new JComboBox<String>();
        type.addItem("工具类");
        type.addItem("小说类");
        type.setPreferredSize(new Dimension(150, 20));
        JButton searchBtn = new JButton("查询");

        JButton cartBtn = new JButton("查看购物车");

        cartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShoppingCartFrame().init();
            }
        });

        centerBox.add(bookName);
        centerBox.add(Box.createHorizontalStrut(20));
        centerBox.add(bookField);
        centerBox.add(Box.createHorizontalStrut(40));
        centerBox.add(l_type);
        centerBox.add(Box.createHorizontalStrut(20));
        centerBox.add(type);
        centerBox.add(Box.createHorizontalStrut(40));
        centerBox.add(searchBtn);
        centerBox.add(Box.createHorizontalStrut(40));
        centerBox.add(cartBtn);
        centerBox.add(Box.createHorizontalStrut(20));

        Box bottomBox = Box.createHorizontalBox();

        BookDataSet bds = new BookDataSet();

        List<Book> books = bds.getBooks();

        Object[][] b = new Object[books.size()][6];
        for (int i = 0; i < books.size(); i++) {
            b[i][0] = books.get(i).getId();
            b[i][1] = books.get(i).getName();
            b[i][2] = books.get(i).getAuthor();
            b[i][3] = books.get(i).getPrice();
            b[i][4] = books.get(i).getNum();
            b[i][5] = books.get(i).getCategory();
        }

        Object[] colName = {"书籍编号", "书籍名称", "书籍作者", "库存", "书籍分类"};
        //bookModel.setDataVector(b, colName);
        //JTable bookTable = new JTable(b, colName);
        JTable bookTable = new JTable(bookModel);

        JTableHeader bookTableHeader = bookTable.getTableHeader();
        bookTableHeader.setFont(new Font("", Font.BOLD, 20));

        bottomBox.add(bookTable);
        bookTable.setPreferredSize(new Dimension(750, 200));
        bookTable.setFont(new Font("", Font.LAYOUT_NO_LIMIT_CONTEXT, 18));

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);
        bookTable.setDefaultRenderer(Object.class, tcr);

        jp.add(topBox);
        jp.add(sep);
        jp.add(centerBox);
        JScrollPane bottomJS = new JScrollPane(bookTable);
        bottomJS.setPreferredSize(new Dimension(750, 650));
        jp.add(bottomJS);
        jf.add(jp);
        jf.setVisible(true);
    }
}
