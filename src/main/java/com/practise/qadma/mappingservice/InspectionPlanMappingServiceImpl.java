package com.practise.qadma.mappingservice;


import com.practise.qadma.entity.InspectionPlan;
import com.practise.qadma.payload.InspectionPlanDTO;
import com.practise.qadma.payload.view.InspectionPlanViewDTO;
import com.practise.qadma.service.InspectionPlanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InspectionPlanMappingServiceImpl implements InspectionPlanMappingService {

    private ModelMapper modelMapper;
    private InspectionPlanService inspectionPlanService;


    @Autowired
    public InspectionPlanMappingServiceImpl(InspectionPlanService inspectionPlanService, ModelMapper modelMapper) {
        this.inspectionPlanService = inspectionPlanService;
        this.modelMapper = modelMapper;
    }

    @Override
    public InspectionPlanViewDTO findById(long id) {

        InspectionPlan inspectionPlan = inspectionPlanService.findById(id);

        return modelMapper.map(inspectionPlan, InspectionPlanViewDTO.class);
    }

    @Override
    public InspectionPlanViewDTO save(InspectionPlanDTO inspectionPlanDTO) {

        InspectionPlan newPlan = modelMapper.map(inspectionPlanDTO, InspectionPlan.class);

        InspectionPlan managedPlan = inspectionPlanService.save(newPlan);

        return modelMapper.map(managedPlan, InspectionPlanViewDTO.class);
    }

    @Override
    public InspectionPlanViewDTO update(InspectionPlanDTO inspectionPlanDTO) {
        return null;
    }
}
