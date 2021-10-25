import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;

public class AddProductForm extends JFrame {
    JLabel lbl_Category,lbl_Name,lbl_Quantity,lbl_Price,lbl_Description;
    JTextField txt_Name,txt_Quantity,txt_Price,txt_Description;
    JComboBox<String> comboBoxCategory;
    JButton btn_Cancel,btn_Add,btn_View;
    JTable data_Table;
    AddProductForm(){
        setTitle("Add Product Form");
        // Labels initialization
        lbl_Category=new JLabel("Category");
        lbl_Name=new JLabel("Name");
        lbl_Quantity=new JLabel("Quantity");
        lbl_Price=new JLabel("Price");
        lbl_Description=new JLabel("Description");
        // TextField initialization
        String[] category={"Mobile","Tv","LED","Computer","Refrigerator"};
        comboBoxCategory=new JComboBox<>(category);
        txt_Name=new JTextField(20);
        txt_Quantity=new JTextField(20);
        txt_Price=new JTextField(20);
        txt_Description=new JTextField(20);
        // Button initialization
        btn_Cancel=new JButton("Cancel");
        btn_Add=new JButton("Add");
        btn_View=new JButton("View");
        // Table initialization
        data_Table=new JTable();
        JScrollPane scrollPane=new JScrollPane(data_Table);
        data_Table.setPreferredScrollableViewportSize(new Dimension(300,150));

        // Layout
        GridBagLayout layOut=new GridBagLayout();
        Container pane=this.getContentPane();
        pane.setLayout(layOut);
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.fill=GridBagConstraints.BOTH;
        gbc.insets=new Insets(5,5,5,5);
        // First Row
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=1;
        pane.add(lbl_Category,gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.gridwidth=2;
        pane.add(comboBoxCategory,gbc);
        // Second Row
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=1;
        pane.add(lbl_Name,gbc);
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.gridwidth=2;
        pane.add(txt_Name,gbc);
        // Third Row
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth=1;
        pane.add(lbl_Quantity,gbc);
        gbc.gridx=1;
        gbc.gridy=2;
        gbc.gridwidth=2;
        pane.add(txt_Quantity,gbc);
        // Forth Row
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.gridwidth=1;
        pane.add(lbl_Price,gbc);
        gbc.gridx=1;
        gbc.gridy=3;
        gbc.gridwidth=2;
        pane.add(txt_Price,gbc);
        // Fifth Row
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.gridwidth=1;
        pane.add(lbl_Description,gbc);
        gbc.gridx=1;
        gbc.gridy=4;
        gbc.gridwidth=2;
        pane.add(txt_Description,gbc);
        // Sixth Row
        gbc.gridx=0;
        gbc.gridy=5;
        gbc.gridwidth=1;
        pane.add(btn_Cancel,gbc);
        gbc.gridx=1;
        gbc.gridy=5;
        gbc.gridwidth=2;
        pane.add(btn_Add,gbc);
        // Seventh Row
        gbc.gridx=0;
        gbc.gridy=6;
        gbc.gridwidth=3;
        pane.add(btn_View,gbc);

        this.pack();
        this.setVisible(true);

        btn_Cancel.addActionListener(e -> System.exit(0));

        btn_Add.addActionListener(e -> {
            if(!txt_Name.getText().equals("") && !txt_Quantity.getText().equals("") && !txt_Price.getText().equals("") && !txt_Description.getText().equals("")) {
                try {
                    // File
                    File file = new File("E:\\New folder\\Products.txt");
                    FileWriter filewriter = new FileWriter(file, true);
                    filewriter.write(comboBoxCategory.getSelectedItem() + "/" + txt_Name.getText() + "/" + txt_Quantity.getText() + "/" + txt_Price.getText() + "/" + txt_Description.getText());
                    filewriter.write("\n");
                    filewriter.close();
                } catch (Exception a) {
                    System.out.println("Error!" + a);
                }
                System.out.println("Success...");
                txt_Name.setText("");
                txt_Price.setText("");
                txt_Quantity.setText("");
                txt_Description.setText("");
            }else
                JOptionPane.showMessageDialog(null,"Filling of All Text field is essential ");
        });

        btn_View.addActionListener(e -> {
            String filePath="E:\\New folder\\Products.txt";
            File file=new File(filePath);
            try {
                // Delete all table row
                ((DefaultTableModel)data_Table.getModel()).setNumRows(0);

                BufferedReader br=new BufferedReader(new FileReader(file));
                // get the first line
                // get the columns name from the first line
                // get columns name to the J table model
                String[] column_Name={"Category","Name","Quantity","Price","Description"};
                DefaultTableModel model=(DefaultTableModel)data_Table.getModel();
                model.setColumnIdentifiers(column_Name);
                // get line from txt file
                Object[] tableLines=br.lines().toArray();
                // Extract data from lines
                // set data to J table model
                for (Object tableLine : tableLines) {
                    String line = tableLine.toString().trim();
                    String[] dataRow = line.split("/");
                    model.addRow(dataRow);
                }
                gbc.gridx = 0;
                gbc.gridy = 7;
                gbc.gridwidth = 3;
                pane.add(scrollPane, gbc);
                pack();
                setVisible(true);
            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        new AddProductForm();
    }
}
