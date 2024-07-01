import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaxiCompany {
    private List<Driver> drivers;
    private List<Car> cars;

    public TaxiCompany() {
        this.drivers = new ArrayList<>();
        this.cars = new ArrayList<>();
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public List<Car> getCars() {
        return cars;
    }

    public Driver findDriverByName(String name) {
        return drivers.stream()
                .filter(driver -> driver.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<Vehicle> getCarsDrivenByDriver(String driverName) {
        Driver driver = findDriverByName(driverName);
        if (driver == null) {
            return new ArrayList<>();
        }
        return driver.getShifts().stream()
                .map(Shift::getCar)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Shiftable> getDriversWorkingOnDate(LocalDate date) {
        return drivers.stream()
                .filter(driver -> driver.getShifts().stream().anyMatch(shift -> shift.getDate().equals(date)))
                .collect(Collectors.toList());
    }
}