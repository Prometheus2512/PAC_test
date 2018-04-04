package sample.IServices;

import sample.Entities.Reservation;

import java.util.List;

public interface IReservationService {
    public void book(int id);
    public void cancel(int id);
    public List<Reservation> myreservations();
    public int howmanyparticipants(int id);
    public boolean amiparticipating(int id);
}
