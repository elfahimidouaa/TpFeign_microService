package exemple.eurekaclient.controller;

import exemple.eurekaclient.entities.Client;
import exemple.eurekaclient.repository.ClientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/clients")
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/client/{id}")
    public Optional<Client> getClient(@PathVariable Long id) {
        return clientRepository.findById(id);
    }
}
