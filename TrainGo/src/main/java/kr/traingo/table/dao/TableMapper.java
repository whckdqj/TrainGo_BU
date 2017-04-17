package kr.traingo.table.dao;

import java.util.List;

import kr.traingo.table.domain.CtyCodeListCommand;
import kr.traingo.table.domain.TrainSttnListCommand;
import kr.traingo.table.domain.VhcleKndListCommand;

public interface TableMapper {
    // --- CtyCodeList Mappers ---
    // CRUD - C : Create/Insert
    public void insertCtyCodeList(CtyCodeListCommand ctyCodeList);
    // CRUD - R : Read/Search
    public List<CtyCodeListCommand> selectCtyCodeListAll();
    public List<String> selectCtyCodeOnly();
    // CRUD - U : Update
    public void updateCtyCodeList(CtyCodeListCommand cityCode);
    // CRUD - D : Delete
    public void deleteCtyCodeList(String cityInfo);
    // --- CtyCodeList Mappers ---
    
    // --- VhcleKndList Mappers ---
    // CRUD - C : Create/Insert
    public void insertVhcleKndList(VhcleKndListCommand vhcleKndList);
    // CRUD - R : Read/Search
    public List<VhcleKndListCommand> selectVhcleKndListAll();
    // CRUD - U : Update
    public void updateVhcleKndList(VhcleKndListCommand vhcleKnd);
    // CRUD - D : Delete
    public void deleteVhcleKndList(String vhcleInfo);
    // --- VhcleKndList Mappers ---
    
    // --- Station Code List Mappers ---
    // CRUD - C : Create/Insert
    public void insertSttnCodeList(TrainSttnListCommand sttnList);
    // CRUD - R : Read/Search
    public List<TrainSttnListCommand> selectSttnCodeListAll();
    public List<TrainSttnListCommand> selectSttnCodeByCtyCode(String ctyCode);
    // CRUD - U : Update
    public void updateSttnCodeList(TrainSttnListCommand sttnList);
    // CRUD - D : Delete
    public void deleteSttnCodeList(String sttnInfo);
    // --- Station Code List Mappers ---
    
    // --- Result Time Table Mappers ---
    
    // --- Result Time Table Mappers ---
    
}
