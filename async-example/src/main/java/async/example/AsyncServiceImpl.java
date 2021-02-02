package async.example;

import java.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service("asyncService")
public class AsyncServiceImpl implements AsyncService {
  private static Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

  @Async
  @Override
  public void asyncTask() {
    logger.info("Start execution of asyncTask");

    try {
      Thread.sleep(10000);
    } catch (Exception e) {
      logger.error("exception:{}", e);
    }

    logger.info("Complete execution of asyncTask");
  }

  @Async
  @Override
  public Future<String> asyncWithReturn(String name) {
    logger.info("Start execution of asyncWithReturn, name:{}", name);

    try {
      Thread.sleep(5000);
    } catch (Exception e) {
      logger.error("exception:{}", e);
    }

    logger.info("Complete execution of asyncWithReture, name:{}", name);

    return new AsyncResult<>("Hello " + name);
  }
}
