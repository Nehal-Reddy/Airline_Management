package com.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;

import com.dao.HibernateStandAloneDemo;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("data")
public class Application {

    HibernateStandAloneDemo fdao=new HibernateStandAloneDemo();
    @GET
    @Path("/flights/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@PathParam("id")int flightid) {
     String result=fdao.getFlightById(flightid);
    	return result;
    }
    
    //:(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-(2[0-9])[0-9]{2}
    @GET
    @Path("/flights/{fromdate:(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-(2[0-9])[0-9]{2}}"
    		+ "/{todate:(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-(2[0-9])[0-9]{2}}/"
    		+ "{tocity}/{fromcity}/{maxprice}")
    
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> sort(@PathParam("fromdate") 
    	String fromdate,@PathParam("todate") String todate,
    		@PathParam("tocity")String destination,@PathParam("fromcity")String origin, 
    		@PathParam("maxprice")int maxprice ) throws ParseException {
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
    	Date fdate = sdf.parse(fromdate);
    	Date tdate = sdf.parse(todate);
    	
    	List<String> result=fdao.sortFlights(fdate, tdate, destination, origin, maxprice);
    	return result;
    }
    
    
    @POST
    @Path("/flights/{field}/{date:(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-(2[0-9])[0-9]{2}}}")
   // @Produces(MediaType.APPLICATION_JSON)
    
    public Response update(@PathParam("field") PathSegment field, 
    		@PathParam("date")String date) throws ParseException {
    	SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
    	Date dt = sdf.parse(date);
    	int flightid = Integer.parseInt(field.getPath());
        int seats = Integer.parseInt(field.getMatrixParameters().getFirst("numberofseats"));
    	fdao.updateAvailability(flightid, seats, dt);
    	return Response.ok().build();
    }
    
    
    
}
