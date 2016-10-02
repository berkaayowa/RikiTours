package za.co.whcb.tp2.rikitours.factories.tour;

import za.co.whcb.tp2.rikitours.domain.tour.City;
import za.co.whcb.tp2.rikitours.domain.tour.EventsDescription;

/**
 * Created by Shaun Mesias on 2016/10/02.
 */
public class EventDescriptionFactory {
    public static EventsDescription getEventDescription(String description, String start, String end, City city)
    {
        return new EventsDescription.Builder()
                .description(description)
                .start(start)
                .end(end)
                .city(city)
                .build();
    }
}