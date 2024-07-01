import java.util.List;

public interface Shiftable {
    List<Shift> getShifts();
    int getWorkingDaysInMonth(int month, int year);
    double calculateSalary(int month, int year);
}