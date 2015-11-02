package org.processmining.database.redologs.transform;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.processmining.openslex.SLEXAttribute;
import org.processmining.openslex.SLEXAttributeValue;
import org.processmining.openslex.SLEXDMAttribute;
import org.processmining.openslex.SLEXDMClass;
import org.processmining.openslex.SLEXDMDataModel;
import org.processmining.openslex.SLEXDMKey;
import org.processmining.openslex.SLEXDMKeyAttribute;
import org.processmining.openslex.SLEXEvent;
import org.processmining.openslex.SLEXEventCollection;
import org.processmining.openslex.SLEXStorage;
import org.processmining.openslex.SLEXStorageCollection;
import org.processmining.openslex.SLEXStorageDataModel;
import org.processmining.openslex.SLEXStorageImpl;
import org.processmining.database.redologs.oracle.OracleLogMinerExtractor;

public class ImportCSV {

	private SLEXEventCollection evCol = null;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	
	public ImportCSV(SLEXEventCollection evCol) {
		this.evCol = evCol;
	}
	
	public int importCSVFile(File f, int initOrder, String[] objectIDField, String className, String timestampField) {
		int i = initOrder;
		if (f.exists()) {
			Reader in = null;
			CSVParser parser = null;
			
			HashMap<String,HashMap<String, String>> recordsMap = new HashMap<>();
			HashMap<String,SLEXAttribute> attMap = new HashMap<>();
			
			try {
				in = new FileReader(f);
				parser = CSVParser.parse(f, Charset.forName("UTF-8"), CSVFormat.EXCEL.withDelimiter(';').withHeader());
				Map<String,Integer> headerMap = parser.getHeaderMap();
				List<String> headerList = new ArrayList<>();
				for (String h: headerMap.keySet()) {
					headerList.add(h.trim().replace("\r","").replace("\n",""));
				}
				Collections.sort(headerList);
				System.out.println("Sorted list for "+className);
				System.out.println(headerList);
				
				String[] changeVector = new String[headerList.size()];
				
				for (int j = 0; j < headerList.size(); j++) {
					SLEXAttribute at = evCol.getStorage().findOrCreateAttribute(className, headerList.get(j), false);
					attMap.put(headerList.get(j), at);
				}
				
				SLEXAttribute timestampAt = evCol.getStorage().findOrCreateAttribute(SLEXStorage.COMMON_CLASS_NAME,
						OracleLogMinerExtractor.COLUMN_TIMESTAMP, true);
				SLEXAttribute tableAt = evCol.getStorage().findOrCreateAttribute(SLEXStorage.COMMON_CLASS_NAME,
						OracleLogMinerExtractor.COLUMN_TABLE_NAME, true);
				SLEXAttribute orderAt = evCol.getStorage().findOrCreateAttribute(SLEXStorage.COMMON_CLASS_NAME,
						OracleLogMinerExtractor.COLUMN_ORDER, true);
				SLEXAttribute operationAt = evCol.getStorage().findOrCreateAttribute(SLEXStorage.COMMON_CLASS_NAME,
						OracleLogMinerExtractor.COLUMN_OPERATION, true);
				SLEXAttribute changeVectorAt = evCol.getStorage().findOrCreateAttribute(SLEXStorage.COMMON_CLASS_NAME,
						OracleLogMinerExtractor.COLUMN_CHANGES, true);
				
				for (CSVRecord record : parser) {
					StringBuffer objIdBf = new StringBuffer();
					for (String o: objectIDField) {
						objIdBf.append("#");
						objIdBf.append(record.get(o));
					}
					String objId = objIdBf.toString();
					String timestampVal = record.get(timestampField);
					String operation = null;
					HashMap<String,String> r = null;
					
					long timestampLong = dateFormat.parse(timestampVal).getTime();
					String orderVal =  String.valueOf(timestampLong + i);
					
					SLEXEvent e = evCol.getStorage().createEvent(evCol.getId());
					
					if (recordsMap.containsKey(objId)) {
						r = recordsMap.get(objId);
						operation = "UPDATE";
					} else {
						r = new HashMap<>();
						recordsMap.put(objId, r);
						operation = "INSERT";
					}
					
					for (int j = 0; j < headerList.size(); j++) {
						String h = headerList.get(j);
						String vOld = r.get(h);
						String vNew = record.get(h);
						if (vNew.equals(vOld)) {
							if (vNew.equals("NULL")) {
								changeVector[j] = OracleLogMinerExtractor.COLUMN_CHANGE_NONE_STILL_NULL;
							} else {
								changeVector[j] = OracleLogMinerExtractor.COLUMN_CHANGE_NONE;
							}
						} else if (vOld != null && vOld.equals("NULL")) {
							changeVector[j] = OracleLogMinerExtractor.COLUMN_CHANGE_FROM_NULL;
						} else if (vNew.equals("NULL")){
							changeVector[j] = OracleLogMinerExtractor.COLUMN_CHANGE_TO_NULL;
						} else {
							changeVector[j] = OracleLogMinerExtractor.COLUMN_CHANGE_UPDATED;
						}
						r.put(h, vNew);
						SLEXAttributeValue atV = evCol.getStorage().createAttributeValue(attMap.get(h).getId(), e.getId(), vNew);
					}
					
					SLEXAttributeValue atTb = evCol.getStorage().createAttributeValue(tableAt.getId(), e.getId(), className);
					SLEXAttributeValue atTs = evCol.getStorage().createAttributeValue(timestampAt.getId(), e.getId(), timestampVal);
					SLEXAttributeValue atOr = evCol.getStorage().createAttributeValue(orderAt.getId(), e.getId(), orderVal);
					SLEXAttributeValue atOp = evCol.getStorage().createAttributeValue(operationAt.getId(), e.getId(), operation);
					StringBuffer changeStr = new StringBuffer();
					for (String c: changeVector) {
						changeStr.append(c);
					}
					SLEXAttributeValue atCv = evCol.getStorage().createAttributeValue(changeVectorAt.getId(), e.getId(), changeStr.toString());
					
					i++;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (parser != null) {
					try {
						parser.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				evCol.getStorage().commit();
			}
		}
		
		return i;
	}
}
