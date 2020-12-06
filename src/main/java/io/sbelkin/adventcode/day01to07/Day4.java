package io.sbelkin.adventcode.day01to07;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * --- Part One ---
 *
 * The automatic passport scanners are slow because they're having trouble detecting which passports have all required fields. The expected fields are as follows:
 *
 * byr (Birth Year)
 * iyr (Issue Year)
 * eyr (Expiration Year)
 * hgt (Height)
 * hcl (Hair Color)
 * ecl (Eye Color)
 * pid (Passport ID)
 * cid (Country ID)
 * Passport data is validated in batch files (your puzzle input). Each passport is represented as a sequence of key:value pairs separated by spaces or newlines. Passports are separated by blank lines.
 *
 * Here is an example batch file containing four passports:
 *
 * ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
 * byr:1937 iyr:2017 cid:147 hgt:183cm
 *
 * iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
 * hcl:#cfa07d byr:1929
 *
 * hcl:#ae17e1 iyr:2013
 * eyr:2024
 * ecl:brn pid:760753108 byr:1931
 * hgt:179cm
 *
 * hcl:#cfa07d eyr:2025 pid:166559648
 * iyr:2011 ecl:brn hgt:59in
 * The first passport is valid - all eight fields are present. The second passport is invalid - it is missing hgt (the Height field).
 *
 * The third passport is interesting; the only missing field is cid, so it looks like data from North Pole Credentials, not a passport at all! Surely, nobody would mind if you made the system temporarily ignore missing cid fields. Treat this "passport" as valid.
 *
 * The fourth passport is missing two fields, cid and byr. Missing cid is fine, but missing any other field is not, so this passport is invalid.
 *
 * According to the above rules, your improved system would report 2 valid passports.
 *
 * Count the number of valid passports - those that have all required fields. Treat cid as optional. In your batch file, how many passports are valid?
 *
 * --- Part Two ---
 * The line is moving more quickly now, but you overhear airport security talking about how passports with invalid data are getting through. Better add some data validation, quick!
 *
 * You can continue to ignore the cid field, but each other field has strict rules about what values are valid for automatic validation:
 *
 * byr (Birth Year) - four digits; at least 1920 and at most 2002.
 * iyr (Issue Year) - four digits; at least 2010 and at most 2020.
 * eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
 * hgt (Height) - a number followed by either cm or in:
 * If cm, the number must be at least 150 and at most 193.
 * If in, the number must be at least 59 and at most 76.
 * hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
 * ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
 * pid (Passport ID) - a nine-digit number, including leading zeroes.
 * cid (Country ID) - ignored, missing or not.
 * Your job is to count the passports where all required fields are both present and valid according to the above rules. Here are some example values:
 *
 * byr valid:   2002
 * byr invalid: 2003
 *
 * hgt valid:   60in
 * hgt valid:   190cm
 * hgt invalid: 190in
 * hgt invalid: 190
 *
 * hcl valid:   #123abc
 * hcl invalid: #123abz
 * hcl invalid: 123abc
 *
 * ecl valid:   brn
 * ecl invalid: wat
 *
 * pid valid:   000000001
 * pid invalid: 0123456789
 * Here are some invalid passports:
 *
 * eyr:1972 cid:100
 * hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926
 *
 * iyr:2019
 * hcl:#602927 eyr:1967 hgt:170cm
 * ecl:grn pid:012533040 byr:1946
 *
 * hcl:dab227 iyr:2012
 * ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277
 *
 * hgt:59cm ecl:zzz
 * eyr:2038 hcl:74454a iyr:2023
 * pid:3556412378 byr:2007
 * Here are some valid passports:
 *
 * pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
 * hcl:#623a2f
 *
 * eyr:2029 ecl:blu cid:129 byr:1989
 * iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm
 *
 * hcl:#888785
 * hgt:164cm byr:2001 iyr:2015 cid:88
 * pid:545766238 ecl:hzl
 * eyr:2022
 *
 * iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719
 * Count the number of valid passports - those that have all required fields and valid values. Continue to treat cid as optional. In your batch file, how many passports are valid?
 */
public class Day4 {

  private static final String SPACE = " ";
  private static final String SPLIT = ":";

  public int countValidPassports(List<String> listOfStrings) {
    int countValid = 0;
    List<Passport> passports = formatListOfStrings(listOfStrings);
    for (Passport passport : passports) {
      if (validate(passport)) {
        countValid++;
      }
    }
    return countValid;
  }

  public int countValidPassportsPart2(List<String> listOfStrings) {
    int countValid = 0;
    List<Passport> passports = formatListOfStrings(listOfStrings);
    for (Passport passport : passports) {
      if (validateAdvanced(passport)) {
        countValid++;
      }
    }
    return countValid;
  }

  public List<Passport> formatListOfStrings(List<String> listOfStrings) {
    //assume first row isnt empty
    List<Passport> passports = new ArrayList<>();
    Passport.PassportBuilder passportBuilder = Passport.builder();
    int counter = 0;
    for (int i = 0; i < listOfStrings.size(); i++) {
      if (listOfStrings.get(i).isEmpty()) {
        passports.add(passportBuilder.build());
        passportBuilder = Passport.builder();
      } else {
        String[] parse = listOfStrings.get(i).split(SPACE);
        if (parse.length == 0) {
          passportBuilder = buildPasswordFromString(passportBuilder, listOfStrings.get(i));
        } else {
          for (String fieldValue : parse) {
            passportBuilder = buildPasswordFromString(passportBuilder, fieldValue);
          }
        }
      }
    }
    passports.add(passportBuilder.build());
    return passports;
  }

  private boolean validate(Passport passport) {
    return passport.getByr() != null &&
        passport.getIyr() != null &&
        passport.getEyr() != null &&
        passport.getHgt() != null &&
        passport.getHcl() != null &&
        passport.getEcl() != null &&
        passport.getPid() != null;
  }

  private boolean validateAdvanced(Passport passport) {
    /**
     * hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
     * ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
     * pid (Passport ID) - a nine-digit number, including leading zeroes.
     * cid (Country ID) - ignored, missing or not.
     */
    // First check that the values arent empty
    if (validate(passport)) {
      boolean validByr = validBirthYear(passport.getByr());
      boolean validIyr = validIssueYear(passport.getIyr());
      boolean validEyr = validExpirationYear(passport.getEyr());
      boolean validHgt = validHeight(passport.getHgt()) ;
      boolean validHcl = validHairColor(passport.getHcl());
      boolean validEcl = validEyeColor(passport.getEcl());
      boolean validPid = validPassportId(passport.getPid());
      return validByr && validIyr && validEyr && validHgt && validHcl && validEcl && validPid;
    }
    return false;
  }

  public boolean validBirthYear(String bry) {
    // byr (Birth Year) - four digits; at least 1920 and at most 2002.
    return validYearInRange(bry, 1920, 2002);
  }

  public boolean validIssueYear(String iyr) {
    // iyr (Issue Year) - four digits; at least 2010 and at most 2020.
    return validYearInRange(iyr, 2010, 2020);
  }

  public boolean validExpirationYear(String eyr) {
    // eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
    return validYearInRange(eyr, 2020, 2030);
  }

  // Check year is number and in range
  public boolean validYearInRange(String stringYear, Integer low, Integer high) {
    try {
      Integer year = Integer.valueOf(stringYear);
      return low <= year && year <= high;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public boolean validHeight(String hgt) {
    // hgt (Height) - a number followed by either cm or in:
    //  * If cm, the number must be at least 150 and at most 193.
    //  * If in, the number must be at least 59 and at most 76.

    // Maybe overly simplistic but
    // hgt size: 5 -> cm
    // hgt size: 4 -> in
    // otherwise false
    try {
      // Could also check the endings to determine which to check against but this seems fine
      if (hgt.length() == 5) {
        int value = Integer.valueOf(hgt.substring(0, 3));
        return 150 <= value && value <= 193 && hgt.substring(3).equals("cm");
      } else if (hgt.length() == 4) {
        int value = Integer.valueOf(hgt.substring(0, 2));
        return 59 <= value && value <= 76 && hgt.substring(2).equals("in");
      }
    } catch (NumberFormatException e) {
      return false;
    }
    return false;
  }

  public boolean validHairColor(String hcl) {
    // hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
    if (hcl.length() == 7 && hcl.charAt(0) == '#') {
      // Ignoring first character which should be #
      char[] chars = hcl.substring(1).toCharArray();
      for (char c : chars) {
        if (!Character.isAlphabetic(c) && !Character.isDigit(c)) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  enum EyeColor {
    amb,
    blu,
    brn,
    gry,
    grn,
    hzl,
    oth
  }

  public boolean validEyeColor(String ecl) {
    // ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth
    // Check all eye colors
    for (EyeColor eyeColor : EyeColor.values()) {
      // if matches eye color
      if (eyeColor.name().equals(ecl)) {
        return true;
      }
    }
    return false;
  }

  public boolean validPassportId(String pid) {
    // pid (Passport ID) - a nine-digit number, including leading zeroes.
    // check size and check all are numeric
    if (pid.length() == 9) {
      char[] chars = pid.toCharArray();
      for (char c : chars) {
        if (!Character.isDigit(c)) {
          return false;
        }
      }
      // if all are digits
      return true;
    }
    return false;
  }


  Passport.PassportBuilder buildPasswordFromString(Passport.PassportBuilder passportBuilder,
      String raw) {
    String[] split = raw.split(SPLIT);
    String field = split[0];
    String value = split[1];
    switch (field) {
      case "byr":
        passportBuilder.byr(value);
        break;
      case "iyr":
        passportBuilder.iyr(value);
        break;
      case "eyr":
        passportBuilder.eyr(value);
        break;
      case "hgt":
        passportBuilder.hgt(value);
        break;
      case "hcl":
        passportBuilder.hcl(value);
        break;
      case "ecl":
        passportBuilder.ecl(value);
        break;
      case "pid":
        passportBuilder.pid(value);
        break;
      case "cid":
        passportBuilder.cid(value);
        break;
      default:
        System.out.println("This is an unknown field: " + field + " - " + value);
    }
    return passportBuilder;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  static class Passport {

    private String byr; // (Birth Year)
    private String iyr; //  (Issue Year)
    private String eyr; //  (Expiration Year)
    private String hgt; //  (Height)
    private String hcl; //  (Hair Color)
    private String ecl; //  (Eye Color)
    private String pid; //  (Passport ID)
    private String cid; //  (Country ID)
  }
}
