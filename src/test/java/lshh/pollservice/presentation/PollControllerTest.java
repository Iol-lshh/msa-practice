package lshh.pollservice.presentation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static lshh.pollservice.common.lib.JsonHelper.asJsonString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class PollControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Nested
    class ListTest {
        @Test
        void list() throws Exception {
            var result = mockMvc.perform(get("/poll/all")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse();

            log.info("result: " + result.getContentAsString());
        }

    }

    @Nested
    class DetailTest {
        @Test
        void detail() {
        }
    }

    @Nested
    class CreateTest {
        @Test
        void create() {
        }
    }
}