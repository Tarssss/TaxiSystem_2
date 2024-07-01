import java.util.ArrayList;
import java.util.List;

public class Driver implements Shiftable {
    private String name;
    private List<Shift> shifts;

    public Driver(String name) {
        this.name = name;
        this.shifts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addShift(Shift shift) {
        shifts.add(shift);
    }

    @Override
    public List<Shift> getShifts() {
        return shifts;
    }

    @Override
    public int getWorkingDaysInMonth(int month, int year) {
        return (int) shifts.stream()
                .filter(shift -> shift.getDate().getMonthValue() == month && shift.getDate().getYear() == year)
                .count();
    }

    @Override
    public double calculateSalary(int month, int year) {
        return shifts.stream()
                .filter(shift -> shift.getDate().getMonthValue() == month && shift.getDate().getYear() == year)
                .mapToDouble(Shift::getPayment)
                .sum();
    }
}