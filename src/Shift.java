import java.time.LocalDate;

public class Shift {
    private LocalDate date;
    private int hours;
    private Car car;

    public Shift(LocalDate date, int hours, Car car) {
        this.date = date;
        this.hours = hours;
        this.car = car;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getHours() {
        return hours;
    }

    public Car getCar() {
        return car;
    }

    public double getPayment() {
        // 假设每小时工资为100元
        return hours * 100;
    }
}