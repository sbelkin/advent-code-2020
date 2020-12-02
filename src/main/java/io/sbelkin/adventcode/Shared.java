package io.sbelkin.adventcode;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Based on doing this before I believe most days will have a file input so I am making a shared area
// for file reading and possibly other functions.
public class Shared {

  // For now as I am not sure how the others inputs will be just assuming that it will be List of strings
  public List<String> readFileInputString(String file) {
    Path filePath = Paths.get(file);
    List<String> strings = new ArrayList<>();
    try (Scanner scanner = new Scanner(filePath)) {
      while (scanner.hasNextLine()) {
        strings.add(scanner.nextLine());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return strings;
  }

  // Adding this with a step conversion to Integer
  public List<Integer> readFileInputInteger(String file) {
    List<String> strings = readFileInputString(file);
    // Initialize to size of strings list;
    List<Integer> integers = new ArrayList<>(strings.size());
    for (String string : strings) {
      integers.add(Integer.valueOf(string));
    }
    if (strings.size() != integers.size()) {
      // Making this an unchecked exception that will throw error and require review of what happened in data.
      throw new RuntimeException("Error in conversion");
    }
    return integers;
  }
}
