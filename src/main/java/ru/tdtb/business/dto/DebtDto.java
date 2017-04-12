package ru.tdtb.business.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DebtDto {
   private Long id;
   private Long money;
   private Date initDateTime;
   private String description;
   private UserDto who;
   private UserDto whom;
   private String importance;
   private Boolean flag;
   private Boolean haveImage;
}
