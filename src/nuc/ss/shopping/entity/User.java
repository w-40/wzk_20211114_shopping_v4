package nuc.ss.shopping.entity;

/**
 * @author wzk
 *
 * @description 显示注册窗体，用户输入用户信息，
 *  *              点击”注册“后到达显示用户信息的窗体
 *  *              相当于MVC模式中的model，用来封装数据
 */
public class User {
    private String id;// 账号
    private String name;// 姓名
    private String password;// 密码
    private char sex;// 性别
    private String city;// 城市
    private String type;// 类别（管理员或普通用户）
    private ShoppingCart shoppingCart;

    public User() {
    }

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public User(String id, String name, String password, char sex, String city, String type) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.city = city;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", city='" + city + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
