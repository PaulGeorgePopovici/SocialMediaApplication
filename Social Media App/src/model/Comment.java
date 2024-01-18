package model;

import java.time.LocalDateTime;

public class Comment {
    int id;
    int postId;
    int userId;
    String mesaj;
    LocalDateTime createdAt;

    public Comment(int id, int postId, int userId, String mesaj, LocalDateTime createdAt) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.mesaj = mesaj;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "\nComment{" +
                "id=" + id +
                ", postId=" + postId +
                ", userId=" + userId +
                ", mesaj='" + mesaj + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
