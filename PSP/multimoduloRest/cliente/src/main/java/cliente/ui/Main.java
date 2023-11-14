package cliente.ui;

import io.reactivex.rxjava3.schedulers.Schedulers;
import cliente.data.DaoEstupido;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import okhttp3.Credentials;

public class Main {


    public static void main(String[] args) {


//        CacheAuthorization ca = new CacheAuthorization();
//        ca.setUser("user");
//        ca.setPass("pass");

        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();

        DaoEstupido dao = container.select(DaoEstupido.class).get();


        dao.getUsuario()
                .subscribeOn(Schedulers.io())
                .blockingSubscribe(either -> {
                    if (either.isRight()) {
                        System.out.println(either.get());

                    } else if (either.isLeft()) {
                        System.out.println(either.getLeft());

                    }


                });


        dao.getLogin(Credentials.basic("admin", "admin"))
                .subscribeOn(Schedulers.io())
                .blockingSubscribe(either -> {
                    if (either.isRight()) {
                        System.out.println(either.get());

                    } else if (either.isLeft()) {
                        System.out.println(either.getLeft());

                    }


                });

        dao.getUsuario()
                .subscribeOn(Schedulers.io())
                .blockingSubscribe(either -> {
                    if (either.isRight()) {
                        System.out.println(either.get());

                    } else if (either.isLeft()) {
                        System.out.println(either.getLeft());

                    }


                });

//        CompletableFuture.runAsync(() -> {
//
//            DaoEstupido dao = new DaoEstupido(ca);
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            dao.getAlumno().observeOn(Schedulers.single())
//                    .blockingSubscribe(either -> {
//                        if (either.isRight()) {
//                            System.out.println(either.get());
//
//                        } else if (either.isLeft()) {
//                            System.out.println(either.getLeft());
//
//                        }
//
//
//                    });
//
//            dao.getJwt().observeOn(Schedulers.single())
//                    .blockingSubscribe(either -> {
//                        if (either.isRight()) {
//
//                            System.out.println(either.get());
//                            dao.getVerify().observeOn(Schedulers.io())
//                                    .subscribe(either2 -> {
//                                        if (either2.isRight()) {
//                                            System.out.println(either2.get());
//
//                                        } else if (either2.isLeft()) {
//                                            System.out.println(either2.getLeft());
//
//                                        }
//
//
//                                    });
//
//
//                        } else if (either.isLeft()) {
//                            System.out.println(either.getLeft());
//
//                        }
//
//
//                    });
//
//
//        }).join();




    }


}
