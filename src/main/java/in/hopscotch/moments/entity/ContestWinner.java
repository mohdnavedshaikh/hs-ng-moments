package in.hopscotch.moments.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import in.hopscotch.moments.constant.NamedQueryConstant;

@Entity
@Table(name = "hsmoments.contest_winner")
@NamedQueries({ @NamedQuery(name = NamedQueryConstant.CONTESTWINNER_ACTIVE, query = "select contestwinner from ContestWinner contestwinner where contestwinner.isActive = true") })
public class ContestWinner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hs_moments_data_id")
    private HSMomentsData hsmomentsdata;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hs_moments_contest_id")
    private HSMomentsContest hsMomentsContest;

    @Column(name = "is_active")
    private boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HSMomentsData getHsmomentsdata() {
        return hsmomentsdata;
    }

    public void setHsmomentsdata(HSMomentsData hsmomentsdata) {
        this.hsmomentsdata = hsmomentsdata;
    }

    public HSMomentsContest getHsMomentsContest() {
        return hsMomentsContest;
    }

    public void setHsMomentsContest(HSMomentsContest hsMomentsContest) {
        this.hsMomentsContest = hsMomentsContest;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
