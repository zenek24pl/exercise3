package wdsr.exercise3.hr;

import wdsr.exercise3.ws.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;

// TODO Complete this class to book holidays by issuing a request to Human Resource web service.
// In order to see definition of the Human Resource web service:
// 1. Run HolidayServerApp.
// 2. Go to http://localhost:8090/holidayService/?wsdl
public class HolidayClient {

	private HumanResourceService humanResourceService;

	public HolidayClient(URL wsdlLocation) {
		humanResourceService = new HumanResourceService(wsdlLocation);
	}

	/**
	 * Sends a holiday request to the HumanResourceService.
	 * 
	 * @param employeeId
	 *            Employee ID
	 * @param firstName
	 *            First name of employee
	 * @param lastName
	 *            Last name of employee
	 * @param startDate
	 *            First day of the requested holiday
	 * @param endDate
	 *            Last day of the requested holiday
	 * @return Identifier of the request, if accepted.
	 * @throws ProcessingException
	 *             if request processing fails.
	 */
	public int bookHoliday(int employeeId, String firstName, String lastName, Date startDate, Date endDate)
			throws ProcessingException {
		ObjectFactory holidayAndEmployeeTypeFactory = new ObjectFactory();
		HolidayResponse holidayResponse = new HolidayResponse();
		HumanResource humanResourcePort = humanResourceService.getHumanResourcePort();

		EmployeeType employeeType = new EmployeeType();
		employeeType = holidayAndEmployeeTypeFactory.createEmployeeType();
		employeeType.setFirstName(firstName);
		employeeType.setLastName(lastName);
		employeeType.setNumber(employeeId);

		HolidayType holidayType = new HolidayType();
		holidayType = holidayAndEmployeeTypeFactory.createHolidayType();
		holidayType.setStartDate((dateToXMLGregorianCalendar(startDate)));
		holidayType.setEndDate((dateToXMLGregorianCalendar(endDate)));

		HolidayRequest holidayRequest = new HolidayRequest();
		holidayRequest = holidayAndEmployeeTypeFactory.createHolidayRequest();
		holidayRequest.setEmployee(employeeType);
		holidayRequest.setHoliday(holidayType);
		try {
			holidayResponse = humanResourcePort.holiday(holidayRequest);
		} catch (Exception exception) {
			throw new ProcessingException("Request processing failed");
		}

		return holidayResponse.getRequestId();
	}

	public XMLGregorianCalendar dateToXMLGregorianCalendar(Date date) {
		DatatypeFactory df = null;
		try {
			df = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		if (date == null) {
			return null;
		} else {
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTimeInMillis(date.getTime());
			return df.newXMLGregorianCalendar(gc);
		}
	}
}
