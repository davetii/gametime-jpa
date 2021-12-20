package software.daveturner.gametimejpa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.daveturner.gametimejpa.domain.Conference;
import software.daveturner.gametimejpa.domain.Player;
import software.daveturner.gametimejpa.domain.Team;
import software.daveturner.gametimejpa.service.V1ApiService;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/gametime")
@RestController
public class V1ApiController {

    private final V1ApiService apiService;

    public V1ApiController(V1ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/league")
    public ResponseEntity<List<Conference>> getLeague() {
        return new ResponseEntity<>(apiService.getLeague(), HttpStatus.OK);
    }

    @GetMapping("/conference/{id}")
    public ResponseEntity<Conference> getConference(@PathVariable String id) {
        return (ResponseEntity<Conference>) handleOptionalResponse(apiService.getConference(id));
    }

    @GetMapping("/team/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable String id) {
        return (ResponseEntity<Team>) handleOptionalResponse(apiService.getTeam(id));
    }

    @GetMapping("/player/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable String id) {
        try {
            Long.valueOf(id);
        }catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return (ResponseEntity<Player>) handleOptionalResponse(apiService.getPlayer(id));
    }

    private ResponseEntity<?> handleOptionalResponse(Optional<?> optional) {
        if(optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }







}
