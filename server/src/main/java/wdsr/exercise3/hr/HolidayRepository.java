package wdsr.exercise3.hr;

import java.util.Date;

/**
 * Created by Marek on 18.04.2016.
 */
public class HolidayRepository {
	public int saveHolidayRequest(int employeeId, Date fromDate, Date endDate) {
        return employeeId * 37;
	}
}
