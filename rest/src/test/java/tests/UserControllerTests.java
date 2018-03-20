package tests;

import com.nntu.StartUpPoint;
import com.nntu.containers.info.UserRole;
import com.nntu.controllers.UserController;
import com.nntu.dao.UserDAO;
import com.nntu.models.User;
import helpers.UserTestInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartUpPoint.class)
public class UserControllerTests {

    private MockMvc mvc;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserController userController;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(userController).build();
        userController.setUserDao(userDAO);
    }

    @Test
    public void registrationTest() throws Exception {
        UserTestInfo newUser = UserTestInfo.builder()
                .name("Mark")
                .lastName("Lemeshevskij")
                .email("gigelbig@gmail.com")
                .password("123456")
                .role(UserRole.CUSTOMER)
                .build();

        MockHttpServletResponse response = mvc.perform(
                post("/users/new-user" + newUser))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains("SUCCESS");
    }

    @Test
    public void authorizationTest() throws Exception {
        UserTestInfo newUser = UserTestInfo.builder()
                .name("Michael")
                .lastName("Seckackin")
                .email("msek@mail.ru")
                .password("123456")
                .role(UserRole.CUSTOMER)
                .build();
        User testUser = new User(newUser.getName(),
                newUser.getLastName(),
                newUser.getEmail(),
                UUID.nameUUIDFromBytes(newUser.getPassword()
                        .getBytes())
                        .toString(),
                UserRole.CUSTOMER);
        userDAO.save(testUser);

        MockHttpServletResponse response = mvc.perform(
                get("/users/authorize-user" + newUser.authorizationString()))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains("SUCCESS");
    }
}
