package com.example.auctionapp.service;

import com.example.auctionapp.dto.RoleDto;
import com.example.auctionapp.model.Role;
import com.example.auctionapp.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleService implements IBaseService<RoleDto> {

    BaseRepository<Role> repository;

    @Autowired
    public void setRepository(BaseRepository<Role> repositoryToSet) {
        repository = repositoryToSet;
        repository.setResourceClass(Role.class);
    }


    public List<RoleDto> getAll() {

        List<Role> roles = repository.findAll();
        List<RoleDto> roleDtos = new ArrayList<>();

        for (Role role:roles) {
            roleDtos.add(new RoleDto(role.getId(), role.getDateCreated(), role.getLastModifiedDate(), role.getName()));
        }

        return  roleDtos;
    }


    public RoleDto getById(Long id) {
        Role role = repository.findById(id);
        return new RoleDto(role.getId(), role.getDateCreated(), role.getLastModifiedDate(), role.getName());
    }


    public RoleDto add(RoleDto resource) {
        Role role = repository.create(new Role(resource.getName()));
        return new RoleDto(role.getId(), role.getDateCreated(), role.getLastModifiedDate(), role.getName());
    }


    public RoleDto update(RoleDto resource) {
        Role resourceToUpdate = repository.findById(resource.getId());
        resourceToUpdate.setName(resource.getName());

        Role role = repository.update(resourceToUpdate);

        return new RoleDto(role.getId(), role.getDateCreated(), role.getLastModifiedDate(), role.getName());
    }


    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
