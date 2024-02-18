package ru.gb;

import lombok.Data;
import org.slf4j.event.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data // getter + setter
@ConfigurationProperties("timer")
public class TimerProperties {

  /**
   * Уровень логирования
   */
  private Level logLevel = Level.INFO;

}
