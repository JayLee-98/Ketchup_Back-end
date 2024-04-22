package com.devsplan.ketchup.mail.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "MAIL_FILE")
public class MailFile {
    @Id
    @Column(name = "MAIL_FILE_NO", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mailFileNo;

    @ManyToOne
    @JoinColumn(name = "MAIL_NO", nullable = false)
    private Mail mailNo;

    @Column(name = "MAIL_FILE_PATH", nullable = false)
    private String mailFilePath;

    @Column(name = "MAIL_FILE_NAME", nullable = false)
    private String mailFileName;

    @Column(name = "MAIL_FILE_ORI_NAME", nullable = false)
    private String mailFileOriName;

    protected MailFile() {}

    public MailFile(int mailFileNo, Mail mailNo, String mailFilePath, String mailFileName, String mailFileOriName) {
        this.mailFileNo = mailFileNo;
        this.mailNo = mailNo;
        this.mailFilePath = mailFilePath;
        this.mailFileName = mailFileName;
        this.mailFileOriName = mailFileOriName;
    }
}