package com.mastercom.employee.controller;

import com.mastercom.employee.entity.Project;
import com.mastercom.employee.entity.Resource;
import com.mastercom.employee.service.ProjectService;
import com.mastercom.employee.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/resource")
    public List<Resource> getAllProject() {
        return resourceService.getProjectsResources();
    }

    @PostMapping(value = "/add-resource")
    public Resource addUser(@RequestBody Resource resource) {
        return resourceService.addProjectResource(resource);
    }
}
