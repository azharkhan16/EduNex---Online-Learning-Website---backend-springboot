package com.edunex.security;

import com.edunex.model.*;
import com.edunex.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (adminRepository.findByEmail(email).isPresent()) {
            Admin admin = adminRepository.findByEmail(email).get();
            return new CustomUserDetails(admin.getEmail(), admin.getPassword(), admin.getRole());
        }
        if (teacherRepository.findByEmail(email).isPresent()) {
            Teacher t = teacherRepository.findByEmail(email).get();
            return new CustomUserDetails(t.getEmail(), t.getPassword(), t.getRole());
        }
        if (studentRepository.findByEmail(email).isPresent()) {
            Student s = studentRepository.findByEmail(email).get();
            return new CustomUserDetails(s.getEmail(), s.getPassword(), s.getRole());
        }
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
