package task.executor.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class TestTaskExecutorService {
  private static Logger logger = LoggerFactory.getLogger(TestTaskExecutorService.class);

  @Autowired
  private TaskExecutor taskExecutor;

  public void executeTask() {
    for (int i = 0; i < 10; i++) {
      taskExecutor.execute(() -> logger.info("Hello from thread: " +
          Thread.currentThread().getName()));
    }
  }

}
