package kr.traingo.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import kr.traingo.domain.DbaCommand;
import kr.traingo.domain.FileCommand;
import kr.traingo.domain.FmemberCommand;
import kr.traingo.domain.TableAdminCommand;
import kr.traingo.domain.TheCommand;

@Transactional
public interface TheService {
    public void insert(TheCommand member);
    @Transactional(readOnly=true)
    public TheCommand selectMember(String id);
    public void update(TheCommand member);
    public void delete(String id);
    
    @Transactional(readOnly=true)
    public List<FileCommand> list(Map<String, Object> map);
    @Transactional(readOnly=true)
    public int getRowCount(Map<String, Object> map);
    public void insertFile(FileCommand board);
    public FileCommand selectFile(Integer seq);
    
    // 2017-04-06 19:33 JCB Add for DBA Statement
    public DbaCommand getTableInfo(DbaCommand dbaCommand);
    // 2017-04-10 17:19 JCB Add for Table Manger
    public TableAdminCommand insertHistory(TableAdminCommand insertHistoryCommand);
    public TableAdminCommand selectHistory(String tableName);
    public void updateHistory(TableAdminCommand updateHistoryCommand);
    // 2017-04-12 02:05 jcb Add for InteceptControl
    public FmemberCommand selectAdminLevel(String id);
}
