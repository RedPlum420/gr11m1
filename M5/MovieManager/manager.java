import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main{

    public static HashMap<String, Movie> movieList = new HashMap<>();
    public static MovieManagementSystem managementSystem;

    public static void main(String[] args){
        managementSystem = new MovieManagementSystem();
        managementSystem.setVisible(true);
    }

    public static void addMovie(Movie movie){
        movieList.put(movie.title, movie);
    }

    public static String getMovieText(Movie movie){
        return movie.title + " a " + movie.genre + "movie";
    }
}

class Movie{
    String title;
    String genre;

    public Movie(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }
}

class MovieManagementSystem extends JFrame {
    private JTextField movieNameField;
    private JComboBox<String> genreComboBox;
    private JComboBox<String> sortComboBox;
    private JTextField searchField;
    private JTable movieTable;
    private DefaultTableModel tableModel;
    private JLabel numberOfMovies;

    public MovieManagementSystem() {
        setTitle("Movie Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 245));


        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;


        JPanel namePanel = createOutlinedLabelPanel("Movie Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(namePanel, gbc);

        movieNameField = new JTextField();
        movieNameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        movieNameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        inputPanel.add(movieNameField, gbc);

        JPanel genrePanel = createOutlinedLabelPanel("Genre:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        inputPanel.add(genrePanel, gbc);

        String[] genres = {"Comedy", "Action", "Sci-Fi", "Horror", "Thriller"};
        genreComboBox = new JComboBox<>(genres);
        genreComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        genreComboBox.setBackground(Color.WHITE);
        genreComboBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        inputPanel.add(genreComboBox, gbc);

        JPanel sortPanel = createOutlinedLabelPanel("Sort by:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        inputPanel.add(sortPanel, gbc);

        String[] sortOptions = {"Title", "Genre"};
        sortComboBox = new JComboBox<>(sortOptions);
        sortComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        sortComboBox.setBackground(Color.WHITE);
        sortComboBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        inputPanel.add(sortComboBox, gbc);

        JPanel actionPanel = new JPanel(new BorderLayout(10, 10));
        actionPanel.setBackground(new Color(245, 245, 245));

        searchField = new JTextField();
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        JButton addButton = new JButton("Add Movie");
        addButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        addButton.setBackground(new Color(70, 130, 180));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

        numberOfMovies = new JLabel("Count: 0") ;
        numberOfMovies.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        numberOfMovies.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        numberOfMovies.setOpaque(true);
        numberOfMovies.setBackground(Color.WHITE);

        actionPanel.add(numberOfMovies, BorderLayout.WEST);
        actionPanel.add(searchField, BorderLayout.CENTER);
        actionPanel.add(addButton, BorderLayout.EAST);

        String[] columnNames = {"Movie Name", "Genre"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        movieTable = new JTable(tableModel);
        movieTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        movieTable.setRowHeight(30);
        movieTable.setGridColor(new Color(230, 230, 230));
        movieTable.setShowGrid(true);
        movieTable.setIntercellSpacing(new Dimension(0, 1));
        movieTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        movieTable.getTableHeader().setBackground(new Color(70, 130, 180));
        movieTable.getTableHeader().setForeground(Color.WHITE);
        movieTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(movieTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBackground(new Color(245, 245, 245));
        topPanel.add(inputPanel, BorderLayout.NORTH);
        topPanel.add(actionPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!movieNameField.getText().isEmpty())
                    Main.addMovie(new Movie(movieNameField.getText(), genreComboBox.getSelectedItem().toString()));
                sortTable();
                movieNameField.setText("");
            }
        });

        sortComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortTable();
            }
        });

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                sortTable();
            }
            public void removeUpdate(DocumentEvent e) {
                sortTable();
            }
            public void changedUpdate(DocumentEvent e) {
                sortTable();
            }
        });

        add(mainPanel);
    }



    private void sortTable() {
        tableModel.setRowCount(0);
        if(searchField.getText().isEmpty()){
            for(String m : Main.movieList.keySet()){
                tableModel.addRow(new Object[]{Main.movieList.get(m).title, Main.movieList.get(m).genre});
            }
        }else{
            String searchedText = searchField.getText().toLowerCase().trim();

            for(String m : Main.movieList.keySet()){
                if(Main.movieList.get(m).title.contains(searchedText))
                tableModel.addRow(new Object[]{Main.movieList.get(m).title, Main.movieList.get(m).genre});
            }
        }

        numberOfMovies.setText("Count: " + Main.movieList.size());

        String sortBy = (String) sortComboBox.getSelectedItem();
        int columnIndex = sortBy.equals("Title") ? 0 : 1;

        tableModel.getDataVector().sort((o1, o2) -> {
            String s1 = (String) o1.get(columnIndex);
            String s2 = (String) o2.get(columnIndex);
            return s1.compareToIgnoreCase(s2);
        });

        tableModel.fireTableDataChanged();
    }

    private JPanel createOutlinedLabelPanel(String text) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 245, 245));

        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        label.setOpaque(true);
        label.setBackground(Color.WHITE);

        panel.add(label, BorderLayout.CENTER);
        return panel;
    }
}
