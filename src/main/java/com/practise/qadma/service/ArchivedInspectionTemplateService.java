package com.practise.qadma.service;

import com.practise.qadma.entity.ArchivedInspectionTemplate;
import com.practise.qadma.entity.InspectionTemplate;

public interface ArchivedInspectionTemplateService {

    ArchivedInspectionTemplate archiveInspectionTemplate(InspectionTemplate inspectionTemplate);

    ArchivedInspectionTemplate getLatestArchiveForInspectionTemplate(long id);
}
