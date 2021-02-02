package task.scheduling.using.annotation;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TestSchedulingService {
  @Scheduled(fixedDelay = 10000)
  public void testScheduling() {
    System.out.println("testScheduling was called");
  }
}
