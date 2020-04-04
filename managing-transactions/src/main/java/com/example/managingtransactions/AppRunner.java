package com.example.managingtransactions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class AppRunner implements CommandLineRunner {
    private final static Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final BookingService bookingService;

    @Autowired
    public AppRunner(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public void run(String... args) throws Exception {
        bookingService.book("Aa", "Bbb", "Cccc");
        Assert.isTrue(bookingService.findAllBookings().size() == 3, "booking succeed");
        logger.info("Aa, Bbb, Cccc have been booked");

        try {
            bookingService.book("Ddddd", "Eeeeee");
        } catch (RuntimeException e) {
            logger.info("expected exception because 'Eeeeee' is too big");
            logger.error(e.getMessage());
        }
        Assert.isTrue(bookingService.findAllBookings().size() == 3, "rollback triggered");

        try {
            bookingService.book("Fffff", null);
        } catch (RuntimeException e) {
            logger.info("expected exception because null is not allowed");
            logger.error(e.getMessage());
        }
        Assert.isTrue(bookingService.findAllBookings().size() == 3, "rollback triggered");
    }
}
