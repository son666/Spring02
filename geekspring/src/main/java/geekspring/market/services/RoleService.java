package geekspring.market.services;

import geekspring.market.entites.Role;
import geekspring.market.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public boolean isRoleWithNameExists(String roleName) {
        return roleRepository.findOneByName(roleName) != null;
    }

    public List<Role> getAllRoles() {
        return (List<Role>)roleRepository.findAll();
    }

    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
