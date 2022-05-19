package com.mastercom.employee.controller;

import com.mastercom.employee.entity.Project;
import com.mastercom.employee.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/")
    public List<Project> getAllProject() {
        return projectService.getProjects();
    }

    @PostMapping(value = "/add-project")
    public Project addUser(@RequestBody Project project) {
        return projectService.addProject(project);
    }
}
