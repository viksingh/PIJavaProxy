package org.demo.proxy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceRef;

import com.sap.engine.services.webservices.espbase.client.api.XIManagementInterface;
import com.sap.engine.services.webservices.espbase.client.api.XIManagementInterfaceFactory;
import com.sap.engine.services.webservices.espbase.client.api.XIMessageContext;
import com.sap.xi.xi.demo.agency.FlightID;
import com.sap.xi.xi.demo.agency.FlightNotFound;
import com.sap.xi.xi.demo.agency.FlightSeatAvailabilityQuery;
import com.sap.xi.xi.demo.agency.FlightSeatAvailabilityQueryOutNew;
import com.sap.xi.xi.demo.agency.FlightSeatAvailabilityQueryOutNewService;
import com.sap.xi.xi.demo.agency.FlightSeatAvailabilityResponse;

/**
 * Servlet implementation class TestServlet
 */
public class ProxyTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@WebServiceRef(name = "FlightSeatAvailabilityQueryOutNewService")
	private FlightSeatAvailabilityQueryOutNewService service;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProxyTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<?xml version=\"1.0\"?>");
		out
				.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
		out
				.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\" xml:lang=\"en\">");
		out
				.println("<head><title>Flight Java Proxy Test Web service</title></head>\n");
		out.println("<body>");
		out.print("<form action=\"");
		out.print("ProxyTestServlet\" ");
		out.println("method=get>");
		
		
		// Instantiate the input objects using plain JAX-WS:
		FlightID flightID = new FlightID();
		
		String flight = request.getParameter("flight");
		String connId = request.getParameter("connId");
		
		flightID.setAirlineID(flight);
		flightID.setConnectionID(connId);
		
		XMLGregorianCalendar date;
		try {
			date = DatatypeFactory.newInstance().newXMLGregorianCalendar();
			date.setDay(1);
			date.setMonth(11);
			date.setYear(2009);
			flightID.setFlightDate(date);			
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		FlightSeatAvailabilityQuery query = new FlightSeatAvailabilityQuery();
		query.setFlightID(flightID);	
		

		FlightSeatAvailabilityQueryOutNew port = service
				.getPort(FlightSeatAvailabilityQueryOutNew.class);
		XIManagementInterface xiMngmnt = XIManagementInterfaceFactory
				.create(port);
		XIMessageContext msgCtx = xiMngmnt.getRequestXIMessageContext();
		 msgCtx.setSenderPartyName("");
		 msgCtx.setSenderService("BS_SENDER");
		// msgCtx.addReceiverService("INTEGRATION_ENGINE_JAVA_C73");
		// FlightSeatAvailabilityQueryOut port =
		// service.getFlightSeatAvailabilityQuery_Out_Port();

		FlightSeatAvailabilityResponse result;
		try {
			result = port
					.flightSeatAvailabilityQueryOut(query);
			int businessFree = result.getBusinessFreeSeats();
			int businessMax = result.getBusinessMaxSeats();
			


			if (flight  != null) {

				out.println("<p>"+ "Total seats in business class"+ "<strong>" + businessFree + "</strong></p>");
				out.println("<p>"+ "Free seats in business class"+ "<strong>" + businessMax + "</strong></p>");
			} else {
				out.println("<strong>" + "Enter flight" + "</strong>");
				out.println("<table style=\"margin: 10px 0px;\" summary=\"Enter flight details\">");
				out.println("<tr>");
				out.println("<td>Flight</td>");
				out.println("<td><input type=text size=20 name=flight /></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>Connection</td>");
				out.println("<td><input type=text size=20 name=connId /></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<input type=submit value=Execute />");

			}
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");			
			
			
			
			
		} catch (FlightNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<?xml version=\"1.0\"?>");
		out
				.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
		out
				.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\" xml:lang=\"en\">");
		out
				.println("<head><title>Flight Java Proxy Test Web service</title></head>\n");
		out.println("<body>");
		out.print("<form action=\"");
		out.print("ProxyTestServlet\" ");
		out.println("method=get>");
		
		
		// Instantiate the input objects using plain JAX-WS:
		FlightID flightID = new FlightID();
		
		String flight = request.getParameter("flight");
		String connId = request.getParameter("connId");
		
		flightID.setAirlineID(flight);
		flightID.setConnectionID(connId);
		
		XMLGregorianCalendar date;
		try {
			date = DatatypeFactory.newInstance().newXMLGregorianCalendar();
			date.setDay(1);
			date.setMonth(11);
			date.setYear(2009);
			flightID.setFlightDate(date);			
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		FlightSeatAvailabilityQuery query = new FlightSeatAvailabilityQuery();
		query.setFlightID(flightID);	
		

		FlightSeatAvailabilityQueryOutNew port = service
				.getPort(FlightSeatAvailabilityQueryOutNew.class);
		XIManagementInterface xiMngmnt = XIManagementInterfaceFactory
				.create(port);
		XIMessageContext msgCtx = xiMngmnt.getRequestXIMessageContext();
		 msgCtx.setSenderPartyName("");
		 msgCtx.setSenderService("BS_SENDER");
		// msgCtx.addReceiverService("INTEGRATION_ENGINE_JAVA_C73");
		// FlightSeatAvailabilityQueryOut port =
		// service.getFlightSeatAvailabilityQuery_Out_Port();

		FlightSeatAvailabilityResponse result;
		try {
			result = port
					.flightSeatAvailabilityQueryOut(query);
			int businessFree = result.getBusinessFreeSeats();
			int businessMax = result.getBusinessMaxSeats();
			


			if (flight  != null) {

				out.println("<p>"+ "Total seats in business class"+ "<strong>" + businessFree + "</strong></p>");
				out.println("<p>"+ "Free seats in business class"+ "<strong>" + businessMax + "</strong></p>");
			} else {
				out.println("<strong>" + "Enter flight" + "</strong>");
				out.println("<table style=\"margin: 10px 0px;\" summary=\"Enter flight details\">");
				out.println("<tr>");
				out.println("<td>Flight</td>");
				out.println("<td><input type=text size=20 name=flight /></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>Connection</td>");
				out.println("<td><input type=text size=20 name=connId /></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<input type=submit value=Execute />");

			}
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");			
			
			
			
			
		} catch (FlightNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
