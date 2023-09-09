package domain.usecases;

import dao.DaoCromos;
import domain.modelo.Cromo;
import domain.modelo.MiJokes;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class LoadCromosUseCase {

    private DaoCromos dao;

    @Inject
    public LoadCromosUseCase(DaoCromos dao) {
        this.dao = dao;
    }

    public List<Cromo> loadCromos() {
        return dao.loadCromos();
    }

    public Either<String, List<Cromo>> llamadaRetrofit() {
        return dao.llamadaRettrofit().map(miJokes -> List.of(new Cromo("jokes", miJokes.id(), miJokes.joke())));
    }


    public Single<Either<String, List<Cromo>>> llamadaRetrofitSingle(int id) {
        return dao.llamadaRettrofitSingle()
                .map(either -> either.map(miJokes -> List.of(new Cromo("jokes", miJokes.id(), miJokes.joke()))));
    }
}
