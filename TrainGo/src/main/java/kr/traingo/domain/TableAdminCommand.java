package kr.traingo.domain;

public class TableAdminCommand {
    String tableName;
    String created;
    String modified;
    String last_called;
    String access_object;
    
    // 2017-04-10 19:43 JCB Add For Date To String
    private String convDate(String dateString){
        if(dateString==null || dateString.equals("")){
            return dateString;
        }
        String[] date_str = dateString.split("-");
        StringBuffer strBuffer = new StringBuffer();
        
        for(int i=0; i<date_str.length; i++){
            switch(i){
            case 0:
                // Year
                strBuffer.append(date_str[i]+"-");
                break;
            case 1:
                // Month
                strBuffer.append(date_str[i]+"-");
                break;
            case 2:
                // Day
                strBuffer.append(date_str[i]+" ");
                break;
            case 3:
                // Hour
                strBuffer.append(date_str[i]+":");
                break;
            case 4:
                // Minute
                strBuffer.append(date_str[i]+":");
                break;
            case 5:
                // Second
                strBuffer.append(date_str[i]);
                break;
            default:
                continue;
            }
        }
        
        return strBuffer.toString();
    }
    
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getCreated() {
        return convDate(created);
    }
    public void setCreated(String created) {
        this.created = created;
    }
    public String getModified() {
        return convDate(modified);
    }
    public void setModified(String modified) {
        this.modified = modified;
    }
    public String getLast_called() {
        return convDate(last_called);
    }
    public void setLast_called(String last_called) {
        this.last_called = last_called;
    }
    public String getAccess_object() {
        return access_object;
    }
    public void setAccess_object(String access_object) {
        this.access_object = access_object;
    }

    @Override
    public String toString() {
        return "TableAdminCommand [tableName=" + tableName + ", created=" + created + ", modified=" + modified
                + ", last_called=" + last_called + ", access_object=" + access_object + "]";
    }
    
}
