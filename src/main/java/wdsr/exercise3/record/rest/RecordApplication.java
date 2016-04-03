package wdsr.exercise3.record.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class RecordApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> result = new HashSet<>();
		result.add(RecordResource.class);
		return result;
	}
}
