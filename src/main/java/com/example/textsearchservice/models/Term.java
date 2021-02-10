package com.example.textsearchservice.models;

public class Term {
    private String term;
    private int occurrences;

    public Term(String term, int occurrences) {
        this.term = term;
        this.occurrences = occurrences;
    }

    public Term() {
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(int occurrences) {
        this.occurrences = occurrences;
    }
}
