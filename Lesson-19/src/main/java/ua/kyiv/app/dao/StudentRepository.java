package ua.kyiv.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.kyiv.app.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

}
