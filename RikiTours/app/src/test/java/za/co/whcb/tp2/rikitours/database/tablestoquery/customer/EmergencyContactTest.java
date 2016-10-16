package za.co.whcb.tp2.rikitours.database.tablestoquery.customer;

import org.junit.Test;

import za.co.whcb.tp2.rikitours.common.Converter;
import za.co.whcb.tp2.rikitours.config.database.table.customer.EmergencyContactTable;
/**
 * Created by Tamonne on 2016-10-10.
 */


public class EmergencyContactTest {
    @Test
    public void testEmergencyContactTable() throws Exception {
        EmergencyContact emergencyContact = new EmergencyContact();
        String query = Converter.toCreateTableQuery(emergencyContact.getTableName(),emergencyContactTable.getAllAttributes());
        System.out.println(query);

    }
}