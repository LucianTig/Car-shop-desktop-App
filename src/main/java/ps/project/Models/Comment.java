package ps.project.Models;

import java.io.Serializable;

public class Comment implements Serializable {

    private static int id_count=0;
    private int id;
    private String username;
    private String comment;
    private int like;
    private int dislike;

    public Comment(String username, String comment) {
        this.username = username;
        this.comment = comment;
        this.id=id_count;
        id_count++;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getId() {
        return id;
    }
}
