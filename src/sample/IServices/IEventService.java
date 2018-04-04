package sample.IServices;

import sample.Entities.Event;

import java.util.List;

public interface IEventService {
    public void addEvent(Event E);
    public void deleteEvent(int id);
    public void updateEvent(Event E);
    public void approveEvent(int id);
    public Event showEvent(int id);
    public List<Event> allEvents();
}
