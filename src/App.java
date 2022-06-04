import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        try {
            menu();
        } catch (IllegalStateException ig) {
            System.out.println("Usuário e/ou senha incorretos.");
        }

    };


    public static void menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Login");
        try {
            int opcao = sc.nextInt();
            switch (opcao) {
                case 1: cadastrar();break;
                case 2: login();break;
                default:
            }
        }catch (InputMismatchException ex){
            System.out.println("Favor digitar '1' para cadastrar ou '2' para login ");
            menu();
        }

    };


    public static void cadastrar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o seu nome: ");
        String nome = sc.nextLine().toUpperCase();
        System.out.println("Digite uma senha: ");
        String senha = sc.nextLine();
        Sistema sistema = new Sistema(new Usuario(nome, senha));
        sistema.salvaBaseDados();
        System.out.println("Cadastrado com Sucesso");
        menu();
    };


    public static void login() {
        Sistema sistema = new Sistema();
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o seu nome: ");
        String nome = sc.nextLine().toUpperCase();
        System.out.println("Digite uma senha: ");
        String senha = sc.nextLine();
        Usuario usuario = sistema.buscarUsuario(nome, senha);
        Periodo periodo = sistema.intervaloHorario();
        switch (periodo) {
            case MANHA: System.out.println("Bom dia " + usuario.getUsuario() + ", você se logou ao nosso sistema." + sistema.toString());break;
            case TARDE: System.out.println("Boa tarde " + usuario.getUsuario() + ", você se logou ao nosso sistema." + sistema.toString());break;
            case NOITE: System.out.println("Boa noite " + usuario.getUsuario() + ", você se logou ao nosso sistema." + sistema.toString());break;
            case MADRUGADA: System.out.println("Boa madrugada " + usuario.getUsuario() + ", você se logou ao nosso sistema." + sistema.toString());break;
            default:
        }
    };




}
