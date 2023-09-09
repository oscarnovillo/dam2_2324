package ui.pantallas.listado;

import domain.modelo.Cromo;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.pdfsam.rxjavafx.schedulers.JavaFxScheduler;
import ui.pantallas.common.BasePantallaController;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ListadoController extends BasePantallaController {


    private final ListadoViewModel viewModel;
    public MFXButton ver;
    @FXML
    private TableColumn<Cromo, String> colCol;
    @FXML
    private TableColumn<Cromo, String> colDes;
    @FXML
    private TableColumn<Cromo, Integer> colNum;

    @FXML
    private TableView<Cromo> tablaNormal;
    @FXML
    private MFXTextField txtNombre;
    @FXML
    private MFXTableView<Cromo> tabla;

    @Inject
    public ListadoController(ListadoViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @FXML
    private void ver(ActionEvent actionEvent) {
        asyncConCompletableFuture();
//        //getPrincipalController().sacarAlertError("ahora con single");
        //      asynConSingle();

        getPrincipalController().root.setCursor(Cursor.WAIT);
//        viewModel.llamadaRetrofitAsyncEnViewModel();

    }

    private void asynConSingle() {
        Single.fromCallable(viewModel::llamadaRetrofitAsyncEnUi)
                .subscribeOn(Schedulers.io())
                .observeOn(JavaFxScheduler.platform())
                .doFinally(() -> getPrincipalController().root.setCursor(Cursor.DEFAULT))
                .subscribe(result ->
                                result.peek(cromos -> {
                                            tabla.getItems().clear();
                                            tabla.getItems().addAll(cromos);
                                        })
                                        .peekLeft(error -> {
                                            getPrincipalController().sacarAlertError(error);
                                        }),
                        throwable -> {
                            getPrincipalController().sacarAlertError(throwable.getMessage());

                        });


        getPrincipalController().root.setCursor(Cursor.WAIT);
    }


    private void asyncConTask() {

        getPrincipalController().root.setCursor(Cursor.WAIT);
        var task = new Task<Either<String, List<Cromo>>>() {
            @Override
            protected Either<String, List<Cromo>> call() throws Exception {
                return viewModel.llamadaRetrofitAsyncEnUi();
            }
        };
        task.setOnSucceeded(workerStateEvent -> {
            //workerStateEvent.getSource().valueProperty().get()
            getPrincipalController().root.setCursor(Cursor.DEFAULT);
            var result = task.getValue();
            result.peek(cromos -> {
                tabla.getItems().clear();
                tabla.getItems().addAll(cromos);


            }).peekLeft(error -> {
                getPrincipalController().sacarAlertError(error);
            });
        });
        task.setOnFailed(workerStateEvent -> {
            //workerStateEvent.getSource().getException().getMessage()
            getPrincipalController().sacarAlertError(task.getException().getMessage());
            getPrincipalController().root.setCursor(Cursor.DEFAULT);
        });

        new Thread(task).start();
    }


    private void asyncConCompletableFuture() {

        getPrincipalController().root.setCursor(Cursor.WAIT);

        CompletableFuture.supplyAsync(viewModel::llamadaRetrofitAsyncEnUi)
                .exceptionally(throwable -> Either.left(throwable.getMessage()))
                .thenAcceptAsync(result ->
                    //Platform.runLater(() -> {
                        {getPrincipalController().root.setCursor(Cursor.DEFAULT);
                        result.peek(cromos -> {
                            tabla.getItems().clear();
                            tabla.getItems().addAll(cromos);


                        }).peekLeft(error -> {
                            getPrincipalController().sacarAlertError(error);
                        });}
                    //})
            );

//        var task = new Task<Either<String, List<Cromo>>>() {
//            @Override
//            protected Either<String, List<Cromo>> call() throws Exception {
//                return viewModel.llamadaRetrofitAsyncEnUi();
//            }
//        };
//        task.setOnSucceeded(workerStateEvent -> {
//            //workerStateEvent.getSource().valueProperty().get()
//            getPrincipalController().root.setCursor(Cursor.DEFAULT);
//            var result = task.getValue();
//            result.peek(cromos -> {
//                tabla.getItems().clear();
//                tabla.getItems().addAll(cromos);
//
//
//            }).peekLeft(error -> {
//                getPrincipalController().sacarAlertError(error);
//            });
//        });
//        task.setOnFailed(workerStateEvent -> {
//            //workerStateEvent.getSource().getException().getMessage()
//            getPrincipalController().sacarAlertError(task.getException().getMessage());
//            getPrincipalController().root.setCursor(Cursor.DEFAULT);
//        });
//
//        new Thread(task).start();
    }


    public void initialize() {
// tabla materialFX
        MFXTableColumn<Cromo> collecionColumn = new MFXTableColumn<>("collecion", true, Comparator.comparing(Cromo::getCollecion));
        MFXTableColumn<Cromo> descriptionColumn = new MFXTableColumn<>("descripcion", true, Comparator.comparing(Cromo::getDescripcion));
        MFXTableColumn<Cromo> numberColumn = new MFXTableColumn<>("numero", true, Comparator.comparing(Cromo::getNumero));
        collecionColumn.setRowCellFactory(persona -> new MFXTableRowCell<>(Cromo::getCollecion));
        descriptionColumn.setRowCellFactory(persona -> new MFXTableRowCell<>(Cromo::getDescripcion));
        numberColumn.setRowCellFactory(persona -> new MFXTableRowCell<>(Cromo::getNumero));

        colCol.setCellValueFactory(new PropertyValueFactory<>("collecion"));
        colDes.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colNum.setCellValueFactory(new PropertyValueFactory<>("numero"));


        tablaNormal.getSelectionModel().getSelectedItems().addListener((ListChangeListener<? super Cromo>) change -> {
            txtNombre.setText("" + change.getList().get(0).getNumero());
        });
        tabla.getSelectionModel().selectionProperty().addListener((observableValue, cromo, cromoNew) -> {
            if (cromoNew != null) {
                txtNombre.setText(cromoNew.values().stream().findFirst().get().getCollecion());
            }
        });

        tabla.getTableColumns().addAll(collecionColumn, descriptionColumn, numberColumn);

        cambiosEstado();

        // tabla.setItems(viewModel.getState().get().getPersonas());
    }


    @Override
    public void principalCargado() {
        viewModel.loadCromos();
    }

    private void cambiosEstado() {
        viewModel.getState().addListener((observableValue, listadoState, listadoStateNew) -> {
            Platform.runLater(() -> {
                if (listadoStateNew.getError() != null) {
                    getPrincipalController().sacarAlertError(listadoStateNew.getError());
                }
                if (listadoStateNew.getCromos() != null) {

                    tabla.getItems().clear();
                    tabla.getItems().addAll(listadoStateNew.getCromos());
                    tablaNormal.getItems().clear();
                    tablaNormal.getItems().addAll(listadoStateNew.getCromos());
                }


                getPrincipalController().root.setCursor(Cursor.DEFAULT);
            });

        });
    }

    public void verNormal(ActionEvent actionEvent) {

        if (tablaNormal.getSelectionModel().getSelectedItem() != null)
            getPrincipalController().onSeleccionCromo(tablaNormal.getSelectionModel().getSelectedItem());


    }
}
