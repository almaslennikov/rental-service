package tests;

import com.nntu.StartUpPoint;
import com.nntu.controllers.OrderController;
import com.nntu.dao.OrderDAO;
import com.nntu.dao.UserDAO;
import com.nntu.dao.VehicleDAO;
import com.nntu.models.Order;
import com.nntu.models.User;
import com.nntu.models.Vehicle;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StartUpPoint.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTests {
    private MockMvc mvc;
    private String SUCCESS = "SUCCESS";
    private String FAILURE = "FAILURE";

    @Mock
    private UserDAO userDAO;

    @Mock
    private VehicleDAO vehicleDAO;

    @Mock
    private OrderDAO orderDAO;

    @InjectMocks
    private OrderController orderController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void newOrderCustomerFailTest() throws Exception {
        Long customerId = 1L;
        Long vehicleId = 1L;

        Mockito.when(userDAO.findById(any(Long.class)))
                .thenReturn(Optional.empty());
        Mockito.when(vehicleDAO.findById(any(Long.class)))
                .thenReturn(Optional.of(new Vehicle()));

        MockHttpServletResponse response = mvc.perform(
                post("/orders/order-vehicle?customerId=" + customerId
                        + "&vehicleId=" + vehicleId))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains(FAILURE);
    }

    @Test
    public void newOrderVehicleFailTest() throws Exception {
        Long customerId = 1L;
        Long vehicleId = 1L;

        Mockito.when(userDAO.findById(any(Long.class)))
                .thenReturn(Optional.of(new User()));
        Mockito.when(vehicleDAO.findById(any(Long.class)))
                .thenReturn(Optional.empty());

        MockHttpServletResponse response = mvc.perform(
                post("/orders/order-vehicle?customerId=" + customerId
                        + "&vehicleId=" + vehicleId))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains(FAILURE);
    }

    @Test
    public void getOrderByCustomerIdTest() throws Exception {
        Long customerId = 1L;
        User user = new User();
        user.setCustomerOrders(new LinkedList<>());

        Mockito.when(userDAO.findById(any(Long.class)))
                .thenReturn(Optional.of(user));

        MockHttpServletResponse response = mvc.perform(
                get("/orders/orders-by-customer-id?id=" + customerId))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains(SUCCESS);
    }

    @Test
    public void getOrderByCustomerIdCustomerFailTest() throws Exception {
        Long customerId = 1L;

        Mockito.when(userDAO.findById(any(Long.class)))
                .thenReturn(Optional.empty());

        MockHttpServletResponse response = mvc.perform(
                get("/orders/orders-by-customer-id?id=" + customerId))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains(FAILURE);
    }

    @Test
    public void getOrderByLandlordIdTest() throws Exception {
        Long customerId = 1L;
        User user = new User();
        user.setLandlordOrders(new LinkedList<>());

        Mockito.when(userDAO.findById(any(Long.class)))
                .thenReturn(Optional.of(user));

        MockHttpServletResponse response = mvc.perform(
                get("/orders/orders-by-landlord-id?id=" + customerId))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains(SUCCESS);
    }

    @Test
    public void getOrderByLandlordIdLandlordFailTest() throws Exception {
        Long customerId = 1L;

        Mockito.when(userDAO.findById(any(Long.class)))
                .thenReturn(Optional.empty());

        MockHttpServletResponse response = mvc.perform(
                get("/orders/orders-by-landlord-id?id=" + customerId))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains(FAILURE);
    }

    @Test
    public void cancelExistingOrderTest() throws Exception {
        Long orderId = 1L;
        Order order = new Order();
        order.setVehicle(new Vehicle());

        Mockito.when(orderDAO.findById(any(Long.class)))
                .thenReturn(Optional.of(order));

        MockHttpServletResponse response = mvc.perform(
                delete("/orders/order?id=" + orderId))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains(SUCCESS);
    }

    @Test
    public void cancelExistingOrderFailTest() throws Exception {
        Long orderId = 1L;

        Mockito.when(orderDAO.findById(any(Long.class)))
                .thenReturn(Optional.empty());

        MockHttpServletResponse response = mvc.perform(
                delete("/orders/order?id=" + orderId))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains(FAILURE);
    }
}
