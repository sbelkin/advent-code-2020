package io.sbelkin.adventcode;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 *  --- Part 1 ---
 * To try to debug the problem, they have created a list (your puzzle input) of passwords (according
 * to the corrupted database) and the corporate policy when that password was set.
 *
 * For example, suppose you have the following list:
 *
 * 1-3 a: abcde
 * 1-3 b: cdefg
 * 2-9 c: ccccccccc
 *
 * Each line gives the password policy and then the password. The password policy indicates the lowest
 * and highest number of times a given letter must appear for the password to be valid. For example,
 * 1-3 a means that the password must contain a at least 1 time and at most 3 times.
 *
 * In the above example, 2 passwords are valid. The middle password, cdefg, is not; it contains no
 * instances of b, but needs at least 1. The first and third passwords are valid: they contain one a
 * or nine c, both within the limits of their respective policies.
 *
 * How many passwords are valid according to their policies?
 *
 * --- Part Two ---
 * While it appears you validated the passwords correctly, they don't seem to be what the Official Toboggan Corporate Authentication System is expecting.
 *
 * The shopkeeper suddenly realizes that he just accidentally explained the password policy rules from
 * his old job at the sled rental place down the street! The Official Toboggan Corporate Policy actually works a little differently.
 *
 * Each policy actually describes two positions in the password, where 1 means the first character,
 * 2 means the second character, and so on. (Be careful; Toboggan Corporate Policies have no concept of "index zero"!)
 * Exactly one of these positions must contain the given letter. Other occurrences of the letter are
 * irrelevant for the purposes of policy enforcement.
 *
 * Given the same example list from above:
 *
 * 1-3 a: abcde is valid: position 1 contains a and position 3 does not.
 * 1-3 b: cdefg is invalid: neither position 1 nor position 3 contains b.
 * 2-9 c: ccccccccc is invalid: both position 2 and position 9 contain c.
 * How many passwords are valid according to the new interpretation of the policies?
 */
public class Day2 {

  public Integer validPasswordsPart1(List<String> passwords) {
    Integer countValidPasswords = 0;
    // Convert to password objects
    List<PasswordObject> passwordObjects = convertListStringToPasswords(passwords);
    // now validate passwords
    for (PasswordObject passwordObject : passwordObjects) {
      int countLetterOfPolicy = 0;
      for (char passwordChar : passwordObject.getPassword()) {
        if (passwordChar == passwordObject.getPolicyLetter()) {
          countLetterOfPolicy++;
        }
      }
      if (passwordObject.getLow() <= countLetterOfPolicy &&
          countLetterOfPolicy <= passwordObject.getHigh()) {
        countValidPasswords++;
      }
    }
    return countValidPasswords;
  }

  public Integer validPasswordsPart2(List<String> passwords) {
    Integer countValidPasswords = 0;
    // Convert to password objects
    List<PasswordObject> passwordObjects = convertListStringToPasswords(passwords);
    // now validate passwords
    for (PasswordObject passwordObject : passwordObjects) {
      boolean checkPosition1 = false;
      boolean checkPosition2 = false;
      // We are reusing same object just Low means position1 and high means position2;
      int position1 = passwordObject.getLow()-1;
      int position2 = passwordObject.getHigh()-1;
      if (passwordObject.getPassword().length > position1) {
        if (passwordObject.getPassword()[position1] == passwordObject.getPolicyLetter()) {
          checkPosition1 = true;
        }
      }
      if (passwordObject.getPassword().length > position2) {
        if (passwordObject.getPassword()[position2] == passwordObject.getPolicyLetter()) {
          checkPosition2 = true;
        }
      }

      // Only one check can ever be valid
      if (checkPosition1 && !checkPosition2 ||
              !checkPosition1 && checkPosition2) {
        countValidPasswords++;
      }
    }
    return countValidPasswords;
  }

  // Making the assumption of no data errors otherwise would need additional checks
  public List<PasswordObject> convertListStringToPasswords(List<String> passwords) {
    List<PasswordObject> passwordObjects = new ArrayList<>();
    for (String password : passwords) {
      // Split on : to get password and the "policy"
      String[] splitPassword = password.split(":");
      PasswordObject passwordObject = new PasswordObject();
      // remove starting space of password and convert to char array for easy use later
      char[] chars = splitPassword[1].strip().toCharArray();
      passwordObject.setPassword(chars);
      // Now split on space?
      String[] splitPolicy = splitPassword[0].split(" ");
      char c = splitPolicy[1].charAt(0);
      passwordObject.setPolicyLetter(c);
      // Now split on dash to get the last part;
      String[] splitPolicyLowHigh = splitPolicy[0].split("-");
      passwordObject.setLow(Integer.parseInt(splitPolicyLowHigh[0]));
      passwordObject.setHigh(Integer.parseInt(splitPolicyLowHigh[1]));
      passwordObjects.add(passwordObject);
    }
    return passwordObjects;
  }

  @Data
  class PasswordObject {

    private int low;
    private int high;
    private char policyLetter;
    private char[] password;
  }
}
