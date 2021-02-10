package com.example.textsearchservice.models;

import java.util.function.Function;

public class Term {
    private String term;
    private int occurrences;
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
