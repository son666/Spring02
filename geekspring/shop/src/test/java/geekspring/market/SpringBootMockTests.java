package geekspring.market;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testng.Assert;

import java.util.regex.Matcher;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpringBootMockTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void tryToStart() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.core.StringContains.containsString("Добро пожаловать в интернет-магазин")));
    }

    @Test
    public void checkQuantityProduct() throws Exception {
        mockMvc.perform(get("/shop"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.core.StringContains.containsString("Телевизор Samsung UE20NU7170U")));
    }

    @Test
    public void checkProductPage() throws Exception {
        mockMvc.perform(get("/shop/product/{id}", 2));
        MvcResult result =
                mockMvc
                        .perform(MockMvcRequestBuilders.get("/shop/product/{id}", 2))
                        .andExpect(MockMvcResultMatchers.view().name("product-page"))
                        .andExpect(MockMvcResultMatchers.model().attributeExists("product"))
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        Assert.assertNotNull(result.getModelAndView().getModel().get("product"));
    }

}
