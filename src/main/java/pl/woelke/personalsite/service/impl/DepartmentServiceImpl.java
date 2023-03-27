package pl.woelke.personalsite.service.impl;

import org.springframework.stereotype.Service;
import pl.woelke.personalsite.exception.DepartmentNotFoundException;
import pl.woelke.personalsite.model.Department;
import pl.woelke.personalsite.repository.DepartmentRepository;
import pl.woelke.personalsite.repository.entity.DepartmentEntity;
import pl.woelke.personalsite.service.DepartmentService;
import pl.woelke.personalsite.service.mapper.DepartmentMapper;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOGGER = Logger.getLogger(DepartmentServiceImpl.class.getName());

    private DepartmentRepository departmentRepository;
    private DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public List<Department> findAllDepartments() {
        LOGGER.info("findAllDepartments()");
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        List<Department> departmentModels = departmentMapper.listModels(departmentEntities);
        LOGGER.info("findAllDepartments(...)");
        return departmentModels;
    }
    @Override
    public Department findDepartmentByName(String name) {
        LOGGER.info("findDeaprtmentsByName()" + name);
        DepartmentEntity departmentEntity = departmentRepository.searchDepartmentByName(name);
        Department department = departmentMapper.entityToModel(departmentEntity);
        LOGGER.info("findDepartmentsByName(...)" + department.getName());
        return department;
    }
    @Override
    public Department createDepartment(Department department) {
        LOGGER.info("create(" + department + ")");
        DepartmentEntity departmentEntity = departmentMapper.modelToEntity(department);
        DepartmentEntity savedDepartmentEntity = departmentRepository.save(departmentEntity);
        Department savedDepartmentModel = departmentMapper.entityToModel(savedDepartmentEntity);
        LOGGER.info("create(...)" + savedDepartmentModel);
        return savedDepartmentModel;
    }

    @Override
    public Department findDepartmentById(Long id) throws DepartmentNotFoundException {
        LOGGER.info("read(" + id + ")");
        Optional<DepartmentEntity> optionalDepartmentEntity = departmentRepository.findById(id);
        DepartmentEntity departmentEntity = optionalDepartmentEntity.orElseThrow(
                () -> new DepartmentNotFoundException("Brak działu o podanym id " + id));
        Department departmentModel = departmentMapper.entityToModel(departmentEntity);
        LOGGER.info("read(...)" + departmentModel);
        return departmentModel;
    }

    @Override
    public Department updateDepartment(Department department, Long id) throws DepartmentNotFoundException {
        LOGGER.info("update()" + department);
        Optional<DepartmentEntity> optionalDepartmentEntity = departmentRepository.findById(id);
        DepartmentEntity departmentEntity = optionalDepartmentEntity.orElseThrow(
                () -> new DepartmentNotFoundException("Brak działu o podanym id " + id));
        LOGGER.info("updateEntity()" + departmentEntity.getId());
        DepartmentEntity editedDepartmentEntity = departmentMapper.modelToEntity(department);
        editedDepartmentEntity.setId(id);
        DepartmentEntity updatedDepartmentEntity = departmentRepository.save(editedDepartmentEntity);
        Department updatedDepartmentModel = departmentMapper.entityToModel(updatedDepartmentEntity);
        LOGGER.info("update(...) " + updatedDepartmentModel);
        return updatedDepartmentModel;
    }

    @Override
    public void deleteDepartment(Long id) {
        LOGGER.info("delete()");
        departmentRepository.deleteById(id);
        LOGGER.info("delete(....)");

    }
}
