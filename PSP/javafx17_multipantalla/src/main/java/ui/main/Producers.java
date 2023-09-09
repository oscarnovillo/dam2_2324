package ui.main;

import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
//import jakarta.inject.Singleton;
//import jakarta.validation.Validation;
//import jakarta.validation.Validator;
//import jakarta.validation.ValidatorFactory;


public class Producers {


    @Produces
    @Named("url")
    public String getUrl()
    {
        return "jjj";
    }

    @Produces
    @Named("configDB")
    public String getDB()
    {
        return "jjj";
    }

//    @Produces
//    @Singleton
//    public Validator createValidator(@Named("url")String s) {
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//        return validator;
//    }

}
