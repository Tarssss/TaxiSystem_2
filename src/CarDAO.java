import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    public void addCar(Car car) throws SQLException {
        String sql = "INSERT INTO Cars (plateNumber) VALUES (?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, car.getPlateNumber());
            stmt.executeUpdate();
        }
    }

    public List<Car> getAllCars() throws SQLException {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM Cars";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                cars.add(new Car(rs.getString("plateNumber")));
            }
        }
        return cars;
    }
}