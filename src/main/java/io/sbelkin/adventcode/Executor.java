package io.sbelkin.adventcode;

import java.util.ArrayList;
import java.util.List;

public class Executor {

  private static final Shared shared = new Shared();

  public static void main(String[] args) {
    day3();
  }

  public static void day4() {

  }

  public static void day3() {
    Day3 day3 = new Day3();
    List<String> testRowsOfTrees = shared.readFileInputString("src/main/resources/day3_input_default.txt");
    List<String> rowsOfTrees = shared.readFileInputString("src/main/resources/day3_input.txt");
    System.out.println(day3.treesEncounteredOnToboggan(testRowsOfTrees)); // expected 7
    System.out.println(day3.treesEncounteredOnToboggan(rowsOfTrees));

    System.out.println(day3.treesEncounterOverSlopesMultipliedPart2(testRowsOfTrees)); //expected 336
    System.out.println(day3.treesEncounterOverSlopesMultipliedPart2(rowsOfTrees));
  }

  public static void day2() {
    Day2 day2 = new Day2();
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
//    System.out.println(day1.expenseReportValidationPart1(testInput)); // Expected 514579
//    System.out.println(day1.expenseReportValidationPart1(integers));
//
//
//    System.out.println(day1.expenseReportValidationPart2Attempt2(testInput));
//    System.out.println(day1.expenseReportValidationPart2Attempt2(integers));

    System.out.println(day1.expenseReportValidationPart2Attempt3(testInput));

  }
}
