package io.sbelkin.adventcode;

import java.util.List;

public class Executor {

  public static void main(String[] args) {
    Day2 day2 = new Day2();
    Shared shared = new Shared();
    List<String> passwords = shared.readFileInputString("src/main/resources/day2_input.txt");
    List<String> testInput = List.of("1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc");
    System.out.println(day2.validPasswordsPart1(testInput)); // Expected 2
    System.out.println(day2.validPasswordsPart1(passwords));
    System.out.println(day2.validPasswordsPart2(testInput)); // Expected 1
    System.out.println(day2.validPasswordsPart2(passwords));
  }

  public static void day1() {
    Day1 day1 = new Day1();
    Shared shared = new Shared();
    List<Integer> integers = shared.readFileInputInteger("src/main/resources/day1_input.txt");
    List<Integer> testInput = List.of(1721, 979, 366, 299, 675, 1456);
    System.out.println(day1.expenseReportValidationPart1(testInput)); // Expected 514579
    System.out.println(day1.expenseReportValidationPart1(integers));

    System.out.println(day1.expenseReportValidationPart2(testInput)); // Expected 241861950
    System.out.println(day1.expenseReportValidationPart2(integers));
  }
}
