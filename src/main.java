

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.* ;
import java.io.*;
import java.nio.charset.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class main {
    public static void main(String[] args) {

        System.out.println("What number line are you looking for?");
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));


        do {
            try {


                String userString = userInput.readLine();
                Socket connection = new Socket("127.0.0.1", 1236);
                OutputStream output = connection.getOutputStream();

                JFrame.setDefaultLookAndFeelDecorated(true);
                MyFrame frame = new MyFrame();
                frame.setSize(750,280);
                frame.setVisible(true);

                frame.WordToSearch.setText(userString);






                output.write(userString.length());
                output.write(userString.getBytes());

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String response = reader.readLine();


                while(response != null)
                {

                    System.out.println(response);
                    response = reader.readLine();


                }



                if (!connection.isClosed()) {
                    connection.close();
                }
                System.out.println("\nWhat number line are you looking for?");


            } catch (IOException e) {
                throw new RuntimeException(e);

            }
        } while (!userInput.equals("shutdown"));


    }
   static public String GetServerResponse(InputStream input) throws IOException {

        int n = input.read();
        byte[] data = new byte[n];
        input.read(data);
        return new String(data,StandardCharsets.UTF_8);
    }
    public static class MyFrame extends JFrame {

        public JTextField WordToSearch;
        public JTextField ResponseText;
        public JList<String> listModel;
        public JComboBox Box;
        public MyFrame() {
            super();
            init();
        }
        private void init() {
            JButton calculate = new JButton("Calculate");
            calculate.addActionListener(new MyButtonListener(this));


            WordToSearch = new JTextField("0");
            ResponseText = new JTextField("0");
            listModel = new JList<String>();

            String [] list =new String[]{"Add","Subtract","Multiply","Divide"};
            Box = new JComboBox(list);


            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(new GridLayout(5, 2));
            this.add(new JLabel("Word to search For:"));
            this.add(WordToSearch);
            this.add(new JLabel("Response:"));
            this.add(listModel);
            this.add(listModel);

            //this.add(ResponseText);
            this.add(new JLabel(""));

            this.add(new JLabel(""));
            this.add(calculate);

            this.pack();
            this.setVisible(true);
        }
    }
    static class MyButtonListener implements ActionListener {
        MyFrame fr;


        public MyButtonListener(MyFrame frame) {
            fr = frame;
        }

        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();





        }
    }

}
