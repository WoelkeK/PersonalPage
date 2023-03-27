package pl.woelke.personalsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.woelke.personalsite.repository.entity.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity,  Long> {
    @Query("SELECT p from DepartmentEntity p WHERE " +
            " p.name LIKE CONCAT('%', :query, '%')")
    DepartmentEntity searchDepartmentByName(String query);
}
