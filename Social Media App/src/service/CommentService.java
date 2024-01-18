package service;

import java.time.LocalDateTime;
import java.util.Scanner;
import model.Comment;
import model.Post;
import repository.CommentRepository;
import repository.PostRepository;
import repository.UserRepository;

public class CommentService {
    private CommentRepository commentRepository = new CommentRepository();
    private PostRepository postRepository = new PostRepository();
    private UserRepository userRepository = new UserRepository();
    private Scanner scNumere;
    private Scanner scText;

    public CommentService() {
        this.scNumere = new Scanner(System.in);
        this.scText = new Scanner(System.in);
    }

    public void create() {
        System.out.println("Introduceti id-ul postarii la care vreti sa comentati: ");
        int postId = this.scNumere.nextInt();
        PostRepository var10000 = this.postRepository;
        Post postareCitita = PostRepository.readById(postId);
        if (postareCitita == null) {
            System.out.println("Nu exista nici o postare cu id-ul " + postId);
        } else {
            System.out.println("Introduceti mesajul: ");
            String mesaj = this.scText.nextLine();
            LocalDateTime createdAt = LocalDateTime.now();
            int affectedRow = this.commentRepository.create(postId, mesaj, createdAt);
            if (affectedRow > 0) {
                System.out.println("Comentariul a fost salvat!");
            } else {
                System.out.println("Comentariul nu a putut fii salvat!");
            }
        }

    }

    public void update() {
        System.out.println("Introduceti id-ul comentariului pe care doriti sa-l modificati: ");
        int commentId = this.scNumere.nextInt();
        Comment CommentCitit = this.commentRepository.readById(commentId);
        if (CommentCitit == null) {
            System.out.println("Nu exista niciun comentariu cu id-ul " + commentId);
        } else {
            System.out.println("Comentariul curent: " + CommentCitit);
            System.out.println("Introduceti noul mesaj pentru comentariu: ");
            String mesajNou = this.scText.nextLine();
            int affectedRows = this.commentRepository.update(commentId, mesajNou);
            if (affectedRows > 0) {
                System.out.println("Comentariul a fost actualizat cu succes!");
            } else {
                System.out.println("Comentariul nu a putut fi actualizat!");
            }
        }

    }

    public void delete() {
        System.out.println("Introduceti id-ul comentariului pe care doriti sa-l stergeti: ");
        int commentId = this.scNumere.nextInt();
        int affectedRows = this.commentRepository.delete(commentId);
        if (affectedRows > 0) {
            System.out.println("Comentariul a fost sters cu succes!");
        } else {
            System.out.println("Comentariul nu a putut fi sters!");
        }

    }
}
