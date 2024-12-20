package exemple.eurekafeignclient.service;

import exemple.eurekafeignclient.entities.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "EurekaClient")
public interface ClientService {
    @GetMapping(path="/client/{id}")
    public Client getClient(@PathVariable("id") int id);
}
