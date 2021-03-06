public class DateDistance {
    public static boolean isLeapYear(long year) {
        if (year % 4 == 0) {
                if (year % 100 == 0) {
                        if (year % 400 == 0) {
                                return true;
                        } else {
                                return false;
                        }

                } else {
                        return true;
                }
        } else {
                return false;
        }
    }

    public static long daysInMonth(long year, long month) {
        switch ((int)month) {
        case 1:  return 31;
        case 2:  if (isLeapYear((int)year)) {
            return 29;
        } else {
            return 28;
        }
        case 3:  return 31;
        case 4:  return 30;
        case 5:  return 31;
        case 6:  return 30;
        case 7:  return 31;
        case 8:  return 31;
        case 9:  return 30;
        case 10: return 31;
        case 11: return 30;
        case 12: return 31;
        default: return 0;
        }
    }

    public static boolean isValidDate(long month, long day, long year ) {
        boolean isValidYear = false;
        boolean isValidMonth = false;
        boolean isValidDay = false;
        if (year > 0) {
                isValidYear = true;
        }
        if (month > 0 && month <= 12) {
                isValidMonth = true;
        }
        if (day > 0 && day <= daysInMonth(year, month)) {
                isValidDay = true;
        }
        if (month == 2) {
                if (isLeapYear(year) && day <= 29) {
                        isValidDay = true;
                }
        }
        return isValidYear && isValidMonth && isValidDay;
    }

    public static long daysBetween(long month0, long day0, long year0, long month1, long day1, long year1) {
        long result = 0;
        long daysPerYear0 = 0;
        long daysPerYear1 = 0;
        long daysPerMonths0 = 0;
        long daysPerMonths1 = 0;

        for (long x = 1; x < year0; x++) {
                if (!isLeapYear(x)) {
                        daysPerYear0 = daysPerYear0 + 365;
                }
                if (isLeapYear(x)) {
                        daysPerYear0 = daysPerYear0 + 366;
                }
        }

        for (long y = 1; y < year1; y++) {
                if (!isLeapYear(y)) {
                        daysPerYear1 = daysPerYear1 + 365;
                }
                if (isLeapYear(y)) {
                        daysPerYear1 = daysPerYear1 + 366;
                }
        }

        for (long m = 1; m < month0; m++) {
                daysPerMonths0 = daysPerMonths0 + daysInMonth(m, year0);
        }

        for (long n = 1; n < month1; n++) {
                daysPerMonths1 = daysPerMonths1 + daysInMonth(n, year1);
        }

        if (isValidDate(month0, day0, year0) && isValidDate(month1, day1, year1)) {
                long initialDays = daysPerMonths0 + day0 + daysPerYear0;
                long finalDays = daysPerMonths1 + day1 + daysPerYear1;
                result = Math.abs(finalDays - initialDays);
        }
        return result;
    }



    public static String dayOfTheWeek(long month, long day, long year ) {
        long initDate = daysBetween(month, day, year, 1, 1, 2000) % 7;
        if (year < 2000) {
                initDate = initDate + 5;
        }
        switch ((int)initDate) {
        case 0: return "Sunday";
        case 1: return "Monday";
        case 2: return "Tuesday";
        case 3: return "Wednesday";
        case 4: return "Thursday";
        case 5: return "Friday";
        case 6: return "Saturday";
        default: return "Invalid Date";
        }
    }


    public static String monthInYear(long month) {
        switch ((int)month) {
        case 1: return "January";
        case 2: return "February";
        case 3: return "March";
        case 4: return "April";
        case 5: return "May";
        case 6: return "June";
        case 7: return "July";
        case 8: return "August";
        case 9: return "September";
        case 10: return "October";
        case 11: return "November";
        case 12: return "December";
        default: return "Invalid Month";
        }
    }

    public static String longformDate(long month, long day, long year) {
        return dayOfTheWeek(month, day, year) + ", " + monthInYear(month) + " " + day + ", " + year;
    }

    public static void main (String[] args) {
        try {
                long day0 = Integer.parseInt(args[0]);
                long month0 = Integer.parseInt(args[1]);
                long year0 = Integer.parseInt(args[2]);
                long day1 = Integer.parseInt(args[3]);
                long month1 = Integer.parseInt(args[4]);
                long year1 = Integer.parseInt(args[5]);
                if (isValidDate(day0, month0, year0) && isValidDate(day1, month1, year1)) {
                        System.out.println("There are " + daysBetween(day0, month0, year0, day1, month1, year1) + " days between " + longformDate(day0, month0, year0) + " and " + longformDate(day1, month1, year1) + "!");
                } else {
                        System.out.println("Invalid date entered!");
                }
        }
        catch (Exception e) {
                System.out.println("Usage instructions: java DateDistance " + "<day0> <month0> <year0> <day1> <month1> <year1>");
        }
    }
}
