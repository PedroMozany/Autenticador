import java.io.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Scanner;

public class Sistema {

    private LinkedList<Usuario> listUsuarios;
    private LocalDateTime horarioAtual;
    private int horas;
    private int minutos;


    public Sistema(Usuario usuario) {
        this.listUsuarios = new LinkedList<>();
        addUsuario(usuario);
        this.horarioAtual = LocalDateTime.now();
        this.horas = horarioAtual.getHour();
        this.minutos = horarioAtual.getMinute();
    }


    public Sistema() {
        this.listUsuarios = lerBaseDados("baseDados.txt");
        this.horarioAtual = LocalDateTime.now();
        this.horas = horarioAtual.getHour();
        this.minutos = horarioAtual.getMinute();
    }

    public int getHoras() {
        return horas;
    }

    public void addUsuario(Usuario usuario) {
        this.listUsuarios.add(usuario);
    }


    public Usuario buscarUsuario(String nome,String senha) {
        return this.listUsuarios.stream()
                .filter(e -> e.getUsuario().equals(nome ) && e.getSenha().equals(senha))
                .findAny()
                .orElseThrow(IllegalStateException::new);
    }


    public String toString() {
        return "\n" + "Você logou as " +
                this.horas + ":" +
                this.minutos;
    }


    public Periodo intervaloHorario() {
        if (getHoras() >= 6 && getHoras() < 12) {
            return Periodo.MANHA;
        } else if (getHoras() >= 12 && getHoras() <= 17) {
            return Periodo.TARDE;
        } else if (getHoras() > 17 && getHoras() <= 23) {
            return Periodo.NOITE;
        } else {
            return Periodo.MADRUGADA;
        }
    };


    public LinkedList<Usuario> lerBaseDados(String arquivo) {
        LinkedList<Usuario> conjuntoUsuarios = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha = br.readLine();
            while (linha != null) {
                Scanner sc = new Scanner(linha);
                sc.useDelimiter(",");
                conjuntoUsuarios.add(new Usuario(sc.next(), sc.next()));
                linha = br.readLine();
                sc.close();
            }
        } catch (IOException e) {
            System.out.println("Erro ao encontra o arquivo");
        }finally {}

        return conjuntoUsuarios;
    };



    public void salvaBaseDados() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("baseDados.txt", true))) {
            for (Usuario usuario : listUsuarios) {
                bw.write(usuario.getUsuario());
                bw.write(",");
                bw.write(usuario.getSenha());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao encontra o arquivo");
        }finally {}
    }
};
