import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.Month;

public class Driver implements Shiftable {
    private int id;
    private String name;
    private List<Shift> shifts;

    public Driver(String name) {
        this.name = name;
        this.shifts = new ArrayList<>();
    }

    public Driver(int id, String name) {
        this.id = id;
        this.name = name;
        this.shifts = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addShift(Shift shift) {
        shifts.add(shift);
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    @Override
    public int getWorkingDaysInMonth(int month, int year) {
        // 计算在指定月份的工作天数
        return (int) shifts.stream()
                .filter(shift -> shift.getDate().getMonthValue() == month && shift.getDate().getYear() == year)
                .count();
    }

    @Override
    public double calculateSalary(int month, int year) {
        double hourlyRate = 50; // 假设每小时薪酬为50元
        return shifts.stream()
                .filter(shift -> shift.getDate().getMonthValue() == month && shift.getDate().getYear() == year)
                .mapToDouble(shift -> shift.getHours() * hourlyRate)
                .sum();
    }
}