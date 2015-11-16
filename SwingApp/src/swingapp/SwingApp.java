/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingapp;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

/**
 *
 * @author Ислам
 */
import javax.swing.JTextField;
public class SwingApp extends JFrame {
    String[] categories;
    Label infoLabel;
 /* 
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Product Manager 2016");
        GridPane grid = new GridPane();       
        Text scenetitle = new Text("Add new product");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        Label productName = new Label("Product Name:");
        grid.add(productName, 0, 1);
        TextField productNameTextField = new TextField();
        grid.add(productNameTextField, 1, 1);
        Label category = new Label("Category:");
        grid.add(category, 0, 2);
        TextField categoryTextField = new TextField();
        grid.add(categoryTextField, 1, 2);
        Label weight = new Label("Weight (kg):");
        grid.add(weight, 0, 3);
        TextField weightTextField = new TextField();
        grid.add(weightTextField, 1, 3);
        Label price = new Label("Price ($):");
        grid.add(price, 0, 4);
        TextField priceTextField = new TextField();
        grid.add(priceTextField, 1, 4);
        
        Button btn = new Button("Add");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 6);
        
        final Text actiontarget = new Text();
        grid.add(actiontarget, 0, 6);
        
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();        
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Failure occured");
            }
        });
    } */
    
    public SwingApp(){
        super("Product Manager 2016");
        setBounds(400, 150, 500, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        JPanel jp = new JPanel();
        add(jp);

        JTabbedPane jtp = new JTabbedPane();
        JPanel addPanel = new JPanel();
        jtp.addTab("Add New", addPanel);
        JPanel outPanel = new JPanel();
        jtp.addTab("Show All Products", outPanel);
        jp.add(jtp);
        
        infoLabel = new Label("");
        infoLabel.setForeground(Color.red);
        
        GridLayout gl = new GridLayout(0,2);
        JPanel p = new JPanel();
        addPanel.add(p);
        p.setSize(100, 200);
        p.setLayout(gl);
        p.add(new Label("Product Name:"));
        JTextField nameTF = new JTextField();
        p.add(nameTF);
        p.add(new Label("Category:"));
        String[] empty1 = {"-"};
        try {
            categories = ProductRepo.getCategories();
        } catch (DBException ex) {
            infoLabel.setText("DB Error");
            categories = empty1;
        }
        JComboBox cBox = new JComboBox(categories);
        p.add(cBox);        
        p.add(new Label("Weight (kg):"));
        JTextField weightTF = new JTextField();
        p.add(weightTF);
        p.add(new Label("Price ($):"));
        JTextField priceTF = new JTextField();
        p.add(priceTF);
        p.add(infoLabel); // текст
        JButton addButton = new JButton("Add");
        ActionListener apal = new AddProductActionListener();
        addButton.addActionListener(apal);
        p.add(addButton);

        String[] header = {
            "Name",
            "Category",
            "Weight (kg)",
            "Price ($)"
        };
        String[][] data;
        String[][] empty2 = {
            {"", "", "", ""},
        };
        try {
            data = ProductRepo.getAll();
        } catch (DBException ex) {
            //ошибка бд
           data = empty2;
        }
        JTable table = new JTable(data, header);
        JScrollPane scrollPane = new JScrollPane(table);
        outPanel.add(scrollPane);
    }
    
    
    public class AddProductActionListener implements ActionListener {
          public void actionPerformed(ActionEvent e) {
              try {
                  if (ProductRepo.addNew(null, null, null, null)){
                      infoLabel.setText("Success!");
                  }
              } catch (DBException ex) {
                  infoLabel.setText("DB Error");
              }
          }
     }

    public static void main(String[] args) {
        SwingApp app = new SwingApp();
        app.setVisible(true);
    }
    
}
