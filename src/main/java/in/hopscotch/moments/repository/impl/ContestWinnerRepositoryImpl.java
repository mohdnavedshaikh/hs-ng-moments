package in.hopscotch.moments.repository.impl;

import org.springframework.stereotype.Repository;

import in.hopscotch.moments.constant.NamedQueryConstant;
import in.hopscotch.moments.entity.ContestWinner;
import in.hopscotch.moments.repository.ContestWinnerRepository;

@Repository
public class ContestWinnerRepositoryImpl extends AbstractRepository<ContestWinner> implements ContestWinnerRepository {

    @Override
    public ContestWinner getActiveContestWinner() {
        ContestWinner contestWinner = findOneUsingNamedQuery(NamedQueryConstant.CONTESTWINNER_ACTIVE);
        return contestWinner;
    }

}
