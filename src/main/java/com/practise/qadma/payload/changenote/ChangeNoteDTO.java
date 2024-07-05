package com.practise.qadma.payload.changenote;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
abstract class ChangeNoteDTO {

    private long id;

    private String changeDescription;

    private long createdBy;

    private Date dateCreated;
}
