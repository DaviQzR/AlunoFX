package View;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import Model.Aluno;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AlunoCrud extends Application {
    private List<Aluno> alunos = new ArrayList<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("ddMMyyyy"); // Formato de entrada alternativo

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestão de Alunos");

        Pane root = new Pane();

        Label lblNome = new Label("Nome:");
        TextField txtNome = new TextField();

        Label lblRa = new Label("RA:");
        TextField txtRa = new TextField();

        Label lblNascimento = new Label("Nascimento:"); 
        TextField txtNascimento = new TextField();

        Button btnAdicionar = new Button("Adicionar");
        Button btnPesquisar = new Button("Pesquisar");

        lblNome.setLayoutX(20);
        lblNome.setLayoutY(20);
        txtNome.setLayoutX(120);
        txtNome.setLayoutY(20);

        lblRa.setLayoutX(20);
        lblRa.setLayoutY(60);
        txtRa.setLayoutX(120);
        txtRa.setLayoutY(60);

        lblNascimento.setLayoutX(20);
        lblNascimento.setLayoutY(100);
        txtNascimento.setLayoutX(120); // Ajusta a posição do campo de data de nascimento
        txtNascimento.setLayoutY(100);

        btnAdicionar.setLayoutX(20);
        btnAdicionar.setLayoutY(140);

        btnPesquisar.setLayoutX(120);
        btnPesquisar.setLayoutY(140);

        btnAdicionar.setOnAction(event -> {
            String nome = txtNome.getText();
            int ra = Integer.parseInt(txtRa.getText());
            String dataNascimento = txtNascimento.getText();
            
            try {
                LocalDate nascimento = LocalDate.parse(dataNascimento, formatter); // Tenta analisar no formato padrão
                Aluno aluno = new Aluno(alunos.size() + 1, ra, nome, nascimento);
                alunos.add(aluno);
                txtNome.clear();
                txtRa.clear();
                txtNascimento.clear();
            } catch (DateTimeParseException e) {
                try {
                    LocalDate nascimento = LocalDate.parse(dataNascimento, inputFormatter); // Tenta analisar no formato alternativo
                    Aluno aluno = new Aluno(alunos.size() + 1, ra, nome, nascimento);
                    alunos.add(aluno);
                    txtNome.clear();
                    txtRa.clear();
                    txtNascimento.clear();
                } catch (DateTimeParseException ex) {
                    // Tratamento do formato de data inválido
                    System.out.println("Formato de data inválido. Use o formato ddMMyyyy ou dd/MM/yyyy.");
                }
            }
        });

        btnPesquisar.setOnAction(event -> {
            String nome = txtNome.getText();

            for (Aluno aluno : alunos) {
                if (aluno.getNome().equalsIgnoreCase(nome)) {
                    // Exibir os resultados nos campos TextField
                    txtRa.setText(Integer.toString(aluno.getRa()));
                    txtNascimento.setText(aluno.getNascimento().format(formatter));
                    return; // Encerra após encontrar o primeiro aluno com o nome correspondente
                }
            }

            // Limpar os campos se nenhum aluno for encontrado
            txtRa.clear();
            txtNascimento.clear();
        });

        root.getChildren().addAll(lblNome, txtNome, lblRa, txtRa, lblNascimento, txtNascimento, btnAdicionar, btnPesquisar);

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
