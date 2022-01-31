package poo.tema3;

public class Student implements Comparable<Student> {
    private String nume;
    private String prenume;
    private int prezenta;

    public Student() {
    }

    public Student(String nume, String prenume, int prezenta) {
        this.nume = nume;
        this.prenume = prenume;
        this.prezenta = prezenta;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public int getPrezenta() {
        return prezenta;
    }

    public void setPrezenta(int prezenta) {
        this.prezenta = prezenta;
    }

    @Override
    public int compareTo(Student o) {
        if (o instanceof Student) {
            Student studentComparator = (Student) o;
            if (this.getPrezenta() == studentComparator.getPrezenta()) {
                return 0;
            } else if (this.getPrezenta() < studentComparator.getPrezenta()) {
                return -1;
            } else {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Student [nume=" + nume + ", prenume=" + prenume + ", prezenta=" + prezenta + "]";
    }
    
    
}
