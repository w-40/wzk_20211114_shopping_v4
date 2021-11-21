package nuc.ss.shopping.frame;

import nuc.ss.shopping.component.CartAddDialog;
import nuc.ss.shopping.component.CartRemoveDialog;
import nuc.ss.shopping.db.CartDataSet;
import nuc.ss.shopping.entity.Book;
import nuc.ss.shopping.entity.ShoppingCart;
import org.junit.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;


public class ShoppingCartFrame {
    private JTable shoppingCartData;
    public static DefaultTableModel cartModel;
    JFrame jf = new JFrame("电商购物平台_购物车详情");

    //@Test
    public void init() {
        CartDataSet cds = new CartDataSet();
        JPanel jp = new JPanel();
        jf.setSize(600, 550);
        jf.setLocation(700, 340);

        Box topBtnBox = Box.createHorizontalBox();
        JButton addBtn = new JButton("添加");
        addBtn.setFont(new Font("宋体", Font.BOLD, 20));

        JButton deleteBtn = new JButton("移除");
        deleteBtn.setFont(new Font("宋体", Font.BOLD, 20));
        JButton clearBtn = new JButton("清空");
        clearBtn.setFont(new Font("宋体", Font.BOLD, 20));
        topBtnBox.add(addBtn);
        topBtnBox.add(Box.createHorizontalStrut(40));
        topBtnBox.add(deleteBtn);
        topBtnBox.add(Box.createHorizontalStrut(40));
        topBtnBox.add(clearBtn);

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CartAddDialog(jf, "购物车添加图书", true).setVisible(true);
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CartRemoveDialog(jf, "购物车移除图书", true).setVisible(true);
            }
        });


        //设置分割线
        JSeparator sep = new JSeparator(SwingConstants.CENTER);
        sep.setPreferredSize(new Dimension(1200, 10));

        jp.add(Box.createVerticalStrut(40));

        Map<Book, Integer> cartMap = cds.getCarts();
        Object[][] c = new Object[cartMap.size()][3];
        //Iterator iterator = cartMap.entrySet().iterator();
        int i = 0;
        for (Map.Entry<Book, Integer> entry : cartMap.entrySet()) {
            c[i][0] = entry.getKey().getName();
            c[i][1] = entry.getValue();
            c[i][2] = entry.getKey().getPrice() * entry.getValue();
            i++;
        }
        Object[] colName = {"书名", "数量", "总价"};
        cartModel = new DefaultTableModel(c, colName);
        //cartModel.setDataVector(c, colName);
        //cartModel = new DefaultTableModel(c, colName);
        shoppingCartData = new JTable(cartModel);

        jp.add(topBtnBox);
        JScrollPane js = new JScrollPane(shoppingCartData);
        jp.add(sep);
        jp.add(js);
        jf.add(jp);

        jf.setVisible(true);

    }
}