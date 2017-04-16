package kr.traingo.table.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.traingo.table.dao.TableMapper;
import kr.traingo.table.domain.TrainSttnListCommand;

@Service("trainSttnService")
@Transactional
public class TrainSttnListService {
    @Resource
    private TableMapper tableMapper;
    
    // CRUD - C : Create/Insert
    public void insertSttnCodeList(TrainSttnListCommand trainSttnList){
        tableMapper.insertSttnCodeList(trainSttnList);
    };
    // CRUD - R : Read/Search
    @Transactional(readOnly=true)
    public List<TrainSttnListCommand> selectSttnCodeListAll(){
        return tableMapper.selectSttnCodeListAll();
    };
    @Transactional(readOnly=true)
    public List<TrainSttnListCommand> selectSttnCodeByCtyCode(String ctyCode){
        return tableMapper.selectSttnCodeByCtyCode(ctyCode);
    };
    // CRUD - U : Update
    public void updateSttnCodeList(TrainSttnListCommand cityCode){
        
    };
    // CRUD - D : Delete
    public void deleteSttnCodeList(String sttnInfo){
        
    };
}
