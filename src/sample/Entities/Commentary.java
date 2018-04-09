package sample.Entities;

import java.util.Date;

public class Commentary {
    int commentator_id;
    int commentedevent_id;
    String content;
    Date CreationDate;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommentator_id() {
        return commentator_id;
    }

    public void setCommentator_id(int commentator_id) {
        this.commentator_id = commentator_id;
    }

    public int getCommentedevent_id() {
        return commentedevent_id;
    }

    public void setCommentedevent_id(int commentedevent_id) {
        this.commentedevent_id = commentedevent_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(Date creationDate) {
        CreationDate = creationDate;
    }
}
