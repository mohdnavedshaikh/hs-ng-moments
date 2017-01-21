package in.hopscotch.moments.service.impl;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import in.hopscotch.moments.entity.ContestWinner;
import in.hopscotch.moments.repository.ContestWinnerRepository;
import in.hopscotch.moments.service.ContestWinnerService;

@Service
@Transactional
public class ContestWinnerServiceImpl implements ContestWinnerService {

    
    @Inject
    ContestWinnerRepository contestWinnerRepository;
    
    @Override
    public ContestWinner getActiveContestWinner() {
        return contestWinnerRepository.getActiveContestWinner();
    }

}
