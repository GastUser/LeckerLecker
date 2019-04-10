package pizza.leckerlecker.leckerplatform;

import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("default")
public class LeckerPlatformApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testeIframeListe() throws Exception {

        this.mockMvc
                .perform(get("/iframe"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        content().string(containsString("Pizza Alfredo"))
                );
    }
}
