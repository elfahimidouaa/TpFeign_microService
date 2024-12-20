package exemple.eurekafeignclient.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity

public class Client {
    @Id
    @GeneratedValue()
    private Long id;
    private String nom;
    private int age;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Client() {
    }

    public Client(Long id, String nom, int age) {
        this.id = id;
        this.nom = nom;
        this.age = age;
    }
}
