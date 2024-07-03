import java.time.LocalDate;

public class Shift {
    private LocalDate date;
    private int hours;
    private Driver driver;
    private Car car;

    public Shift(LocalDate date, int hours, Driver driver, Car car) {
        this.date = date;
        this.hours = hours;
        this.driver = driver;
        this.car = car;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getHours() {
        return hours;
    }

    public Driver getDriver() {
        return driver;
    }

    public Car getCar() {
        return car;
    }
}