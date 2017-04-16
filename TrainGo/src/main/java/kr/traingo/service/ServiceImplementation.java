package kr.traingo.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.traingo.dao.TheMapper;
import kr.traingo.domain.DbaCommand;
import kr.traingo.domain.FileCommand;
import kr.traingo.domain.FmemberCommand;
import kr.traingo.domain.TableAdminCommand;
import kr.traingo.domain.TheCommand;

@Service("theService")
public class ServiceImplementation implements TheService{
    
    @Resource
    private TheMapper theMapper;
    
    @Override
    public void insert(TheCommand member) {
        theMapper.insert(member);
    }

    @Override
    public TheCommand selectMember(String id) {
        return null;
    }

    @Override
    public void update(TheCommand member) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<FileCommand> list(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getRowCount(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void insertFile(FileCommand board) {
        theMapper.insertFile(board);
    }

    @Override
    public FileCommand selectFile(Integer seq) {
        return theMapper.selectFile(seq);
    }

    @Override
    public DbaCommand getTableInfo(DbaCommand dbaCommand) {
        return theMapper.getTableInfo(dbaCommand);
    }
    
    @Override
    public TableAdminCommand insertHistory(TableAdminCommand insertHistoryCommand) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TableAdminCommand selectHistory(String tableName) {
        return theMapper.selectHistory(tableName);
    }

    @Override
    public void updateHistory(TableAdminCommand updateHistoryCommand) {
        theMapper.updateHistory(updateHistoryCommand);
        return;
    }

	@Override
	public FmemberCommand selectAdminLevel(String id) {
		return theMapper.selectAdminLevel(id);
	}
}
