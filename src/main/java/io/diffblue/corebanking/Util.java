/*
    Copyright 2018-2024 Diffblue Limited

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

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
