import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO {
    public void addDriver(Driver driver) throws SQLException {
        String sql = "INSERT INTO Drivers (name) VALUES (?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, driver.getName());
            stmt.executeUpdate();
        }
    }

    public Driver findDriverByName(String name) throws SQLException {
        String sql = "SELECT * FROM Drivers WHERE name = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Driver(rs.getString("name"));
            }
        }
        return null;
    }

    public List<Driver> getAllDrivers() throws SQLException {
        List<Driver> drivers = new ArrayList<>();
        String sql = "SELECT * FROM Drivers";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                drivers.add(new Driver(rs.getString("name")));
            }
        }
        return drivers;
    }
}