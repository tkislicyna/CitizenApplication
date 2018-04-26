package ru.nsk.tkozlova.model;
import javax.persistence.*;
import java.util.Date;

/**
 * @project CitizenApplication
 * @autor Toma on 4/26/2018.
 */
@Entity
@Table (name = "documents")
public class IdentityDocument {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "holder_id", nullable = false)
//    private Citizen holder;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private DocumentType type;

    @Column(name = "authority")
    private  String authority;

    @Column(name = "issue_date")
    @Temporal(TemporalType.DATE)
    private Date issueDate;

    @Column(name = "expiry_date")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;

    public Integer getId() {
        return id;
    }
//
//    public Citizen getHolder() {
//        return holder;
//    }
//
//    public void setHolder(Citizen holder) {
//        this.holder = holder;
//    }

    public DocumentType getType() {
        return type;
    }

    public void setType(DocumentType type) {
        this.type = type;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
