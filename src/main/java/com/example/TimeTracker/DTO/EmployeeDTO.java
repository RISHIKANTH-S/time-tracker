package com.example.TimeTracker.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {
        private String username;
        private String password;
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
