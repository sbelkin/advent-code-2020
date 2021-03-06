package io.sbelkin.adventcode.day01to07;

import io.sbelkin.adventcode.Shared;
import java.util.List;

public class Executor {

  private static final Shared shared = new Shared();

  public static void main(String[] args) {
    day7();
  }

  public static void day7() {
    Day7 day7 = new Day7();
  }

  public static void day6() {
    Day6 day6 = new Day6();
    List<String> listDefault = shared
        .readFileInputString("src/main/resources/day6_input_default.txt");
    List<String> listInput = shared.readFileInputString("src/main/resources/day6_input.txt");
    System.out.println(day6.countAllAnswers(listDefault)); // expected: 11
    System.out.println(day6.countAllAnswers(listInput));
    System.out.println(day6.countAllAnswersPart2(listDefault)); // expected: 6
    System.out.println(day6.countAllAnswersPart2(listInput));
  }

  public static void day5() {
    Day5 day5 = new Day5();
    List<String> listOfStrings = List.of("BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL");
    List<String> listOfFBFBBFFRLR = List.of("FBFBBFFRLR");
    List<String> listOfFFFBFFFRLL = List.of("FFFBFFFRLL");
    List<String> listInput = shared.readFileInputString("src/main/resources/day5_input.txt");
    System.out.println(day5.findHighestSeatId(listOfFBFBBFFRLR)); // highest id: 357
    System.out.println(day5.findHighestSeatId(listOfFFFBFFFRLL)); // highest id: 357
    System.out.println(day5.findHighestSeatId(listOfStrings)); // highest id: 820
    System.out.println(day5.findHighestSeatId(listInput));
    System.out.println(day5.findYourSeatId(listInput));
  }

  public static void day4() {
    Day4 day4 = new Day4();
    List<String> listOfStrings = shared
        .readFileInputString("src/main/resources/day4_input_default.txt");
    List<String> listOfStrings2 = shared
        .readFileInputString("src/main/resources/day4_input_default_valid.txt");
    List<String> listInput = shared.readFileInputString("src/main/resources/day4_input.txt");
    System.out.println(day4.countValidPassports(listOfStrings)); // expected 2
    System.out.println(day4.countValidPassports(listInput));
    System.out.println(day4.countValidPassportsPart2(listOfStrings2)); // expected 4
    System.out.println(day4.countValidPassportsPart2(listInput));

  }

  public static void day3() {
    Day3 day3 = new Day3();
    List<String> testRowsOfTrees = shared
        .readFileInputString("src/main/resources/day3_input_default.txt");
    List<String> rowsOfTrees = shared.readFileInputString("src/main/resources/day3_input.txt");
    System.out.println(day3.treesEncounteredOnToboggan(testRowsOfTrees)); // expected 7
    System.out.println(day3.treesEncounteredOnToboggan(rowsOfTrees));

    System.out
        .println(day3.treesEncounterOverSlopesMultipliedPart2(testRowsOfTrees)); //expected 336
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
    System.out.println(day1.expenseReportValidationPart1(testInput)); // Expected 514579
    System.out.println(day1.expenseReportValidationPart1(integers));
    System.out.println(day1.expenseReportValidationPart2Attempt3(testInput));

  }
}
