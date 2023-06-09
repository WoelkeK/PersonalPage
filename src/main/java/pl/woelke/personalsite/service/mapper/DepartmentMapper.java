package pl.woelke.personalsite.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.woelke.personalsite.model.Department;
import pl.woelke.personalsite.repository.entity.DepartmentEntity;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class DepartmentMapper {

    private static final Logger LOGGER = Logger.getLogger(DepartmentMapper.class.getName());

    private DepartmentEntity departmentEntity;

    public List<Department> listModels(List<DepartmentEntity> departmentEntities) {
        LOGGER.info("list()" + departmentEntities);

        List<Department> departmentModels = departmentEntities.stream()
                .map(this::entityToModel)
                .collect(Collectors.toList());
        return departmentModels;
    }


    public Department entityToModel(DepartmentEntity departmentEntity) {
        LOGGER.info("entityToModel" + departmentEntity);
        ModelMapper modelMapper = new ModelMapper();
        Department departmentModel = modelMapper.map(departmentEntity, Department.class);
        return departmentModel;
    }

    public DepartmentEntity modelToEntity(Department departmentModel) {
        LOGGER.info("modelToEntity()" + departmentModel);
        ModelMapper modelMapper = new ModelMapper();
        DepartmentEntity departmentEntity = modelMapper.map(departmentModel, DepartmentEntity.class);
        return departmentEntity;
    }
}
