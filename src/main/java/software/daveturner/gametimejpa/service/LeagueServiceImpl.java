package software.daveturner.gametimejpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.daveturner.gametimejpa.domain.Conference;
import software.daveturner.gametimejpa.repo.ConferenceRepo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LeagueServiceImpl implements LeagueService{

    @Autowired
    ConferenceRepo conferenceRepo;

    @Override
    public List<Conference> fetchLeague() {
        return StreamSupport
                .stream(conferenceRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
