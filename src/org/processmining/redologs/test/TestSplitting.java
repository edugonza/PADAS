package org.processmining.redologs.test;

import org.processmining.openslex.SLEXAttribute;
import org.processmining.openslex.SLEXEvent;
import org.processmining.openslex.SLEXEventCollection;
import org.processmining.openslex.SLEXStorage;

public class TestSplitting {
	public static void main(String[] args) {
		try {
			SLEXStorage storage = new SLEXStorage();
			SLEXEventCollection evCol = storage.createEventCollection("Test");
			
			SLEXAttribute scn_Attr = storage.findOrCreateAttribute("COMMON", "SCN", true);
			SLEXAttribute table_name_Attr = storage.findOrCreateAttribute("COMMON", "TABLE_NAME", true);
			SLEXAttribute operation_Attr = storage.findOrCreateAttribute("COMMON", "OPERATION", true);
			SLEXAttribute column_changes_Attr = storage.findOrCreateAttribute("COMMON", "COLUMN_CHANGES", true);
			
			SLEXAttribute customer_attr_customer_id = storage.findOrCreateAttribute("CUSTOMER", "CUSTOMER_ID", false);
			SLEXAttribute customer_attr_birth_date = storage.findOrCreateAttribute("CUSTOMER", "BIRTH_DATE", false);
			SLEXAttribute customer_attr_customer_name = storage.findOrCreateAttribute("CUSTOMER", "CUSTOMER_NAME", false);
			SLEXAttribute customer_attr_address = storage.findOrCreateAttribute("CUSTOMER", "ADDRESS", false);
			
			SLEXAttribute booking_attr_customer_id = storage.findOrCreateAttribute("BOOKING", "CUSTOMER_ID", false);
			SLEXAttribute booking_attr_booking_id = storage.findOrCreateAttribute("BOOKING", "BOOKING_ID", false);
			
			SLEXAttribute ticket_attr_ticket_id = storage.findOrCreateAttribute("TICKET", "TICKET_ID", false);
			SLEXAttribute ticket_attr_price = storage.findOrCreateAttribute("TICKET", "PRICE", false);
			SLEXAttribute ticket_attr_belongs_to = storage.findOrCreateAttribute("TICKET", "BELONGS_TO", false);
			SLEXAttribute ticket_attr_for_concert = storage.findOrCreateAttribute("TICKET", "FOR_CONCERT", false);
			SLEXAttribute ticket_attr_booking_id= storage.findOrCreateAttribute("TICKET", "BOOKING_ID", false);
			
			SLEXEvent ev_customer = storage.createEvent(evCol.getId());
			
			storage.createAttributeValue(scn_Attr.getId(), ev_customer.getId(), "1");
			storage.createAttributeValue(table_name_Attr.getId(), ev_customer.getId(), "CUSTOMER");
			storage.createAttributeValue(operation_Attr.getId(), ev_customer.getId(), "INSERT");
			storage.createAttributeValue(column_changes_Attr.getId(), ev_customer.getId(), "1111");
			
			storage.createAttributeValue(customer_attr_customer_id.getId(), ev_customer.getId(), "1234");
			storage.createAttributeValue(customer_attr_birth_date.getId(), ev_customer.getId(), "yesterday");
			storage.createAttributeValue(customer_attr_customer_name.getId(), ev_customer.getId(), "mengano");
			storage.createAttributeValue(customer_attr_address.getId(), ev_customer.getId(), "under the bridge");
			
			
			SLEXEvent ev_booking = storage.createEvent(evCol.getId());
			
			storage.createAttributeValue(scn_Attr.getId(), ev_booking.getId(), "2");
			storage.createAttributeValue(table_name_Attr.getId(), ev_booking.getId(), "BOOKING");
			storage.createAttributeValue(operation_Attr.getId(), ev_booking.getId(), "INSERT");
			storage.createAttributeValue(column_changes_Attr.getId(), ev_booking.getId(), "1111");
			
			storage.createAttributeValue(booking_attr_customer_id.getId(), ev_booking.getId(), "1234");
			storage.createAttributeValue(booking_attr_booking_id.getId(), ev_booking.getId(), "1235");
			
			SLEXEvent ev_ticket = storage.createEvent(evCol.getId());
			
			storage.createAttributeValue(scn_Attr.getId(), ev_ticket.getId(), "3");
			storage.createAttributeValue(table_name_Attr.getId(), ev_ticket.getId(), "TICKET");
			storage.createAttributeValue(operation_Attr.getId(), ev_ticket.getId(), "UPDATE");
			storage.createAttributeValue(column_changes_Attr.getId(), ev_ticket.getId(), "0010");
			
			storage.createAttributeValue(ticket_attr_ticket_id.getId(), ev_ticket.getId(), "1236");
			storage.createAttributeValue(ticket_attr_price.getId(), ev_ticket.getId(), "4000000");
			storage.createAttributeValue(ticket_attr_belongs_to.getId(), ev_ticket.getId(), "1");
			storage.createAttributeValue(ticket_attr_for_concert.getId(), ev_ticket.getId(), "2");
			storage.createAttributeValue(ticket_attr_booking_id.getId(), ev_ticket.getId(), "1235");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
