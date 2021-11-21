package nuc.ss.shopping.frame;
/**
 * @author：wzk
 * @desc：电商购物平台-主管理页面
 */
import nuc.ss.shopping.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainManagementInterface {
    JFrame jf = new JFrame("主管理界面");
    public void init(User u) {
        JPanel jp = new JPanel();
        jf.setLocation(720, 280);
        jf.setSize(800, 650);
        JLabel l_hello = new JLabel("你好," + u.getName() + ",你是尊贵的管理员", JLabel.LEFT);
        l_hello.setFont(new Font("", Font.BOLD, 20));
        JLabel l_from = new JLabel("来自于:" + u.getCity(), JLabel.RIGHT);
        l_from.setFont(new Font("", Font.BOLD, 20));

        Box vBox = Box.createVerticalBox();

        Box topBox = Box.createHorizontalBox();
        topBox.add(l_hello);
        topBox.add(Box.createHorizontalStrut(360));
        topBox.add(l_from);
        topBox.add(Box.createHorizontalStrut(50));
        //设置分割线
        JSeparator sep = new JSeparator(SwingConstants.CENTER);
        sep.setPreferredSize(new Dimension(1200, 10));

        jp.add(Box.createVerticalStrut(40));

        Box addBookBtnBox = Box.createHorizontalBox();
        Box userManageBtnBox = Box.createHorizontalBox();
        Box backBtnBox = Box.createHorizontalBox();

        JButton addBookBtn = new JButton("图书管理");
        addBookBtn.setFont(new Font("宋体",Font.BOLD,35));
        addBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddBookFrame().init(u);
            }
        });

        JButton userManageBtn = new JButton("用户管理");
        userManageBtn.setFont(new Font("宋体",Font.BOLD,35));
        userManageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserInfoFrame().init();
            }
        });

        JButton backBtn = new JButton("返回");
        backBtn.setFont(new Font("宋体",Font.BOLD,35));
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
            }
        });

        addBookBtnBox.add(addBookBtn);
        userManageBtnBox.add(userManageBtn);
        backBtnBox.add(backBtn);

        vBox.add(topBox);
        vBox.add(sep);
        vBox.add(Box.createVerticalStrut(70));
        vBox.add(addBookBtnBox);
        vBox.add(Box.createVerticalStrut(70));
        vBox.add(userManageBtnBox);
        vBox.add(Box.createVerticalStrut(70));
        vBox.add(backBtnBox);

        jp.add(vBox);

        jf.add(jp);
        jf.setVisible(true);
    }
}
