package exemple.eurekafeignclient.controller;

import exemple.eurekafeignclient.entities.Client;
import exemple.eurekafeignclient.entities.Voiture;
import exemple.eurekafeignclient.repository.VoitureRepository;
import exemple.eurekafeignclient.service.ClientService;
import exemple.eurekafeignclient.service.VoitureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class VoitureController {


    private final VoitureService voitureService;
    private final ClientService clientService;
    private final VoitureRepository voitureRepository;

    public VoitureController(VoitureService voitureService, ClientService clientService, VoitureRepository voitureRepository) {
        this.voitureService = voitureService;
        this.clientService = clientService;
        this.voitureRepository = voitureRepository;
    }

    @GetMapping("/voituress")
    public List<Voiture> getVoitures() {
        return voitureService.findAll().stream().map(voiture -> {
            Client client = clientService.getClient(voiture.getId_client());
            voiture.setClient(client); // Explicitly set the client
            return voiture;
        }).collect(Collectors.toList());
    }

    @GetMapping(value = "/voitures", produces = {"application/json"})
    public ResponseEntity<Object> findAll() {
        try {
            List<Voiture> voitures = voitureService.findAll().stream().map(voiture -> {
                voiture.setClient(clientService.getClient(voiture.getId_client()));
                return voiture;
            }).collect(Collectors.toList());
            return ResponseEntity.ok(voitures);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }


    @GetMapping("/voitures/{id}")
    public ResponseEntity<Object> getVoitureById(@PathVariable Long id) {
        try{
            Voiture voiture = voitureRepository.findById(id).orElse(null);

            voiture.setClient(clientService.getClient(voiture.getId_client()));
            return ResponseEntity.ok(voiture);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Voiture not found with ID: " + id);
        }
    }

    @PostMapping("/voitures/{clientId}")
    public ResponseEntity<Object> save(@PathVariable("clientId") int clientId, @RequestBody Voiture voiture) {
        try {
            Client client = clientService.getClient(clientId);

            if (client != null) {
                voiture.setClient(client);

                voiture.setId_client(clientId);
                voiture.setClient(client);
                Voiture savedVoiture = voitureService.save(voiture);
                return ResponseEntity.ok(savedVoiture);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Client not found with ID: " + clientId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }

    }

    @PutMapping("/voitures/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Voiture updatedVoiture) {
        try {
            Voiture existingVoiture = voitureRepository.findById(id)
                    .orElseThrow(() -> new Exception("Voiture not found with ID: " + id));

            if (updatedVoiture.getMatricule() != null && !updatedVoiture.getMatricule().isEmpty()) {
                existingVoiture.setMatricule(updatedVoiture.getMatricule());
            }

            if (updatedVoiture.getMarque() != null && !updatedVoiture.getMarque().isEmpty()) {
                existingVoiture.setMarque(updatedVoiture.getMarque());
            }

            if (updatedVoiture.getModel() != null && !updatedVoiture.getModel().isEmpty()) {
                existingVoiture.setModel(updatedVoiture.getModel());
            }

            if (updatedVoiture.getId_client() > 0) {
                Client client = clientService.getClient(updatedVoiture.getId_client());
                if (client != null) {
                    existingVoiture.setId_client(updatedVoiture.getId_client());
                    existingVoiture.setClient(client); // Mise à jour du client
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("Client not found with ID: " + updatedVoiture.getId_client());
                }
            }

            Voiture savedVoiture = voitureRepository.save(existingVoiture);

            return ResponseEntity.ok(savedVoiture);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }






}
