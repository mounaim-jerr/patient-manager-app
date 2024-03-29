package ma.mtit.bmp.bmpcore.controllers;

import ma.mtit.bmp.bmpcore.model.Department;
import ma.mtit.bmp.bmpcore.model.Doctor;
import ma.mtit.bmp.bmpcore.model.Speciality;
import ma.mtit.bmp.bmpcore.service.interfaces.DoctorService;
import ma.mtit.bmp.bmpcore.utility.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
public class DoctorControllerTest {
   @MockBean
    private DoctorService doctorService;
    private MockMvc mockMvc;
    @Before
            public void setUp(){
        DoctorControllers doctorControllers = new DoctorControllers(doctorService);
        mockMvc = standaloneSetup(doctorControllers).build();
    }
    @Test
    public void testGetAllDoctors() throws Exception {
        mockMvc.perform(get("/doctor/all"))
                .andExpect(status().isOk());
    }
    @Test
    public  void testAddDoctor() throws Exception{
        Department department = new Department();
        department.setName("department");
        Speciality speciality = new Speciality();
        speciality.setName("speciality");
        speciality.setDepartment(department);
        Doctor doctor = new Doctor();
        doctor.setName("doctor");
        doctor.setSpeciality(speciality);

        when(doctorService.addDoctor(doctor)).thenReturn(doctor);
        mockMvc.perform(post("/doctor/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(doctor)))
                .andExpect(status().isCreated());
    }
    @Test
    public void testDeleteDoctor() throws Exception{
        Long id = 1L;
        mockMvc.perform(delete("/doctor/delete/{id}" , id))
                .andExpect(status().isOk());
        verify(doctorService).deleteDoctor(1L);
    }
    @Test
    public void testGetDoctorById() throws Exception{
        Long id = 1L;
        mockMvc.perform(get("/doctor/find/id/{id}", id))
                .andExpect(status().isOk());
        verify(doctorService).findDoctorById(1L);
    }
    @Test
    public void testGetDoctorByName() throws Exception {
        String name = "name";
        mockMvc.perform(get("/doctor/find/name/{name}" , name))
                .andExpect(status().isOk());
        verify(doctorService).findDoctorByName("name");
    }
    @Test
    public void testUpdateDoctor() throws Exception {
        Department department = new Department();
        department.setName("department");
        Speciality speciality = new Speciality();
        speciality.setName("speciality");
        speciality.setDepartment(department);
        Doctor doctor = new Doctor();
        doctor.setName("doctor");
        doctor.setSpeciality(speciality);
        when(doctorService.updateDoctor(doctor)).thenReturn(doctor);
        mockMvc.perform(put("/doctor/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(doctor)))
                .andExpect(status().isOk());

    }
}
