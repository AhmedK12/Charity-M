package com.example.helptek.village;

import ca.krasnay.sqlbuilder.SelectBuilder;
import com.example.helptek.common.NativeQueryRepository;
import com.example.helptek.common.PageSortResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public class VillageNativeRepository implements NativeQueryRepository<VillageDto, VillageSearchDto> {

    public static final String NAME = "name like :%name%";
    public static final String DISTRICT = "district like :%district%";
    public static final String PIN_CODE = "pin_code like :%pinCode%";
    public static final String REPRESENTATIVE_ID = "user_id = :representativeId";
    public static final String TABLE_NAME = "villages";


    private final VillageDtoMapper villageDtoMapper;
    private final EntityManager entityManager;

    @Autowired
    public VillageNativeRepository(EntityManager entityManager, VillageDtoMapper villageDtoMapper) {
        this.villageDtoMapper = villageDtoMapper;
        this.entityManager = entityManager;
    }

    @Override
    public void getSearchResult(PageSortResponse<List<VillageDto>> response, VillageSearchDto villageSearchDto) {
        SelectBuilder selectBuilder = createSelectQuBuilder();
        addColumnsAndTable(selectBuilder);
        setWhereClauses(selectBuilder, villageSearchDto);
        Query selectQuery = createSearchQuery(entityManager,selectBuilder);
        setParameters(selectQuery,villageSearchDto);
        List<Tuple> results = selectQuery.getResultList();
        response.setPageSize(results.size());
        response.setTotalRecords(getCount(villageSearchDto));
        populate(response, results);
    }

    @Override
    public long getCount(VillageSearchDto searchDto) {
        SelectBuilder countQuBuilder = createCountSelectQuBuilder(TABLE_NAME);
        setWhereClauses(countQuBuilder, searchDto);
        Query countQuery = createCountQuery(entityManager,countQuBuilder);
        setParameters(countQuery,searchDto);
        return (Long) countQuery.getSingleResult();
    }

    @Override
    public void setPageSize(StringBuilder strQueryBuilder, VillageSearchDto villageSearchDto) {
        strQueryBuilder.append(LIMIT)
                .append(villageSearchDto.getPageSize());
    }

    @Override
    public void setPageNo(StringBuilder strQueryBuilder, VillageSearchDto villageSearchDto) {
        strQueryBuilder.append(OFFSET)
                .append((villageSearchDto.getPageNumber() - 1) * villageSearchDto.getPageSize());
    }

    @Override
    public void populate(PageSortResponse<List<VillageDto>> response, List<Tuple> result) {
        List<VillageDto> villageDtos =  result.stream().map(villageDtoMapper::tupleToVillageDto).toList();
        response.addSearchResults(villageDtos);
    }

    @Override
    public void setParameters(Query query, VillageSearchDto villageSearchDto) {
        setParameter(query,NAME, Optional.ofNullable(villageSearchDto.getName()));
        setParameter(query,DISTRICT, Optional.ofNullable(villageSearchDto.getDistrict()));
        setParameter(query,PIN_CODE, Optional.ofNullable(villageSearchDto.getPinCode()));
        setParameter(query,REPRESENTATIVE_ID, Optional.ofNullable(villageSearchDto.getRepresentativeId()));
    }

    @Override
    public void setWhereClauses(SelectBuilder selectBuilder, VillageSearchDto villageSearchDto) {
        setWhereClause(selectBuilder,NAME, Optional.ofNullable(villageSearchDto.getName()));
        setWhereClause(selectBuilder,DISTRICT, Optional.ofNullable(villageSearchDto.getDistrict()));
        setWhereClause(selectBuilder,PIN_CODE, Optional.ofNullable(villageSearchDto.getPinCode()));
        setWhereClause(selectBuilder,REPRESENTATIVE_ID, Optional.ofNullable(villageSearchDto.getRepresentativeId()));
    }



    @Override
    public void addColumnsAndTable(SelectBuilder selectBuilder) {
         selectBuilder.column("*")
//                 .column("v.name")
//                 .column("v.district")
//                 .column("v.pin_code")
                 .from("villages");
    }


}
