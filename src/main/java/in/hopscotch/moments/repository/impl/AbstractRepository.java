package in.hopscotch.moments.repository.impl;

import javax.inject.Inject;

import in.hopscotch.moments.db.util.JPAAccess;



public abstract class AbstractRepository {
    
    @Inject
    protected JPAAccess jpaAccess;
    
}
