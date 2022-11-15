package com.ilCary.EPIC_ENERGY_SERVICES.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilCary.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import com.ilCary.EPIC_ENERGY_SERVICES.models.User;
import com.ilCary.EPIC_ENERGY_SERVICES.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo repository;

    public User save(User x) {
        return repository.save(x);
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getById(Long id) {

        Optional<User> user = repository.findById(id);

        if(!user.isPresent())
            throw new NotFoundException("User not available");

        return user.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
