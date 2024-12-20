package exemple.eurekafeignclient;

import exemple.eurekafeignclient.entities.Client;
import exemple.eurekafeignclient.entities.Voiture;
import exemple.eurekafeignclient.repository.VoitureRepository;
import exemple.eurekafeignclient.service.ClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class EurekaFeignClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaFeignClientApplication.class, args);
    }

    @Bean
    CommandLineRunner initializerBaseH2(VoitureRepository voitureRepository, ClientService clientService) {
        return args -> {
            Client c1 = clientService.getClient(1);
            Client c2 = clientService.getClient(2);

            voitureRepository.save(new Voiture(null, "Toyota", "A25333", "Corolla", c1.getId().intValue(), c1));
            voitureRepository.save(new Voiture(null, "Renault", "B63456", "Magane", c2.getId().intValue(), c2));
            voitureRepository.save(new Voiture(null, "Peugeot", "A554444", "301", c1.getId().intValue(), c1));

            voitureRepository.findAll().forEach(voiture -> {
                Client client = clientService.getClient(voiture.getId_client());
                voiture.setClient(client);
                System.out.println(voiture);
            });
        };
    }


}
