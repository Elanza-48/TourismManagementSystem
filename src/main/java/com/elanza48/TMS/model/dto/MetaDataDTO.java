package com.elanza48.TMS.model.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MetaDataDTO {

  private Timestamp createdTimesatmp;
  private Timestamp updateTimestamp;
  

  @Override
  public String toString() {
    return "MetaData [createdTimesatmp=" + createdTimesatmp + ", updateTimestamp=" + updateTimestamp + "]";
  }
 
}

