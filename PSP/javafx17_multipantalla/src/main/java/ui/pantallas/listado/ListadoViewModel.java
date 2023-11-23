package ui.pantallas.listado;

import domain.modelo.Cromo;
import domain.usecases.LoadCromosUseCase;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.pdfsam.rxjavafx.schedulers.JavaFxScheduler;
//import org.pdfsam.rxjavafx.schedulers.JavaFxScheduler;

import java.util.List;

public class ListadoViewModel {


    private final ObjectProperty<ListadoState> _state;
    private LoadCromosUseCase loginUseCase;

    @Inject
    public ListadoViewModel(LoadCromosUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;

        ListadoState ls = null;
        List<Cromo> cromos = loginUseCase.loadCromos();
        if (cromos == null)
            ls = new ListadoState(null, "no se han podido cargar cromos",false);
        else
            ls = new ListadoState(cromos, null,false);

        _state = new SimpleObjectProperty<>(new ListadoState(null, null,false));
    }

    public ReadOnlyObjectProperty<ListadoState> getState() {
        return _state;
    }

    public void loadCromos() {
        ListadoState ls = null;
        List<Cromo> cromos = loginUseCase.loadCromos();
        if (cromos == null)
            ls = new ListadoState(null, "no se han podido cargar cromos",false);
        else
            ls = new ListadoState(cromos, null,false);
        _state.setValue(ls);
    }


    public Either<String, List<Cromo>> llamadaRetrofitAsyncEnUi() {
        return loginUseCase.llamadaRetrofit();
    }

    public void llamadaRetrofitAsyncEnViewModel() {

        _state.setValue(new ListadoState(null, null, true));

        loginUseCase.llamadaRetrofitSingle(10)
                .delay(5, java.util.concurrent.TimeUnit.SECONDS)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    ListadoState ls = null;
                    if (either.isLeft())
                        ls = new ListadoState(null, either.getLeft(),false);
                    else
                        ls = new ListadoState(either.get(), null,false);
                    _state.setValue(ls);
                });

    }


    public void llamadaRetrofit() {
        ListadoState ls = null;
        var cromos = loginUseCase.llamadaRetrofit();
//        if (cromos.isLeft())
//            ls = new ListadoState(null, cromos.getLeft());
//        else
//            ls = new ListadoState(cromos.get(), null);

        cromos.peek(mijoke -> {
            _state.setValue(new ListadoState(mijoke, null,false));
        }).peekLeft(error -> {
            _state.setValue(new ListadoState(null, error,false));
        });

    }

}
