package io.diffblue.corebanking;

/** Utility class. */
public class Util {
  /**
   * Generates a number with the number of digits passed as a parameter.
   *
   * @param numberOfDigits The number of digits for the number to generate.
   * @return A randomly generated number with the number of digits passed as the method parameter.
   */
  public static long generateXdigitNumber(int numberOfDigits) {
    String result = "";
    for (int i = 0; i < numberOfDigits; i++) {
      int digit = (int) (Math.random() * 10);
      result += digit;
    }
    return Long.parseLong(result);
  }
}
