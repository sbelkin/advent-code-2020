package io.sbelkin.adventcode.day01to07;

import java.math.BigInteger;
import java.util.List;

/**
 * https://adventofcode.com/2020/day/3
 *
 * --- Part 1 --- Due to the local geology, trees in this area only grow on exact integer
 * coordinates in a grid. You make a map (your puzzle input) of the open squares (.) and trees (#)
 * you can see. For example:
 *
 * ..##....... #...#...#.. .#....#..#. ..#.#...#.# .#...##..#. ..#.##..... .#.#.#....# .#........#
 * #.##...#... #...##....# .#..#...#.# These aren't the only trees, though; due to something you
 * read about once involving arboreal genetics and biome stability, the same pattern repeats to the
 * right many times. You start on the open square (.) in the top-left corner and need to reach the
 * bottom (below the bottom-most row on your map).
 *
 * The toboggan can only follow a few specific slopes (you opted for a cheaper model that prefers
 * rational numbers); start by counting all the trees you would encounter for the slope right 3,
 * down 1:
 *
 * From your starting position at the top-left, check the position that is right 3 and down 1. Then,
 * check the position that is right 3 and down 1 from there, and so on until you go past the bottom
 * of the map.
 *
 * The locations you'd check in the above example are marked here with O where there was an open
 * square and X where there was a tree:
 *
 * ..##.........##.........##.........##.........##.........##.......  --->
 * #..O#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..
 * .#....X..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.
 * ..#.#...#O#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#
 * .#...##..#..X...##..#..#...##..#..#...##..#..#...##..#..#...##..#.
 * ..#.##.......#.X#.......#.##.......#.##.......#.##.......#.##.....  --->
 * .#.#.#....#.#.#.#.O..#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#
 * .#........#.#........X.#........#.#........#.#........#.#........#
 * #.##...#...#.##...#...#.X#...#...#.##...#...#.##...#...#.##...#...
 * #...##....##...##....##...#X....##...##....##...##....##...##....#
 * .#..#...#.#.#..#...#.#.#..#...X.#.#..#...#.#.#..#...#.#.#..#...#.#  ---> In this example,
 * traversing the map using this slope would cause you to encounter 7 trees.
 *
 * --- Part Two --- Time to check the rest of the slopes - you need to minimize the probability of a
 * sudden arboreal stop, after all.
 *
 * Determine the number of trees you would encounter if, for each of the following slopes, you start
 * at the top-left corner and traverse the map all the way to the bottom:
 *
 * Right 1, down 1. Right 3, down 1. (This is the slope you already checked.) Right 5, down 1. Right
 * 7, down 1. Right 1, down 2. In the above example, these slopes would find 2, 7, 3, 4, and 2
 * tree(s) respectively; multiplied together, these produce the answer 336.
 */
public class Day3 {



  public Integer treesEncounteredOnToboggan(List<String> grid) {
    // Travel 3 Right and 1 down each time starting at point 0-0 (doesnt include start point?)
    // Because we of how this is setup we dont need to make an entire grid to keep traversing right
    // we can iterate over the same grid just offset the traveled right location once it goes out of bounds
    //
    char tree = '#';
    int countTreesSeen = 0;
    // Assume all rows are the same.
    int rowSize = grid.get(0).length() - 1;
    int traveledRight = 3;
    int traveled = 3;
    for (int row = 1; row < grid.size(); row++) {
      if (traveled > rowSize) {
        traveled -= (rowSize + 1);
      }
      char geoPoint = grid.get(row).charAt(traveled);
      if (geoPoint == tree) {
        countTreesSeen++;
      }
      traveled += traveledRight;
    }
    return countTreesSeen;
  }

  public BigInteger treesEncounterOverSlopesMultipliedPart2(List<String> grid) {
    BigInteger firstRight1Dow1 = treesEncounteredOnTobogganDynamic(grid, 1, 1);
    BigInteger secondRight3Down1 = treesEncounteredOnTobogganDynamic(grid, 3, 1);
    BigInteger thirdRight5Down1 = treesEncounteredOnTobogganDynamic(grid, 5, 1);
    BigInteger fourthRight7Down1 = treesEncounteredOnTobogganDynamic(grid, 7, 1);
    BigInteger fifthRight1Down2 = treesEncounteredOnTobogganDynamic(grid, 1, 2);
    return firstRight1Dow1.multiply(secondRight3Down1).multiply(thirdRight5Down1)
        .multiply(fourthRight7Down1).multiply(fifthRight1Down2);
  }

  // Use big integer since this multiplication is going ot be large
  public BigInteger treesEncounteredOnTobogganDynamic(List<String> grid, int travelRight,
      int travelDown) {
    char tree = '#';
    int countTreesSeen = 0;
    // Assume all rows are the same.
    int rowSize = grid.get(0).length() - 1;
    int traveled = travelRight;
    for (int row = travelDown; row < grid.size(); row += travelDown) {
      if (traveled > rowSize) {
        traveled -= (rowSize + 1);
      }
      char geoPoint = grid.get(row).charAt(traveled);
      if (geoPoint == tree) {
        countTreesSeen++;
      }
      traveled += travelRight;
    }
    return BigInteger.valueOf(countTreesSeen);
  }

}
