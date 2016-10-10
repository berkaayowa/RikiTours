package za.co.whcb.tp2.rikitours.config.database.table.room;

import za.co.whcb.tp2.rikitours.config.database.table.Attribute;
import za.co.whcb.tp2.rikitours.config.database.table.Table;

/**
 * Created by Tamonne on 9/26/2016.
 */
public class RoomTable  {


    public final String tableName = "room";
    public static Attribute id = new Attribute("id", "number");
    public static Attribute size = new Attribute("size", "Text");
    public static Attribute type = new Attribute("type", "Text");
    public static Attribute description = new Attribute("description", "Text");
    public static Attribute hotelId = new Attribute("hotelId", "Text");


}
