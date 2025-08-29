package com.escuelait.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFormatter {

  public String format(LocalDate date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu", Locale.of("ES"));
    return date.format(formatter);
  }

}
