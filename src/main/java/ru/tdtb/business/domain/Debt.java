package ru.tdtb.business.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "DEBT")
public class Debt extends AbstractDomain<Long> {
    @Id
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq", sequenceName = "DEBT_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "MONEY")
    private Long money;

    @Column(name = "INIT_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date initDateTime;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WHO_ID")
    private User who;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WHOM_ID")
    private User whom;

    @Column(name = "IMPORTANCE")
    private String importance;

    @Column(name = "FLAG")
    private Boolean flag;

    @Column(name = "IMAGE_PATH")
    private String imagePath;
}
