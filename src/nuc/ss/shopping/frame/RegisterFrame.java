package nuc.ss.shopping.frame;

import nuc.ss.shopping.db.UserDataSet;
import nuc.ss.shopping.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author wzk
 * @description 显示注册窗体，用户输入用户信息，
 *              点击”注册“后到达显示用户信息的窗体
 *              相当于MVC模式中的view
 */
public class RegisterFrame extends JFrame {
    UserDataSet uds = new UserDataSet();

    // 窗体上的组件
    private JLabel l_id, l_name, l_password1, l_password2, l_sex, l_city;
    private JTextField t_id, t_name;
    private JPasswordField p_password1, p_password2;
    private JRadioButton r_male, r_female;
    private JComboBox<String> c_city;
    private JButton b_reg, b_reset;

    public RegisterFrame() {
        //窗体的初始化
        this.setTitle("电商购物平台_注册页面");
        this.setSize(300, 450);
        this.setLocation(800, 300);

        init();

        this.setVisible(true);
    }

    public void init() {
        this.setLayout(new GridLayout(7, 2, 5, 5));

        l_id = new JLabel("账号", JLabel.CENTER);
        l_name = new JLabel("姓名", JLabel.CENTER);
        l_password1 = new JLabel("密码", JLabel.CENTER);
        l_password2 = new JLabel("确认密码", JLabel.CENTER);
        l_sex = new JLabel("性别", JLabel.CENTER);
        l_city = new JLabel("城市", JLabel.CENTER);

        t_id = new JTextField();
        t_name = new JTextField();

        p_password1 = new JPasswordField();
        p_password2 = new JPasswordField();

        ButtonGroup bg = new ButtonGroup();
        r_male = new JRadioButton("男");
        r_female = new JRadioButton("女");
        bg.add(r_male);
        bg.add(r_female);
        // 将两个单选按钮放到一个中间容器上
        JPanel p = new JPanel();
        p.add(r_male);
        p.add(r_female);

        c_city = new JComboBox<String>();
        c_city.addItem("山西");
        c_city.addItem("北京");
        c_city.addItem("上海");
        c_city.addItem("天津");
        c_city.addItem("重庆");

        b_reg = new JButton("注册");//事件源，一旦出现点击事件，收集用户信息，并显示到UserInfoFrame窗体上
        //在事件源上添加事件监听器，匿名内部类对象相当于MAC模式中的控制层
        b_reg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 从窗体收集用户信息
                String id = t_id.getText();
                String name = t_name.getText();
                String password1 = new String(p_password1.getPassword());
                String password2 = new String(p_password2.getPassword());
                // 两次密码不一致的处理
                if (!password1.equals(password2)) {
                    showMessage();
                    p_password1.setText("");
                    p_password2.setText("");
                    return;
                } else {

                }
                char sex = ' ';
                if (r_male.isSelected()) {
                    sex = '男';
                } else {
                    sex = '女';
                }
                String city = (String) c_city.getSelectedItem();

                // 用模型类封装收集到的信息
                User u = new User(id,name,password1,sex,city,"");
                uds.addUser(u);
                JOptionPane.showMessageDialog(p_password1,"注册成功,请登录");
                dispose();
                // 在用户信息窗体上展示注册的用户信息
                //new UserInfoFrame();
            }
        });

        b_reset = new JButton("重置");

        this.add(l_id);
        this.add(t_id);

        this.add(l_name);
        this.add(t_name);

        this.add(l_password1);
        this.add(p_password1);

        this.add(l_password2);
        this.add(p_password2);

        this.add(l_sex);
        this.add(p);

        this.add(l_city);
        this.add(c_city);

        this.add(b_reg);
        this.add(b_reset);
    }
    public void showMessage() {
        JOptionPane.showMessageDialog(this,"两次密码应该相等",
                "提示框",JOptionPane.WARNING_MESSAGE);
    }
}
