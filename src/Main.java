import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaxiCompany company = new TaxiCompany();

        // 添加30名司机
        for (int i = 1; i <= 30; i++) {
            Driver driver = new Driver("Driver" + i);
            company.addDriver(driver);
        }

        // 添加25辆车
        for (int i = 1; i <= 25; i++) {
            Car car = new Car("CAR" + String.format("%03d", i));
            company.addCar(car);
        }

        // 为每个司机添加一些班次
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        Random random = new Random();

        for (int i = 1; i <= 30; i++) {
            Driver driver = company.findDriverByName("Driver" + i);
            for (int j = 0; j < 36; j++) { // 每个司机36个班次
                // 生成2024年内的随机日期
                long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
                LocalDate randomDate = startDate.plusDays(random.nextInt((int) daysBetween + 1));

                Car car = company.getCars().get(j % company.getCars().size());
                driver.addShift(new Shift(randomDate, 6 + random.nextInt(3), car)); // 工作时长随机在6到8小时之间
            }
        }

        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            System.out.println("\n请选择一个操作：");
            System.out.println("1. 查询某一司机的上班天数和薪酬");
            System.out.println("2. 查询某司机驾驶过的车辆");
            System.out.println("3. 查询某天上班的司机");
            System.out.println("4. 退出程序");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 处理换行符

            switch (choice) {
                case 1:
                    System.out.print("输入司机姓名: ");
                    String driverName = scanner.nextLine();
                    System.out.print("输入查询月份 (1-12): ");
                    int month = scanner.nextInt();
                    System.out.print("输入查询年份 (例如 2024): ");
                    int year = scanner.nextInt();
                    scanner.nextLine(); // 处理换行符

                    Shiftable driver = company.findDriverByName(driverName);
                    if (driver != null) {
                        int workingDays = driver.getWorkingDaysInMonth(month, year);
                        double salary = driver.calculateSalary(month, year);
                        System.out.println(driverName + " 在 " + year + " 年 " + month + " 月工作了 " + workingDays + " 天，薪酬为 " + salary + " 元。");
                    } else {
                        System.out.println("未找到名为 " + driverName + " 的司机。");
                    }
                    break;

                case 2:
                    System.out.print("输入司机姓名: ");
                    driverName = scanner.nextLine();
                    driver = company.findDriverByName(driverName);
                    if (driver != null) {
                        List<Vehicle> carsDriven = company.getCarsDrivenByDriver(driverName);
                        System.out.println(driverName + " 驾驶过的车辆:");
                        for (Vehicle car : carsDriven) {
                            System.out.println(car.getPlateNumber());
                        }
                    } else {
                        System.out.println("未找到名为 " + driverName + " 的司机。");
                    }
                    break;

                case 3:
                    System.out.print("输入查询日期 (例如 2024-06-15): ");
                    String dateString = scanner.nextLine();
                    LocalDate date = LocalDate.parse(dateString, dateFormatter);
                    List<Shiftable> workingDrivers = company.getDriversWorkingOnDate(date);
                    System.out.println("在 " + date + " 上班的司机:");
                    for (Shiftable workingDriver : workingDrivers) {
                        if (workingDriver instanceof Driver) {
                            System.out.println(((Driver) workingDriver).getName());
                        }
                    }
                    break;
                    case 4:
                    System.out.println("退出程序。");
                    scanner.close();
                    return;

                default:
                    System.out.println("无效的选择，请重新输入。");
            }
        }
    }
}