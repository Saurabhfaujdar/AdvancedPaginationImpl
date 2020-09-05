package org.example;

import org.example.DAO.JDBCDAO;
import org.example.Model.ProblemModel;
import org.example.Response.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("problems")
public class ProblemResource {
    final static JDBCDAO jdbcdao = new JDBCDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProblemsByPage(@QueryParam("id") int id) throws SQLException, ClassNotFoundException {
        System.out.println(id);
        return jdbcdao.getProblems(id);
    }

    @GET
    @Path("/test")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProblemModel> testFunction() {
        System.out.println("API end point working fine");
        return null;
    }
}
