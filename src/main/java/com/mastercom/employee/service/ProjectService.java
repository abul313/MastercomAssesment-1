package com.mastercom.employee.service;

import com.mastercom.employee.entity.Project;
import com.mastercom.employee.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public Project addProject(Project project) {
        List<Project> list = getProjects();

        if (project.getProjectStartDate().equals(project.getProjectEndDate())) {
            return null;
        }

        Optional<Project> any = list.stream()
                .filter(p -> p.getProjectName().equals(project.getProjectName()) && p.getClientName().equals(project.getClientName()))
                .findAny();

        if (any.isPresent()) {
            return null;
        }

        return projectRepository.save(project);
    }

    public List<Project> getProjects() {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll().forEach(projects::add);
        return projects;
    }
}
