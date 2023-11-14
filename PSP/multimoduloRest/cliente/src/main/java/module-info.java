module cliente {

    requires seguridad;
    requires domain;


    requires jakarta.inject;
    requires jakarta.cdi;
    requires io.vavr;
    requires lombok;
    requires okhttp3;

    requires retrofit2;
    requires com.squareup.moshi;
    requires retrofit2.adapter.rxjava3;
    requires retrofit2.converter.gson;
    requires retrofit2.converter.moshi;
    requires retrofit2.converter.scalars;
    requires io.reactivex.rxjava3;
    requires jjwt.api;
    requires com.google.gson;
    requires org.bouncycastle.provider;
    requires java.naming;
    requires java.logging;
    requires com.google.common;
    requires org.bouncycastle.pkix;

    exports cliente.ui;
    exports cliente.data;
    opens cliente.data.modelo;
    opens cliente.ui;
    exports cliente.data.network;


}
