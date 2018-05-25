package com.example.paul.fitme;

class UserEntity {
    private String email;
    private String password;
    private int age;
    private double taille;
    private int poids;

    public UserEntity(String email, String password, int age, double taille, int poids) {
        this.email = email;
        this.password = password;
        this.age = age;
        this.taille = taille/100.0;
        this.poids = poids;
    }


    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getTaille() {
        return taille;
    }

    public void setTaille(int taille) {

        this.taille = taille;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    protected String calculImc(){
        double imc = (this.poids / (this.taille * this.taille));
        return String.format("%.1f", imc);
    }
}
