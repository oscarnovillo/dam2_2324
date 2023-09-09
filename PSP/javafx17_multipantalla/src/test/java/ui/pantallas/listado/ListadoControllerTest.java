package ui.pantallas.listado;

import domain.modelo.Cromo;
import domain.usecases.LoadCromosUseCase;
import io.github.palexdev.materialfx.controls.MFXTableView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import ui.pantallas.principal.PrincipalController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
@ExtendWith(MockitoExtension.class)
class ListadoControllerTest {


    Cromo c = new Cromo("Cromo 1", 1, "des");
    Cromo c1 = new Cromo("Cromo2", 2, "des");
    Cromo c2 = new Cromo("Cromo2", 2, "des");
    @Captor
    ArgumentCaptor<Cromo> captor;
    //class under test
    private ListadoController listado;
    //@Mock
    private PrincipalController principalController; // = mock(PrincipalController.class);;
    //@Mock
    private LoadCromosUseCase useCase; // = mock(LoginUseCase.class);
    private ListadoViewModel viewModel;

    @BeforeEach
    void setUp() {
        //principalController = mock(PrincipalController.class);
    }

    @Start
    public void start(Stage stage) throws IOException {

        principalController = mock(PrincipalController.class);
        useCase = mock(LoadCromosUseCase.class);
        viewModel = new ListadoViewModel(useCase);
        when(useCase.loadCromos()).thenReturn(List.of(c, c1));

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(param -> new ListadoController(viewModel));
        InputStream s = getClass().getResourceAsStream("/fxml/listado.fxml");
        Parent fxmlParent = fxmlLoader.load(s);
        listado = fxmlLoader.getController();
        listado.setPrincipalController(principalController);
        ;

        stage.setScene(new Scene(fxmlParent));
        stage.getScene().getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        stage.show();

    }

    @Test
    void testListadoController(FxRobot robot) {
        //given
//        Cromo c = new Cromo("Cromo 1",1,"des");
//        when(useCase.loadCromos()).thenReturn(List.of(c));

        //when
        //se carga


        //then
        MFXTableView<Cromo> tabla = robot.lookup("#tabla").queryAs(MFXTableView.class);
        TableView<Cromo> tablaNormal = robot.lookup("#tablaNormal").queryAs(TableView.class);
        assertAll(() -> assertThat(tabla.getItems().size()).isEqualTo(2),
                () -> assertThat(tabla.getItems()).containsExactly(c, c1),
                () -> assertThat(tablaNormal.getItems().size()).isEqualTo(2),
                () -> assertThat(tablaNormal.getItems()).containsExactly(c, c1)

        );

        robot.sleep(1000);

    }


    @Test
    void testClickTablaController(FxRobot robot) {
        //given
//        Cromo c = new Cromo("Cromo 1",1,"des");
//        when(useCase.loadCromos()).thenReturn(List.of(c));

        //when
        //se carga

        //then
        MFXTableView<Cromo> tabla = robot.lookup("#tabla").queryAs(MFXTableView.class);

        robot.interact(() -> tabla.getSelectionModel().selectItem(c));
        robot.clickOn("#tabla");

        tabla.getCell(0).setId("cell0");
        tabla.getCell(1).setId("cell1");

        robot.clickOn("#cell0");

        TextField text =  robot.lookup("#txtNombre").queryAs(TextField.class);

        assertThat(text.getText()).isEqualTo(c.getCollecion());


//        robot.interact(() -> tabla.getSelectionModel().selectItem(c));
        //Platform.runLater(() -> tabla.getSelectionModel().selectItem(c));
        //tabla.getSelectionModel().selectItem(c);
        //robot.clickOn("#tabla");

        robot.sleep(2000);

        robot.clickOn("#ver");

        verify(principalController).onSeleccionCromo(captor.capture());

        assertThat(captor.getValue()).isEqualTo(c);


    }

    @Test
    void testClickTablaNormalController(FxRobot robot) {
        //given
//        Cromo c = new Cromo("Cromo 1",1,"des");
//        when(useCase.loadCromos()).thenReturn(List.of(c));

        //when
        //se carga

        //then
        TableView<Cromo> tabla = robot.lookup("#tablaNormal").queryAs(TableView.class);

        robot.interact(() -> tabla.getSelectionModel().select(1));
        robot.sleep(1000);

        robot.clickOn("#tablaNormal");

    robot.sleep(1000);
//        Node n = robot.lookup(".table-row-cell").query();
        robot.clickOn("#verNormal");
//        robot.clickOn("#cell1");
//        robot.interact(() -> tabla.getSelectionModel().selectItem(c));
        //Platform.runLater(() -> tabla.getSelectionModel().selectItem(c));
        //tabla.getSelectionModel().selectItem(c);
        //robot.clickOn("#tabla");

        //robot.sleep(1000);
        verify(principalController).onSeleccionCromo(captor.capture());

        assertThat(captor.getValue()).isEqualTo(c1);


    }

}
