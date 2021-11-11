package io.diffblue.corebanking.ui.batch;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.Test;

public class BatchProcessorIT {

  @Test
  public void runBatch() {
    File directory = new File("src/test/resources/batch");
    File[] inputFiles = directory.listFiles();
    assert inputFiles != null;

    for (File file : inputFiles) {
        BatchProcessor.main(file.toString());
    }
  }

}
