package in.hopscotch.moments.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import in.hopscotch.moments.entity.HSMomentsData;
import in.hopscotch.moments.repository.HSMomentsRepository;

@Repository
public class HSMomentsRepositoryImpl extends AbstractRepository<HSMomentsData> implements HSMomentsRepository {

    @Override
    public List<HSMomentsData> getHSMomentsData(boolean newest, int pageNo, int pageSize) {
        String query = "";
        if (newest)
            query = "From HSMomentsData order by updatedDate DESC";
        else
            query = "From HSMomentsData order by likes DESC";
        List<HSMomentsData> hsMomentsResponse = findByPagination(query, pageNo - 1, pageSize);
        return hsMomentsResponse;
    }

}
