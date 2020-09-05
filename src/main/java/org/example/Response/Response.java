package org.example.Response;

import org.example.Model.PageURL;
import org.example.Model.ProblemModel;

import java.util.List;

public class Response {

    private List<ProblemModel> problemModels;
    private PageURL nextPage,PreviousPage;

    public Response() {

    }

    public List<ProblemModel> getProblemModels() {
        return problemModels;
    }

    public void setProblemModels(List<ProblemModel> problemModels) {
        this.problemModels = problemModels;
    }

    public PageURL getNextPage() {
        return nextPage;
    }

    public void setNextPage(PageURL nextPage) {
        this.nextPage = nextPage;
    }

    public Response(List<ProblemModel> problemModels, PageURL nextPage, PageURL previousPage) {
        this.problemModels = problemModels;
        this.nextPage = nextPage;
        PreviousPage = previousPage;
    }

    public PageURL getPreviousPage() {
        return PreviousPage;
    }

    public void setPreviousPage(PageURL previousPage) {
        PreviousPage = previousPage;
    }
}
