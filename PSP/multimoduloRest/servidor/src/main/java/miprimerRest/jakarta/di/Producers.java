package miprimerRest.jakarta.di;

import jakarta.enterprise.inject.Produces;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.Context;
import org.modelmapper.ModelMapper;

public class Producers {




    @Produces
    public ModelMapper producesModelMapper(@Context HttpServletRequest request)
    {
        request.getServletContext();
        return new ModelMapper();
    }




    @Produces
    public Jsonb producesJsonb()
    {
        return JsonbBuilder.create();
    }



}
