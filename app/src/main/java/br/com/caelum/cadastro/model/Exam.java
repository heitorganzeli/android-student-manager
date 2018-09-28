package br.com.caelum.cadastro.model;

import java.util.Date;

public class Exam {

    private Date date;
    private String subject;
    private String description;

    public Exam(Date date, String suject) {
        this.date = date;
        this.subject = suject;
    }

    public Date getDate() {

        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "subject='" + subject + '\'' +
                '}';
    }
}
