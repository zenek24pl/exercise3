package wdsr.exercise3.hr;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.ws.Endpoint;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class HolidayClientTest {
	private static final String SERVICE_ADDRESS = "http://localhost:8090/holidayService/";
	private static final String WSDL_ADDRESS = SERVICE_ADDRESS + "?wsdl";
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private static Date startDate;
	private static Date endDate;
	private static final int employeeId = 367;
	private static final String firstName = "firstName";
	private static final String lastName = "secondName";
	
	static {
		try {
			startDate = sdf.parse("11/04/2016");
			endDate = sdf.parse("14/04/2016");		
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Mock
	private HolidayRepository repo;
	private Object implementor;
	private Endpoint endpoint;
	private HolidayClient client;
	
	@Before
	public void setUp() throws MalformedURLException {
		MockitoAnnotations.initMocks(this);
		implementor = new HumanResourcePortImpl(repo);
        endpoint = Endpoint.publish(SERVICE_ADDRESS, implementor);        
        client = new HolidayClient(new URL(WSDL_ADDRESS));
	}
	
	@After
	public void tearDown() {
		endpoint.stop();
	}
	
	@Test
	public void shouldBookHoliday() throws MalformedURLException {
		// given
		final int requestId = 367;
		given(repo.saveHolidayRequest(employeeId, startDate, endDate)).willReturn(requestId);
		
		// when
        int result = client.bookHoliday(employeeId, firstName, lastName, startDate, endDate);
		
		// then
        assertEquals(requestId, result);
        BDDMockito.verify(repo).saveHolidayRequest(employeeId, startDate, endDate);
	}
	
	@Test(expected=ProcessingException.class)
	public void shouldBookHoliday_whenFaultOccurs() throws MalformedURLException {
		// given
		given(repo.saveHolidayRequest(employeeId, startDate, endDate)).willThrow(new UnsupportedOperationException("Expected exception for test"));
		
		// when
        client.bookHoliday(employeeId, firstName, lastName, startDate, endDate);
	}	
}
