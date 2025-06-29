package com.example.TimeTracker.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {
        @NotBlank(message = "Username must not be empty")
        private String username;
        @NotBlank(message = "password must not be empty")
        private String password;
        @NotBlank(message="Email must not be empty")
        @Email
        private String email;
        private String department;


        public String getDepartment() {
                return department;
        }

        public void setDepartment(String department) {
                this.department = department;
        }

        public String getEmail() {
                return email;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public void setEmail(String email) {
                this.email = email;
        }
        public String getUsername() {
                return username;
        }
        public void setUsername(String username) {
                this.username = username;
        }
}
