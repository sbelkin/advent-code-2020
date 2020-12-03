package io.sbelkin.adventcode;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Part #1
 *
 * Before you leave, the Elves in accounting just need you to fix your expense report (your puzzle
 * input); apparently, something isn't quite adding up. Specifically, they need you to find the two
 * entries that sum to 2020 and then multiply those two numbers together.
 *
 * For example, suppose your expense report contained the following:
 *
 * 1721 979 366 299 675 1456 In this list, the two entries that sum to 2020 are 1721 and 299.
 * Multiplying them together produces 1721 * 299 = 514579, so the correct answer is 514579.
 *
 * Of course, your expense report is much larger. Find the two entries that sum to 2020; what do you
 * get if you multiply them together?
 *
 * Part 2: The Elves in accounting are thankful for your help; one of them even offers you a
 * starfish coin they had left over from a past vacation. They offer you a second one if you can
 * find three numbers in your expense report that meet the same criteria.
 *
 * Using the above example again, the three entries that sum to 2020 are 979, 366, and 675.
 * Multiplying them together produces the answer, 241861950.
 *
 * In your expense report, what is the product of the three entries that sum to 2020?
 */
public class Day1 {

  // Runtime 2N and Space N - where N is size of input
  public Integer expenseReportValidationPart1(List<Integer> input) {
    Set<Integer> integersDifferencesFromValue = new HashSet<>();
    // Fill set with values that are differences of 2020;
    for (Integer integer : input) {
      Integer value = 2020 - integer;
      integersDifferencesFromValue.add(value);
    }
    // now that we a populated set we iterate again looking for the matching value.
    for (Integer integer : input) {
      if (integersDifferencesFromValue.contains(integer)) {
        return integer * (2020 - integer);
      }
    }
    // not sure what to do if not found.
    return -1;
  }

  // Attempt to write a better algorithm for solving part 2
  // Can reduce Space usage
  // Space: N
  // Runtime: N^2+N-> N^2
  public Integer expenseReportValidationPart2Attempt2(List<Integer> input) {
    Set<Integer> integersDifferencesFromValue = new HashSet<>();
    // Fill set with values that are differences of 2020;
    for (Integer integer : input) {
      Integer value = 2020 - integer;
      integersDifferencesFromValue.add(value);
    }
    for (Integer integer : input) {
      for (Integer integer2 : input) {
        if (integersDifferencesFromValue.contains(integer+integer2) && !integer.equals(integer2)) {
          Integer integer3 = 2020-integer-integer2;
          return integer*integer2*integer3;
        }
      }
    }

    // Will need to think more.
    return -1;
  }

  // Hmmm with 3 numbers this become a bit more complicate it can be brute forced by creating a map
  // based on the set values with those size would be N*N-1 of input.
  // runtime N^2+2N -> N^2 and space N^2+N -> N^2 - where N is size of input so should look to see if we can do this better.
  public Integer expenseReportValidationPart2(List<Integer> input) {
    Set<Integer> integersDifferencesFromValue = new HashSet<>();
    Map<Integer, Integer> integersDifferencesFromValuePart2 = new HashMap<>();
    // Fill set with values that are differences of 2020;
    for (Integer integer : input) {
      Integer value = 2020 - integer;
      integersDifferencesFromValue.add(value);
    }
    // Fill set with values that are differences of 2020;
    for (Integer integer : input) {
      for (Integer setInteger : integersDifferencesFromValue) {
        // probably want to exclude same number and we assuming numbers don't repeat;
        if (2020 - integer != setInteger) {
          // do we care if we are over 0? since then we won't have an expense under 0?
          integersDifferencesFromValuePart2.put(setInteger - integer, integer);
        }
      }
    }
    // now that we a populated set we iterate again looking for the matching value.
    for (Integer integer : input) {
      if (integersDifferencesFromValuePart2.containsKey(integer)) {
        return integer * integersDifferencesFromValuePart2.get(integer) * (2020 - integer
            - integersDifferencesFromValuePart2.get(integer));
      }
    }
    // not sure what to do if not found.
    return -1;
  }



}
