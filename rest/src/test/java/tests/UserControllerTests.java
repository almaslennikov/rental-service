package tests;

import com.nntu.StartUpPoint;
import com.nntu.containers.info.UserRole;
import com.nntu.controllers.UserController;
import com.nntu.dao.UserDAO;
import com.nntu.models.User;
import helpers.UserTestInfo;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StartUpPoint.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTests {
    private MockMvc mvc;
    private UserTestInfo userTestInfo;
    private UserTestInfo invalidUserTestInfo;
    private String SUCCESS = "SUCCESS";
    private String FAILURE = "FAILURE";

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserController userController;

    @BeforeClass
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(userController).build();
        userTestInfo = UserTestInfo.builder()
                .id(1L)
                .name("Mark")
                .lastName("Lemeshevskij")
                .email("gigelbig@gmail.com")
                .password("123456")
                .role(UserRole.CUSTOMER)
                .build();
        User testRecord = new User(userTestInfo.getName(),
                userTestInfo.getLastName(),
                userTestInfo.getEmail(),
                UUID.nameUUIDFromBytes(userTestInfo.getPassword()
                        .getBytes())
                        .toString(),
                UserRole.CUSTOMER);
        testRecord.setId(userTestInfo.getId());
        invalidUserTestInfo = UserTestInfo.builder()
                .id(2L)
                .name("Alex")
                .lastName("Maslennikov")
                .email("combat0705@yandex.ru")
                .password("a12345")
                .role(UserRole.LANDLORD)
                .build();

        Mockito.when(userDAO.findById(testRecord.getId()))
                .thenReturn(Optional.of(testRecord));
        Mockito.when(userDAO.getUserByEmail(testRecord.getEmail()))
                .thenReturn(testRecord);
    }

    @Test
    public void registrationTest() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                post("/users/new-user" + invalidUserTestInfo))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains(SUCCESS);
    }

    @Test
    public void authorizationTest() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                get("/users/authorize-user" + userTestInfo.authorizationString()))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains(SUCCESS);
    }

    @Test
    public void userByIdTest() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                get("/users/user-by-id" + userTestInfo.idString()))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains(SUCCESS);
    }

    @Test
    public void registrationFailTest() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                post("/users/new-user" + userTestInfo))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains(FAILURE);
    }

    @Test
    public void authorizationFailTest() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                get("/users/authorize-user" + invalidUserTestInfo.authorizationString()))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains(FAILURE);
    }

    @Test
    public void userByIdFailTest() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                get("/users/user-by-id" + invalidUserTestInfo.idString()))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains(FAILURE);
    }
}
