package com.example.helptek.common;

import ca.krasnay.sqlbuilder.SelectBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;

import java.util.List;
import java.util.Optional;


public interface NativeQueryRepository<DTO, SDTO> {


    String LIMIT = " LIMIT ";

    String OFFSET = " OFFSET ";

    String SQLCountQuery = "count(*)";

     void getSearchResult(PageSortResponse<List<DTO>> response, SDTO searchDto);

     long getCount(SDTO searchDto);
    default void setParameter(Query query, String name, Optional<?> value){
        value.ifPresent(o -> query.setParameter(name, o));
    }

    default void setWhereClause(SelectBuilder selectBuilder, String name, Optional<?> value){
        value.ifPresent(o -> selectBuilder.where(name));
    }

    void setPageSize(StringBuilder strQueryBuilder, SDTO searchDto);

    void setPageNo(StringBuilder strQueryBuilder, SDTO searchDto);

    void populate(PageSortResponse<List<DTO>> response, List<Tuple> result);

    void setParameters(Query query, SDTO searDto);

    void setWhereClauses(SelectBuilder selectBuilder,SDTO searchDto);

    default SelectBuilder createSelectQuBuilder(){
        return new SelectBuilder();
    }

    default SelectBuilder createCountSelectQuBuilder(String tableName){
        return new SelectBuilder().column(SQLCountQuery).from(tableName);
    }
    default Query createSearchQuery(EntityManager entityManager, SelectBuilder selectBuilder){
       return entityManager.createNativeQuery(selectBuilder.toString(), Tuple.class);
    }

    default Query createCountQuery(EntityManager entityManager, SelectBuilder selectBuilder){
        return entityManager.createNativeQuery(selectBuilder.toString());
    }

    void addColumnsAndTable(SelectBuilder selectBuilder);

}
