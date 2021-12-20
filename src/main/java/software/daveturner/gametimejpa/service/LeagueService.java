package software.daveturner.gametimejpa.service;

import software.daveturner.gametimejpa.domain.Conference;

import java.util.List;


public interface LeagueService {
    List<Conference> fetchLeague();
}
