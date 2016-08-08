package com.upwork.exercise.calculatorws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 * A simple calculator web service that caches the results of its computations.
 * 
 * @author Dali Freire - dalifreire@gmail.com
 * @since 1.0
 */
@Path("/calculator")
public class CalculatorRestService {

    @Context
    private Request request;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add/{a}/{b}")
    public Response add(@PathParam("a") double a, @PathParam("b") double b) {

        String tagname = String.format("/add/%s/%s", a, b);
        final ResponseBuilder responseBuilder = responseBuilder(tagname);
        if (responseBuilder == null) {

            double result = a + b;
            return responseOk(String.valueOf(result), tagname);
        }
        return responseBuilder.build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add/{a}/{b}/{c}")
    public Response add(@PathParam("a") double a, @PathParam("b") double b, @PathParam("c") double c) {

        String tagname = String.format("/add/%s/%s/%s", a, b, c);
        final ResponseBuilder responseBuilder = responseBuilder(tagname);
        if (responseBuilder == null) {

            double result = a + b + c;
            return responseOk(String.valueOf(result), tagname);
        }
        return responseBuilder.build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/subtract/{a}/{b}")
    public Response subtract(@PathParam("a") double a, @PathParam("b") double b) {

        String tagname = String.format("/subtract/%s/%s", a, b);
        final ResponseBuilder responseBuilder = responseBuilder(tagname);
        if (responseBuilder == null) {

            double result = a - b;
            return responseOk(String.valueOf(result), tagname);
        }
        return responseBuilder.build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/subtract/{a}/{b}/{c}")
    public Response subtract(@PathParam("a") double a, @PathParam("b") double b, @PathParam("c") double c) {

        String tagname = String.format("/subtract/%s/%s/%s", a, b, c);
        final ResponseBuilder responseBuilder = responseBuilder(tagname);
        if (responseBuilder == null) {

            double result = a - b - c;
            return responseOk(String.valueOf(result), tagname);
        }
        return responseBuilder.build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/multiply/{a}/{b}")
    public Response multiply(@PathParam("a") double a, @PathParam("b") double b) {

        String tagname = String.format("/multiply/%s/%s", a, b);
        final ResponseBuilder responseBuilder = responseBuilder(tagname);
        if (responseBuilder == null) {

            double result = a * b;
            return responseOk(String.valueOf(result), tagname);
        }
        return responseBuilder.build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/multiply/{a}/{b}/{c}")
    public Response multiply(@PathParam("a") double a, @PathParam("b") double b, @PathParam("c") double c) {

        String tagname = String.format("/multiply/%s/%s/%s", a, b, c);
        final ResponseBuilder responseBuilder = responseBuilder(tagname);
        if (responseBuilder == null) {

            double result = a * b * c;
            return responseOk(String.valueOf(result), tagname);
        }
        return responseBuilder.build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/divide/{a}/{b}")
    public Response divide(@PathParam("a") Double a, @PathParam("b") Double b) {

        String tagname = String.format("/divide/%s/%s", a, b);
        final ResponseBuilder responseBuilder = responseBuilder(tagname);
        if (responseBuilder == null) {

            double result = a / b;
            return responseOk(String.valueOf(result), tagname);
        }
        return responseBuilder.build();
    }

    private ResponseBuilder responseBuilder(String tagname) {
        final EntityTag tag = new EntityTag(tagname);
        return this.request.evaluatePreconditions(tag);
    }

    private Response responseOk(String value, String tagname) {
        return Response.ok(value).tag(new EntityTag(tagname)).build();
    }

}

