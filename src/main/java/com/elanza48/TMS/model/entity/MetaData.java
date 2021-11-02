package com.elanza48.TMS.model.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Embeddable
public class MetaData {

  @Column(name = "created_at", updatable = false)
  private Timestamp createdTimesatmp;

  @Column(name = "last_update")
  private Timestamp updateTimestamp;


  public MetaData(){}

  public Timestamp getCreatedTimesatmp() {
    return createdTimesatmp;
  }

  @PrePersist
  public void setCreatedTimesatmp() {
    this.createdTimesatmp = new Timestamp(new Date().getTime());
    this.updateTimestamp=this.createdTimesatmp;
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
