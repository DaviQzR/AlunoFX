package Model;

import java.time.LocalDate;

public class Aluno {
    private int id;
    private int ra;
    private String nome;
    private LocalDate nascimento;

    public Aluno(int id, int ra, String nome, LocalDate nascimento) {
        this.id = id;
        this.ra = ra;
        this.nome = nome;
        this.nascimento = nascimento;
    }

    public int getId() {
        return id;
    }

    public int getRa() {
        return ra;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String toString() {
        return "Aluno{" +
               "id=" + id +
               ", ra=" + ra +
               ", nome='" + nome + '\'' +
               ", nascimento=" + nascimento +
               '}';
    }
}