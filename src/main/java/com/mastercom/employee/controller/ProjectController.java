package com.mastercom.employee.controller;

import com.mastercom.employee.entity.Project;
import com.mastercom.employee.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/")
    public List<Project> getAllProject() {
        return projectService.getProjects();
    }

    @PostMapping(value = "/add-project")
    public Project addUser(@RequestBody Project project) throws ParseException {
        return projectService.addProject(project);
    }
}
