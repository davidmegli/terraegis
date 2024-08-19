package com.terraegis.terraegis.services;

import com.terraegis.terraegis.models.User;
import com.terraegis.terraegis.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private ProjectRepository projectRepository;

    public Optional<List<User>> findProjectsByCreatorId(Long creatorId) {
        return projectRepository.findProjectsByCreatorId(creatorId);
    }
}
