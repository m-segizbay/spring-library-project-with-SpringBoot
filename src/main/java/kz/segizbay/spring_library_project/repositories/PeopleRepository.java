package kz.segizbay.spring_library_project.repositories;

import kz.segizbay.spring_library_project.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    public Optional<Person> findByFullName(String fullName);
}
