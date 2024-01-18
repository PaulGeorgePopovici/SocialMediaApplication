package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import model.User;
import repository.PostRepository;
import repository.UserRepository;

public class UserService {
    private UserRepository userRepository = new UserRepository();
    private PostRepository postRepository = new PostRepository();
    private Scanner scannerNumere;
    private Scanner scannerText;
    private Scanner scannerBoolean;

    public UserService() {
        this.scannerNumere = new Scanner(System.in);
        this.scannerText = new Scanner(System.in);
        this.scannerBoolean = new Scanner(System.in);
    }

    public void readAll() {
        ArrayList<User> allUsers = this.userRepository.readAll();

        User user;
        for(Iterator var2 = allUsers.iterator(); var2.hasNext(); user.postari = this.postRepository.readPostsFromUserWithId(user.id)) {
            user = (User)var2.next();
        }

        allUsers.forEach((userx) -> {
            System.out.println(userx);
        });
    }

    public void readById() {
        System.out.println("Introduceti id-ul utilizatorului dorit: ");
        int id = this.scannerNumere.nextInt();
        User userCitit = UserRepository.readById(id);
        if (userCitit == null) {
            System.out.println("Nu exista nici un user cu id-ul " + id);
        } else {
            userCitit.postari = this.postRepository.readPostsFromUserWithId(id);
            System.out.println(userCitit);
        }

    }

    public void create() {
        System.out.println("Introduceti numele:");
        String nume = this.scannerText.nextLine();
        System.out.println("Introduceti prenumele:");
        String prenume = this.scannerText.nextLine();
        System.out.println("Introduceti emailul:");
        String email = this.scannerText.nextLine();
        System.out.println("Introduceti numarul de telefon:");
        String numarTelefon = this.scannerText.nextLine();
        boolean succes = this.userRepository.create(nume, prenume, email, numarTelefon);
        if (succes) {
            System.out.println("Utilizator salvat!");
        } else {
            System.out.println("A aparut o eroare!");
        }

    }

    public void update() {
        System.out.println("Introduceti id-ul user-ului: ");
        int id = this.scannerNumere.nextInt();
        User userCitit = UserRepository.readById(id);
        if (userCitit != null) {
            boolean modificamNumele = this.modificamProprietatea("nume");
            boolean modificamPrenumele = this.modificamProprietatea("prenume");
            boolean modificamEmailul = this.modificamProprietatea("email");
            boolean modificamNumarulDeTelefon = this.modificamProprietatea("numarTelefon");
            String numarTelefonNou;
            if (modificamNumele) {
                System.out.println("Introduceti noul nume: ");
                numarTelefonNou = this.scannerText.nextLine();
                this.userRepository.modifyName(id, numarTelefonNou);
            }

            if (modificamPrenumele) {
                System.out.println("Introduceti noul prenume: ");
                numarTelefonNou = this.scannerText.nextLine();
                this.userRepository.modifyPrenume(id, numarTelefonNou);
            }

            if (modificamEmailul) {
                System.out.println("Introduceti noul email: ");
                numarTelefonNou = this.scannerText.nextLine();
                this.userRepository.modifyEmail(id, numarTelefonNou);
            }

            if (modificamNumarulDeTelefon) {
                System.out.println("Introduceti noul numar de telefon: ");
                numarTelefonNou = this.scannerText.nextLine();
                this.userRepository.modifyNumarTelefon(id, numarTelefonNou);
            }
        } else {
            System.out.println("Nu exista nici un user cu id-ul " + id);
        }

    }

    public boolean modificamProprietatea(String proprietate) {
        System.out.println("Doriti sa modificati proprietatea " + proprietate + " ?");
        return this.scannerBoolean.nextBoolean();
    }

    public void delete() {
        System.out.println("Introduceti id-ul user-ului pe care doriti sa-l stergeti: ");
        int id = this.scannerNumere.nextInt();
        boolean succes = this.userRepository.delete(id);
        System.out.println(succes ? "Utilizatorul a fost sters!" : "Utilizatorul nu a putut fii sters!");
    }
}
