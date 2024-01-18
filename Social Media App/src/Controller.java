import java.util.Scanner;
import service.CommentService;
import service.PostService;
import service.UserService;

public class Controller {
    private static CommentService commentService = new CommentService();
    private static UserService userService = new UserService();
    private static PostService postService = new PostService();
    static Scanner scText;

    public Controller() {
    }

    public static void main(String[] args) {
        while(true) {
            System.out.println("Introduceti flow-ul dorit (user/post/comment): ");
            switch (scText.nextLine()) {
                case "user":
                    startUserFlow();
                    break;
                case "post":
                    startPostFlow();
                    break;
                case "comment":
                    startCommentFlow();
                    break;
                default:
                    System.out.println("Acest flow nu exista!");
            }
        }
    }

    private static void startUserFlow() {
        switch (choseOperation("RA / RBI / C / U / D")) {
            case "RA":
                userService.readAll();
                break;
            case "RBI":
                userService.readById();
                break;
            case "C":
                userService.create();
                break;
            case "U":
                userService.update();
                break;
            case "D":
                userService.delete();
                break;
            default:
                System.out.println("Operatie invalida!");
        }

    }

    private static void startPostFlow() {
        switch (choseOperation("RA / RUP / C / U / D")) {
            case "RA":
                postService.readAll();
                break;
            case "RUP":
                postService.readUserPosts();
                break;
            case "C":
                postService.create();
                break;
            case "U":
                postService.update();
                break;
            case "D":
                postService.delete();
                break;
            default:
                System.out.println("Operatie invalida!");
        }

    }

    private static void startCommentFlow() {
        switch (choseOperation("C / U / D")) {
            case "C":
                commentService.create();
                break;
            case "U":
                commentService.update();
                break;
            case "D":
                commentService.delete();
                break;
            default:
                System.out.println("Operatie invalida!");
        }

    }

    private static String choseOperation(String operations) {
        System.out.println("Introduceti operatia dorita: (" + operations + ")");
        String operatieAleasa = scText.nextLine();
        return operatieAleasa;
    }

    static {
        scText = new Scanner(System.in);
    }
}
