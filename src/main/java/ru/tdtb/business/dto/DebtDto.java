package ru.tdtb.business.dto;

import lombok.Getter;
import lombok.Setter;
import ru.tdtb.business.domain.User;

import java.util.Date;

@Getter
@Setter
public class DebtDto {
   private Long id;
   private Long money;
   private Date initDateTime;
   private String description;
   private User who;
   private User whom;
}
