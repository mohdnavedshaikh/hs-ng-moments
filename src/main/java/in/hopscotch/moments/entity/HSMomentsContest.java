package in.hopscotch.moments.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hsmoments.hs_moments_contest")
public class HSMomentsContest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "contest_name", length = 100)
    private String contestName;

    @Column(name = "description", length = 300)
    private String description;

    @Column(name = "contest_start_date")
    private LocalDateTime contestStartDate = LocalDateTime.now();

    @Column(name = "contest_end_date")
    private LocalDateTime contestEndDate = LocalDateTime.now();

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getContestStartDate() {
        return contestStartDate;
    }

    public void setContestStartDate(LocalDateTime contestStartDate) {
        this.contestStartDate = contestStartDate;
    }

    public LocalDateTime getContestEndDate() {
        return contestEndDate;
    }

    public void setContestEndDate(LocalDateTime contestEndDate) {
        this.contestEndDate = contestEndDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
