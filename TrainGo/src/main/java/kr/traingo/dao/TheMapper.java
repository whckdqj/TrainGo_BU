package kr.traingo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.traingo.domain.DbaCommand;
import kr.traingo.domain.FileCommand;
import kr.traingo.domain.FmemberCommand;
import kr.traingo.domain.TableAdminCommand;
import kr.traingo.domain.TheCommand;

public interface TheMapper {
    @Insert("INSERT INTO smember (id,passwd,name,email,reg_date) VALUES (#{id},#{passwd},#{name},#{email},sysdate)")
    public void insert(TheCommand member);
    
    @Select("SELECT * FROM smember WHERE id=#{id}")
    public TheCommand selectMember(String id);
    
    @Update("UPDATE smember SET name=#{name}, passwd=#{passwd},email=#{email} WHERE id=#{id}")
    public void update(TheCommand member);
    
    @Delete("DELETE FROM smember WHERE id=#{id}")
    public void delete(String id);
    
    public List<FileCommand> list(Map<String, Object> map);
    public int getRowCount(Map<String, Object> map);
    @Insert("INSERT INTO sboard (seq, title, content, regdate, uploadfile, filename, id) VALUES (BOARD_SEQ.NEXTVAL, #{title}, #{content}, SYSDATE, #{uploadfile, jdbcType=BLOB}, #{filename, jdbcType=VARCHAR}, #{id})")
    public void insertFile(FileCommand board);
    @Select("SELECT * FROM sboard WHERE seq=#{seq}")
    public FileCommand selectFile(Integer seq);
    
    // 2017-04-06 19:16 jcb add for DBA Statement Test
    public DbaCommand getTableInfo(DbaCommand dbaCommand);
    // 2017-04-10 16:47 jcb add for Table Manager
    public TableAdminCommand insertHistory(TableAdminCommand insertHistoryCommand);
    public TableAdminCommand selectHistory(String tableName);
    public void updateHistory(TableAdminCommand updateHistoryCommand);
    // 2017-04-12 02:05 jcb Add for InteceptControl
    public FmemberCommand selectAdminLevel(String id);
}
