package wdsr.exercise3.record;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RecordInventory {
	private AtomicInteger counter;
	private Map<Integer, Record> records;
	
	public RecordInventory() {
		this.counter = new AtomicInteger(0);
		this.records = new HashMap<>();
	}
	
	public synchronized int addRecord(final Record record) {
		int id = counter.incrementAndGet();
		record.setId(id);
		records.put(id, record);
		return id;
	}
	
	public synchronized boolean updateRecord(final int id, final Record record) {
		if (! records.containsKey(id)) {
			return false;
		}
		record.setId(id);
		records.put(id, record);
		return true;
	}
	
	public synchronized boolean deleteRecord(final int id) {
		if (! records.containsKey(id)) {
			return false;
		}
		records.remove(id);
		return true;
	}
	
	public synchronized List<Record> getRecords() {
		return new ArrayList<Record>(records.values());
	}
	
	public synchronized Record getRecord(final int id) {
		if (! records.containsKey(id)) {
			return null;
		}
		return records.get(id);
	}		
}
