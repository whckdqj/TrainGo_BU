package kr.traingo.table.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.traingo.table.dao.TableMapper;
import kr.traingo.table.domain.CtyCodeListCommand;

@Service("ctyCodeService")
@Transactional
public class CtyCodeService {
    @Resource
    private TableMapper tableMapper;
    
    // CRUD - C : Create/Insert
    public void insertCtyCodeList(CtyCodeListCommand ctyCodeList){
        tableMapper.insertCtyCodeList(ctyCodeList);
    };
    // CRUD - R : Read/Search
    @Transactional(readOnly=true)
    public List<CtyCodeListCommand> selectCtyCodeListAll(){
        return tableMapper.selectCtyCodeListAll();
    };
    @Transactional(readOnly=true)
    public List<String> selectCtyCodeOnly(){
        return tableMapper.selectCtyCodeOnly();
    };
    // CRUD - U : Update
    public void updateCtyCodeList(CtyCodeListCommand cityCode){
        
    };
    // CRUD - D : Delete
    public void deleteCtyCodeList(String cityInfo){
        
    };
}
