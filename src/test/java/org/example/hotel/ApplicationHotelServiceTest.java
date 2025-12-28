package org.example.hotel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ApplicationHotelService.class)
public class ApplicationHotelServiceTest {
        @Test
        void contextLoads() {

        }
        @Test
        void main_shouldStartApplication() {
                ApplicationHotelService.main(new String[]{});
        }
}
