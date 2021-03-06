package com.globusdigital.patientsmanager.repo;

import com.globusdigital.patientsmanager.model.Patient;
import com.globusdigital.patientsmanager.model.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpecialityRepo extends JpaRepository<Speciality,Long> {
    Optional<Speciality> findSpecialitiesById(Long id);
    //@Query("")
    List<Speciality> findBySpecialityNameContaining(String name);

}
