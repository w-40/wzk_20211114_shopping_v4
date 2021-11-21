package nuc.ss.shopping.frame;

import nuc.ss.shopping.db.UserDataSet;
import nuc.ss.shopping.entity.User;
import org.junit.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UserInfoFrame extends JFrame {
    private JTable t_user;
    public static DefaultTableModel userModel;


    public UserInfoFrame() {
        // 窗体初始化
        this.setTitle("电商购物平台_用户信息(管理员)");
        this.setSize(400,350);
        this.setLocation(800,400);
        init();
        this.setVisible(true);
    }

    public void init() {
        UserDataSet uds = new UserDataSet();
        List<User> users = uds.getUsers();
        Object[][] u = new Object[users.size()][6];
        for (int i = 0; i < users.size(); i++) {
            u[i][0] = users.get(i).getId();
            u[i][1] = users.get(i).getName();
            u[i][2] = users.get(i).getPassword();
            u[i][3] = users.get(i).getSex();
            u[i][4] = users.get(i).getCity();
            u[i][5] = users.get(i).getType();
        }
        Object[] colName = {"账号","姓名","密码","性别", "城市", "用户类型"};
        userModel = new DefaultTableModel(u, colName);
        JTable userTable = new JTable(userModel);
        this.add(new JScrollPane(userTable));
    }
}
