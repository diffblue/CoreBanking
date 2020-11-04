package io.diffblue.corebanking.ui.batch;

import java.io.File;
import java.util.stream.Stream;
import org.junit.Test;

public class BatchProcessorIT {

  @Test
  public void runBatch() {
    Stream.of(new File("src/test/resources/batch").listFiles())
        .map(file -> file.toString())
        .forEach(fileName -> BatchProcessor.main(new String[]{fileName}));
  }

}
