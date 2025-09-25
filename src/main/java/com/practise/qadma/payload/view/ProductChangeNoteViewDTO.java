package com.practise.qadma.payload.view;

import com.practise.qadma.auth.payload.QadmaUserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ProductChangeNoteViewDTO {

    private long id;
    private String changeDescription;
    private QadmaUserDTO createdBy;
    private Date dateCreated;
    private ProductViewDTO product;
}
