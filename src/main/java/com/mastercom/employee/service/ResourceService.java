package com.mastercom.employee.service;

import com.mastercom.employee.entity.Resource;
import com.mastercom.employee.repository.ProjectRepository;
import com.mastercom.employee.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ResourceService {

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    ProjectRepository projectRepository;

    public List<Resource> getProjectsResources() {
        List<Resource> list = new ArrayList<>();
        resourceRepository.findAll().forEach(list::add);
        return list;
    }

    public Resource addProjectResource(Resource resource) {
        List<Resource> resources = getProjectsResources();
        int count = (int) resources.stream().filter(r -> r.getResourceName().equals(resource.getResourceName())).count();

        if (count > 3) {
            return null;
        }

        AtomicBoolean isEndingEarly = new AtomicBoolean(false);
        projectRepository.findById(Long.valueOf(resource.getProjectId())).ifPresent(
                p -> {
                    try {
                        Date start = new SimpleDateFormat("yyyy-MM-dd").parse(p.getProjectStartDate());
                        Date end = new SimpleDateFormat("yyyy-MM-dd").parse(p.getProjectEndDate());

                        long time_difference = end.getTime() - start.getTime();
                        long days_difference = (time_difference / (1000*60*60*24)) % 365;

                        if(((int)days_difference) < 15){
                            isEndingEarly.set(true);
                        }
                    } catch (ParseException e) {

                    }
                });

        if (isEndingEarly.get()) {
            return  null;
        }

        return resourceRepository.save(resource);
    }
}
