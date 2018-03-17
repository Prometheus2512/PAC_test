package sample.IServices;

import sample.Entities.Event;

public interface IEventService {
    public void addEvent(Event E);
    public void deleteEvent(int id);
    public void updateEvent(Event E);
    public void approveEvent(int id);
}
