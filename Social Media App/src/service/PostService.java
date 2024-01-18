package service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import model.Comment;
import model.Post;
import model.User;
import repository.CommentRepository;
import repository.PostRepository;
import repository.UserRepository;

public class PostService {
    private CommentRepository commentRepository = new CommentRepository();
    private PostRepository postRepository = new PostRepository();
    private UserRepository userRepository = new UserRepository();
    private Scanner scNumere;
    private Scanner scText;

    public PostService() {
        this.scNumere = new Scanner(System.in);
        this.scText = new Scanner(System.in);
    }

    public void readAll() {
        ArrayList<Post> allPosts = this.postRepository.readAll();
        Iterator var2 = allPosts.iterator();

        while(var2.hasNext()) {
            Post post = (Post)var2.next();
            System.out.println(post);
        }

        ArrayList<Comment> allComments = this.commentRepository.readAll();
        Iterator var6 = allComments.iterator();

        while(var6.hasNext()) {
            Comment comment = (Comment)var6.next();
            System.out.println(comment);
        }

    }

    public void readUserPosts() {
        System.out.println("Introduceti id-ul user-ului pentru a-i returna postarile: ");
        int userId = this.scNumere.nextInt();
        User userCitit = UserRepository.readById(userId);
        if (userCitit == null) {
            System.out.println("Nu exista nici un user cu id-ul " + userId);
        } else {
            ArrayList<Post> postarileUserului = this.postRepository.readPostsFromUserWithId(userId);
            postarileUserului.forEach((postare) -> {
                System.out.println(postare);
            });
        }

    }

    public void create() {
        System.out.println("Introduceti id-ul user-ului: ");
        int userId = this.scNumere.nextInt();
        UserRepository var10000 = this.userRepository;
        User userCitit = UserRepository.readById(userId);
        if (userCitit == null) {
            System.out.println("Nu exista nici un user cu id-ul " + userId);
        } else {
            System.out.println("Introduceti mesajul: ");
            String mesaj = this.scText.nextLine();
            LocalDateTime createdAt = LocalDateTime.now();
            int affectedRows = this.postRepository.create(userId, mesaj, createdAt);
            if (affectedRows > 0) {
                System.out.println("Postarea a fost salvata!");
            } else {
                System.out.println("Postarea nu a putut fii salvata!");
            }
        }

    }

    public void update() {
        System.out.println("Introduceti id-ul postarii: ");
        int postId = this.scNumere.nextInt();
        PostRepository var10000 = this.postRepository;
        Post postare = PostRepository.readById(postId);
        if (postare == null) {
            System.out.println("Nu exista nici o postare cu id-ul " + postId);
        } else {
            System.out.println("Introduceti noul mesaj: ");
            String mesajNou = this.scText.nextLine();
            int affectedRows = this.postRepository.update(postId, mesajNou);
            if (affectedRows > 0) {
                System.out.println("Postarea a fost modificata!");
            } else {
                System.out.println("Postarea nu a putut fii modificata!");
            }
        }

    }

    public void delete() {
        System.out.println("Introduceti id-ul postarii pe care doriti sa o stergeti: ");
        int postId = this.scNumere.nextInt();
        int affectedRows = this.postRepository.delete(postId);
        System.out.println("Postarea " + (affectedRows == 0 ? "nu" : "") + " a fost stearsa!");
    }
}
