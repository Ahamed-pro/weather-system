package com.mycompany.weatherapp;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D; 

public class NewJFrame extends javax.swing.JFrame {

    private JTextField txtcity;
    private JButton btnsearch;
    private JLabel lblCity;
    private JLabel lblTemp;
    private JLabel lblCondition;
    private JLabel lblTempIcon;
    private JPanel cardPanel;
    private JPanel historyPanel;
    private JLabel lblHistory;
    private JLabel lblHumidity;
    private JLabel lblWind;
    private JLabel lblFeels;

    public NewJFrame() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Weather App");
        setResizable(false);

       
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(
                    0, 0, new Color(26, 26, 46),
                    getWidth(), getHeight(), new Color(15, 52, 96)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());

                g2d.setColor(new Color(255, 255, 255, 18));
                g2d.fillOval(260, -60, 200, 200);
                g2d.fillOval(-60, 380, 160, 160);
                g2d.dispose();
            }
        };
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(380, 620));

      
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(28, 24, 10, 24));

        JLabel titleLabel = new JLabel("Weather App", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new BorderLayout(8, 0));
        searchPanel.setOpaque(false);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(14, 0, 0, 0));

        txtcity = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255, 38));
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 14, 14));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        txtcity.setOpaque(false);
        txtcity.setForeground(Color.WHITE);
        txtcity.setCaretColor(Color.WHITE);
        txtcity.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtcity.setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
        txtcity.putClientProperty("JTextField.placeholderText", "Enter city or town...");
        txtcity.addActionListener(e -> performSearch());

        btnsearch = new JButton("Search") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2.setColor(new Color(60, 110, 200));
                } else if (getModel().isRollover()) {
                    g2.setColor(new Color(80, 140, 255));
                } else {
                    g2.setColor(new Color(79, 142, 247));
                }
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 14, 14));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnsearch.setOpaque(false);
        btnsearch.setContentAreaFilled(false);
        btnsearch.setBorderPainted(false);
        btnsearch.setFocusPainted(false);
        btnsearch.setForeground(Color.WHITE);
        btnsearch.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnsearch.setPreferredSize(new Dimension(85, 42));
        btnsearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnsearch.addActionListener(e -> performSearch());

        searchPanel.add(txtcity, BorderLayout.CENTER);
        searchPanel.add(btnsearch, BorderLayout.EAST);
        topPanel.add(searchPanel, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 24, 10, 24));

        cardPanel = new RoundPanel(new Color(255, 255, 255, 35), 20);
        cardPanel.setLayout(new BorderLayout(0, 6));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(18, 20, 18, 20));
        cardPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 160));

        lblCity = new JLabel("Search a city to see weather");
        lblCity.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblCity.setForeground(new Color(255, 255, 255, 160));

        JPanel tempRow = new JPanel(new BorderLayout());
        tempRow.setOpaque(false);

        JPanel tempLeft = new JPanel();
        tempLeft.setLayout(new BoxLayout(tempLeft, BoxLayout.Y_AXIS));
        tempLeft.setOpaque(false);

        lblTemp = new JLabel("--°C");
        lblTemp.setFont(new Font("Segoe UI", Font.BOLD, 48));
        lblTemp.setForeground(Color.WHITE);

        lblCondition = new JLabel("--");
        lblCondition.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblCondition.setForeground(new Color(255, 255, 255, 200));

        tempLeft.add(lblTemp);
        tempLeft.add(lblCondition);

        lblTempIcon = new JLabel("🌤", SwingConstants.RIGHT);
        lblTempIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 52));
        lblTempIcon.setVerticalAlignment(SwingConstants.BOTTOM);

        tempRow.add(tempLeft, BorderLayout.CENTER);
        tempRow.add(lblTempIcon, BorderLayout.EAST);

        cardPanel.add(lblCity, BorderLayout.NORTH);
        cardPanel.add(tempRow, BorderLayout.CENTER);

        JPanel statPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        statPanel.setOpaque(false);
        statPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        statPanel.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0));

        lblHumidity = createStatPill("Humidity", "--");
        lblWind     = createStatPill("Wind", "--");
        lblFeels    = createStatPill("Feels like", "--");

        statPanel.add(lblHumidity.getParent());
        statPanel.add(lblWind.getParent());
        statPanel.add(lblFeels.getParent());

        historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));
        historyPanel.setOpaque(false);
        historyPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));

        lblHistory = new JLabel("RECENT SEARCHES");
        lblHistory.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblHistory.setForeground(new Color(255, 255, 255, 120));
        lblHistory.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblHistory.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));

        historyPanel.add(lblHistory);

        centerPanel.add(cardPanel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(statPanel);
        centerPanel.add(historyPanel);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private JLabel createStatPill(String title, String value) {
        RoundPanel pill = new RoundPanel(new Color(255, 255, 255, 28), 14);
        pill.setLayout(new BoxLayout(pill, BoxLayout.Y_AXIS));
        pill.setBorder(BorderFactory.createEmptyBorder(12, 8, 12, 8));

        JLabel lblTitle = new JLabel(title.toUpperCase());
        lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        lblTitle.setForeground(new Color(255, 255, 255, 130));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lbl = new JLabel(value);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lbl.setForeground(Color.WHITE);
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        pill.add(lblTitle);
        pill.add(Box.createVerticalStrut(4));
        pill.add(lbl);

        return lbl;
    }

private void addHistoryRow(String city, String temp, String condition, String icon) {
    RoundPanel row = new RoundPanel(new Color(255, 255, 255, 18), 10);
    row.setLayout(new BorderLayout(10, 0)); // spacing between left & right
    row.setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));

    row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
    row.setPreferredSize(new Dimension(0, 50));
    row.setAlignmentX(Component.LEFT_ALIGNMENT);

    JLabel left = new JLabel(icon + "  " + city);
    left.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 13));
    left.setForeground(Color.WHITE);

    JLabel right = new JLabel(temp + "°C · " + condition);
    right.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    right.setForeground(new Color(255, 255, 255, 170));
    right.setHorizontalAlignment(SwingConstants.RIGHT); 

    row.add(left, BorderLayout.WEST);
    row.add(right, BorderLayout.CENTER); 

    historyPanel.add(row);
    historyPanel.add(Box.createVerticalStrut(8));

    historyPanel.revalidate();
    historyPanel.repaint();
}

    private void performSearch() {
        String city = txtcity.getText().trim();
        if (city.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a city name.", "Empty Field", JOptionPane.WARNING_MESSAGE);
            return;
        }

        btnsearch.setEnabled(false);
        btnsearch.setText("...");

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            String temp, condition;

            @Override
            protected Void doInBackground() {
                WeatherApp obj = new WeatherApp(city);
                temp = obj.getTemperature();
                condition = obj.getCondition();
                return null;
            }

            @Override
            protected void done() {
                btnsearch.setEnabled(true);
                btnsearch.setText("Search");

                if (temp == null || temp.equals("N/A")) {
                    JOptionPane.showMessageDialog(NewJFrame.this, "City not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                lblCity.setText(city);
                lblTemp.setText(temp + "°C");
                lblCondition.setText(condition);
                lblTempIcon.setText(getWeatherIcon(condition));

                lblHumidity.setText("--");
                lblWind.setText("--");
                lblFeels.setText("--");

                String icon = getWeatherIcon(condition);
                addHistoryRow(city, temp, condition, icon);

                txtcity.setText("");
                cardPanel.repaint();
            }
        };
        worker.execute();
    }

    private String getWeatherIcon(String condition) {
        if (condition == null) return "🌡";
        String c = condition.toLowerCase();
        if (c.contains("rain"))   return "🌧";
        if (c.contains("cloud"))  return "⛅";
        if (c.contains("storm"))  return "⛈";
        if (c.contains("snow"))   return "❄";
        if (c.contains("fog") || c.contains("mist")) return "🌫";
        if (c.contains("clear") || c.contains("sunny")) return "☀";
        return "🌤";
    }

    static class RoundPanel extends JPanel {
        private final Color bg;
        private final int radius;

        RoundPanel(Color bg, int radius) {
            this.bg = bg;
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bg);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));
            g2.dispose();
            super.paintComponent(g);
        }
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        java.awt.EventQueue.invokeLater(() -> new NewJFrame().setVisible(true));
    }
}
package com.mycompany.weatherapp;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class NewJFrame extends javax.swing.JFrame {

    private JTextField txtcity;
    private JButton btnsearch;
    private JLabel lblCity;
    private JLabel lblTemp;
    private JLabel lblCondition;
    private JLabel lblTempIcon;
    private JPanel cardPanel;
    private JPanel historyPanel;
    private JLabel lblHistory;
    private JLabel lblHumidity;
    private JLabel lblWind;
    private JLabel lblFeels;

    public NewJFrame() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Weather App");
        setResizable(false);

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(
                    0, 0, new Color(26, 26, 46),
                    getWidth(), getHeight(), new Color(15, 52, 96)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());

                g2d.setColor(new Color(255, 255, 255, 18));
                g2d.fillOval(260, -60, 200, 200);
                g2d.fillOval(-60, 380, 160, 160);
                g2d.dispose();
            }
        };
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(380, 620));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(28, 24, 10, 24));

        JLabel titleLabel = new JLabel("Weather App", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new BorderLayout(8, 0));
        searchPanel.setOpaque(false);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(14, 0, 0, 0));

        txtcity = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255, 38));
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 14, 14));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        txtcity.setOpaque(false);
        txtcity.setForeground(Color.WHITE);
        txtcity.setCaretColor(Color.WHITE);
        txtcity.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtcity.setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
        txtcity.putClientProperty("JTextField.placeholderText", "Enter city or town...");
        txtcity.addActionListener(e -> performSearch());

        btnsearch = new JButton("Search") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2.setColor(new Color(60, 110, 200));
                } else if (getModel().isRollover()) {
                    g2.setColor(new Color(80, 140, 255));
                } else {
                    g2.setColor(new Color(79, 142, 247));
                }
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 14, 14));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnsearch.setOpaque(false);
        btnsearch.setContentAreaFilled(false);
        btnsearch.setBorderPainted(false);
        btnsearch.setFocusPainted(false);
        btnsearch.setForeground(Color.WHITE);
        btnsearch.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnsearch.setPreferredSize(new Dimension(85, 42));
        btnsearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnsearch.addActionListener(e -> performSearch());

        searchPanel.add(txtcity, BorderLayout.CENTER);
        searchPanel.add(btnsearch, BorderLayout.EAST);
        topPanel.add(searchPanel, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 24, 10, 24));

        cardPanel = new RoundPanel(new Color(255, 255, 255, 35), 20);
        cardPanel.setLayout(new BorderLayout(0, 6));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(18, 20, 18, 20));
        cardPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 160));

        lblCity = new JLabel("Search a city to see weather");
        lblCity.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblCity.setForeground(new Color(255, 255, 255, 160));

        JPanel tempRow = new JPanel(new BorderLayout());
        tempRow.setOpaque(false);

        JPanel tempLeft = new JPanel();
        tempLeft.setLayout(new BoxLayout(tempLeft, BoxLayout.Y_AXIS));
        tempLeft.setOpaque(false);

        lblTemp = new JLabel("--°C");
        lblTemp.setFont(new Font("Segoe UI", Font.BOLD, 48));
        lblTemp.setForeground(Color.WHITE);

        lblCondition = new JLabel("--");
        lblCondition.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblCondition.setForeground(new Color(255, 255, 255, 200));

        tempLeft.add(lblTemp);
        tempLeft.add(lblCondition);

        lblTempIcon = new JLabel("🌤", SwingConstants.RIGHT);
        lblTempIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 52));
        lblTempIcon.setVerticalAlignment(SwingConstants.BOTTOM);

        tempRow.add(tempLeft, BorderLayout.CENTER);
        tempRow.add(lblTempIcon, BorderLayout.EAST);

        cardPanel.add(lblCity, BorderLayout.NORTH);
        cardPanel.add(tempRow, BorderLayout.CENTER);

        JPanel statPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        statPanel.setOpaque(false);
        statPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        statPanel.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0));

        lblHumidity = createStatPill("Humidity", "--");
        lblWind     = createStatPill("Wind", "--");
        lblFeels    = createStatPill("Feels like", "--");

        statPanel.add(lblHumidity.getParent());
        statPanel.add(lblWind.getParent());
        statPanel.add(lblFeels.getParent());

        historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));
        historyPanel.setOpaque(false);
        historyPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));

        lblHistory = new JLabel("RECENT SEARCHES");
        lblHistory.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblHistory.setForeground(new Color(255, 255, 255, 120));
        lblHistory.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblHistory.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));

        historyPanel.add(lblHistory);

        centerPanel.add(cardPanel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(statPanel);
        centerPanel.add(historyPanel);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private JLabel createStatPill(String title, String value) {
        RoundPanel pill = new RoundPanel(new Color(255, 255, 255, 28), 14);
        pill.setLayout(new BoxLayout(pill, BoxLayout.Y_AXIS));
        pill.setBorder(BorderFactory.createEmptyBorder(12, 8, 12, 8));

        JLabel lblTitle = new JLabel(title.toUpperCase());
        lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        lblTitle.setForeground(new Color(255, 255, 255, 130));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lbl = new JLabel(value);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lbl.setForeground(Color.WHITE);
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        pill.add(lblTitle);
        pill.add(Box.createVerticalStrut(4));
        pill.add(lbl);

        return lbl;
    }

private void addHistoryRow(String city, String temp, String condition, String icon) {
    RoundPanel row = new RoundPanel(new Color(255, 255, 255, 18), 10);
    row.setLayout(new BorderLayout(10, 0)); 
    row.setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));

    row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
    row.setPreferredSize(new Dimension(0, 50));
    row.setAlignmentX(Component.LEFT_ALIGNMENT);

    JLabel left = new JLabel(icon + "  " + city);
    left.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 13));
    left.setForeground(Color.WHITE);

    JLabel right = new JLabel(temp + "°C · " + condition);
    right.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    right.setForeground(new Color(255, 255, 255, 170));
    right.setHorizontalAlignment(SwingConstants.RIGHT); 

    row.add(left, BorderLayout.WEST);
    row.add(right, BorderLayout.CENTER); 

    historyPanel.add(row);
    historyPanel.add(Box.createVerticalStrut(8));

    historyPanel.revalidate();
    historyPanel.repaint();
}

    private void performSearch() {
        String city = txtcity.getText().trim();
        if (city.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a city name.", "Empty Field", JOptionPane.WARNING_MESSAGE);
            return;
        }

        btnsearch.setEnabled(false);
        btnsearch.setText("...");

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            String temp, condition;

            @Override
            protected Void doInBackground() {
                WeatherApp obj = new WeatherApp(city);
                temp = obj.getTemperature();
                condition = obj.getCondition();
                return null;
            }

            @Override
            protected void done() {
                btnsearch.setEnabled(true);
                btnsearch.setText("Search");

                if (temp == null || temp.equals("N/A")) {
                    JOptionPane.showMessageDialog(NewJFrame.this, "City not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                lblCity.setText(city);
                lblTemp.setText(temp + "°C");
                lblCondition.setText(condition);
                lblTempIcon.setText(getWeatherIcon(condition));

                lblHumidity.setText("--");
                lblWind.setText("--");
                lblFeels.setText("--");

                String icon = getWeatherIcon(condition);
                addHistoryRow(city, temp, condition, icon);

                txtcity.setText("");
                cardPanel.repaint();
            }
        };
        worker.execute();
    }

    private String getWeatherIcon(String condition) {
        if (condition == null) return "🌡";
        String c = condition.toLowerCase();
        if (c.contains("rain"))   return "🌧";
        if (c.contains("cloud"))  return "⛅";
        if (c.contains("storm"))  return "⛈";
        if (c.contains("snow"))   return "❄";
        if (c.contains("fog") || c.contains("mist")) return "🌫";
        if (c.contains("clear") || c.contains("sunny")) return "☀";
        return "🌤";
    }

    static class RoundPanel extends JPanel {
        private final Color bg;
        private final int radius;

        RoundPanel(Color bg, int radius) {
            this.bg = bg;
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bg);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));
            g2.dispose();
            super.paintComponent(g);
        }
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        java.awt.EventQueue.invokeLater(() -> new NewJFrame().setVisible(true));
    }
}
