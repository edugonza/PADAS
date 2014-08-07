package org.processmining.redologs.common;

import org.processmining.redologs.oracle.OracleLogMinerExtractor;

public class EventAttributeColumn {
	public static final int VALUE_NEW = 1;
	public static final int VALUE_OLD = 2;
	public Column c;
	public int type;
	
	@Override
	public String toString() {
		switch (type) {
		case VALUE_NEW:
			return c.table.toString()+"#"+OracleLogMinerExtractor.NEW_VALUES_PREFIX+c.name;
		case VALUE_OLD:
			return c.table.toString()+"#"+OracleLogMinerExtractor.OLD_VALUES_PREFIX+c.name;
		default:
			if (c == null) {
				return "";
			}
			if (c.table != null) {
				return c.table.toString()+"#"+c.name;
			} else {
				return c.name;
			}
		}
	}
}
