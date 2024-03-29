package ma.mtit.bmp.bmpcore.service.interfaces;


import ma.mtit.bmp.bmpcore.model.Speciality;

import java.util.List;

public interface SpecialityService {
    Speciality addSpeciality(Speciality speciality);
    void deleteSpeciality(Long id);
    Speciality findSpecialityById(Long id);
    List<Speciality> findSpecialityByName(String name);
    List<Speciality> findAllSpeciality();
    Speciality updateSpeciality(Speciality speciality);
}
