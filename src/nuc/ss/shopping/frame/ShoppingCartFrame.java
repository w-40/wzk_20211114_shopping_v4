package nuc.ss.shopping.frame;
/**
 * @author:wzk
 * @desc 电商购物平台，购物cn主界面
 */

import nuc.ss.shopping.component.CartAddDialog;
import nuc.ss.shopping.component.CartRemoveDialog;
import nuc.ss.shopping.entity.Book;
import nuc.ss.shopping.entity.ShoppingCart;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;


public class ShoppingCartFrame {
    private JTable shoppingCartTable;
    public static DefaultTableModel cartModel;
    JFrame jf = new JFrame("电商购物平台_购物车详情");

    //@Test
    public void init() {
        ShoppingCart sc = new ShoppingCart();
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

        Map<Book, Integer> cartMap = sc.getCarts();
        Object[][] c = new Object[cartMap.size()][3];
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
        shoppingCartTable = new JTable(cartModel);
        JTableHeader cartTableHeader = shoppingCartTable.getTableHeader();
        cartTableHeader.setFont(new Font("", Font.BOLD, 20));
        shoppingCartTable.setPreferredSize(new Dimension(750, 200));
        shoppingCartTable.setFont(new Font("", Font.LAYOUT_NO_LIMIT_CONTEXT, 18));
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);
        shoppingCartTable.setDefaultRenderer(Object.class, tcr);

        jp.add(topBtnBox);
        JScrollPane js = new JScrollPane(shoppingCartTable);
        jp.add(sep);
        jp.add(js);
        jf.add(jp);

        jf.setVisible(true);

    }
}
