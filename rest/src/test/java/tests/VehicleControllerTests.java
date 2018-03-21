package tests;

import com.nntu.StartUpPoint;
import com.nntu.containers.info.ModelInfo;
import com.nntu.containers.info.UserInfo;
import com.nntu.containers.info.UserRole;
import com.nntu.controllers.VehicleController;
import com.nntu.dao.ModelDAO;
import com.nntu.dao.UserDAO;
import com.nntu.dao.VehicleDAO;
import com.nntu.models.Vehicle;
import helpers.VehicleTestInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StartUpPoint.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VehicleControllerTests {
    private MockMvc mvc;
    private VehicleTestInfo vehicleTestInfo;
    private Vehicle testRecord;
    private List<Vehicle> testList;

    private String SUCCESS = "SUCCESS";
    private String FAILURE = "FAILURE";

    @Mock
    private VehicleDAO vehicleDAO;

    @Mock
    private ModelDAO modelDAO;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private VehicleController vehicleController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(vehicleController).build();
        vehicleTestInfo = VehicleTestInfo.builder()
                .id(1L)
                .landlord(UserInfo.builder()
                        .id(1L)
                        .name("Mark")
                        .lastName("Lemeshevskij")
                        .email("gigelbig@gmail.com")
                        .role(UserRole.CUSTOMER)
                        .build())
                .modelInfo(ModelInfo.builder()
                        .id(1L)
                        .brand("SomeBrand")
                        .model("SomeModel")
                        .build())
                .isBusy(Boolean.FALSE)
                .build();
        testRecord = vehicleTestInfo.getVehicle();
        testRecord.setId(vehicleTestInfo.getId());
        testList = new LinkedList<>();
        testList.add(testRecord);
    }

    @Test
    public void newVehicleTest() throws Exception {
        Mockito.when(modelDAO.findById(any(Long.class)))
                .thenReturn(Optional.of(testRecord.getModel()));
        Mockito.when(userDAO.findById(any(Long.class)))
                .thenReturn(Optional.of(testRecord.getLandlord()));

        MockHttpServletResponse response = mvc.perform(
                post("/vehicles/new-vehicle" + vehicleTestInfo.newVehicleString()))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains(SUCCESS);
    }

    @Test
    public void availableVehiclesTest() throws Exception {
        Mockito.when(vehicleDAO.findAllByIsBusy(Boolean.FALSE))
                .thenReturn(testList.stream()
                        .filter(x -> !x.getIsBusy())
                        .collect(Collectors.toList()));

        MockHttpServletResponse response = mvc.perform(
                get("/vehicles/available-vehicles"))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .contains(SUCCESS)
                .contains(vehicleTestInfo.getId().toString());
    }

    @Test
    public void vehicleByIdTest() throws Exception {
        Mockito.when(vehicleDAO.findAllById(any(Long.class)))
                .thenReturn(testList);

        MockHttpServletResponse response = mvc.perform(
                get("/vehicles/vehicle-by-id" + vehicleTestInfo.idString()))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .contains(SUCCESS)
                .contains(vehicleTestInfo.getId().toString());
    }

    @Test
    public void newVehicleNoModelTest() throws Exception {
        Mockito.when(modelDAO.findById(any(Long.class)))
                .thenReturn(Optional.empty());
        Mockito.when(userDAO.findById(any(Long.class)))
                .thenReturn(Optional.of(testRecord.getLandlord()));

        MockHttpServletResponse response = mvc.perform(
                post("/vehicles/new-vehicle" + vehicleTestInfo.newVehicleString()))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains(FAILURE);
    }

    @Test
    public void newVehicleNoOwnerTest() throws Exception {
        Mockito.when(modelDAO.findById(any(Long.class)))
                .thenReturn(Optional.of(testRecord.getModel()));
        Mockito.when(userDAO.findById(any(Long.class)))
                .thenReturn(Optional.empty());

        MockHttpServletResponse response = mvc.perform(
                post("/vehicles/new-vehicle" + vehicleTestInfo.newVehicleString()))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains(FAILURE);
    }
}
