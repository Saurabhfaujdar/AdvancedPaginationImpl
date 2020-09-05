package org.example.Model;

public class ProblemModel {
    private int id;
    private String problemName,authorName,topic;

    public ProblemModel(int id, String problemName, String authorName) {
        this.id = id;
        this.problemName = problemName;
        this.authorName = authorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

}
