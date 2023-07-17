package com.example.helptek.user;

import ca.krasnay.sqlbuilder.SelectBuilder;
import com.example.helptek.common.NativeQueryRepository;
import com.example.helptek.common.PageSortResponse;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserNativeRepository implements NativeQueryRepository<UserDto, UserSearchDto> {

    public static final String FIRST_NAME = "name like :%first_name%";
    public static final String LAST_NAME = "name like :%last_name%";
    public static final String EMAIL = "district like :%email%";
    public static final String USERNAME = "pin_code like :%username%";
    public static final String MOBILE = "pin_code like :%mobile%";
    public static final String VILLAGE_ID = "user_id = :representativeId";
    public static final String TABLE_NAME = "users";


    private final UserDtoMapper userDtoMapper;
    private final EntityManager entityManager;

    @Autowired
    public UserNativeRepository(EntityManager entityManager, UserDtoMapper userDtoMapper) {
        this.userDtoMapper = userDtoMapper;
        this.entityManager = entityManager;
    }

    @Override
    public void getSearchResult(PageSortResponse<List<UserDto>> response, UserSearchDto userSearchDto) {
        SelectBuilder selectBuilder = createSelectQuBuilder();
        addColumnsAndTable(selectBuilder);
        setWhereClauses(selectBuilder, userSearchDto);
        Query selectQuery = createSearchQuery(entityManager,selectBuilder);
        setParameters(selectQuery,userSearchDto);
        List<Tuple> results = selectQuery.getResultList();
        response.setPageSize(results.size());
        response.setTotalRecords(getCount(userSearchDto));
        populate(response, results);
    }

    @Override
    public long getCount(UserSearchDto searchDto) {
        SelectBuilder countQuBuilder = createCountSelectQuBuilder(TABLE_NAME);
        setWhereClauses(countQuBuilder, searchDto);
        Query countQuery = createCountQuery(entityManager,countQuBuilder);
        setParameters(countQuery,searchDto);
        return (Long) countQuery.getSingleResult();
    }

    @Override
    public void setPageSize(StringBuilder strQueryBuilder, UserSearchDto userSearchDto) {
        strQueryBuilder.append(LIMIT)
                .append(userSearchDto.getPageSize());
    }

    @Override
    public void setPageNo(StringBuilder strQueryBuilder, UserSearchDto userSearchDto) {
        strQueryBuilder.append(OFFSET)
                .append((userSearchDto.getPageNumber() - 1) * userSearchDto.getPageSize());
    }

    @Override
    public void populate(PageSortResponse<List<UserDto>> response, List<Tuple> result) {
        List<UserDto> userDtos =  result.stream().map(userDtoMapper::tupleToUserDto).toList();
        response.addSearchResults(userDtos);
    }

    @Override
    public void setParameters(Query query, UserSearchDto userSearchDto) {
        setParameter(query,FIRST_NAME, Optional.ofNullable(userSearchDto.getFirstName()));
        setParameter(query,LAST_NAME, Optional.ofNullable(userSearchDto.getLastName()));
        setParameter(query,MOBILE, Optional.ofNullable(userSearchDto.getMobile()));
        setParameter(query,EMAIL, Optional.ofNullable(userSearchDto.getEmail()));
        setParameter(query,USERNAME, Optional.ofNullable(userSearchDto.getUsername()));
        setParameter(query,VILLAGE_ID, Optional.ofNullable(userSearchDto.getVillageId()));
    }

    @Override
    public void setWhereClauses(SelectBuilder selectBuilder, UserSearchDto userSearchDto) {
        setWhereClause(selectBuilder,FIRST_NAME, Optional.ofNullable(userSearchDto.getFirstName()));
        setWhereClause(selectBuilder,LAST_NAME, Optional.ofNullable(userSearchDto.getLastName()));
        setWhereClause(selectBuilder,MOBILE, Optional.ofNullable(userSearchDto.getMobile()));
        setWhereClause(selectBuilder,EMAIL, Optional.ofNullable(userSearchDto.getEmail()));
        setWhereClause(selectBuilder,USERNAME, Optional.ofNullable(userSearchDto.getUsername()));
        setWhereClause(selectBuilder,VILLAGE_ID, Optional.ofNullable(userSearchDto.getVillageId()));
    }



    @Override
    public void addColumnsAndTable(SelectBuilder selectBuilder) {
        selectBuilder.column("*")
//                 .column("v.name")
//                 .column("v.district")
//                 .column("v.pin_code")
                .from("users");
    }


}
