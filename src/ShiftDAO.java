import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShiftDAO {
    private Connection connection;

    public ShiftDAO() {
        this.connection = DatabaseManager.getConnection();
        if (this.connection == null) {
            throw new IllegalStateException("无法建立数据库连接");
        }
    }

    public List<Shift> getShiftsByDriver(String driverName) throws SQLException {
        String query = "SELECT s.date, s.hours, c.plateNumber " +
                "FROM Shifts s " +
                "JOIN Drivers d ON s.driver_id = d.id " +
                "JOIN Cars c ON s.car_id = c.id " +
                "WHERE d.name = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, driverName);
        ResultSet resultSet = statement.executeQuery();

        List<Shift> shifts = new ArrayList<>();
        while (resultSet.next()) {
            LocalDate date = resultSet.getDate("date").toLocalDate();
            int hours = resultSet.getInt("hours");
            String plateNumber = resultSet.getString("plateNumber");
            Car car = new Car(plateNumber);
            Driver driver = new Driver(driverName);
            Shift shift = new Shift(date, hours, driver, car);
            shifts.add(shift);
        }
        return shifts;
    }

    public List<Shift> getShiftsByDate(LocalDate date) throws SQLException {
        String query = "SELECT s.hours, c.plateNumber, d.name " +
                "FROM Shifts s " +
                "JOIN Drivers d ON s.driver_id = d.id " +
                "JOIN Cars c ON s.car_id = c.id " +
                "WHERE s.date = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setDate(1, java.sql.Date.valueOf(date));
        ResultSet resultSet = statement.executeQuery();

        List<Shift> shifts = new ArrayList<>();
        while (resultSet.next()) {
            int hours = resultSet.getInt("hours");
            String plateNumber = resultSet.getString("plateNumber");
            String driverName = resultSet.getString("name");
            Car car = new Car(plateNumber);
            Driver driver = new Driver(driverName);
            Shift shift = new Shift(date, hours, driver, car);
            shifts.add(shift);
        }
        return shifts;
    }
}