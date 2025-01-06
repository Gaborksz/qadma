package com.practise.qadma.dao;

import com.practise.qadma.entity.ArchivedInspectionTemplate;
import com.practise.qadma.entity.InspectionTemplate;

public interface ArchivedInspectionTemplateRepository {

    ArchivedInspectionTemplate archiveTemplate(ArchivedInspectionTemplate inspectionTemplate);

    ArchivedInspectionTemplate getLatestArchiveForInspectionTemplate(long id);

}
