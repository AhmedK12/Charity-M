package com.example.helptek.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageSortResponse<T> {

    int pageSize;

    int pageNo;

    List<T> searchResults = new ArrayList<T>();

    private Long totalRecords;

    public void addSearchResults(T result) {
        this.searchResults.add(result);
    }
}
