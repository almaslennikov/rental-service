package tests;

import com.nntu.StartUpPoint;
import com.nntu.controllers.ModelController;
import com.nntu.dao.ModelDAO;
import com.nntu.models.Model;
import helpers.ModelTestInfo;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StartUpPoint.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ModelControllerTests {
    private MockMvc mvc;
    private ModelTestInfo modelTestInfo;
    private Model testRecord;
    private Model anotherTestRecord;
    private List<Model> testList;

    private String SUCCESS = "SUCCESS";
    private String FAILURE = "FAILURE";

    @Mock
    private ModelDAO modelDAO;

    @InjectMocks
    private ModelController modelController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(modelController).build();
        modelTestInfo = ModelTestInfo.builder()
                .id(1L)
                .brand("SomeBrand")
                .model("SomeModel")
                .build();
        testRecord = new Model(modelTestInfo.getBrand(), modelTestInfo.getModel());
        anotherTestRecord = new Model(modelTestInfo.getBrand(), "AnotherModel");
        testList = new LinkedList<>();
        testList.add(testRecord);
        testList.add(anotherTestRecord);
    }

    @Test
    public void newModelTest() throws Exception {
        Mockito.when(modelDAO.getModelByBrand(any(String.class)))
                .thenReturn(null);

        MockHttpServletResponse response = mvc.perform(
                post("/models/new-model" + modelTestInfo))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains(SUCCESS);
    }

    @Test
    public void newModelWithExistingBrandTest() throws Exception {
        Mockito.when(modelDAO.getModelByBrand(any(String.class)))
                .thenReturn(anotherTestRecord);

        MockHttpServletResponse response = mvc.perform(
                post("/models/new-model" + modelTestInfo))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains(SUCCESS);
    }

    @Test
    public void availableModelsTest() throws Exception {
        Mockito.when(modelDAO.findAll())
                .thenReturn(testList);

        MockHttpServletResponse response = mvc.perform(
                get("/models/available-models"))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .contains(SUCCESS)
                .contains(testRecord.getModelName())
                .contains(anotherTestRecord.getModelName());
    }
}
