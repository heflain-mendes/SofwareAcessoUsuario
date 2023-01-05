/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao;

import com.ufes.sofwareacessousuario.dao.interfaces.INotificationDAO;
import com.ufes.sofwareacessousuario.dao.interfaces.IUserDAO;
import com.ufes.sofwareacessousuario.dao.sqlite.NotificationSQLiteDAO;
import com.ufes.sofwareacessousuario.dao.sqlite.UserSQLiteDAO;
import com.ufes.sofwareacessousuario.logger.LogService;
import com.ufes.sofwareacessousuario.logger.SystemLog;
import com.ufes.sofwareacessousuario.model.Notification;
import com.ufes.sofwareacessousuario.model.NotificationDTO;
import com.ufes.sofwareacessousuario.model.UserRegistro;
import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.observable.EventManager;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author heflainrmendes
 */
public class UsersDAOService {
    public static final String USUARIO_ADICIONADO = "usuário adiciondo";

    private static UsersDAOService instance;
    private IUserDAO userDAO;
    private List<UserRetorno> users;
    private INotificationDAO notificacoesDAO;
    private EventManager eventManager;

    private UsersDAOService() {
        eventManager = new EventManager();
        
        try {
            userDAO = new UserSQLiteDAO();
            notificacoesDAO = new NotificationSQLiteDAO();
            users = userDAO.getUsers();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static UsersDAOService getInstance() {
        if (instance == null) {
            instance = new UsersDAOService();
        }

        return instance;
    }
    
    public void subcribe(EventListerners listerners) {
        eventManager.subscribe(listerners);
    }

    public void unsubcribe(EventListerners listerners) {
        eventManager.unsubcribe(listerners);
    }

    public long getQtdUserRegistered() {
        return users.size();
    }

    public List<UserRetorno> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public UserRetorno login(String name, String password) {
        try {
            return userDAO.login(name, password);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return null;
    }

    public void registered(String name, String password) {
        int state;
        int type;

        if (getQtdUserRegistered() > 0) {
            type = UserRetorno.USER;
            if (UsuarioLogadoService.getInstance().userLogged()
                    && UsuarioLogadoService.getInstance().getType() == UserRetorno.ADMINISTERED) {

                state = UserRetorno.AUTORIZED;

            } else {
                state = UserRetorno.UNAUTORIZED;

            }
        } else {
            state = UserRetorno.AUTORIZED;
            type = UserRetorno.ADMINISTERED;
        }

        try {
            UserRetorno u = userDAO.registered(new UserRegistro(name, password, state, type));

            if (u == null) {
                throw new Exception("O usuário "
                        + name
                        + " aparentemente foi adicionado "
                        + "mas, não foi possivel reculpera-lo do banco de dados");
            } else {
                users.add(u);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        
        eventManager.notify(USUARIO_ADICIONADO);

        LogService.getInstance().escrever(new SystemLog(
                LogService.INCLUSAO,
                name,
                LocalDateTime.now(),
                ""
        ));
    }

    public void updatePassword(String password) {

        LogService.getInstance().escrever(new SystemLog(
                LogService.ALTERACAO_SENHA,
                UsuarioLogadoService.getInstance().getNome(),
                LocalDateTime.now(),
                UsuarioLogadoService.getInstance().getNome()
        ));

        try {
            userDAO.updatePassword(UsuarioLogadoService.getInstance().getUser(), password);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public boolean nomeEmUso(String nome) {
        try {
            return userDAO.nomeEmUso(nome);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return true;
    }

    public void autorizarUsuario(UserRetorno user) {
        try {
            userDAO.autorizarUsuario(user);
            user.setState(UserRegistro.AUTORIZED);

            LogService.getInstance().escrever(new SystemLog(
                    LogService.AUTORIZACAO_USUARIO,
                    user.getName(),
                    LocalDateTime.now(),
                    UsuarioLogadoService.getInstance().getNome()
            ));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void removeUser(UserRetorno user) {
        try {
            userDAO.removeUser(user);
            users.remove(user);

            LogService.getInstance().escrever(new SystemLog(
                    LogService.EXCLUSAO,
                    user.getName(),
                    LocalDateTime.now(),
                    UsuarioLogadoService.getInstance().getNome()
            ));
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    }

    public UserRetorno getUsuario(long id) {
        for (var u : users) {
            if (u.getId() == id) {
                return u;
            }
        }

        return null;
    }

    public String getNomeUsuario(long id) {
        try {
            return userDAO.getNome(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return "";
    }
    
    public void enviarNoticacao(UserRetorno user, String assunto, String mensagem) {
        try {
            NotificationDTO n = notificacoesDAO.enviarNoticacao(new Notification(
                    UsuarioLogadoService.getInstance().getId(),
                    user.getId(),
                    assunto,
                    mensagem
            ));
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    
}
