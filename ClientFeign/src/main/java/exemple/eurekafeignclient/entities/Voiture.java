package exemple.eurekafeignclient.entities;

import jakarta.persistence.*;

@Entity
public class Voiture {

    @Id
    @GeneratedValue
    private Long id;
    private String marque;
    private String matricule;
    private String model;
    private int id_client;


    @Transient
    @ManyToOne
    private Client client;

    public Voiture(Long id, String marque, String matricule, String model, int id_client, Client client) {
        this.id = id;
        this.marque = marque;
        this.matricule = matricule;
        this.model = model;
        this.id_client = id_client;
        this.client = client;
    }

    public Voiture() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "id=" + id +
                ", marque='" + marque + '\'' +
                ", matricule='" + matricule + '\'' +
                ", model='" + model + '\'' +
                ", id_client=" + id_client +
                ", client=" + client +
                '}';
    }
}
