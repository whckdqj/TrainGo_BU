package kr.traingo.domain;

import java.sql.Date;

public class DbaCommand {
    String owner;
    String object_name;
    String subobject_name;
    int object_id;
    int data_object_id;
    String object_type;
    Date created;
    Date last_ddl_time;
    String timestamp;
    String status;
    String temporary;
    String generated;
    String secondary;
    int namespace;
    String edition_name;
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getObject_name() {
        return object_name;
    }
    public void setObject_name(String object_name) {
        this.object_name = object_name;
    }
    public String getSubobject_name() {
        return subobject_name;
    }
    public void setSubobject_name(String subobject_name) {
        this.subobject_name = subobject_name;
    }
    public int getObject_id() {
        return object_id;
    }
    public void setObject_id(int object_id) {
        this.object_id = object_id;
    }
    public int getData_object_id() {
        return data_object_id;
    }
    public void setData_object_id(int data_object_id) {
        this.data_object_id = data_object_id;
    }
    public String getObject_type() {
        return object_type;
    }
    public void setObject_type(String object_type) {
        this.object_type = object_type;
    }
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public Date getLast_ddl_time() {
        return last_ddl_time;
    }
    public void setLast_ddl_time(Date last_ddl_time) {
        this.last_ddl_time = last_ddl_time;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getTemporary() {
        return temporary;
    }
    public void setTemporary(String temporary) {
        this.temporary = temporary;
    }
    public String getGenerated() {
        return generated;
    }
    public void setGenerated(String generated) {
        this.generated = generated;
    }
    public String getSecondary() {
        return secondary;
    }
    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }
    public int getNamespace() {
        return namespace;
    }
    public void setNamespace(int namespace) {
        this.namespace = namespace;
    }
    public String getEdition_name() {
        return edition_name;
    }
    public void setEdition_name(String edition_name) {
        this.edition_name = edition_name;
    }
    
}
