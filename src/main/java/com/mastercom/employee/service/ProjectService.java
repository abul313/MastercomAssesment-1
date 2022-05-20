package com.mastercom.employee.service;

import com.mastercom.employee.entity.Project;
import com.mastercom.employee.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public Project addProject(Project project) throws ParseException {
        List<Project> list = getProjects();
        Date start = new SimpleDateFormat("yyyy-MM-dd").parse(project.getProjectStartDate());
        Date end = new SimpleDateFormat("yyyy-MM-dd").parse(project.getProjectEndDate());
        if (start.equals(end) || start.after(end)) {
            Project p= new Project();
            p.setMessage("Invalid Request");
            return p;
        }

        Optional<Project> any = list.stream()
                .filter(p -> p.getProjectName().equals(project.getProjectName()) && p.getClientName().equals(project.getClientName()))
                .findAny();

        if (any.isPresent()) {
            Project p= new Project();
            p.setMessage("Invalid Request");
            return p;
        }

        return projectRepository.save(project);
    }

    public List<Project> getProjects() {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll().forEach(projects::add);
        return projects;
    }
}
