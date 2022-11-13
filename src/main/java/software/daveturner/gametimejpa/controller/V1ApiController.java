package software.daveturner.gametimejpa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.daveturner.gametimejpa.domain.ConferenceInfo;
import software.daveturner.gametimejpa.domain.PlayerInfo;
import software.daveturner.gametimejpa.domain.TeamInfo;
import software.daveturner.gametimejpa.service.V1ApiService;

import java.util.Optional;
import java.util.Set;

@RequestMapping("api/v1/gametime")
@RestController
public class V1ApiController {

    private final V1ApiService apiService;

    public V1ApiController(V1ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/league")
    public ResponseEntity<Set<ConferenceInfo>> getLeague() {
        return new ResponseEntity<Set<ConferenceInfo>>(apiService.getLeague(), HttpStatus.OK);
    }

    @GetMapping("/conference/{id}")
    public ResponseEntity<ConferenceInfo> getConference(@PathVariable String id) {
        return (ResponseEntity<ConferenceInfo>) handleOptionalResponse(apiService.getConference(id));
    }

    @GetMapping("/team/{id}")
    public ResponseEntity<TeamInfo> getTeam(@PathVariable String id) {
        return (ResponseEntity<TeamInfo>) handleOptionalResponse(apiService.getTeam(id));
    }

    @GetMapping("/player/{id}")
    public ResponseEntity<PlayerInfo> getPlayer(@PathVariable String id) {
        try {
            return (ResponseEntity<PlayerInfo>) handleOptionalResponse(apiService.getPlayer(id));
        }catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity<?> handleOptionalResponse(Optional<?> optional) {
        if(optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }







}
