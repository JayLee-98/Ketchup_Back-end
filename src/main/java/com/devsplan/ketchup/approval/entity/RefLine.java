package com.devsplan.ketchup.approval.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "TBL_REFLINE")
public class RefLine {
    @Id
    @Column(name = "REF_LINE_NO" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int refLineNo;
    @Column(name = "APPROVAL_NO", nullable = false)
    private int approvalNo;
    @Column(name = "MEMBER_NO", nullable = false)
    private int refMemberNo;

    protected RefLine(){}
}
