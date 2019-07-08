package com.example.coba;

public class User {

        private int nohp;
        private String nama, alamat,  email, password;

        public User( String nama, String nohp, String alamat, String email, String password  ) {
            this.nama = nama;
            this.email = email;
        }


        public String getNama() {
            return nama;
        }
        public int getNohp() {
            return nohp;
        }

        public String getAlamat() {
            return alamat;
        }
        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

}
