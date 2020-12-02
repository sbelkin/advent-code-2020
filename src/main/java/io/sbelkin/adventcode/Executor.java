package io.sbelkin.adventcode;

import java.util.List;

public class Executor {

  public static void main(String[] args) {
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
