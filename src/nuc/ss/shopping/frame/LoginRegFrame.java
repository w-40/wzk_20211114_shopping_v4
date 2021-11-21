package nuc.ss.shopping.frame;
/**
 * @author：wzk
 * @desc：电商购物平台-注册登录界面
 */
import nuc.ss.shopping.db.BookDataSet;
import nuc.ss.shopping.db.UserDataSet;
import nuc.ss.shopping.entity.Book;
import nuc.ss.shopping.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

public class LoginRegFrame {
    JFrame jf = new JFrame("王志凯的电商购物平台");

    public void init() throws Exception {
        //设置窗口相关的属性
        //窗口居中
        jf.setLocation(800, 300);
        jf.setSize(500, 350);
        //jf.setResizable(false);//设置窗口大小不可变

        JPanel jp = new JPanel();

        //组装登录相关的元素
        Box vBox = Box.createVerticalBox();
        //组装用户名
        Box uBox = Box.createHorizontalBox();
        JLabel uLabel = new JLabel("  用户名:");
        JTextField uField = new JTextField(15);

        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(uField);

        // 组装用户类型
        Box tBox = Box.createVerticalBox();
        JLabel tLabel = new JLabel("用户类型:");
        JComboBox<String> userType = new JComboBox<String>();
        userType.addItem("管理员");
        userType.addItem("普通用户");
        JPanel checkPanel = new JPanel();
        checkPanel.add(tLabel);
        checkPanel.add(Box.createHorizontalStrut(150));
        checkPanel.add(userType);
        checkPanel.setPreferredSize(new Dimension(200, 30));

        tBox.add(Box.createHorizontalStrut(20));
        tBox.add(checkPanel);


        //组装密码
        Box pBox = Box.createHorizontalBox();
        JLabel pLabel = new JLabel("  密 码：");
        JPasswordField pField = new JPasswordField(15);

        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(20));
        pBox.add(pField);


        //组装按钮
        Box btnBox = Box.createHorizontalBox();
        JButton loginBtn = new JButton("登录");
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*BookDataSet bds = new BookDataSet();
                List<Book> books = bds.getBooks();*/
                UserDataSet uds = new UserDataSet();
                List<User> users = uds.getUsers();
                //Set<User> users = uds.getUsers();
                if (users.isEmpty()) {
                    JOptionPane.showMessageDialog(jf, "请先注册");
                } else {
                    for (int i = 0; i < users.size(); i++) {
                        if (uField.getText().equals(users.get(i).getId())) {
                            if (pField.getText().equals(users.get(i).getPassword())) {
                                if (userType.getSelectedItem().equals("管理员")) {
                                    JOptionPane.showMessageDialog(jf, "登录成功");
                                    new MainManagementInterface().init(users.get(i));
                                } else {
                                    JOptionPane.showMessageDialog(jf, "登录成功");
                                    new InquireFrame().init(users.get(i));
                                }
                            } else {
                                JOptionPane.showMessageDialog(jf, "账号或密码错误");
                            }
                        }
                    }
                }

            }
        });

        JButton resetBtn = new JButton("重置");
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uField.setText("");
                pField.setText("");
            }
        });

        JButton registBtn = new JButton("点我注册");
        registBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterFrame();
            }
        });


        btnBox.add(loginBtn);
        btnBox.add(Box.createHorizontalStrut(50));
        btnBox.add(resetBtn);
        btnBox.add(Box.createHorizontalStrut(50));
        btnBox.add(registBtn);

        vBox.add(Box.createVerticalStrut(50));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(tBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(pBox);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(btnBox);

        jp.add(vBox);
        jf.add(jp);
        jf.setVisible(true);
    }
}
