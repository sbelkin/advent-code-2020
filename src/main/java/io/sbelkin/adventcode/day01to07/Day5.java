package io.sbelkin.adventcode.day01to07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Instead of zones or groups, this airline uses binary space partitioning to seat people.
 * A seat might be specified like FBFBBFFRLR, where F means "front", B means "back", L means "left", and R means "right".
 *
 * The first 7 characters will either be F or B; these specify exactly one of the 128 rows on the plane (numbered 0 through 127).
 * Each letter tells you which half of a region the given seat is in. Start with the whole list of rows;
 * the first letter indicates whether the seat is in the front (0 through 63) or the back (64 through 127).
 * The next letter indicates which half of that region the seat is in, and so on until you're left with exactly one row.
 *
 * For example, consider just the first seven characters of FBFBBFFRLR:
 *
 * Start by considering the whole range, rows 0 through 127.
 * F means to take the lower half, keeping rows 0 through 63.
 * B means to take the upper half, keeping rows 32 through 63.
 * F means to take the lower half, keeping rows 32 through 47.
 * B means to take the upper half, keeping rows 40 through 47.
 * B keeps rows 44 through 47.
 * F keeps rows 44 through 45.
 * The final F keeps the lower of the two, row 44.
 * The last three characters will be either L or R; these specify exactly one of the 8 columns of seats on the plane (numbered 0 through 7).
 * The same process as above proceeds again, this time with only three steps. L means to keep the lower half, while R means to keep the upper half.
 *
 * For example, consider just the last 3 characters of FBFBBFFRLR:
 *
 * Start by considering the whole range, columns 0 through 7.
 * R means to take the upper half, keeping columns 4 through 7.
 * L means to take the lower half, keeping columns 4 through 5.
 * The final R keeps the upper of the two, column 5.
 * So, decoding FBFBBFFRLR reveals that it is the seat at row 44, column 5.
 *
 * Every seat also has a unique seat ID: multiply the row by 8, then add the column. In this example, the seat has ID 44 * 8 + 5 = 357.
 *
 * Here are some other boarding passes:
 *
 * BFFFBBFRRR: row 70, column 7, seat ID 567.
 * FFFBBBFRRR: row 14, column 7, seat ID 119.
 * BBFFBBFRLL: row 102, column 4, seat ID 820.
 * As a sanity check, look through your list of boarding passes. What is the highest seat ID on a boarding pass?
 *
 * --- Part Two ---
 * Ding! The "fasten seat belt" signs have turned on. Time to find your seat.
 *
 * It's a completely full flight, so your seat should be the only missing boarding pass in your list. However, there's a catch: some of the seats at the very front and back of the plane don't exist on this aircraft, so they'll be missing from your list as well.
 *
 * Your seat wasn't at the very front or back, though; the seats with IDs +1 and -1 from yours will be in your list.
 *
 * What is the ID of your seat?
 */
public class Day5 {

  public Integer findHighestSeatId(List<String> boardingPassList) {
    int highestSeatId = -1;
    for (String boardingPass : boardingPassList) {
      int currentSeatId = boardingPassId(boardingPass);
      if (currentSeatId > highestSeatId) {
        highestSeatId = currentSeatId;
      }
    }
    return highestSeatId;
  }

  public Integer findYourSeatId(List<String> boardingPassList) {
    // Fill list with existing ids and then search for the 1 missing
    List<Integer> boardingPassIdsInList = new ArrayList<>(boardingPassList.size());
    for (String boardingPass : boardingPassList) {
      Integer boardingPassId = boardingPassId(boardingPass);
      boardingPassIdsInList.add(boardingPassId);
    }
    // sort boarding ids
    Collections.sort(boardingPassIdsInList);
    // iterate until find the missing one
    int previousId = boardingPassIdsInList.get(0);
    for (Integer pointer = 1; pointer < boardingPassIdsInList.size(); pointer++) {
      int currentId = boardingPassIdsInList.get(pointer);
      if (previousId+1!=currentId){
        return previousId+1;
      }
      previousId=currentId;
    }
    // Hit error
    return -1;
  }

  private static final Integer rowMultiplier = 8;
  public Integer boardingPassId(String boardingPass) {
    Integer row = findRow(boardingPass.substring(0,7));
    Integer column = findColumn(boardingPass.substring(7));
    return row * rowMultiplier +column;
  }

  private static final char Front = 'F';
  private static final char Back = 'B';
  public Integer findRow(String rowString) {
    return findValue(rowString.toCharArray(), 0, 127, Front, Back);
  }

  private static final char Right = 'R';
  private static final char Left = 'L';
  public Integer findColumn(String columnString) {
    return findValue(columnString.toCharArray(), 0, 7, Left, Right);
  }

  public Integer findValue(char[] chars, int lowRange, int highRange, char low, char high) {
    int middle = (highRange + lowRange) / 2;
    for (int pointer = 0; pointer < chars.length; pointer++) {
      char current = chars[pointer];
      if (current == high) {
        lowRange = middle+1;
      } else {
        highRange = middle;
      }
      middle = (highRange + lowRange) / 2;
    }
    if (chars[chars.length-1] == low) {
      return lowRange;
    }
    return highRange;
  }
}
