import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/TaxiCompanyDB";
    private static final String USER = "root";  // 替换为你的数据库用户名
    private static final String PASSWORD = "qcyt0513";  // 替换为你的数据库密码
    private static Connection connection;

    static {
        try {
            // 确保加载 MySQL 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("无法加载数据库驱动：" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("数据库连接成功");
            } catch (SQLException e) {
                System.err.println("数据库连接失败：" + e.getMessage());
                e.printStackTrace();
            }
        }
        return connection;
    }
}