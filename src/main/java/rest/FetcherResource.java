package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.UserDTO;
import entities.User;
import errorhandling.API_Exception;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;
import facades.FacadeExample;
import utils.HttpUtils;

import javax.persistence.EntityManagerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutionException;


//Todo Remove or change relevant parts before ACTUAL use
@Path("fetch")
public class FetcherResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final FacadeExample FACADE = FacadeExample.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJokes() throws IOException, ExecutionException, InterruptedException {

        LocalTime start2 = LocalTime.now();
        String result2 = gson.toJson(HttpUtils.fetchDataParallel());
        result2 = result2.replaceAll("[@]", "");
        LocalTime end2 = LocalTime.now();
        System.out.println("resultat 2 Parallel " + ChronoUnit.MILLIS.between(start2, end2));

        return result2;

    }


    @GET
    @Path("getcovid/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCovidStatus(@PathParam("user") String user) {

        String res = gson.toJson(FACADE.getcovid(user));
        return Response.ok().entity(GSON.toJson(res)).build();

    }

//    @GET
//    @Path("setcovid")
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response setCovidStatus(String u) throws Exception {
//
//        UserDTO result = FACADE.setCovid(u);
//
//
//        return Response.ok().entity(GSON.toJson(result)).build();
//    }

}


