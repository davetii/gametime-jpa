package software.daveturner.gametimejpa.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.daveturner.gametimejpa.domain.Conference;
import software.daveturner.gametimejpa.repo.ConferenceRepo;
import software.daveturner.gametimejpa.service.LeagueService;

import java.util.List;

@RequestMapping("api/v1/gametime")
@RestController
public class LeagueController {

    @Autowired
    LeagueService leagueService;

    @GetMapping("/league")
    public ResponseEntity<List<Conference>> getLeague() {
        return new ResponseEntity<>(leagueService.fetchLeague(), HttpStatus.OK);
    }





}
