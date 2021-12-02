package com.elanza48.TMS.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.PastOrPresent;

@Embeddable
public class MetaData implements Serializable {

  @Column(name = "created_at", updatable = false)
  @PastOrPresent
  private Timestamp createdTimesatmp=null;

  @Column(name = "last_update")
  @PastOrPresent
  private Timestamp updateTimestamp=null;


  public MetaData(){}

  public Timestamp getCreatedTimesatmp() {
    return createdTimesatmp;
  }

  @PrePersist
  public void setCreatedTimesatmp() {
    if(this.createdTimesatmp==null){
      this.createdTimesatmp = new Timestamp(new Date().getTime());
      this.updateTimestamp=this.createdTimesatmp;
    }
  }

  public Timestamp getUpdateTimestamp() {
    return updateTimestamp;
  }
  
  @PreUpdate
  public void setUpdateTimestamp() {
    this.updateTimestamp = new Timestamp(new Date().getTime());
  }
  
  @Override
  public String toString() {
    return "MetaData [createdTimesatmp=" + createdTimesatmp + ", updateTimestamp=" + updateTimestamp + "]";
  }
 
}
