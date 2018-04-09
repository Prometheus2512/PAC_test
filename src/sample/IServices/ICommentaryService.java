package sample.IServices;

import sample.Entities.Commentary;
import sample.Entities.Event;

import java.util.List;

public interface ICommentaryService {
    List<Commentary> ListCommentaries(Event e);
    void comment(Commentary c);
    void deletecomment(int id);

}
