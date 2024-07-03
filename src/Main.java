import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ShiftDAO shiftDAO = new ShiftDAO();
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            System.out.println("选择操作：");
            System.out.println("1. 查询司机班次及薪酬情况");
            System.out.println("2. 查询某天班次");

            System.out.println("0. 退出");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 清除换行符

            switch (choice) {
                case 1:
                    System.out.println("输入司机姓名：");
                    String queryDriverName = scanner.nextLine();
                    try {
                        List<Shift> driverShifts = shiftDAO.getShiftsByDriver(queryDriverName);
                        System.out.println("司机 " + queryDriverName + " 的班次：");
                        for (Shift driverShift : driverShifts) {
                            System.out.println("日期：" + driverShift.getDate() +
                                    ", 小时数：" + driverShift.getHours() + ",薪酬："+driverShift.getHours()*80+
                                    ", 车辆：" + driverShift.getCar().getPlateNumber());
                        }
                    } catch (SQLException e) {
                        System.err.println("查询班次失败：" + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("输入查询日期 (格式：yyyy-MM-dd)：");
                    String queryDateStr = scanner.nextLine();
                    LocalDate queryDate = LocalDate.parse(queryDateStr, formatter);
                    try {
                        List<Shift> dateShifts = shiftDAO.getShiftsByDate(queryDate);
                        System.out.println("日期 " + queryDate + " 的班次：");
                        for (Shift dateShift : dateShifts) {
                            System.out.println("司机：" + dateShift.getDriver().getName() +
                                    ", 小时数：" + dateShift.getHours() +
                                    ", 车辆：" + dateShift.getCar().getPlateNumber());
                        }
                    } catch (SQLException e) {
                        System.err.println("查询班次失败：" + e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("退出系统。");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("无效的选择，请重新选择。");
            }
        }
    }
}