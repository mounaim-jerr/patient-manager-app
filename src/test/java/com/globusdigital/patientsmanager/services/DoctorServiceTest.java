package com.globusdigital.patientsmanager.services;

import com.globusdigital.patientsmanager.exception.UserNotFoundException;
import com.globusdigital.patientsmanager.model.Department;
import com.globusdigital.patientsmanager.model.Doctor;
import com.globusdigital.patientsmanager.model.Speciality;
import com.globusdigital.patientsmanager.repo.DoctorRepo;
import com.globusdigital.patientsmanager.service.DepartmentServiceImp;
import com.globusdigital.patientsmanager.service.DoctorServiceImp;
import com.globusdigital.patientsmanager.service.SpecialityServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DoctorServiceTest {
@Autowired
    DoctorServiceImp doctorServiceImp;
@Autowired
    DepartmentServiceImp departmentServiceImp;
@Autowired
    SpecialityServiceImp specialityServiceImp;
@Autowired
    DoctorRepo doctorRepo;
@BeforeEach
public void initContext(){
    doctorRepo.deleteAll();
}
@Test
public void findDoctorByName(){
    List<Doctor> doctorFind ;
    Department department = new Department();
    department.setDepartmentName("departement");
    departmentServiceImp.addDepartment(department);
    Speciality speciality = new Speciality();
    speciality.setSpecialityName("cardio");
    speciality.setDepartmentOfTheSpeciality(department);
    specialityServiceImp.addSpeciality(speciality);
    Doctor doctor = new Doctor();
    Doctor doctor1 = new Doctor();
    Doctor doctor2 = new Doctor();
    doctor.setSpecialityOfDoctor(speciality);
    doctor1.setSpecialityOfDoctor(speciality);
    doctor2.setSpecialityOfDoctor(speciality);
    doctor.setDoctorCin("FA171412");
    doctor.setDoctorName("momo");
    doctor1.setDoctorCin("FA121312");
    doctor1.setDoctorName("mounaim");
    doctor2.setDoctorCin("FA131313");
    doctor2.setDoctorName("moumen");
    doctor =  doctorServiceImp.addDoctor(doctor);
    doctor1= doctorServiceImp.addDoctor(doctor1);
    doctor2= doctorServiceImp.addDoctor(doctor2);
    //doctorFind = doctorServiceImp.findDoctorByName("mo");
    //Doctor doctorFinal = doctorFind;
    //Assertions.assertThatThrownBy(()->{
      //  doctorServiceImp.findDoctorByName( doctorFinal.getDoctorName());
    //}).isInstanceOf(UserNotFoundException.class);
    doctorFind = doctorServiceImp.findDoctorByName("momo");
    Assertions.assertThat(doctorFind.size()).isEqualTo(1);
    Assertions.assertThat(doctorFind.get(0).getDoctorName()).isEqualTo("momo");

}
@Test
    public void addDoctorTest(){
    Doctor doctor = new Doctor();
    doctor.setDoctorCin("FA171493");
    doctor = doctorServiceImp.addDoctor(doctor);
    Assertions.assertThat(doctor.getDoctorCin()).isEqualTo("FA171493");
    }
    @Test
    public void updateDoctorTest(){
    Doctor doctor = new Doctor();
    doctor.setDoctorCin("FA171493");
    doctor = doctorServiceImp.addDoctor(doctor);
    Long id = doctor.getId();
    doctor.setDoctorCin("FA121212");
    doctor = doctorServiceImp.updateDoctor(doctor);
    Assertions.assertThat(doctor.getId()).isEqualTo(id);
    doctor = doctorServiceImp.findDoctorById(doctor.getId());
    Assertions.assertThat(doctor.getDoctorCin()).isEqualTo("FA121212");
    }
    @Test
    public void deleteDoctorTest(){
    Doctor doctor = new Doctor();
    doctor.setDoctorCin("FA171492");
    doctor= doctorServiceImp.addDoctor(doctor);
    doctorServiceImp.deleteDoctor(doctor.getId());
    Doctor finalDoctor = doctor;
        Assertions.assertThatThrownBy(()->{
            doctorServiceImp.findDoctorById( finalDoctor.getId());
        }).isInstanceOf(UserNotFoundException.class);
    }


}
