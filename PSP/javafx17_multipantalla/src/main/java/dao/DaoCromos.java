package dao;

import dao.retrofit.modelo.ResponseJoke;
import domain.modelo.Cromo;
import domain.modelo.MiJokes;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

import java.util.List;

public interface DaoCromos {

    List<Cromo> loadCromos();

    Either<String, MiJokes> llamadaRettrofit();

    Single<Either<String, MiJokes>> llamadaRettrofitSingle();
}
