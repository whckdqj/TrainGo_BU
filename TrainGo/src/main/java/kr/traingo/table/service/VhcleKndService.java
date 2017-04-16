package kr.traingo.table.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.traingo.table.dao.TableMapper;
import kr.traingo.table.domain.VhcleKndListCommand;

@Service("vhcleKndService")
@Transactional
public class VhcleKndService {
    @Resource
    private TableMapper tableMapper;
    
    // CRUD - C : Create/Insert
    public void insertVhcleKndList(VhcleKndListCommand vhcleKndList){
        tableMapper.insertVhcleKndList(vhcleKndList);
    };
    // CRUD - R : Read/Search
    @Transactional(readOnly=true)
    public List<VhcleKndListCommand> selectVhcleKndListAll(){
        return tableMapper.selectVhcleKndListAll();
    };
    // CRUD - U : Update
    public void updateVhcleKndList(VhcleKndListCommand cityCode){
        
    };
    // CRUD - D : Delete
    public void deleteVhcleKndList(String cityInfo){
        
    };
}
