package org.processmining.redologs.transform;

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
import org.processmining.redologs.oracle.OracleLogMinerExtractor;

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
							changeVector[j] = OracleLogMinerExtractor.COLUMN_CHANGE_NONE;
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
	
	public static void main(String[] args) {
		String dir = "data";
		
		File f1 = new File("/home/eduardo/Code/datasets/Marcus/2015-04-15/PMDS_WW_ExtractEventLog Test/PMDS_WW_TestExtractEventLog_WWRecht_Hub_C01-no-bom.csv");
		File f2 = new File("/home/eduardo/Code/datasets/Marcus/2015-04-15/PMDS_WW_ExtractEventLog Test/PMDS_WW_TestExtractEventLog_WWRecht_Sat_v1_C01-no-bom.csv");
		File f3 = new File("/home/eduardo/Code/datasets/Marcus/2015-04-15/PMDS_WW_ExtractEventLog Test/PMDS_WW_TestExtractEventLog_WWRecht_WWSessie_Link_v1_C01-no-bom.csv");
		File f4 = new File("/home/eduardo/Code/datasets/Marcus/2015-04-15/PMDS_WW_ExtractEventLog Test/PMDS_WW_TestExtractEventLog_WWSessie_Hub_C01-no-bom.csv");
		File f5 = new File("/home/eduardo/Code/datasets/Marcus/2015-04-15/PMDS_WW_ExtractEventLog Test/PMDS_WW_TestExtractEventLog_WWSessie_Sat_v1_C01-no-bom.csv");
		
		File f = new File("data/marcus-imported-01.slexcol");
		
		try {
			
			SLEXStorageCollection st = new SLEXStorageImpl(dir,f.getName(),SLEXStorage.TYPE_COLLECTION);
			
			SLEXEventCollection evCol = st.createEventCollection("UWV-01");
			st.setAutoCommit(false);
			
			ImportCSV imp = new ImportCSV(evCol);
			
			int index = imp.importCSVFile(f1,0,new String[] {"DVK_WWRecht_ID"},"WWRecht_Hub_C01","DVA_LaadDatum");
			index = imp.importCSVFile(f2,index,new String[] {"DVK_WWRecht_ID"},"WWRecht_Sat_v1_C01","DVA_LaadDatum");
			index = imp.importCSVFile(f4,index,new String[] {"DVK_WWSessie_ID"},"WWSessie_Hub_C01","DVA_LaadDatum");
			index = imp.importCSVFile(f5,index,new String[] {"DVK_WWSessie_ID"},"WWSessie_Sat_v1_C01","DVA_LaadDatum");
			index = imp.importCSVFile(f3,index,new String[] {"DVK_WWRecht_WWSessie_ID"},"WWRecht_WWSessie_Link_v1_C01","DVA_LaadDatum");
			
			evCol.commit();
			
			File fdm = new File("data/marcus-imported-01.slexdm");
			SLEXStorageDataModel stDm = new SLEXStorageImpl(dir, fdm.getName(), SLEXStorage.TYPE_DATAMODEL);
			
			SLEXDMDataModel dm = stDm.createDMDataModel("UWV-DM-01");
			
			SLEXDMClass c1 = stDm.createDMClass(dm.getId(), "WWRecht_Hub_C01", false);
			SLEXDMClass c2 = stDm.createDMClass(dm.getId(), "WWRecht_Sat_v1_C01", false);
			SLEXDMClass c3 = stDm.createDMClass(dm.getId(), "WWRecht_WWSessie_Link_v1_C01", false);
			SLEXDMClass c4 = stDm.createDMClass(dm.getId(), "WWSessie_Hub_C01", false);
			SLEXDMClass c5 = stDm.createDMClass(dm.getId(), "WWSessie_Sat_v1_C01", false);
			
			
			SLEXDMAttribute c1at01 = stDm.createDMAttribute(c1.getId(), "DVK_WWRecht_ID", false);
			SLEXDMAttribute c1at02 = stDm.createDMAttribute(c1.getId(), "DVA_LaadDatum", false);
			
			SLEXDMAttribute c2at01 = stDm.createDMAttribute(c2.getId(), "DVK_WWRecht_ID", false);
			SLEXDMAttribute c2at02 = stDm.createDMAttribute(c2.getId(), "DVA_LaadDatum", false);
			SLEXDMAttribute c2at03 = stDm.createDMAttribute(c2.getId(), "FirstDayOfUnemployment", false);
			SLEXDMAttribute c2at04 = stDm.createDMAttribute(c2.getId(), "Decision_ID", false);
			SLEXDMAttribute c2at05 = stDm.createDMAttribute(c2.getId(), "DVA_BeginDatum", false);
			SLEXDMAttribute c2at06 = stDm.createDMAttribute(c2.getId(), "DVA_EindDatum", false);
			
			SLEXDMAttribute c3at01 = stDm.createDMAttribute(c3.getId(), "DVK_WWRecht_WWSessie_ID", false);
			SLEXDMAttribute c3at02 = stDm.createDMAttribute(c3.getId(), "DVK_WWRecht_ID", false);
			SLEXDMAttribute c3at03 = stDm.createDMAttribute(c3.getId(), "DVK_WWSessie_ID", false);
			SLEXDMAttribute c3at04 = stDm.createDMAttribute(c3.getId(), "DVA_LaadDatum", false);
			SLEXDMAttribute c3at05 = stDm.createDMAttribute(c3.getId(), "DVA_BeginDatum", false);
			SLEXDMAttribute c3at06 = stDm.createDMAttribute(c3.getId(), "DVA_EindDatum", false);
			
			SLEXDMAttribute c4at01 = stDm.createDMAttribute(c4.getId(), "DVK_WWSessie_ID", false);
			SLEXDMAttribute c4at02 = stDm.createDMAttribute(c4.getId(), "DVA_LaadDatum", false);
			
			SLEXDMAttribute c5at01 = stDm.createDMAttribute(c5.getId(), "DVK_WWSessie_ID", false);
			SLEXDMAttribute c5at02 = stDm.createDMAttribute(c5.getId(), "DVA_LaadDatum", false);
			SLEXDMAttribute c5at03 = stDm.createDMAttribute(c5.getId(), "DateStartBenefits", false);
			SLEXDMAttribute c5at04 = stDm.createDMAttribute(c5.getId(), "DateEndBenefits", false);
			SLEXDMAttribute c5at05 = stDm.createDMAttribute(c5.getId(), "DateDecision", false);
			SLEXDMAttribute c5at06 = stDm.createDMAttribute(c5.getId(), "DateDecisionBenefitsEnd", false);
			SLEXDMAttribute c5at07 = stDm.createDMAttribute(c5.getId(), "DateOfClaim", false);
			SLEXDMAttribute c5at08 = stDm.createDMAttribute(c5.getId(), "AverageNumberWorkingHours", false);
			SLEXDMAttribute c5at09 = stDm.createDMAttribute(c5.getId(), "DaylyWages", false);
			SLEXDMAttribute c5at10 = stDm.createDMAttribute(c5.getId(), "TimingOfNotificationOfUnemployment", false);
			SLEXDMAttribute c5at11 = stDm.createDMAttribute(c5.getId(), "DVA_BeginDatum", false);
			SLEXDMAttribute c5at12 = stDm.createDMAttribute(c5.getId(), "DVA_EindDatum", false);
			
			SLEXDMKey c1pk = stDm.createDMKey(c1.getId(), "WWRecht_Hub_C01_PK", SLEXDMKey.PRIMARY_KEY, SLEXDMKey.REFERS_TO_NULL);
			SLEXDMKeyAttribute c1pkAt01 = stDm.createDMKeyAttribute(c1pk.getId(), c1at01.getId(), SLEXDMKeyAttribute.REFERS_TO_NULL, 0);
			
			SLEXDMKey c2pk = stDm.createDMKey(c2.getId(), "WWRecht_Sat_v1_C01_PK", SLEXDMKey.PRIMARY_KEY, SLEXDMKey.REFERS_TO_NULL);
			SLEXDMKeyAttribute c2pkAt01 = stDm.createDMKeyAttribute(c2pk.getId(), c2at01.getId(), SLEXDMKeyAttribute.REFERS_TO_NULL, 0);
			//SLEXDMKeyAttribute c2pkAt02 = stDm.createDMKeyAttribute(c2pk.getId(), c2at02.getId(), SLEXDMKeyAttribute.REFERS_TO_NULL, 1);
			
			SLEXDMKey c3pk = stDm.createDMKey(c3.getId(), "WWRecht_WWSessie_Link_v1_C01_PK", SLEXDMKey.PRIMARY_KEY, SLEXDMKey.REFERS_TO_NULL);
			SLEXDMKeyAttribute c3pkAt01 = stDm.createDMKeyAttribute(c3pk.getId(), c3at01.getId(), SLEXDMKeyAttribute.REFERS_TO_NULL, 0);
			
			SLEXDMKey c4pk = stDm.createDMKey(c4.getId(), "WWSessie_Hub_C01_PK", SLEXDMKey.PRIMARY_KEY, SLEXDMKey.REFERS_TO_NULL);
			SLEXDMKeyAttribute c4pkAt01 = stDm.createDMKeyAttribute(c4pk.getId(), c4at01.getId(), SLEXDMKeyAttribute.REFERS_TO_NULL, 0);
			
			SLEXDMKey c5pk = stDm.createDMKey(c5.getId(), "WWSessie_Sat_v1_C01_PK", SLEXDMKey.PRIMARY_KEY, SLEXDMKey.REFERS_TO_NULL);
			SLEXDMKeyAttribute c5pkAt01 = stDm.createDMKeyAttribute(c5pk.getId(), c5at01.getId(), SLEXDMKeyAttribute.REFERS_TO_NULL, 0);
			//SLEXDMKeyAttribute c5pkAt02 = stDm.createDMKeyAttribute(c5pk.getId(), c5at02.getId(), SLEXDMKeyAttribute.REFERS_TO_NULL, 1);
			
			SLEXDMKey c2fk = stDm.createDMKey(c2.getId(), "WWRecht_Sat_v1_C01_FK", SLEXDMKey.FOREIGN_KEY, c1pk.getId());
			SLEXDMKeyAttribute c2fkAt01 = stDm.createDMKeyAttribute(c2fk.getId(), c2at01.getId(), c1pkAt01.getAttributeId(), 0);
			
			SLEXDMKey c3fk1 = stDm.createDMKey(c3.getId(), "WWRecht_WWSessie_Link_v1_C01_TO_WWRecht_FK", SLEXDMKey.FOREIGN_KEY, c1pk.getId());
			SLEXDMKeyAttribute c3fk1At01 = stDm.createDMKeyAttribute(c3fk1.getId(), c3at02.getId(), c1pkAt01.getAttributeId(), 0);
			
			SLEXDMKey c3fk2 = stDm.createDMKey(c3.getId(), "WWRecht_WWSessie_Link_v1_C01_TO_WWSessie_FK", SLEXDMKey.FOREIGN_KEY, c4pk.getId());
			SLEXDMKeyAttribute c3fk2At01 = stDm.createDMKeyAttribute(c3fk2.getId(), c3at01.getId(), c4pkAt01.getAttributeId(), 0);
			
			SLEXDMKey c5fk = stDm.createDMKey(c5.getId(), "WWSessie_Sat_v1_C01_FK", SLEXDMKey.FOREIGN_KEY, c4pk.getId());
			SLEXDMKeyAttribute c5fkAt01 = stDm.createDMKeyAttribute(c5fk.getId(), c5at01.getId(), c4pkAt01.getAttributeId(), 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
