package se1_prog_lab.client.gui;

import se1_prog_lab.client.ClientCore;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.util.Locale.*;

public class LoginFrame extends JFrame implements LangChangeSubscriber {
    private final ClientCore clientCore;
    private ResourceBundle r;
    private final JLabel title;
    private final JButton login;
    private final JButton register;

    public LoginFrame(ClientCore clientCore) throws HeadlessException {
        super();
        r = ResourceBundle.getBundle("localization/gui", clientCore.getLocale());
        setTitle(r.getString("LoginFrame.title"));
        this.clientCore = clientCore;
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));
        panel.setLayout(new GridLayout(0, 1));
        title = new JLabel(r.getString("LoginFrame.login_or_register"));
        panel.add(title);

        JTextField username = new JTextField("");
        panel.add(username);

        JPasswordField password = new JPasswordField("");
        panel.add(password);

        login = new JButton(r.getString("LoginFrame.login"));
        panel.add(login);
        login.addActionListener(event -> clientCore.login(username.getText(), Arrays.toString(password.getPassword())));

        register = new JButton(r.getString("LoginFrame.register"));
        panel.add(register);
        register.addActionListener(event -> clientCore.register(username.getText(), Arrays.toString(password.getPassword())));

        JComboBox<String> languages =
                new JComboBox<>(new String[]{"Русский", "Slovenščina", "Polski", "Español (Ecuador)"});

        languages.addActionListener(e -> {
            String item = Objects.requireNonNull(languages.getSelectedItem()).toString();
            switch (item) {
                case "Русский":
                    clientCore.setLocale(forLanguageTag("ru-RU"));
                    break;
                case "Slovenščina":
                    clientCore.setLocale(forLanguageTag("sl-SL"));
                    break;
                case "Polski":
                    clientCore.setLocale(forLanguageTag("pl-PL"));
                    break;
                case "Español (Ecuador)":
                    clientCore.setLocale(forLanguageTag("es-EC"));
                    break;
            }
        });

        panel.add(languages);
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void changeLang() {
        Locale newLocale = clientCore.getLocale();
        r = ResourceBundle.getBundle("localization/gui", newLocale);
        setTitle(r.getString("LoginFrame.title"));
        title.setText(r.getString("LoginFrame.login_or_register"));
        login.setText(r.getString("LoginFrame.login"));
        register.setText(r.getString("LoginFrame.register"));
    }
}
