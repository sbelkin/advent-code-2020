package io.sbelkin.adventcode;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The form asks a series of 26 yes-or-no questions marked a through z. All you need to do is identify
 * the questions for which anyone in your group answers "yes". Since your group is just you, this doesn't take very long.
 *
 * However, the person sitting next to you seems to be experiencing a language barrier and asks if you can help.
 * For each of the people in their group, you write down the questions for which they answer "yes", one per line. For example:
 *
 * abcx
 * abcy
 * abcz
 *
 * In this group, there are 6 questions to which anyone answered "yes": a, b, c, x, y, and z.
 * (Duplicate answers to the same question don't count extra; each question counts at most once.)
 *
 * Another group asks for your help, then another, and eventually you've collected answers from every group on the plane (your puzzle input).
 * Each group's answers are separated by a blank line, and within each group, each person's answers are on a single line. For example:
 *
 * abc
 *
 * a
 * b
 * c
 *
 * ab
 * ac
 *
 * a
 * a
 * a
 * a
 *
 * b
 * This list represents answers from five groups:
 *
 * The first group contains one person who answered "yes" to 3 questions: a, b, and c.
 * The second group contains three people; combined, they answered "yes" to 3 questions: a, b, and c.
 * The third group contains two people; combined, they answered "yes" to 3 questions: a, b, and c.
 * The fourth group contains four people; combined, they answered "yes" to only 1 question, a.
 * The last group contains one person who answered "yes" to only 1 question, b.
 * In this example, the sum of these counts is 3 + 3 + 3 + 1 + 1 = 11.
 *
 * For each group, count the number of questions to which anyone answered "yes". What is the sum of those counts?
 *
 * --- Part Two ---
 * As you finish the last group's customs declaration, you notice that you misread one word in the instructions:
 *
 * You don't need to identify the questions to which anyone answered "yes"; you need to identify the questions to which everyone answered "yes"!
 *
 * Using the same example as above:
 *
 * abc
 *
 * a
 * b
 * c
 *
 * ab
 * ac
 *
 * a
 * a
 * a
 * a
 *
 * b
 * This list represents answers from five groups:
 *
 * In the first group, everyone (all 1 person) answered "yes" to 3 questions: a, b, and c.
 * In the second group, there is no question to which everyone answered "yes".
 * In the third group, everyone answered yes to only 1 question, a. Since some people did not answer "yes" to b or c, they don't count.
 * In the fourth group, everyone answered yes to only 1 question, a.
 * In the fifth group, everyone (all 1 person) answered "yes" to 1 question, b.
 * In this example, the sum of these counts is 3 + 0 + 1 + 1 + 1 = 6.
 *
 * For each group, count the number of questions to which everyone answered "yes". What is the sum of those counts?
 */
public class Day6 {

  public Integer countAllAnswers(List<String> listOfAnswers) {
    Set<Character> groupAnswers = new HashSet<>();
    int totalAnswers = 0;
    for (String answer : listOfAnswers) {
      if (answer.isEmpty()) {
        //reset answer set and add to total
        totalAnswers+=groupAnswers.size();
        groupAnswers = new HashSet<>();
      } else {
        char[] chars = answer.toCharArray();
        for (char c : chars) {
          if (!groupAnswers.contains(c)){
            groupAnswers.add(c);
          }
        }
      }
    }
    totalAnswers+=groupAnswers.size();
    return totalAnswers;
  }

  public Integer countAllAnswersPart2(List<String> listOfAnswers) {
    // Now we need to find which everyone answered yes to so use
    Set<Character> sharedAnswers = new HashSet<>();
    boolean firstUser = true;
    int totalAnswers = 0;
    for (int line = 0; line<listOfAnswers.size(); line++) {
      String answer = listOfAnswers.get(line);
      if (answer.isEmpty()) {
        //reset answer set and add to total
        totalAnswers+=sharedAnswers.size();
        sharedAnswers = new HashSet<>();
        firstUser = true;
      } else {
        if (firstUser) {
          char[] charAnswers = answer.toCharArray();
          for (char c : charAnswers){
            sharedAnswers.add(c);
          }
          firstUser=false;
        } else {
          Set<Character> temp = new HashSet<>();
          for (Character c : sharedAnswers) {
            if (answer.contains(c.toString())){
              temp.add(c);
            }
          }
          sharedAnswers=temp;
        }
      }
    }
    // Add the last one since doesn't end with empty line.
    totalAnswers+=sharedAnswers.size();
    return totalAnswers;
  }

  public Integer countAllAnswersPart2Original(List<String> listOfAnswers) {
    // Now we need to find which everyone answered yes to
    Map<Character, Integer> groupAnswers = new HashMap<>();
    int countCurrentUsers = 0;
    int totalAnswers = 0;
    for (int line = 0; line<listOfAnswers.size(); line++) {
      String answer = listOfAnswers.get(line);
      if (answer.isEmpty()) {
        //reset answer set and add to total
        for (Map.Entry<Character,Integer> groupAnswer : groupAnswers.entrySet()) {
          if(groupAnswer.getValue()==countCurrentUsers){
            totalAnswers++;
          }
        }
        groupAnswers = new HashMap<>();
        countCurrentUsers=0;
      } else {
        countCurrentUsers++;
        char[] chars = answer.toCharArray();
        for (char c : chars) {
          int count = 1;
          if (groupAnswers.containsKey(c)){
            count = groupAnswers.get(c)+1;
          }
          groupAnswers.put(c, count);
        }
      }
    }
    // Iterate over one last time because doesn't end with empty line..
    for (Map.Entry<Character,Integer> groupAnswer : groupAnswers.entrySet()) {
      if(groupAnswer.getValue()==countCurrentUsers){
        totalAnswers++;
      }
    }
    return totalAnswers;
  }
}
