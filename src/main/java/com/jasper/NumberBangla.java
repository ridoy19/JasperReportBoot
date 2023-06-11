package com.jasper;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;

public class NumberBangla {
  public static final String[] units = new String[]{"", "এক", "দুই", "তিন", "চার", "পাঁচ", "ছয়", "সাত", "আট", "নয়", "দশ", "এগার", "বার", "তের", "চৌদ্দ", "পনের", "ষোল", "সতের", "আঠার", "ঊনিশ"};
  public static final String[] tens = new String[]{"", "", "বিশ", "ত্রিশ", "চল্লিশ", "পঞ্চাশ", "ষাট", "সত্তর", "আশি", "নব্বই"};

  public static String convert(double n) {

    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    String numberAsString = decimalFormat.format(n);
    String[] convert = numberAsString.split("\\.");
    long taka = Long.parseLong(convert[0]);
    int paisa = Integer.parseInt(convert[1]);
    NumberBangla numberToBanglaTaka = new NumberBangla();
    String totalTaka = "";
    if (taka != 0L) {
      totalTaka = numberToBanglaTaka.convertTaka(taka) + " টাকা ";
    }

    if (taka == 0L && n < 0.0D) {
      totalTaka = "ঋনাত্মক ";
    }

    if (paisa > 0) {
      totalTaka = totalTaka + numberToBanglaTaka.convertPaisa(paisa) + " পয়সা ";
    }

    totalTaka = totalTaka + "মাত্র";
    byte[] bytes = totalTaka.getBytes(StandardCharsets.UTF_8);

    String utf8EncodedString = new String(bytes, StandardCharsets.UTF_8);
    return utf8EncodedString;
  }

  public String convertTaka(Long n) {
    if (n < 0L) {
      return "ঋনাত্মক " + this.convertTaka(Math.abs(n));
    } else {
      int a;
      if (n < 20L) {
        a = Integer.parseInt(n.toString());
        return units[a];
      } else if (n < 100L) {
        a = Integer.parseInt(n.toString());
        return tens[a / 10] + (n % 10L != 0L ? " " : "") + units[a % 10];
      } else if (n < 1000L) {
        a = Integer.parseInt(n.toString());
        return units[a / 100] + " শ'ত" + (a % 100 != 0 ? " " : "") + this.convertTaka(n % 100L);
      } else if (n < 100000L) {
        return this.convertTaka(n / 1000L) + " হাজার" + (n % 10000L != 0L ? " " : "") + this.convertTaka(n % 1000L);
      } else {
        return n < 10000000L ? this.convertTaka(n / 100000L) + " লক্ষ" + (n % 100000L != 0L ? " " : "") + this.convertTaka(n % 100000L) : this.convertTaka(n / 10000000L) + " কোটি" + (n % 10000000L != 0L ? " " : "") + this.convertTaka(n % 10000000L);
      }
    }
  }

  public String convertPaisa(int n) {
    if (n < 20) {
      return units[n];
    } else {
      return n < 100 ? tens[n / 10] + (n % 10 != 0 ? " " : "") + units[n % 10] : "";
    }
  }

  public static void main(String[] args) {
    System.out.println(convert(1234.56));  // Example usage
  }
}
